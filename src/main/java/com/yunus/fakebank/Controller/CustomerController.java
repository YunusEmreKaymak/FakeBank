package com.yunus.fakebank.Controller;

import com.yunus.fakebank.Entity.Customer;
import com.yunus.fakebank.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@CrossOrigin("*")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("/{ssn}")
    public Customer getCustomer(@PathVariable("ssn") Long ssn) {
        return customerService.getCustomer(ssn);
    }

    @GetMapping("/{ssn}/{password}")
    public Customer getCustomerId_Password(@PathVariable("ssn") Long ssn, @PathVariable("password") String password) {
        return customerService.getCustomerId_Password(ssn, password);
    }

    @GetMapping("/exist/{email}/{phone}")
    public Customer getCustomerEmail_Phone(@PathVariable("email") String email, @PathVariable("phone") String phone) {
        return customerService.getCustomerEmail_Phone(email, phone);
    }

    @PostMapping
    public void registerNewCustomer(@RequestBody Customer customer) {
        customerService.addCustomer(customer);
    }

    @DeleteMapping("/{ssn}")
    public void deleteCustomer(@PathVariable("ssn") Long ssn) {
        customerService.deleteCustomer(ssn);
    }

    @PutMapping("/{ssn}/{nameSurname}/{email}")
    public void updateCustomer(@PathVariable("ssn") Long ssn, @PathVariable("nameSurname") String nameSurname, @PathVariable("email") String email) {
        customerService.updateCustomer(ssn, nameSurname, email);
    }

}
