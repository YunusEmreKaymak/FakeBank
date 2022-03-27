package com.yunus.fakebank.dto;

import com.yunus.fakebank.Entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerDtoConverter {

    public CustomerDto convert(Customer customer){
        if (customer == null){
            return new CustomerDto();
        }
        CustomerDto dto = new CustomerDto();

        dto.setName_surname(customer.getName_surname());
        dto.setSalary(customer.getSalary());
        dto.setAccounts(customer.getAccounts());

        return dto;
    }

    public Customer dtoToEntity(CustomerDto customerDto){
        Customer customer= new Customer();

        customer.setName_surname(customerDto.getName_surname());
        customer.setSalary(customerDto.getSalary());
        customer.setAccounts(customerDto.getAccounts());

        return customer;
    }
}
