package com.carwash.admin_service.service;


import com.carwash.admin_service.client.CustomerServiceClient;
import com.carwash.admin_service.client.OrderServiceClient;
import com.carwash.admin_service.client.WasherServiceClient;
import com.carwash.admin_service.dto.CustomerDTO;
import com.carwash.admin_service.dto.WasherDTO;
import com.carwash.admin_service.model.Customer;
import com.carwash.admin_service.model.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final WasherServiceClient washerServiceClient;

    private final CustomerServiceClient customerServiceClient;

    private final OrderServiceClient orderServiceClient;


    public AdminService(WasherServiceClient washerServiceClient, CustomerServiceClient customerServiceClient, OrderServiceClient orderServiceClient) {
        this.washerServiceClient = washerServiceClient;
        this.customerServiceClient = customerServiceClient;
        this.orderServiceClient = orderServiceClient;
    }


    public List<Customer> getAllCustomers() {
        return customerServiceClient.getAllCustomers().getBody();
    }

    public Customer getCustomerById(Long id) {
        return customerServiceClient.getCustomerById(id).getBody();
    }

    public Customer updateCustomer(Long id, CustomerDTO dto) {
        return customerServiceClient.updateCustomer(id, dto).getBody();
    }

    public void deactivateCustomer(Long id) {
        customerServiceClient.deactivateCustomer(id);
    }

    public void activateCustomer(Long id) {
        customerServiceClient.activateCustomer(id);
    }

    public List<WasherDTO> getAllWashers() {
        return washerServiceClient.getAllWashers();
    }

    public WasherDTO addNewWasher(WasherDTO washer) {
        return washerServiceClient.createWasher(washer);
    }

    public WasherDTO updateWasher(Long id, WasherDTO washer) {
        return washerServiceClient.updateWasher(id, washer);
    }

    public Order getOrderById(Long id) {
        ResponseEntity<Order> response = orderServiceClient.getById(id);
        return response.getBody();
    }

    public List<Order> getAllOrders() {
        ResponseEntity<List<Order>> response = orderServiceClient.getAll();
        return response.getBody();
    }

}
