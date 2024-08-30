package dev.group.demo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;

@Controller
public class SecurityController {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    public SecurityController(UserService userService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "registerUser";
    }

    @PostMapping("/register")
    public String addUser(@ModelAttribute("user") User user) {
//        String encrypted = passwordEncoder.encode(user.getPassword());
//        user.setPassword(encrypted);
//        userService.save(user);
        return "redirect:users";
    }


    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user) {
        return "redirect:users";
    }



}


//1) post to /login


//2)this is who I say I am (new User("user", encoder.encode("password")

//3) if you are who you say you then if we encode it and it shows up on the database
// hibernate is doing a select statement select * from user where username=?
//use SecurityUser.getPassword SecurityUser.getPassword