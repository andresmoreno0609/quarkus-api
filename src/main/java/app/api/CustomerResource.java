package app.api;

import app.dto.CreateCustomerRequest;
import app.dto.CustomerResponse;
import app.dto.UpdateRequest;
import application.usecase.*;
import domain.model.Customer;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Path("/customers")
@Tag(name = "Customers", description = "CRUD de clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {

    private final CreateCustomer createUC;
    private final ListCustomers listUC;
    private final GetCustomerById getUC;
    private final UpdateCustomer updateUC;
    private final DeleteCustomer deleteUC;

    public CustomerResource(CreateCustomer createUC, ListCustomers listUC, GetCustomerById getUC, UpdateCustomer updateUC, DeleteCustomer deleteUC) {
        this.createUC = createUC;
        this.listUC = listUC;
        this.getUC = getUC;
        this.updateUC = updateUC;
        this.deleteUC = deleteUC;
    }

    private static CustomerResponse toResponse(Customer c){
        var fmt = DateTimeFormatter.ISO_INSTANT;
        return new CustomerResponse(
                c.getId(), c.getFullName(), c.getEmail(),
                c.getCreatedAt() != null ? fmt.format(c.getCreatedAt()) : null,
                c.getUpdatedAt() != null ? fmt.format(c.getUpdatedAt()) : null
        );
    }

    @POST
    @Operation(summary = "Crear un nuevo cliente")
    @APIResponse(responseCode = "200", description = "Cliente creado")
    @APIResponse(responseCode = "400", description = "Solicitud inválida")
    public CustomerResponse create(CreateCustomerRequest req) {
        var c = createUC.handle(req.fullName(), req.email());
        return toResponse(c);
    }

    @GET
    @Operation(summary = "Listar clientes")
    @APIResponse(responseCode = "200", description = "OK")
    public List<CustomerResponse> list(){
        return listUC.handle().stream().map(CustomerResource::toResponse).toList();
    }

    @GET @Path("{id}")
    @Operation(summary = "Obtener cliente por id")
    @APIResponse(responseCode = "200", description = "Encontrado")
    @APIResponse(responseCode = "404", description = "No existe")
    public CustomerResponse get(@PathParam("id") String id){
        return getUC.handle(id).map(CustomerResource::toResponse)
                .orElseThrow(() -> new NotFoundException("Customer not found"));
    }

    @PUT @Path("{id}")
    @Operation(summary = "Actualizar un cliente")
    @APIResponse(responseCode = "200", description = "Actualizado")
    @APIResponse(responseCode = "404", description = "No existe")
    public CustomerResponse update(@PathParam("id") String id, UpdateRequest req){
        return updateUC.handle(id, req.fullName(), req.email())
                .map(CustomerResource::toResponse)
                .orElseThrow(() -> new NotFoundException("Customer not found"));
    }

    @DELETE @Path("{id}")
    @Operation(summary = "Eliminar cliente")
    @APIResponse(responseCode = "204", description = "Eliminado")
    @APIResponse(responseCode = "404", description = "No existe")
    public jakarta.ws.rs.core.Response delete(@PathParam("id") String id){
        boolean removed = deleteUC.handle(id);
        if (!removed) throw new NotFoundException("Customer not found");
        return jakarta.ws.rs.core.Response.status(Response.Status.NO_CONTENT).build();
    }

}
