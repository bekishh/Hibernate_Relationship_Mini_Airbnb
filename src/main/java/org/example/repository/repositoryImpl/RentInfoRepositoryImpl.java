package org.example.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.config.HibernateConfig;
import org.example.entity.RentInfo;
import org.example.repository.RentInfoRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class RentInfoRepositoryImpl implements RentInfoRepository {
    private final EntityManagerFactory entityManagerFactory = HibernateConfig.getEntityManagerFactory();

    @Override
    public List<RentInfo> rentInfoBetweenDates(LocalDate fromDate, LocalDate toDate) {
        List<RentInfo> houses = new ArrayList<>();
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            houses = entityManager.createQuery("select r from RentInfo r where r.checkOut between :from and :to",
                            RentInfo.class)
                    .setParameter("from", fromDate)
                    .setParameter("to", toDate)
                    .getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return houses;
    }

    @Override
    public Long housesByAgencyIdAndDate(Long agencyId) {
        Long countHouse = 0L;
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            countHouse = entityManager.createQuery("select count(r) from RentInfo r where r.agency.id =:agencyId and r.checkIn <=:currentDate and r.checkOut>=:currentDate", Long.class)
                    .setParameter("agencyId", agencyId)
                    .setParameter("currentDate", LocalDate.now())
                    .getSingleResult();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Failed: " + e.getMessage());
        }
        return countHouse;
    }
}
