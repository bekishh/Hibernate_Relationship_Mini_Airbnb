package org.example.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.config.HibernateConfig;
import org.example.entity.Address;
import org.example.entity.Agency;
import org.example.repository.AddressRepository;
import org.hibernate.HibernateException;

import java.util.*;

public class AddressRepositoryImpl implements AddressRepository {
    private final EntityManagerFactory entityManagerFactory = HibernateConfig.getEntityManagerFactory();

    @Override
    public List<Address> getAllAddress() {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            String query = "select a from Address a";
            List<Address> addresses = entityManager.createQuery(query).getResultList();
            entityManager.getTransaction().commit();
            entityManager.close();
            return addresses;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Address findAddressById(Long id) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            Address address = entityManager.find(Address.class, id);
            entityManager.getTransaction().commit();
            return address;
        } catch (HibernateException e) {
            System.out.println("Address с ID " + id + " не найден.");
            return null;
        }
    }

    @Override
    public String updateAddress(Long id, Address newAddress) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            Address address = findAddressById(id);
            if (address != null) {
                address.setCity(newAddress.getCity());
                address.setRegion(newAddress.getRegion());
                address.setStreet(newAddress.getStreet());
                entityManager.merge(address);
                entityManager.getTransaction().commit();
                return "Адрес успешно изменен!";
            } else {
                return "Адрес не существует";
            }
        } catch (HibernateException e) {
            return e.getMessage();
        }
    }

    @Override
    public Map<Address, Agency> getAllAgencyWithAddress() {
        Map<Address, Agency> addressAgencyMap = new HashMap<>();
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            List<Address> resultList = entityManager.createQuery(
                            "select distinct a from Address a join a.agency", Address.class)
                    .getResultList();
            for (Address address : resultList) {
                addressAgencyMap.put(address, address.getAgency());
            }
            entityManager.getTransaction().commit();
            return addressAgencyMap;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public int getAgencyCountByCityName(String cityName) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            String query = "select count(a.agency.id) from Address a where a.city = :cityName";
            Long count = entityManager.createQuery(query, Long.class)
                    .setParameter("cityName", cityName)
                    .getSingleResult();
            entityManager.getTransaction().commit();
            return count.intValue();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    @Override
    public Map<String, List<Agency>> groupByRegion() {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
//            String query = "select a from Address a";
//            List<Address> resultList1 = entityManager.createQuery(query, Address.class).getResultList();
//            entityManager.getTransaction().commit();
//            Map<String, List<Agency>> groupedByRegion = new HashMap<>();
//            Iterator<Address> iterator = resultList1.iterator();
//            while (iterator.hasNext()) {
//                groupedByRegion.computeIfAbsent(iterator.next().getRegion(), k -> new ArrayList<>()).add(iterator.next().getAgency());
//            }
            String query = "select a.region, a.agency from Address a";
            List<Object[]> resultList = entityManager.createQuery(query, Object[].class).getResultList();
            entityManager.getTransaction().commit();
            Map<String, List<Agency>> groupedByRegion = new HashMap<>();
            for (Object[] result : resultList) {
                String region = (String) result[0];
                Agency agency = (Agency) result[1];
                groupedByRegion.computeIfAbsent(region, k -> new ArrayList<>()).add(agency);
            }
            return groupedByRegion;
        } catch (Exception e) {
            System.out.println("Произошла ошибка при выполнении запроса.");
            return null;
        }
    }
}
