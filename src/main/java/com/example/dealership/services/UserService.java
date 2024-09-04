package com.example.dealership.services;

import com.example.dealership.models.Property;
import com.example.dealership.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService{

    List<Property> getListings(String username);


    boolean saveNewUser(User user);

}
