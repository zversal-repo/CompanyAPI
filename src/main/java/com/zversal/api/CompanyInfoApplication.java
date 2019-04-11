package com.zversal.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * The main spring boot application which will start up a web container and wire
 * up all the required beans.
 * 
 * @author Bhupinder
 *
 */
@SpringBootApplication
public class CompanyInfoApplication {
    public static void main(String[] args) {
	SpringApplication.run(CompanyInfoApplication.class, args);
    }
}
