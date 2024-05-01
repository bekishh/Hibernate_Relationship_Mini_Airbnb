package org.example.service;

import org.example.entity.House;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface HouseService {
    String saveHouse(Long ownerId, House newHouse);

    House findHouseById(Long houseId);

    List<House> getAllHouses();

    String updateHouseById(Long houseId, House newHouse);

    String deleteHouseById(Long houseId);

    List<House> getHousesInRegion(String region);

    List<House> allHousesByAgencyId(Long agencyId);

    List<House> allHousesByOwnerId(Long ownerId);

    List<House> housesBetweenDates(LocalDate fromDate, LocalDate toDate);
}
