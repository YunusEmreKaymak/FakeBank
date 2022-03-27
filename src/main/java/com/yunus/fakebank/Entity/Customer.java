package com.yunus.fakebank.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor

@ToString
@Entity
@Table(
        name = "customer",
        uniqueConstraints = {
                @UniqueConstraint(name = "customer_email_unique", columnNames = "email"),
                @UniqueConstraint(name = "customer_phone_unique", columnNames = "phone")
        }
)

public class Customer {
    public Customer(Long ssn, String email, String phone, String name_surname, String password, int salary) {
        this.ssn = ssn;
        this.email = email;
        this.phone = phone;
        this.name_surname = name_surname;
        this.password = password;
        this.salary = salary;
    }

    @Id
    @Column(name = "ssn")
    private Long ssn;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "phone", nullable = false)
    private String phone;
    @Column(nullable = false)
    private String name_surname;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private int salary;
    // Referenced means here
    @OneToMany
    @JoinColumn(name = "ssn", referencedColumnName = "ssn")
    @Transient
    private List<Account> accounts = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return salary == customer.salary &&
                ssn.equals(customer.ssn) &&
                email.equals(customer.email) &&
                phone.equals(customer.phone) &&
                name_surname.equals(customer.name_surname) &&
                password.equals(customer.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ssn, email, phone, name_surname, password, salary);
    }
}
