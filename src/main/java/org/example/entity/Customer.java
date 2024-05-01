package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.enums.FamilyStatus;
import org.example.enums.Gender;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_gen")
    @SequenceGenerator(
            name = "customer_gen",
            sequenceName = "customer_seq",
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
    private String nationality;
    @Column(name = "family_status")
    private FamilyStatus familyStatus;

    @ManyToOne
    private RentInfo rentInfo;

    @OneToMany(mappedBy = "customer")
    private List<RentInfo> rentInfos;

    public Customer(String firstName, String lastName, String email, Gender gender, String nationality, FamilyStatus familyStatus) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.nationality = nationality;
        this.familyStatus = familyStatus;
    }
}
