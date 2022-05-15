package com.example.wpproject.web.controller;

import com.example.wpproject.model.enumerations.Role;
import com.example.wpproject.model.exceptions.InvalidArgumentsException;
import com.example.wpproject.model.exceptions.PasswordsDoNotMatchException;
import com.example.wpproject.service.AuthService;
import com.example.wpproject.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final AuthService authService;
    private final UserService userService;

    public RegisterController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }


    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model) {
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("bodyContent","register");
        return "master-template";
    }

    @PostMapping
    public String register(@RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam String email,
                           @RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatedPassword,
                           @RequestParam Role role){
        try{
            this.userService.register(name, surname, email, username, password, repeatedPassword, role);
            return "redirect:/login";
        } catch (InvalidArgumentsException | PasswordsDoNotMatchException exception) {
            return "redirect:/register?error=" + exception.getMessage();
        }
    }
}
