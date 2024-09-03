package com.example.dealership.controllers;

import com.example.dealership.enums.Units;
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
    public ResponseEntity<?> listProperty(
            @RequestBody Property property,
            @RequestParam(value = "name") String username,
            @RequestParam(value = "unit") Units unit
            ){
        try{
            Boolean listing = propertiesService.saveNewProperty(property, username, unit);
            if(!listing){
                return new ResponseEntity<>("Bad Request Params", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>("Property Listed", HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PatchMapping("/sold/{propId}")
    public ResponseEntity<?> setAsSold(@PathVariable Integer propId, @RequestParam(value = "userId") Integer userId){
        try{
            Boolean marked = propertiesService.markedSold(propId, userId);
            if(!marked) return new ResponseEntity<>("Invalid Request", HttpStatus.BAD_REQUEST);

            return new ResponseEntity<>("Marked as Sold", HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
