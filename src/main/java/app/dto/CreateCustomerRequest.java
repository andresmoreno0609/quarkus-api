package app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "Datos para crear un cliente")
public record CreateCustomerRequest(
        @NotBlank @Schema(example = "Andrés Moreno") String fullName,
        @NotBlank @Email @Schema(example = "andres@example.com") String email
) {}
