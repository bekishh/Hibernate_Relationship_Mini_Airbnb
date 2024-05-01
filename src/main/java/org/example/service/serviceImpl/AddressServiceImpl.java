package org.example.service.serviceImpl;

import org.example.entity.Address;
import org.example.entity.Agency;
import org.example.repository.AddressRepository;
import org.example.repository.repositoryImpl.AddressRepositoryImpl;
import org.example.service.AddressService;

import java.util.List;
import java.util.Map;

public class AddressServiceImpl implements AddressService {
    AddressRepository addressRepository = new AddressRepositoryImpl();

    @Override
    public List<Address> getAllAddress() {
        return addressRepository.getAllAddress();
    }

    @Override
    public Address findAddressById(Long id) {
        return addressRepository.findAddressById(id);
    }

    @Override
    public String updateAddress(Long id, Address newAddress) {
        return addressRepository.updateAddress(id, newAddress);
    }

    @Override
    public Map<Address, Agency> getAllAgencyWithAddress() {
        return addressRepository.getAllAgencyWithAddress();
    }

    @Override
    public int getAgencyCountByCityName(String cityName) {
        return addressRepository.getAgencyCountByCityName(cityName);
    }

    @Override
    public Map<String, List<Agency>> groupByRegion() {
        return addressRepository.groupByRegion();
    }
}
