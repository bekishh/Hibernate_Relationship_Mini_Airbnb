package org.example.service;

import org.example.entity.RentInfo;

import java.time.LocalDate;
import java.util.List;

public interface RentInfoService {
    List<RentInfo> rentInfoBetweenDates(LocalDate fromDate, LocalDate toDate);

    Long housesByAgencyIdAndDate(Long agencyId);
}
