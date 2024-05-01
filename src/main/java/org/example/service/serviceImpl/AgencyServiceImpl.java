package org.example.service.serviceImpl;

import org.example.entity.Agency;
import org.example.repository.AgencyRepository;
import org.example.repository.repositoryImpl.AgencyRepositoryImpl;
import org.example.service.AgencyService;

import java.util.List;

public class AgencyServiceImpl implements AgencyService {
    AgencyRepository agencyRepository = new AgencyRepositoryImpl();

    @Override
    public String saveAgency(Agency agency) {
        return agencyRepository.saveAgency(agency);
    }

    @Override
    public Agency getAgencyById(Long agencyId) {
        return agencyRepository.getAgencyById(agencyId);
    }

    @Override
    public List<Agency> getAllAgencies() {
        return agencyRepository.getAllAgencies();
    }

    @Override
    public String updateAgencyById(Long agencyId, Agency newAgency) {
        return agencyRepository.updateAgencyById(agencyId, newAgency);
    }

    @Override
    public String deleteAgencyById(Long agencyId) {
        return agencyRepository.deleteAgencyById(agencyId);
    }
}
