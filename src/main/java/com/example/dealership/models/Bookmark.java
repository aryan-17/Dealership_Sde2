package com.example.dealership.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Bookmark")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Bookmark {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User appUser;

    @ManyToOne(fetch = FetchType.LAZY)
    private Property property;
}
