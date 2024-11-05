package com.youcode.itlens.common.annotations.implementations;

import com.youcode.itlens.common.annotations.declarations.Exist;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.annotation.Annotation;

public class ExistValidator implements ConstraintValidator<Exist, Object> {

    @Override
    public void initialize(Exist constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {

        return false;
    }
}
