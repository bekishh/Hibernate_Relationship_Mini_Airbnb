package org.example.service.serviceImpl;

import org.example.entity.House;
import org.example.repository.HouseRepository;
import org.example.repository.repositoryImpl.HouseRepositoryImpl;
import org.example.service.HouseService;

import java.time.LocalDate;
import java.util.List;

public class HouseServiceImpl implements HouseService {
    HouseRepository houseRepository = new HouseRepositoryImpl();

    @Override
    public String saveHouse(Long ownerId, House newHouse) {
        return houseRepository.saveHouse(ownerId, newHouse);
    }

    @Override
    public House findHouseById(Long houseId) {
        return houseRepository.findHouseById(houseId);
    }

    @Override
    public List<House> getAllHouses() {
        return houseRepository.getAllHouses();
    }

    @Override
    public String updateHouseById(Long houseId, House newHouse) {
        return houseRepository.updateHouseById(houseId, newHouse);
    }

    @Override
    public String deleteHouseById(Long houseId) {
        return houseRepository.deleteHouseById(houseId);
    }

    @Override
    public List<House> getHousesInRegion(String region) {
        return houseRepository.getHousesInRegion(region);
    }

    @Override
    public List<House> allHousesByAgencyId(Long agencyId) {
        return houseRepository.allHousesByAgencyId(agencyId);
    }

    @Override
    public List<House> allHousesByOwnerId(Long ownerId) {
        return houseRepository.allHousesByOwnerId(ownerId);
    }

    @Override
    public List<House> housesBetweenDates(LocalDate fromDate, LocalDate toDate) {
        return houseRepository.housesBetweenDates(fromDate, toDate);
    }
}
