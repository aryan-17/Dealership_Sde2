package com.example.dealership.services;

import com.example.dealership.Repository.Impl.BookmarkRepo;
import com.example.dealership.Repository.Impl.PropertiesRepoImpl;
import com.example.dealership.Repository.Impl.UserRepoImpl;
import com.example.dealership.models.Bookmark;
import com.example.dealership.models.Property;
import com.example.dealership.models.User;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BookmarkService {

    @Autowired
    private BookmarkRepo bookmarkRepo;

    @Autowired
    private UserRepoImpl userRepoImpl;

    @Autowired
    private PropertiesRepoImpl propertiesRepoImpl;

    public Boolean addBookmark(String username, Integer propId) {

        User userOpt = userRepoImpl.findByName(username);
        if (userOpt == null) {
            log.error("User with username {} not found.", username);
            return false;
        }

        Optional<Property> propertyOpt = propertiesRepoImpl.findById(propId);
        if (!propertyOpt.isPresent()) {
            log.error("Property with ID {} not found.", propId);
            return false;
        }

        if(bookmarkRepo.findByAppUserAndAndProperty(userOpt, propertyOpt.get()).size() != 0){
            log.info("Bookmark already exists for user {} and property ID {}", username, propId);
            return false;
        }

        Bookmark bookmark = new Bookmark();
        bookmark.setProperty(propertyOpt.get());
        bookmark.setAppUser(userOpt);
        bookmarkRepo.save(bookmark);

        log.info("Bookmark added for user {} and property ID {}", username, propId);
        return true;
    }

    public List<Bookmark> getBookmarks(Integer userId){
        Optional<User> user = userRepoImpl.findById(userId);

        if(!user.isPresent()){
            log.error("No user found with userId {}", userId);
            return new ArrayList<>();
        }

        List<Bookmark> bookmarks = bookmarkRepo.findByAppUser(user.get());

        if (bookmarks.isEmpty()) {
            log.error("No bookmarks found for userId {}", userId);
        } else {
            bookmarks.forEach(bookmark -> {
                Hibernate.initialize(bookmark.getProperty());
                Hibernate.initialize(bookmark.getAppUser());
            });
        }

        return bookmarks;
    }

}