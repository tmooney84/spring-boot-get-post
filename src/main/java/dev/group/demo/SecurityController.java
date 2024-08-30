package dev.group.demo;

import org.apache.catalina.Authenticator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;


@Controller
public class SecurityController {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;



    public SecurityController(UserService userService, BCryptPasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/register")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "registerUser";
    }

    @PostMapping("/register")
    public String addUser(@ModelAttribute("user") User user) {
        //Save user to db
        String encrypted = passwordEncoder.encode(user.getPassword());
        User temp = user;
        temp.setPassword(encrypted);
        userService.save(temp);
        Optional<User> dbUser = userService.findByUsername(temp.getUsername());
        Authentication token = authenticationManager.authenticate(dbUser);
        SecurityContextHolder.getContext().setAuthentication(token);

//         Auto-login the user



        return "redirect::login";

    }

    @GetMapping("/error")
    public String error(){
        return "login-error";
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