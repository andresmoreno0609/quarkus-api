package infrastructure.memory;

import domain.model.Customer;
import domain.ports.CustomerRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class InMemoryCustomerRepository implements CustomerRepository {

    private final Map<String, Customer> store = new ConcurrentHashMap<>();

    @Override
    public Customer save(Customer c) {
        store.put(c.getId(), c);
        return c;
    }

    @Override
    public Optional<Customer> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public boolean deleteById(String id) {
        return store.remove(id) != null;
    }
}
