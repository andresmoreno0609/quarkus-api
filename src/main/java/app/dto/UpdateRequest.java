package app.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "CustomerUpdateRequest", description = "Campos editables del cliente")
public record UpdateRequest(
        @Schema(example = "Andrés R. Moreno") String fullName,
        @Schema(example = "andres.m@example.com") String email
) {}
