package dev.group.demo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserController(UserService userService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("")
    public String users(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @GetMapping("/register")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "registerUser";
    }

    //1. need a get request to our login
   //2.

    @GetMapping ("login")




    @PostMapping("")
    public String addUser(@ModelAttribute("user") User user) {
        String encrypted = passwordEncoder.encode(user.getPassword());
        user.setPassword(encrypted);
        userService.save(user);
        return "redirect:/";
    }

    @GetMapping("/private")
    public String myprivatepage(){
        return "private";
    }

}


//1) post to /login


//2)this is who I say I am (new User("user", encoder.encode("password")

//3) if you are who you say you then if we encode it and it shows up on the database
// hibernate is doing a select statement select * from user where username=?
//use SecurityUser.getPassword SecurityUser.getPassword