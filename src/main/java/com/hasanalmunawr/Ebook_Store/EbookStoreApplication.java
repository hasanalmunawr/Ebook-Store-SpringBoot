package com.hasanalmunawr.Ebook_Store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class EbookStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbookStoreApplication.class, args);
    }

}