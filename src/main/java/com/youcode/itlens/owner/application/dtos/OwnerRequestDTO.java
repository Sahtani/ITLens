package com.youcode.itlens.owner.application.dtos;

import jakarta.validation.constraints.NotBlank;

public record OwnerRequestDTO(@NotBlank String name) {
}
