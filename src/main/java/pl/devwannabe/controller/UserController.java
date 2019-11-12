package pl.devwannabe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.devwannabe.domain.User;
import pl.devwannabe.service.ContractServiceImpl;
import pl.devwannabe.service.security.SecurityService;
import pl.devwannabe.service.security.UserService;
import pl.devwannabe.utils.WelcomeAsciiArt;
import pl.devwannabe.validation.user_validation.UserValidator;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

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
        WelcomeAsciiArt asciiArt = new WelcomeAsciiArt();
        model.addAttribute("hello", asciiArt.makeAsciiArt('#', "Hello " + loggedInUsername + " !!!"));
        return "welcome";
    }
}