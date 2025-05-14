package com.carwash.auth_service;

import com.carwash.auth_service.model.AuthUser;
import com.carwash.auth_service.model.Role;
import com.carwash.auth_service.repository.AuthUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
@EntityScan(basePackages = "com.carwash.auth_service.model")
@EnableJpaRepositories(basePackages = "com.carwash.auth_service.repository")
public class AuthenticateServiceApplication implements CommandLineRunner {


	@Autowired(required = true)
	private AuthUserDAO authUserDAO;


    public static void main(String[] args) {
		SpringApplication.run(com.carwash.auth_service.AuthenticateServiceApplication.class, args);
	}

	@Override
	public void run(String... args) {
		AuthUser authUser = authUserDAO.findByRole(Role.ADMIN);

		if(authUser == null)
		{
			authUser= new AuthUser();

			authUser.setUsername("Admin");
			authUser.setFullName("Admin");
			authUser.setEmail("Admin@gmail.com");
			authUser.setRole(Role.ADMIN);
			authUser.setPassword(new BCryptPasswordEncoder().encode("admin"));

			authUserDAO.save(authUser);

		}

	}
}