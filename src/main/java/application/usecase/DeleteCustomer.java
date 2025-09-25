package application.usecase;

import domain.ports.CustomerRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DeleteCustomer {
    private final CustomerRepository repo;
    public DeleteCustomer(CustomerRepository repo){ this.repo = repo; }
    public boolean handle(String id){ return repo.deleteById(id); }
}
