package com.cuneytokmen.service.impl;

import com.cuneytokmen.entity.Customer;
import com.cuneytokmen.repository.ICustomerRepository;
import com.cuneytokmen.service.ICustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {
    private final ICustomerRepository iCustomerRepository;

    public CustomerServiceImpl(ICustomerRepository iCustomerRepository) {
        this.iCustomerRepository = iCustomerRepository;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return iCustomerRepository.findAll();
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return iCustomerRepository.save(customer);
    }

    @Override
    public Customer findCustomerById(long id) {
        return iCustomerRepository.findCustomerById(id);
    }


}
