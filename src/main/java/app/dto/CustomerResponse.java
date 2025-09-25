package app.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "Representación pública del cliente")
public record CustomerResponse(
        @Schema(example = "b1f3a1b3-1a2b-4d5e-9f11-223344556677") String id,
        @Schema(example = "Andrés Moreno") String fullName,
        @Schema(example = "andres@example.com") String email,
        @Schema(example = "2025-09-25T19:15:01Z") String createdAt,
        @Schema(example = "2025-09-25T19:15:01Z") String updatedAt
) {}
