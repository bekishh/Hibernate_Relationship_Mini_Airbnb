package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.enums.Gender;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "owners")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "owner_gen")
    @SequenceGenerator(
            name = "owner_gen",
            sequenceName = "owner_seq",
            allocationSize = 1
    )
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    private Gender gender;

    @OneToMany(mappedBy = "owner")
    private List<House> houses;

    @ManyToMany
    private List<Agency> agencies;

    @OneToMany(mappedBy = "owner")
    private List<RentInfo> rentInfos;

    public Owner(String firstName, String lastName, String email, LocalDate dateOfBirth, Gender gender, List<House> houses, List<Agency> agencies, List<RentInfo> rentInfos) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.houses = houses;
        this.agencies = agencies;
        this.rentInfos = rentInfos;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender=" + gender +
                '}';
    }
}
