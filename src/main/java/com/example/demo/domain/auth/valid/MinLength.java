package com.example.demo.domain.auth.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MinLength {
    String message() default "Password must be at least {value} characters long";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    int value() default 8;
}
