package domain.ports;

import domain.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    /**
     * Crea o actualiza un Customer.
     * @param customer entidad a persistir
     * @return entidad persistida (puede incluir cambios como timestamps)
     */
    Customer save(Customer customer);

    /**
     * Busca un Customer por su id.
     */
    Optional<Customer> findById(String id);

    /**
     * Lista todos los Customers.
     */
    List<Customer> findAll();

    /**
     * Elimina un Customer por su id.
     * @return true si existía y fue eliminado, false si no existía
     */
    boolean deleteById(String id);
}
