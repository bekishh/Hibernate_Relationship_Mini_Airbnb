package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.enums.HouseType;

import java.math.BigDecimal;

@Entity
@Table(name = "houses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "house_gen")
    @SequenceGenerator(
            name = "house_gen",
            sequenceName = "house_seq",
            allocationSize = 1
    )
    private Long id;
    @Column(name = "house_type")
    private HouseType houseType;
    private BigDecimal price;
    private Double rating;
    private String description;
    private Integer room;
    private Boolean furniture;

    @OneToOne
    private Address address;

    @ManyToOne
    private Owner owner;

    @OneToOne(cascade = CascadeType.REMOVE)
    private RentInfo rentInfo;

    public House(HouseType houseType, BigDecimal price, Double rating, String description, Integer room, Boolean furniture) {
        this.houseType = houseType;
        this.price = price;
        this.rating = rating;
        this.description = description;
        this.room = room;
        this.furniture = furniture;
    }
}
