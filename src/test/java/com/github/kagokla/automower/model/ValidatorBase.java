package com.github.kagokla.automower.model;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class ValidatorBase<T> {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeAll
    static void setUp() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterAll
    static void tearDown() {
        validatorFactory.close();
    }

    protected void isValid(final T validLawn) {
        var violations = validator.validate(validLawn);
        assertThat(violations).isEmpty();
    }

    protected void isInvalid(final T invalidLawn, final int numberOfViolations) {
        var violations = validator.validate(invalidLawn);
        assertThat(violations).isNotEmpty();
        assertThat(violations).hasSize(numberOfViolations);
    }
}
