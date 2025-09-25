package application.usecase;

import domain.model.Customer;
import domain.ports.CustomerRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class GetCustomerById {
    private final CustomerRepository repo;
    public GetCustomerById(CustomerRepository repo){ this.repo = repo; }
    public Optional<Customer> handle(String id){ return repo.findById(id); }
}
