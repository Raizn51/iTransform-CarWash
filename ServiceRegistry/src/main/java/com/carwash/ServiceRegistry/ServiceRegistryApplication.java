package com.carwash.ServiceRegistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceRegistryApplication.class, args);
	}

}

echo "# iTransform-CarWash" >> README.md
git init
git add README.md
git commit -m "[Gourav Raikwar] feat . initial commit"
git branch -M main
git remote add origin https://github.com/Raizn51/iTransform-CarWash.git
git push -u origin main