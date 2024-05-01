package org.example.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.config.HibernateConfig;
import org.example.entity.*;
import org.example.repository.CustomerRepository;
import org.hibernate.HibernateException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository {
    private final EntityManagerFactory entityManagerFactory = HibernateConfig.getEntityManagerFactory();

    @Override
    public String saveCustomer(Customer customer) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.persist(customer);
            entityManager.getTransaction().commit();
            entityManager.close();
            return "Клиент успешно сохранен!";
        } catch (HibernateException e) {
            return e.getMessage();
        }
    }

    @Override
    public String saveCustomerRentInto(Customer customer, Long houseId, Long agencyId, LocalDate checkIn, LocalDate checkOut) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            Agency findAgency = entityManager.find(Agency.class, agencyId);
            House findHouse = entityManager.find(House.class, houseId);
            if (!checkHouseAble(entityManager, houseId, checkIn, checkOut)) {
                return "There are no houses for the selected dates";
            }
            RentInfo rentInfo = new RentInfo();
            rentInfo.setCustomer(customer);
            rentInfo.setHouse(findHouse);
            rentInfo.setAgency(findAgency);
            rentInfo.setCheckIn(checkIn);
            rentInfo.setCheckOut(checkOut);

            findHouse.setRentInfo(rentInfo);
            findAgency.getRentInfos().add(rentInfo);
            entityManager.persist(rentInfo);
            entityManager.persist(customer);

            if (customer.getRentInfos() == null) {
                customer.setRentInfos(new ArrayList<>());
            }
            customer.getRentInfos().add(rentInfo);

            entityManager.persist(customer);
            entityManager.persist(rentInfo);
            entityManager.getTransaction().commit();
            return customer.getFirstName() + " successfully saved!!!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        String query = "select a from Customer a";
        List<Customer> CistomerList = entityManager.createQuery(query).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return CistomerList;
    }

    @Override
    public String deleteCustomerById(Long id) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            Customer customer = entityManager.find(Customer.class, id);
            List<RentInfo> rentInfos = customer.getRentInfos();
            if (!rentInfos.isEmpty()) {
                for (RentInfo info : rentInfos) {
                    if (info.getCheckOut().isAfter(LocalDate.now())) {
                        System.out.println("У клиента есть активная аренда, невозможно удалить");
                    }
                    House house = info.getHouse();
                    house.setRentInfo(null);
                    Owner owner = info.getOwner();
                    owner.getRentInfos().remove(info);
                    Agency agency = info.getAgency();
                    agency.getRentInfos().remove(info);
                    entityManager.remove(info);
                }
            }
            entityManager.remove(customer);
            entityManager.getTransaction().commit();
            return customer.getFirstName() + " успешно удален!";
        } catch (HibernateException e) {
            return e.getMessage();
        }
    }

    @Override
    public String updateCustomerById(Long id, Customer newCustomer) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            try {
                Customer customer = entityManager.find(Customer.class, id);
                customer.setFirstName(newCustomer.getFirstName());
                entityManager.merge(customer);
                entityManager.getTransaction().commit();
                return "Клиент успешно изменен!";
            } catch (NullPointerException e) {
                entityManager.getTransaction().rollback();
                return "Customer с ID " + id + " не найден!";
            }
        } catch (HibernateException e) {
            return e.getMessage();
        }
    }

    @Override
    public Customer findByCustomerId(Long id) {
        Customer findCustomer = null;
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            findCustomer = entityManager.createQuery("select c from Customer c where id =:customerId", Customer.class)
                    .setParameter("customerId", id)
                    .getSingleResult();

            entityManager.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("Failed: " + e.getMessage());
        }
        return findCustomer;
    }

    private boolean checkHouseAble(EntityManager entityManager, Long houseId, LocalDate checkIn, LocalDate checkout) {
        String jpql = "select count(distinct r.house.id) from RentInfo r " +
                "where r.house.id = :houseId " +
                "and (:checkIn between r.checkIn and r.checkIn " +
                "or :checkout between r.checkIn and r.checkOut)";

        Long count = entityManager.createQuery(jpql, Long.class)
                .setParameter("houseId", houseId)
                .setParameter("checkIn", checkIn)
                .setParameter("checkout", checkout)
                .getSingleResult();

        return count == 0;
    }
}
