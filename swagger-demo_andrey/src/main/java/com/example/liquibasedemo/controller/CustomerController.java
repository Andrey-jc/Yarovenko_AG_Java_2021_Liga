package com.example.liquibasedemo.controller;

import com.example.liquibasedemo.dto.CustomerDTO;
import com.example.liquibasedemo.entity.Customer;
import com.example.liquibasedemo.services.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/crud/customer")
@RequiredArgsConstructor
@Api(value = "Customer CRUD operations", description = "Customer CRUD operations")
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiOperation(value = "Enumerates all Customer entities")
    @GetMapping
    public List<CustomerDTO> enumerate() {
        return customerService.allCustomer();
    }

    @ApiOperation(value = "Store given Customer entity")
    @PostMapping
    public CustomerDTO save(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    @ApiOperation(value = "Retrieves Customer entity by it ID")
    @GetMapping("/{id}")
    public CustomerDTO get(@PathVariable("id") String id) {
        return customerService.getCustomerId(id);
    }

    //TODO: добавить и проаннотировать операции удаления сущности Customer, и создания новой пустой сущности Customer
    @ApiOperation(value = "Delete customer")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        customerService.deleteCustomer(id);
     }

    @ApiOperation(value = "Create Customer")
    @PutMapping("/create")
    public CustomerDTO update(@RequestBody Customer customer) {
         return customerService.saveCustomer(customer);
    }
}
