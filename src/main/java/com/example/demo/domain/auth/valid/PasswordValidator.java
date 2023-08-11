package com.example.demo.domain.auth.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<MinLength, String> {

    private int minLength;

    @Override
    public void initialize(MinLength constraintAnnotation) {
        this.minLength = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.length() >= minLength;
    }
}