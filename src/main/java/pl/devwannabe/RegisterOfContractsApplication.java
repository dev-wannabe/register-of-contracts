package pl.devwannabe;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import pl.devwannabe.registerofcontracts.configuration.SecurityConfig;
import pl.devwannabe.registerofcontracts.controller.MainController;

@SpringBootApplication
//@ComponentScan(basePackageClasses = MainController.class)
//@ComponentScan
public class RegisterOfContractsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegisterOfContractsApplication.class, args);
	}
}
