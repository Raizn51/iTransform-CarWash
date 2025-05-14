package com.carwash.service;

import com.carwash.dto.CustomerDTO;
import com.carwash.model.Customer;
import com.carwash.repository.CustomerDAO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerDAO customerRepository;

    public CustomerService(CustomerDAO customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer registerCustomer(CustomerDTO customerDTO) {
        if (customerRepository.existsByEmail(customerDTO.getEmail())) {
            throw new RuntimeException("Email already in use");
        }

        if (customerRepository.existsByUserName(customerDTO.getUserName())) {
            throw new RuntimeException("Username already taken");
        }


        Customer customer = new Customer(customerDTO);
        // Removed password encoding
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public Optional<Customer> getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    public Customer updateCustomer(Long id, CustomerDTO customerDTO) {
        return customerRepository.findById(id)
                .map(existingCustomer -> {
                    existingCustomer.setFullName(customerDTO.getFullName());
                    existingCustomer.setPhoneNumber(customerDTO.getPhoneNumber());
                    existingCustomer.setProfileImageUrl(customerDTO.getProfileImageUrl());
                    return customerRepository.save(existingCustomer);
                })
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public void deactivateCustomer(Long id) {
        customerRepository.findById(id)
                .ifPresent(customer -> {
                    customer.setActive(false);
                    customerRepository.save(customer);
                });
    }
    public void activateCustomer(Long id) {
        customerRepository.findById(id)
                .ifPresent(customer -> {
                    customer.setActive(true);
                    customerRepository.save(customer);
                });
    }

    public void addCarToCustomer(Long customerId, Long carId) {
        customerRepository.findById(customerId)
                .ifPresent(customer -> {
                    customer.getCarIds().add(carId);
                    customerRepository.save(customer);
                });
    }

    public void addOrderToCustomer(Long customerId, Long orderId) {
        customerRepository.findById(customerId)
                .ifPresent(customer -> {
                    customer.getOrderIds().add(orderId);
                    customerRepository.save(customer);
                });
    }
}