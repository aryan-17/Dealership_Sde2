package com.example.dealership.services;


import com.example.dealership.Repository.PropertiesRepo;
import com.example.dealership.enums.ListingType;
import com.example.dealership.models.Property;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SearchService {

    @Autowired
    private PropertiesRepo propertiesRepo;

    public List<Property> getProperties(
            String location,
            Integer min,
            Integer max,
            ListingType type,
            Integer rooms,
            Integer size,
            Boolean sort
    ) {
        log.error("{} {} {} {} {} {} {}", location, min, max, type, rooms, size, sort);

        List<Property> allListings = new ArrayList<>();

        if (location != null) {
            String[] locationArr = location.split(",");
            allListings = Arrays
                    .stream(locationArr)
                    .flatMap(loc -> propertiesRepo.findByLocationContainingIgnoreCase(loc).stream())
                    .collect(Collectors.toList());
        }

        allListings = propertiesRepo.findAll();

        allListings = allListings
                .stream()
                .filter((property -> property.getAvailable() == true))
                .collect(Collectors.toList());

        if (min != null) {
            allListings = allListings
                    .stream()
                    .filter((property) -> property.getPrice() >= min)
                    .collect(Collectors.toList());
        }

        if (max != null) {
            allListings = allListings
                    .stream()
                    .filter((property) -> property.getPrice() <= max)
                    .collect(Collectors.toList());
        }

        if (type != null) {
            allListings = allListings
                    .stream()
                    .filter((property) -> property.getListing() == type)
                    .collect(Collectors.toList());
        }

        if (rooms != null) {
            allListings = allListings
                    .stream()
                    .filter((property) -> property.getRooms() == rooms)
                    .collect(Collectors.toList());
        }

        if (size != null) {
            allListings = allListings
                    .stream()
                    .filter((property) -> property.getSize() >= size - 10 && property.getSize() <= size + 10)
                    .collect(Collectors.toList());
        }

        if (sort == true) {
            allListings.sort(Comparator.comparingInt(Property::getPrice));
        }

        return allListings;
    }
}
