package pl.devwannabe.validation.user_validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pl.devwannabe.domain.User;
import pl.devwannabe.service.security.UserService;

@Component
public class UserValidator implements Validator {


    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username",
                "NotEmpty", "Field 'Username' can not be empty.");
        if (user.getUsername().length() < 6 || user.getUsername().length() > 32) {
            errors.rejectValue("username",
                    "Size.userForm.username","Please use between 6 and 32 characters.");
        }
        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username",
                    "Duplicate.userForm.username", "Someone already has that username.");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
                "NotEmpty", "Field 'Password' can not be empty.");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password",
                    "Size.userForm.password", "Try one with at least 8 characters");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm",
                    "Diff.userForm.passwordConfirm", "These passwords don't match.");
        }
    }

}