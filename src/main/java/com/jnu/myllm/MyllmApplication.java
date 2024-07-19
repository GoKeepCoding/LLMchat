package com.jnu.myllm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@ServletComponentScan
public class MyllmApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyllmApplication.class, args);
    }

}
