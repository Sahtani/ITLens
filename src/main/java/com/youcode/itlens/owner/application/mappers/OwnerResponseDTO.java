package com.youcode.itlens.owner.application.mappers;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OwnerResponseDTO(@NotNull Long id, @NotBlank String name) {
}
