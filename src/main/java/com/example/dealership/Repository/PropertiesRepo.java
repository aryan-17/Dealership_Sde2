package com.example.dealership.Repository;

import com.example.dealership.models.Property;
import com.example.dealership.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PropertiesRepo extends JpaRepository<Property, Integer> {

    List<Property> findByOwner(User user);
}