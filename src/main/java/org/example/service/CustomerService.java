package org.example.service;

import org.example.entity.Customer;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CustomerService {
    String saveCustomer(Customer customer);

    String saveCustomerRentInto(Customer customer, Long houseId, Long agencyId, LocalDate checkIn, LocalDate checkOut);

    List<Customer> getAllCustomers();

    String deleteCustomerById(Long id);

    String updateCustomerById(Long id, Customer newCustomer);

    Customer findByCustomerId(Long id);
}
