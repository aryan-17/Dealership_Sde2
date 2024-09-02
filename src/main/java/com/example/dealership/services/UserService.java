package com.example.dealership.services;

import com.example.dealership.Repository.PropertiesRepo;
import com.example.dealership.Repository.UserRepo;
import com.example.dealership.models.Property;
import com.example.dealership.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PropertiesRepo propertiesRepo;

    public Boolean saveNewUser(User user){

        if(userRepo.findByName(user.getName()) != null) {
            log.error("User with username {} already present", user.getName());
            return false;
        }

        if(userRepo.findByEmail(user.getEmail()) != null) {
            log.error("User with email {} already present", user.getEmail());
            return false;
        }
        userRepo.save(user);
        return true;
    }

    public List<Property> getListings(String username){
        User user = userRepo.findByName(username);
        return propertiesRepo.findByOwner(user);
    }
}
