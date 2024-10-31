package com.youcode.itlens.owner.application.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OwnerResponseDTO(@NotNull Long id, @NotBlank String name) {
}
