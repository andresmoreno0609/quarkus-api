package domain.model;

import java.time.Instant;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
@Builder
public class Customer {
    private String id;
    private String fullName;
    private String email;
    private Instant createdAt;
    private Instant updatedAt;
}
