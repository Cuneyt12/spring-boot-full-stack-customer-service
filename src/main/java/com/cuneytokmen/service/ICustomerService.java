package com.cuneytokmen.service;


import com.cuneytokmen.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICustomerService {
    List<Customer> getAllCustomers();
    Customer saveCustomer(Customer customer);
    Customer findCustomerById(long id);
}
