package ch.supsi.webapp.web.controller;

import ch.supsi.webapp.web.model.*;
import ch.supsi.webapp.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value="/login")
    public String login() {
        return "login";
    }

    @GetMapping(value="/register")
    public String getRegister() {
        return "register";
    }

    @PostMapping(value="/register")
    public String postNewUser(@RequestParam("surname") String surname,
                                @RequestParam("name") String name,
                                @RequestParam("username") String username,
                                @RequestParam("password") String password) {

        User user = new User(name, surname, username, password);
        user.setRole(Role.ROLE_USER);

        this.userService.post(user);

        return "redirect:/login";
    }
}
