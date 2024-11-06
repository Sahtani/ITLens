package com.youcode.itlens.common.annotations.implementations;

import com.youcode.itlens.common.annotations.declarations.Existe;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExistValidator implements ConstraintValidator<Existe, Long> {

    private final EntityManager entityManager;
    private Class<?> entityClass;
    private String fieldName;

    @Override
    public void initialize(Existe constraintAnnotation) {
        // Initialisation avec l'annotation
        this.entityClass = constraintAnnotation.entityClass();
        this.fieldName = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        try {

            String queryStr = String.format("SELECT e FROM %s e WHERE e.%s = :value",
                    entityClass.getSimpleName(), fieldName);
            entityManager.createQuery(queryStr)
                    .setParameter("value", value)
                    .getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }
}
