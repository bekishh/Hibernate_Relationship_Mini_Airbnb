package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "rent_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RentInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rent_info_gen")
    @SequenceGenerator(
            name = "rent_info_gen",
            sequenceName = "rent_info_seq",
            allocationSize = 1
    )
    private Long id;
    @Column(name = "check_in")
    private LocalDate checkIn;
    @Column(name = "check_out")
    private LocalDate checkOut;

    @ManyToOne
    private Owner owner;

    @OneToOne
    private House house;

    @ManyToOne
    private Customer customer;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Agency agency;
}
