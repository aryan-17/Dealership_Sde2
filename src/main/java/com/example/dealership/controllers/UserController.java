package com.example.dealership.controllers;

import com.example.dealership.models.Property;
import com.example.dealership.models.User;
import com.example.dealership.services.Implementation.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    private UserServiceImpl userService;

    UserController(UserServiceImpl userService){
        this.userService = userService;
    }

    @PostMapping("/create-user")
    public ResponseEntity<?> addUser(@RequestBody User user){
        try{
            Boolean userCreation = userService.saveNewUser(user);
            if(!userCreation){
                return new ResponseEntity<>("User exists", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("New User Created", HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/my-properties")
    public ResponseEntity<?> myListings(@RequestParam(value = "name") String username){
        try{
            List<Property> properties = userService.getListings(username);

            if(properties.size() == 0) return new ResponseEntity<>("No Properties found with the user provided", HttpStatus.BAD_REQUEST);

            return new ResponseEntity<>(properties, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}