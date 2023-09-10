package com.cuneytokmen.controller;


import com.cuneytokmen.entity.Customer;
import com.cuneytokmen.service.ICustomerService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/home")
public class CustomerController {
    private final ICustomerService iCustomerService;

    public CustomerController(@Qualifier("customerServiceImpl") ICustomerService iCustomerService) {
        this.iCustomerService = iCustomerService;
    }

    @PostMapping("/add-customer")
    public String addCustomer(Customer customer){
        //System.out.println(customer.getKod() + " " + customer.getUnvan() + " | " + customer.getIl() + " | " + customer.getIlce() + " | " + customer.getSatisMiktari());
        iCustomerService.saveCustomer(customer);
        return "redirect:/home/get-customers";
    }

    @GetMapping("/get-customers")
    public String getCustomers(@ModelAttribute("yeniMusteriUnvan") String kayit, Model model){
        Customer customer;
        if (kayit != null){
            customer = new Customer();
            model.addAttribute("customer", customer);
        }
        List<Customer> musteriler = iCustomerService.getAllCustomers();

        model.addAttribute("musteriler", iCustomerService.getAllCustomers());
        model.addAttribute("sehir", musteriler.stream().map(x -> x.getIl()).toList());
        model.addAttribute("satis", musteriler.stream().map(x -> x.getSatisMiktari()).toList());
        return "customers";
    }

    @GetMapping("/customer/sale/{id}")
    public String satisYap(@PathVariable("id") Long id){
        Customer customer = iCustomerService.findCustomerById(id);
        long satisSayisi = Long.valueOf(customer.getSatisMiktari());
        satisSayisi++;
        customer.setSatisMiktari(String.valueOf(satisSayisi));
        iCustomerService.saveCustomer(customer);
        return "redirect:/home/get-customers";
    }
}