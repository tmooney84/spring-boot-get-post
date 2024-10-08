package dev.group.demo;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    List<User> findAll(){
        return userRepository.findAll();
    }

    User save(User user){
        return userRepository.save(user);

    }

}
