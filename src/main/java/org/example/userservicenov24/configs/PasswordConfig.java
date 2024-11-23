package org.example.userservicenov24.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordConfig {
   @Bean
   BCryptPasswordEncoder getBcryptPasswordEncoder () {
	  return new BCryptPasswordEncoder ();
   }
}
