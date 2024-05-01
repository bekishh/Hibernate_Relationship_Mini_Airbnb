package org.example.repository;

import org.example.entity.Agency;

import java.util.List;

public interface AgencyRepository {
    String saveAgency(Agency agency);

    Agency getAgencyById(Long agencyId);

    List<Agency> getAllAgencies();

    String updateAgencyById(Long agencyId, Agency newAgency);

    String deleteAgencyById(Long agencyId);
}
