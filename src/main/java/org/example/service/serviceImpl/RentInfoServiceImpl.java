package org.example.service.serviceImpl;

import org.example.entity.RentInfo;
import org.example.repository.RentInfoRepository;
import org.example.repository.repositoryImpl.RentInfoRepositoryImpl;
import org.example.service.RentInfoService;

import java.time.LocalDate;
import java.util.List;

public class RentInfoServiceImpl implements RentInfoService {
    RentInfoRepository rentInfoRepository = new RentInfoRepositoryImpl();

    @Override
    public List<RentInfo> rentInfoBetweenDates(LocalDate fromDate, LocalDate toDate) {
        return rentInfoRepository.rentInfoBetweenDates(fromDate, toDate);
    }

    @Override
    public Long housesByAgencyIdAndDate(Long agencyId) {
        return rentInfoRepository.housesByAgencyIdAndDate(agencyId);
    }
}
