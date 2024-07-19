package com.theaaronrussell.soci.validator;

import com.theaaronrussell.soci.dto.NewUserFormDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PasswordsMatchValidator implements ConstraintValidator<PasswordsMatch, NewUserFormDto> {

    private static final Logger log = LoggerFactory.getLogger(PasswordsMatchValidator.class);

    @Override
    public boolean isValid(NewUserFormDto value, ConstraintValidatorContext context) {
        log.trace("Checking if both passwords match");
        boolean doPasswordsMatch = value.getPassword().equals(value.getConfirmPassword());
        log.debug("Do passwords match: {}", doPasswordsMatch);
        if (!doPasswordsMatch) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode("password")
                    .addConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode("confirmPassword")
                    .addConstraintViolation();
        }
        return doPasswordsMatch;
    }

}
