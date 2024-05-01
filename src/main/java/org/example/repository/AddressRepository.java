package org.example.repository;

import org.example.entity.Address;
import org.example.entity.Agency;

import java.util.List;
import java.util.Map;

public interface AddressRepository {
    List<Address> getAllAddress();

    Address findAddressById(Long id);

    String updateAddress(Long id, Address newAddress);

    Map<Address, Agency> getAllAgencyWithAddress();

    int getAgencyCountByCityName(String cityName);

    Map<String, List<Agency>> groupByRegion();
}
