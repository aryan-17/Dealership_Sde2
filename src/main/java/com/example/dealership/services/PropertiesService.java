package com.example.dealership.services;

import com.example.dealership.Repository.PropertiesRepo;
import com.example.dealership.Repository.UserRepo;
import com.example.dealership.enums.ListingType;
import com.example.dealership.enums.Units;
import com.example.dealership.models.Property;
import com.example.dealership.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class PropertiesService {

    @Autowired
    private PropertiesRepo propertiesRepo;

    @Autowired
    private UserRepo userRepo;

    public Boolean saveNewProperty(Property property, String username, Units unit) {
        User user = userRepo.findByName(username);

        if (property.getDeposit() != null && property.getListing() == ListingType.SELL) {
            log.error("No deposit required if selling a property");
            return false;
        }

        if (user != null) {
            Integer size = property.getSize();
            if(unit == Units.FEET) size = size*2;
            if(unit == Units.YARD) size = size*3;
            property.setSize(size);
            property.setOwner(user);
            propertiesRepo.save(property);
            return true;
        }
        log.error("User with name {} not found.", username);
        return false;
    }

    public Boolean markedSold(Integer propId, Integer userId) {

        log.error(String.valueOf(propId));

        Optional<User> user = userRepo.findById(userId);
        Optional<Property> property = propertiesRepo.findById(propId);

        if (user.isEmpty()) {
            log.error("Invalid userId");
            return false;
        }

        if (property.isEmpty()) {
            log.error("Invalid Property Id");
            return false;
        }

        Property sold = propertiesRepo.findByOwnerAndId(user.get(), propId);
        sold.setAvailable(false);
        propertiesRepo.save(sold);
        return true;

    }

}
