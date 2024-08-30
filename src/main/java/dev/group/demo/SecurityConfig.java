package dev.group.demo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    private JpaDetailsService testJpaDetailsService;

    SecurityConfig(JpaDetailsService jpaDetailsService){
        this.testJpaDetailsService = jpaDetailsService;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> {
                    auth.anyRequest().authenticated();
                })
                .userDetailsService(testJpaDetailsService)
                .formLogin(withDefaults()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")  // Specify the logout URL (default is /logout)
                        .logoutSuccessUrl("/login?logout") // Redirect to the login page with a logout parameter
                        .invalidateHttpSession(true) // Invalidate the session
                        .deleteCookies("JSESSIONID") // Delete cookies
                )

                .build();
                //this our code that fixes our updated login...
    }

    @Bean
    public static BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}

//    @Bean
//    public static PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }