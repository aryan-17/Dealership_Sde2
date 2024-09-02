package com.example.dealership.services;

import com.example.dealership.Repository.PropertiesRepo;
import com.example.dealership.Repository.UserRepo;
import com.example.dealership.models.Property;
import com.example.dealership.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PropertiesService {

    @Autowired
    private PropertiesRepo propertiesRepo;

    @Autowired
    private UserRepo userRepo;

    public Boolean saveNewProperty(Property property, String username){
        User user = userRepo.findByName(username);
        if(user!=null){
            property.setOwner(user);
            propertiesRepo.save(property);
            return true;
        }
        log.error("User with name {} not found.", username);
        return false;
    }


}
