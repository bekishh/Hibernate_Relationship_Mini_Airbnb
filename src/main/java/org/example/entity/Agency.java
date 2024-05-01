package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "agencies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Agency {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "agency_gen")
    @SequenceGenerator(
            name = "agency_gen",
            sequenceName = "agency_seq",
            allocationSize = 1
    )
    private Long id;
    private String name;
    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToOne(cascade = {CascadeType.ALL})
    private Address address;

    @ManyToMany(mappedBy = "agencies")
    private List<Owner> owners;

    @OneToMany(mappedBy = "agency", cascade = {CascadeType.REMOVE, CascadeType.DETACH})
    private List<RentInfo> rentInfos;

    public Agency(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public Agency(String name, String phoneNumber, Address address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}
