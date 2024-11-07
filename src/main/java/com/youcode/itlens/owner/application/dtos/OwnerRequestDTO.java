package com.youcode.itlens.owner.application.dtos;

import com.youcode.itlens.common.annotations.declarations.Unique;
import com.youcode.itlens.owner.domain.Owner;
import jakarta.validation.constraints.NotBlank;

public record OwnerRequestDTO(
        @NotBlank
        @Unique(fieldName = "name", entityClass = Owner.class, message = "Owner name already exists")
        String name
) {}
