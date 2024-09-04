package com.example.dealership.Repository;

import com.example.dealership.Repository.Impl.PropertiesRepoImpl;
import com.example.dealership.models.Property;
import com.example.dealership.models.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PropertiesRepo {

    private PropertiesRepoImpl propertiesRepoImpl;

    PropertiesRepo(PropertiesRepoImpl propertiesRepoImpl){
        this.propertiesRepoImpl = propertiesRepoImpl;
    }

    public List<Property> findPropertiesByOwner(User user){
        return propertiesRepoImpl.findByOwner(user);
    }

    public Property findPropertyByOwnerAndProperty(User user, Integer id){
        return propertiesRepoImpl.findByOwnerAndId(user, id);
    }

    public List<Property> findPropertyByLocationContainingIgnoreCase(String location){
        return  propertiesRepoImpl.findByLocationContainingIgnoreCase(location);
    }
}
