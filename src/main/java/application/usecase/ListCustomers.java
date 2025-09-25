package application.usecase;

import domain.model.Customer;
import domain.ports.CustomerRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class ListCustomers {
    private final CustomerRepository repo;
    public ListCustomers(CustomerRepository repo){ this.repo = repo; }
    public List<Customer> handle(){ return repo.findAll(); }
}
