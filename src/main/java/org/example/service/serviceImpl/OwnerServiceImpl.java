package org.example.service.serviceImpl;

import org.example.entity.House;
import org.example.entity.Owner;
import org.example.repository.OwnerRepository;
import org.example.repository.repositoryImpl.OwnerRepositoryImpl;
import org.example.service.OwnerService;

import java.util.List;

public class OwnerServiceImpl implements OwnerService {
    OwnerRepository ownerRepository = new OwnerRepositoryImpl();

    @Override
    public List<Owner> getAllOwners() {
        return ownerRepository.getAllOwners();
    }

    @Override
    public String saveOwner(Owner owner, House house) {
        return ownerRepository.saveOwner(owner, house);
    }

    @Override
    public String updateOwnerById(Long id, Owner newOwner) {
        return ownerRepository.updateOwnerById(id, newOwner);
    }

    @Override
    public Owner findOwnerById(Long ownerId) {
        return ownerRepository.findOwnerById(ownerId);
    }

    @Override
    public String deleteOwnerById(Long ownerId) {
        return ownerRepository.deleteOwnerById(ownerId);
    }

    @Override
    public String assignOwnerToAgency(Long ownerId, Long agencyId) {
        return ownerRepository.assignOwnerToAgency(ownerId, agencyId);
    }

    @Override
    public List<Owner> getOwnersByAgencyId(Long agencyId) {
        return ownerRepository.getOwnersByAgencyId(agencyId);
    }
}
