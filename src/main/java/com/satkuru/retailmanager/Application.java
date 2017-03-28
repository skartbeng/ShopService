package com.satkuru.retailmanager;


import com.satkuru.retailmanager.repository.ShopRepository;
import com.satkuru.retailmanager.repository.ShopRepositoryImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Application {

    public static void main( String... args )
    {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ShopRepository commandLineRunner(ApplicationContext ctx) {
        return new ShopRepositoryImpl();
    }
}
