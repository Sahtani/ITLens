package com.youcode.itlens.common.annotations.declarations;


import com.youcode.itlens.common.annotations.implementations.ExistValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ExistValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Exist {
    String message() default "L'entité existe déjà";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

