package org.example.repository;

import org.example.entity.RentInfo;

import java.time.LocalDate;
import java.util.List;

public interface RentInfoRepository {
    List<RentInfo> rentInfoBetweenDates(LocalDate fromDate, LocalDate toDate);

    Long housesByAgencyIdAndDate(Long agencyId);
}
