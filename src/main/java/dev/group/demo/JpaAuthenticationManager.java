package dev.group.demo;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JpaAuthenticationManager implements AuthenticationManager {

    private UserService userService;
    public JpaAuthenticationManager(UserService userService){
        this.userService = userService;
    }

    //signature
    @Override
    public Authentication authenticate(Optional<User> user) throws AuthenticationException {
        return new UsernamePasswordAuthenticationToken(user.stream(), user.getPassword());
    }
}
