package org.example.service;

import org.example.entity.House;
import org.example.entity.Owner;

import java.util.List;
import java.util.Optional;

public interface OwnerService {
    List<Owner> getAllOwners();

    String saveOwner(Owner owner, House house);

    String updateOwnerById(Long id, Owner newOwner);

    Owner findOwnerById(Long ownerId);

    String deleteOwnerById(Long ownerId);

    String assignOwnerToAgency(Long ownerId, Long agencyId);

    List<Owner> getOwnersByAgencyId(Long agencyId);
}
