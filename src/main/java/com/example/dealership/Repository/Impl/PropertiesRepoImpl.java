package com.example.dealership.Repository.Impl;

import com.example.dealership.models.Property;
import com.example.dealership.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PropertiesRepoImpl extends JpaRepository<Property, Integer> {

    List<Property> findByOwner(User user);

    Property findByOwnerAndId(User user, Integer id);

    @Query("SELECT p FROM Property p WHERE LOWER(p.location) LIKE LOWER(CONCAT('%', :location, '%'))")
    List<Property> findByLocationContainingIgnoreCase(@Param("location") String location);
}