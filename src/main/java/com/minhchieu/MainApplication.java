package com.minhchieu;

import com.minhchieu.config.ServiceInterceptorAppConfig;
import com.minhchieu.config.SpringSecurityConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.minhchieu.controller", "com.minhchieu.orm", "com.minhchieu.service", "com.minhchieu.serviceimpl", "com.minhchieu.mapstruct.mapper", "com.minhchieu.exception", "com.minhchieu.interceptor"})
@EnableJpaRepositories("com.minhchieu.orm")
@EntityScan(basePackages = { "com.minhchieu.model" })
@Import({SpringSecurityConfiguration.class, ServiceInterceptorAppConfig.class})
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MainApplication.class);
        app.setLazyInitialization(true);
        app.run(args);
//        SpringApplication.run(MainApplication.class, args);
        System.out.println("Server started! Enjoy at http://127.0.0.1:8080");
    }


}
