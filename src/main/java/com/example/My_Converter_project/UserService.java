package com.example.My_Converter_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// provides logic for user operations, interacts with "UserRepository"
@Service
public class UserService {

    private UserRepository userRepository;
    //dependency constructor of UserRepository
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public User authenticate(String username, String password){
        return userRepository.findByUsernameAndPassword(username,password);
    }
    public boolean isUserExist(String username, String email){
        return userRepository.existsByUsernameOrEmail(username,email);
    };
    public  void  saveUser(User user){
        userRepository.save(user);
    }

}
