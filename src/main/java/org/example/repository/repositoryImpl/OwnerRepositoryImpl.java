package org.example.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.config.HibernateConfig;
import org.example.entity.*;
import org.example.repository.OwnerRepository;
import org.hibernate.HibernateException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class OwnerRepositoryImpl implements OwnerRepository {
    private final EntityManagerFactory entityManagerFactory = HibernateConfig.getEntityManagerFactory();

    @Override
    public String saveOwnerWithHouse(Owner owner, House house) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            LocalDate date1 = owner.getDateOfBirth();
            LocalDate date2 = LocalDate.now();
//            long yearsDifference = date1.until(date2, ChronoUnit.YEARS);
            int yearsDifference = date2.getYear() - date1.getYear();
            if (yearsDifference < 18) {
                return "Владелец не может быть младше 18 лет!";
            } else {
                entityManager.getTransaction().begin();
                owner.getHouses().add(house);
                house.setOwner(owner);
                entityManager.persist(owner);
                entityManager.persist(house);
                entityManager.getTransaction().commit();
                return owner.getFirstName() + " успешно сохранен!";
            }
        } catch (Exception e) {
            return "Failed: " + e.getMessage();
        }
    }

    @Override
    public String saveOwner(Owner owner) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            LocalDate date1 = owner.getDateOfBirth();
            LocalDate date2 = LocalDate.now();
            long yearsDifference = date1.until(date2, ChronoUnit.YEARS);
            if (yearsDifference < 18) {
                return "Владелец не может быть младше 18 лет!";
            } else {
                entityManager.getTransaction().begin();
                entityManager.persist(owner);
                entityManager.getTransaction().commit();
                return owner.getFirstName() + " успешно сохранен!";
            }
        } catch (Exception e) {
            return "Failed: " + e.getMessage();
        }
    }

    @Override
    public List<Owner> getAllOwners() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        String query = "select a from Owner a";
        List<Owner> OwnerList = entityManager.createQuery(query).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return OwnerList;
    }

    @Override
    public String updateOwnerById(Long id, Owner newOwner) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            try {
                Owner owner = entityManager.find(Owner.class, id);
                owner.setFirstName(newOwner.getFirstName());
                owner.setLastName(newOwner.getLastName());
                owner.setEmail(newOwner.getEmail());
                owner.setDateOfBirth(newOwner.getDateOfBirth());
                owner.setGender(newOwner.getGender());
                entityManager.merge(owner);
                entityManager.getTransaction().commit();
                return "Владелец успешно изменен!";
            } catch (NullPointerException e) {
                entityManager.getTransaction().rollback();
                return "Влделец с ID " + id + " не найден!";
            }
        } catch (HibernateException e) {
            return e.getMessage();
        }
    }

    @Override
    public Owner findOwnerById(Long ownerId) {
        Owner findOwner = null;
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            findOwner = entityManager.createQuery("select o from Owner o where id =:ownerId", Owner.class)
                    .setParameter("ownerId", ownerId)
                    .getSingleResult();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Failed: " + e.getMessage());
        }
        return findOwner;
    }

    @Override
    public String deleteOwnerById(Long ownerId) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            Owner findOwner = entityManager.find(Owner.class, ownerId);
            List<RentInfo> rentInfo = findOwner.getRentInfos();
            if (!rentInfo.isEmpty()) {
                for (RentInfo info : rentInfo) {
                    if (info.getCheckOut().isAfter(LocalDate.now())) {
                        return "Невозможно удалить владельца у которого сдан в аренду!";
                    }
                    Agency agency = info.getAgency();
                    agency.getRentInfos().remove(info);
                    Customer customer = info.getCustomer();
                    customer.getRentInfos().remove(info);
                    entityManager.remove(info);
                }
            }
            List<Agency> findAgency = findOwner.getAgencies();
            for (Agency agency : findAgency) {
                agency.getOwners().remove(findOwner);
            }
            entityManager.remove(findOwner);
            entityManager.getTransaction().commit();
            return findOwner.getFirstName() + " Успешно удален!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public String assignOwnerToAgency(Long ownerId, Long agencyId) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            Owner findOwner = entityManager.find(Owner.class, ownerId);
            Agency findAgency = entityManager.find(Agency.class, agencyId);
            if (findOwner == null || findAgency == null) {
                return "Агентсво или продавец с указанным id не найдено!";
            }
            findOwner.getAgencies().add(findAgency);
            findAgency.getOwners().add(findOwner);
            entityManager.getTransaction().commit();
            return "Successfully assigned!!!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public List<Owner> getOwnersByAgencyId(Long agencyId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Owner> owners = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();
            owners = entityManager.createQuery("select o from Owner o join o.agencies a where a.id =:agencyId", Owner.class)
                    .setParameter("agencyId", agencyId)
                    .getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println("Failed: " + e.getMessage());
        }
        return owners;
    }
}
