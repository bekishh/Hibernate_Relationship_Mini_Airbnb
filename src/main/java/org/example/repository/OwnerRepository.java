package org.example.repository;

import org.example.entity.House;
import org.example.entity.Owner;

import java.util.List;

public interface OwnerRepository {
    List<Owner> getAllOwners();

    String saveOwnerWithHouse(Owner owner, House house);

    String saveOwner(Owner owner);

    String updateOwnerById(Long id, Owner newOwner);

    Owner findOwnerById(Long ownerId);

    String deleteOwnerById(Long ownerId);

    String assignOwnerToAgency(Long ownerId, Long agencyId);

    List<Owner> getOwnersByAgencyId(Long agencyId);
}
