package com.example.starecardgame.controllers;

import com.example.starecardgame.daos.UsersRepository;
import com.example.starecardgame.models.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private UsersRepository usersDao;
    private PasswordEncoder passwordEncoder;

    public UserController(UsersRepository usersDao) {
        this.usersDao = usersDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String showHomePage(Model model) {

        model.addAttribute("title", "Stare Card Game Home");
        return "home";
    }

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("title", "Signup");
        model.addAttribute("user", new User());
        return "users/signupLogin";
    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        usersDao.save(user);
        return "redirect:/login";
    }
}
