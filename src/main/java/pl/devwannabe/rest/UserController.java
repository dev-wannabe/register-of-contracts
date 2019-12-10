package pl.devwannabe.rest;

import lombok.NonNull;
import org.apache.commons.lang3.Validate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.devwannabe.domain.user.SecurityService;
import pl.devwannabe.domain.user.User;
import pl.devwannabe.domain.user.UserService;
import pl.devwannabe.service.contract.ContractServiceImpl;
import pl.devwannabe.service.validation.user_validation.UserValidator;

@Controller
public class UserController {

    @NonNull
    private UserService userService;
    @NonNull
    private SecurityService securityService;
    @NonNull
    private UserValidator userValidator;

    public UserController(@NonNull UserService userService, @NonNull SecurityService securityService,
                          @NonNull UserValidator userValidator) {
        Validate.notNull(userService);
        Validate.notNull(securityService);
        Validate.notNull(userValidator);
        this.userService = userService;
        this.securityService = securityService;
        this.userValidator = userValidator;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        ContractServiceImpl.printBlue(userForm);

        if (bindingResult.hasErrors()) {

            bindingResult.getAllErrors().
                    forEach(error -> System.out.println(
                            error.getObjectName() + " " +
                                    error.getCode() + " " +
                                    error.getDefaultMessage()));
            return "registration";
        }
        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Wrong user or password.");
        }
        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully.");
        }

        return "login";
    }

    @GetMapping(value={"/","/welcome"})
    public String welcome(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUsername = auth.getName();
        model.addAttribute("hello", userService.generateUserGreeting('#', "Hello " + loggedInUsername + " !!!"));
        return "welcome";
    }
}