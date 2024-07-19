package com.theaaronrussell.soci.validator;

import com.theaaronrussell.soci.dto.NewUserFormDto;
import com.theaaronrussell.soci.service.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class UsernameExistsValidator implements ConstraintValidator<UsernameExists, NewUserFormDto> {

    private static final Logger log = LoggerFactory.getLogger(UsernameExistsValidator.class);
    private final UserService userService;

    @Autowired
    public UsernameExistsValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(NewUserFormDto value, ConstraintValidatorContext context) {
        log.trace("Checking if username {} is already taken", value.getUsername());
        boolean doesUserExist = userService.doesUserExists(value.getUsername());
        log.debug("Does username already exist in database: {}", doesUserExist);
        if (doesUserExist) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode("username")
                    .addConstraintViolation();
        }
        return !doesUserExist;
    }

}
