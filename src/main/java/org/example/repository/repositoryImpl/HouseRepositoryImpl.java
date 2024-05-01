package org.example.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.config.HibernateConfig;
import org.example.entity.*;
import org.example.repository.HouseRepository;
import org.hibernate.HibernateException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HouseRepositoryImpl implements HouseRepository {
    private final EntityManagerFactory entityManagerFactory = HibernateConfig.getEntityManagerFactory();

    @Override
    public String saveHouse(Long ownerId, House house) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            Owner findOwner = entityManager.find(Owner.class, ownerId);
            findOwner.getHouses().add(house);
            house.setOwner(findOwner);
            entityManager.persist(house);
            entityManager.getTransaction().commit();
            return house.getHouseType() + " Успешно сохранен!!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public House findHouseById(Long houseId) {
        House findHouse = null;
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            findHouse = entityManager.createQuery("select h from House h where id =:houseId", House.class)
                    .setParameter("houseId", houseId)
                    .getSingleResult();

            entityManager.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("Failed: " + e.getMessage());
        }
        return findHouse;
    }

    @Override
    public List<House> getAllHouses() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<House> houses = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();
            houses = entityManager.createQuery("select h from House h", House.class)
                    .getResultList();

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println("Failed: " + e.getMessage());
        } finally {
            entityManager.close();
        }
        return houses;
    }

    @Override
    public String updateHouseById(Long houseId, House newHouse) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            House findHouse = entityManager.find(House.class, houseId);
            findHouse.setHouseType(newHouse.getHouseType());
            findHouse.setPrice(newHouse.getPrice());
            findHouse.setRating(newHouse.getRating());
            findHouse.setDescription(newHouse.getDescription());
            findHouse.setRoom(newHouse.getRoom());
            findHouse.setFurniture(newHouse.getFurniture());
            entityManager.getTransaction().commit();
            return newHouse.getHouseType() + " успешно изменен!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public String deleteHouseById(Long houseId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            House findHouse = entityManager.find(House.class, houseId);
            RentInfo rentInfo = findHouse.getRentInfo();
            if (rentInfo.getCheckIn() != null) {
                if (rentInfo.getCheckOut().isAfter(LocalDate.now())) {
                    return "Невозможно удалить, в доме есть жители";
                }
                Owner owner = rentInfo.getOwner();
                owner.getRentInfos().remove(rentInfo);
                Agency agencyForDelete = rentInfo.getAgency();
                agencyForDelete.getRentInfos().remove(rentInfo);
                Customer infoCustomer = rentInfo.getCustomer();
                infoCustomer.getRentInfos().remove(rentInfo);
            }
            Address address = findHouse.getAddress();
            Agency agency = address.getAgency();
            agency.setAddress(null);
            Owner houseOwner = findHouse.getOwner();
            houseOwner.getHouses().remove(findHouse);
            entityManager.remove(findHouse);
            entityManager.getTransaction().commit();
            return findHouse.getHouseType() + " успешно удален!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public List<House> getHousesInRegion(String region) {
        List<House> houses = new ArrayList<>();
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            houses = entityManager.createQuery("select h from House h " +
                            " where h.address.region =:region", House.class)
                    .setParameter("region", region).getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return houses;
    }

    @Override
    public List<House> allHousesByAgencyId(Long agencyId) {
        List<House> houses = new ArrayList<>();
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            houses = entityManager.createQuery("select h from House h " +
                            " where h.address.agency.id =:agencyId", House.class)
                    .setParameter("agencyId", agencyId).getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Failed: " + e.getMessage());
        }
        return houses;
    }

    @Override
    public List<House> allHousesByOwnerId(Long ownerId) {
        List<House> houses = new ArrayList<>();
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            houses = entityManager.createQuery("select h from House h " +
                            " where h.owner.id =:ownerId", House.class)
                    .setParameter("ownerId", ownerId).getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return houses;
    }

    @Override
    public List<House> housesBetweenDates(LocalDate fromDate, LocalDate toDate) {
        List<House> houses = new ArrayList<>();
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            houses = entityManager.createQuery("select h from House h " +
                            "where h.rentInfo.checkIn between :from and :to", House.class)
                    .setParameter("from", fromDate)
                    .setParameter("to", toDate)
                    .getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Failed: " + e.getMessage());
        }
        return houses;
    }
}
