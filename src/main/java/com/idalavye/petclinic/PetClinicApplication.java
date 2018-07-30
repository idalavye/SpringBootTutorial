package com.idalavye.petclinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = PetClinicProperties.class)
public class PetClinicApplication {

    public static void main(String[] args){
        SpringApplication.run(PetClinicApplication.class,args);
    }
}


/*
    http://localhost:8080/actuator/health

    http://localhost:8080/actuator/env => İzin verdik
*/