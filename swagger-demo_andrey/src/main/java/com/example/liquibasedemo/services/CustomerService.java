package com.example.liquibasedemo.services;

import com.example.liquibasedemo.dto.CustomerDTO;
import com.example.liquibasedemo.entity.Customer;

import java.util.List;

public interface CustomerService {
    CustomerDTO getCustomerId(String id);

    CustomerDTO saveCustomer(Customer customer);

    void deleteCustomer(String id);

    List<CustomerDTO> allCustomer();

}
