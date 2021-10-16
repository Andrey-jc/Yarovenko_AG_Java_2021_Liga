package com.example.liquibasedemo.services;

import com.example.liquibasedemo.dto.CustomerDTO;
import com.example.liquibasedemo.entity.Customer;
import com.example.liquibasedemo.persistence.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Transactional(readOnly = true)
@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerDTO getCustomerId(String id) {
        Customer customer = customerRepository.findById(
                        UUID.fromString(id)
                )
                .get();
        CustomerDTO customerDTO = new CustomerDTO(customer.getName());
        return customerDTO;
    }

    @Transactional
    @Override
    public CustomerDTO saveCustomer(Customer customer) {
        customerRepository.save(customer);
        CustomerDTO customerDTO = new CustomerDTO(customer.getName());
        return customerDTO;
    }

    @Transactional
    @Override
    public void deleteCustomer(String id) {
        customerRepository.deleteById(
                UUID.fromString(id)
        );

    }

    @Override
    public List<CustomerDTO> allCustomer() {
        List<Customer> all = customerRepository.findAll();
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        for (Customer customer:
             all) {
            customerDTOList.add(new CustomerDTO(customer.getName()));
        }
        return customerDTOList;
    }
}
