package application.usecase;

import domain.model.Customer;
import domain.ports.CustomerRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.Instant;
import java.util.UUID;

@ApplicationScoped
public class CreateCustomer {

    private final CustomerRepository repo;

    @Inject
    public CreateCustomer(CustomerRepository repo) {
        this.repo = repo;
    }

    /**
     * Crea un nuevo Customer y lo persiste mediante el puerto de repositorio.
     */
    public Customer handle(String fullName, String email) {
        var now = Instant.now();

        domain.model.Customer c = new Customer();
        c.setId(UUID.randomUUID().toString());
        c.setFullName(fullName);
        c.setEmail(email);
        c.setCreatedAt(now);
        c.setUpdatedAt(now);

        return repo.save(c);
    }
}