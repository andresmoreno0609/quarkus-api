package application.usecase;

import domain.model.Customer;
import domain.ports.CustomerRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.Instant;
import java.util.Optional;

@ApplicationScoped
public class UpdateCustomer {
    private final CustomerRepository repo;
    public UpdateCustomer(CustomerRepository repo){ this.repo = repo; }

    public Optional<Customer> handle(String id, String fullName, String email){
        return repo.findById(id).map(existing -> {
            if (fullName != null && !fullName.isBlank()) existing.setFullName(fullName);
            if (email != null && !email.isBlank())       existing.setEmail(email);
            existing.setUpdatedAt(Instant.now());
            return repo.save(existing);
        });
    }
}
