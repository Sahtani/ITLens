package com.youcode.itlens.common.annotations.implementations;

import com.youcode.itlens.common.annotations.declarations.Unique;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
@RequiredArgsConstructor
@Component
public class UniqueValidator implements ConstraintValidator<Unique, String> {

    private final EntityManager entityManager;

    private Class<?> entityClass;
    private String fieldName;

    @Override
    public void initialize(Unique constraintAnnotation) {
        this.entityClass = constraintAnnotation.entityClass();
        this.fieldName = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        System.out.println(value);
        if (value == null || value.isEmpty()) {
            return true;
        }

        String queryStr = String.format("SELECT COUNT(e) FROM %s e WHERE e.%s = :value",
                entityClass.getSimpleName(), fieldName);
        Long count = entityManager.createQuery(queryStr, Long.class)
                .setParameter("value", value)
                .getSingleResult();

        return count == 0;
    }
}
