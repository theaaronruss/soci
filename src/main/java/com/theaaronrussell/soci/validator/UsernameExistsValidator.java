package com.theaaronrussell.soci.validator;

import com.theaaronrussell.soci.dto.NewUserFormDto;
import com.theaaronrussell.soci.service.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UsernameExistsValidator implements ConstraintValidator<UsernameExists, NewUserFormDto> {

    private final UserService userService;

    @Autowired
    public UsernameExistsValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(NewUserFormDto value, ConstraintValidatorContext context) {
        boolean doesUserExist = userService.doesUserExists(value.getUsername());
        if (doesUserExist) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode("username")
                    .addConstraintViolation();
        }
        return !doesUserExist;
    }

}
