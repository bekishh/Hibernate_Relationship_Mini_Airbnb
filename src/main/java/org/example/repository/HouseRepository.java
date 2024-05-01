package org.example.repository;

import org.example.entity.House;

import java.time.LocalDate;
import java.util.List;

public interface HouseRepository {
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
