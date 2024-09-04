package com.example.dealership.Repository;

import com.example.dealership.Repository.Impl.UserRepoImpl;
import com.example.dealership.models.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepo {
    private UserRepoImpl userRepoImpl;

    UserRepo(UserRepoImpl userRepoImpl){
        this.userRepoImpl = userRepoImpl;
    }

    public User findUserByName(String name) {
        return userRepoImpl.findByName(name);
    }

    public User findUserByEmail(String email) {
        return userRepoImpl.findByEmail(email);
    }

    public void saveUser(User user) {
        userRepoImpl.save(user);
    }
}
