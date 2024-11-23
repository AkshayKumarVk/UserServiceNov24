package org.example.userservicenov24.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;

@Service
public class SecurityConfig {
   @Bean
   public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
	  http
			  .authorizeHttpRequests ((requests) -> {
						 try {
							requests
									.anyRequest ().permitAll ()
									.and ().cors ().disable ()
									.csrf ().disable ();
						 } catch (Exception e) {
							throw new RuntimeException (e);
						 }
					  }
			  );

	  return http.build ();
   }
}