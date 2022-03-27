package com.yunus.fakebank.Controller;

import com.yunus.fakebank.Controller.CustomerController;
import com.yunus.fakebank.Entity.Customer;
import com.yunus.fakebank.Service.CustomerService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerControllerTest {
    @Autowired
    CustomerService customerService;


    @Test
    void getCustomer() {
        CustomerController controller = new CustomerController(customerService);
        Customer customer = new Customer(3L, "asd@gmail.com", "5321345643", "John Doe", "asdasd", 4300);
        controller.registerNewCustomer(customer);
        Set<Customer> customers = new HashSet<>();
        customers.add(customer);
        customers.add(controller.getCustomer(3L));
        controller.deleteCustomer(3L);
        assertEquals(1,customers.size());
    }

    @Test
    void getCustomerId_Password() {
        CustomerController controller = new CustomerController(customerService);
        Customer customer = new Customer(3L, "asd@gmail.com", "5321345643", "John Doe", "asdasd", 4300);
        controller.registerNewCustomer(customer);
        Set<Customer> customers = new HashSet<>();
        customers.add(customer);
        customers.add(controller.getCustomerId_Password(3L,"asdasd"));
        controller.deleteCustomer(3L);
        assertEquals(1,customers.size());
    }

    @Test
    void getCustomerEmail_Phone() {
        CustomerController controller = new CustomerController(customerService);
        Customer customer = new Customer(3L,     "asd@gmail.com", "5321345643", "John Doe", "asdasd", 4300);
        controller.registerNewCustomer(customer);
        Set<Customer> customers = new HashSet<>();
        customers.add(customer);
        customers.add(controller.getCustomerEmail_Phone("asd@gmail.com","5321345643"));
        controller.deleteCustomer(3L);
        assertEquals(1,customers.size());
    }

    // Register customer test will be same as get customer.
    @Test
    void registerNewCustomer() {
        CustomerController controller = new CustomerController(customerService);
        Customer customer = new Customer(3L, "asd@gmail.com", "5321345643", "John Doe", "asdasd", 4300);
        controller.registerNewCustomer(customer);
        Set<Customer> customers = new HashSet<>();
        customers.add(customer);
        customers.add(controller.getCustomer(3L));
        controller.deleteCustomer(3L);
        assertEquals(1,customers.size());
    }

    @Test
    void deleteCustomer() {
        CustomerController controller = new CustomerController(customerService);
        assertThrows(new IllegalStateException().getClass(),
                () -> controller.deleteCustomer(3L));
    }

    @Test
    void updateCustomer() {
        CustomerController controller = new CustomerController(customerService);
        Customer customer = new Customer(3L, "asd@gmail.com", "5321345643", "John Doe", "asdasd", 4300);
        controller.registerNewCustomer(customer);
        //changing customer object
        customer.setName_surname("John Boe");
        customer.setEmail("asb@gmail.com");
        //changing database object
        controller.updateCustomer(3L,"John Boe","asb@gmail.com");
        // They must be same
        Set<Customer> customers = new HashSet<>();
        customers.add(customer);
        customers.add(controller.getCustomer(3L));
        controller.deleteCustomer(3L);
        assertEquals(1,customers.size());
    }
}