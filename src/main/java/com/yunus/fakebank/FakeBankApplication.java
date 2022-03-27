package com.yunus.fakebank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.yunus.fakebank")
public class FakeBankApplication {

    public static void main(String[] args) {
        SpringApplication.run(FakeBankApplication.class, args);
    }

}
