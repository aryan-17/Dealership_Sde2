package com.example.dealership.controllers;


import com.example.dealership.models.Bookmark;
import com.example.dealership.services.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookmark")
public class BookmarkController {

    @Autowired
    private BookmarkService bookmarkService;

    @PostMapping("/add")
    public ResponseEntity<?> addBookmark(@RequestParam(value = "user") String username, @RequestParam(value = "id") Integer propId){
        try{
            Boolean bookmark = bookmarkService.addBookmark(username, propId);
            if(!bookmark){
                return new ResponseEntity<>("Invalid username or property id", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("Bookmark added", HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getBookmarks(@PathVariable Integer userId){
        try{
            List<Bookmark> bookmarks = bookmarkService.getBookmarks(userId);
            return new ResponseEntity<>(bookmarks, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
