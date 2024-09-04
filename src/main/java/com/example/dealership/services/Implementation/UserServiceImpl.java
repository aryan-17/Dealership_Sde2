package com.example.dealership.services.Implementation;

import com.example.dealership.Repository.Impl.PropertiesRepoImpl;
import com.example.dealership.Repository.PropertiesRepo;
import com.example.dealership.Repository.UserRepo;
import com.example.dealership.models.Property;
import com.example.dealership.models.User;
import com.example.dealership.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;
    private PropertiesRepo propertiesRepo;

    UserServiceImpl(UserRepo userRepo, PropertiesRepo propertiesRepo) {
        this.userRepo = userRepo;
        this.propertiesRepo = propertiesRepo;
    }


    @Override
    public List<Property> getListings(String username) {
        User user = userRepo.findUserByName(username);
        return propertiesRepo.findPropertiesByOwner(user);
    }

    @Override
    public boolean saveNewUser(User user) {
        if (userRepo.findUserByName(user.getName()) != null) {
            log.error("User with username {} already present", user.getName());
            return false;
        }

        if (userRepo.findUserByEmail(user.getEmail()) != null) {
            log.error("User with email {} already present", user.getEmail());
            return false;
        }
        userRepo.saveUser(user);
        return true;
    }


}
