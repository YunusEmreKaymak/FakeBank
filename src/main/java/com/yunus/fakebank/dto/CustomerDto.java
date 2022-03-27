package com.yunus.fakebank.dto;

import com.yunus.fakebank.Entity.Account;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class CustomerDto {
    private String name_surname;
    private int salary;
    private List<Account> accounts=new ArrayList<>();
}
