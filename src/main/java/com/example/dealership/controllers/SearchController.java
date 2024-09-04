package com.example.dealership.controllers;

import com.example.dealership.enums.ListingType;
import com.example.dealership.models.Property;
import com.example.dealership.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("find")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping()
    public ResponseEntity<?> search(
            @RequestParam(value = "location", required = false) String location,
            @RequestParam(value = "price-min", required = false) Integer min,
            @RequestParam(value = "price-max", required = false) Integer max,
            @RequestParam(value = "type", required = false) ListingType type,
            @RequestParam(value = "rooms", required = false) Integer rooms,
            @RequestParam(value = "size", required = false) Integer size,
            @RequestParam(value = "sort", required = false) Boolean sort
    ) {
        try {
            List<Property> searchResult = searchService.getProperties(
                    location,
                    min,
                    max,
                    type,
                    rooms,
                    size,
                    sort
            );

            if(searchResult.size() == 0) return new ResponseEntity<>("No properties matches your search filters", HttpStatus.BAD_REQUEST);

            return new ResponseEntity<>(searchResult, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
