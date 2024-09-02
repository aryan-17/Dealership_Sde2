package com.example.dealership.controllers;

import com.example.dealership.models.Property;
import com.example.dealership.services.PropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/property")
public class PropertiesController {

    @Autowired
    private PropertiesService propertiesService;

    @PostMapping("/list")
    public ResponseEntity<?> listProperty(@RequestBody Property property, @RequestParam(value = "name") String username){
        try{
//            if(username == "") return new ResponseEntity<>("Provide a Username to list a property", HttpStatus.BAD_REQUEST);
            Boolean listing = propertiesService.saveNewProperty(property, username);
            if(!listing){
                return new ResponseEntity<>("User not Found", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>("Property Listed", HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
