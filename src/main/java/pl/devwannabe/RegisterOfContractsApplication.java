package pl.devwannabe;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.naming.Context;
import java.util.Arrays;



@SpringBootApplication

public class RegisterOfContractsApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(RegisterOfContractsApplication.class, args);

//		System.out.println("Inspect the beans provided by Spring Boot:");
//		String[] beanNames = context.getBeanDefinitionNames();
//		Arrays.sort(beanNames);
//		for (String beanName : beanNames) {
//			System.out.println(beanName);
//		}
		System.out.println("MainController  "+ context.
				containsBeanDefinition("MainController"));
	}

}
