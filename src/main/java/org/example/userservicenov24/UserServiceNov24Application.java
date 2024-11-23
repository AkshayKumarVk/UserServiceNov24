package org.example.userservicenov24;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class UserServiceNov24Application {

   public static void main (String[] args) {
	  SpringApplication.run (UserServiceNov24Application.class, args);
   }

}
