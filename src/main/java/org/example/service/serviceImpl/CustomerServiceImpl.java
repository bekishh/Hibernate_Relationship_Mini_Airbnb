package org.example.service.serviceImpl;

import org.example.entity.Customer;
import org.example.repository.CustomerRepository;
import org.example.repository.repositoryImpl.CustomerRepositoryImpl;
import org.example.service.CustomerService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class CustomerServiceImpl implements CustomerService {
    CustomerRepository customerRepository = new CustomerRepositoryImpl();

    @Override
    public String saveCustomer(Customer customer) {
        return customerRepository.saveCustomer(customer);
    }

    @Override
    public String saveCustomerRentInto(Customer customer, Long houseId, Long agencyId, LocalDate checkIn, LocalDate checkOut) {
        return customerRepository.saveCustomerRentInto(customer, houseId, agencyId, checkIn, checkOut);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }

    @Override
    public String deleteCustomerById(Long id) {
        return customerRepository.deleteCustomerById(id);
    }

    @Override
    public String updateCustomerById(Long id, Customer newCustomer) {
        return customerRepository.updateCustomerById(id, newCustomer);
    }

    @Override
    public Customer findByCustomerId(Long id) {
        return customerRepository.findByCustomerId(id);
    }
}
