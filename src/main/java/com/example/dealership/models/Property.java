package com.example.dealership.models;


import com.example.dealership.enums.ListingType;
import jakarta.persistence.*;
import jdk.jfr.BooleanFlag;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Property")
public class Property {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private Integer size;

    @Column(nullable = false)
    private ListingType listing;

    @Column(nullable = false)
    private Integer price;

    private Integer deposit;

    @Column(nullable = false)
    private Integer rooms;

    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;

    @Column(nullable = false)
    @ColumnDefault(value = "true")
    private Boolean available;
}
