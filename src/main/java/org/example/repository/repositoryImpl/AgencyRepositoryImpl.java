package org.example.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.config.HibernateConfig;
import org.example.entity.Agency;
import org.example.repository.AgencyRepository;
import org.hibernate.HibernateException;

import java.util.List;

public class AgencyRepositoryImpl implements AgencyRepository {
    private final EntityManagerFactory entityManagerFactory = HibernateConfig.getEntityManagerFactory();

    @Override
    public String saveAgency(Agency agency) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            String phoneNumber = agency.getPhoneNumber();
            if (phoneNumber != null && !phoneNumber.startsWith("+996")) {
                return "Номер агентства должен начинаться с +996";
            }
            entityManager.getTransaction().begin();
            entityManager.persist(agency);
            entityManager.getTransaction().commit();
            return "Agency успешно сохранен!";
        } catch (HibernateException e) {
            return e.getMessage();
        }
    }

    @Override
    public Agency getAgencyById(Long agencyId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Agency agency = entityManager.createQuery("select a from Agency a where a.id = :id", Agency.class)
                    .setParameter("id", agencyId)
                    .getSingleResult();
            entityManager.getTransaction().commit();
            return agency;
        } catch (HibernateException e) {
            System.out.println("Агентство с ID " + agencyId + " не найден!");
            return null;
        }
    }

    @Override
    public List<Agency> getAllAgencies() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        String query = "select a from Agency a";
        List<Agency> agencyList = entityManager.createQuery(query).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return agencyList;
    }

    @Override
    public String updateAgencyById(Long agencyId, Agency newAgency) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            try {
                Agency agency = getAgencyById(agencyId);
                agency.setName(newAgency.getName());
                entityManager.merge(agency);
                entityManager.getTransaction().commit();
                return "Успешно изменен!";
            } catch (NullPointerException e) {
                entityManager.getTransaction().rollback();
                return "Агентство с ID " + agencyId + " не найден!";
            }
        } catch (HibernateException e) {
            return e.getMessage();

        }
    }

    @Override
    public String deleteAgencyById(Long agencyId) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            Agency agency = getAgencyById(agencyId);
            entityManager.remove(agency);
            entityManager.getTransaction().commit();
            return "Агентство успешно удалено!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
