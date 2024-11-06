package com.youcode.itlens.common.annotations.declarations;

import com.youcode.itlens.common.annotations.implementations.ExistValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ExistValidator.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Existe {

    String message() default "L'entité n'existe pas";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    Class<?> entityClass();
    String fieldName();
}
