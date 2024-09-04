package com.example.dealership.Repository.Impl;

import com.example.dealership.models.Bookmark;
import com.example.dealership.models.Property;
import com.example.dealership.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkRepo extends JpaRepository<Bookmark, Integer> {

    List<Bookmark> findByAppUserAndAndProperty(User appUser, Property property);
    List<Bookmark> findByAppUser(User appUser);
}