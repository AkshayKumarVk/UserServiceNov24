package org.example.userservicenov24.securityconfigs.services;

import org.example.userservicenov24.models.User;
import org.example.userservicenov24.repositories.UserRepository;
import org.example.userservicenov24.securityconfigs.models.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService  {

   private UserRepository userRepository;

   public CustomUserDetailsService (UserRepository userRepository) {
	  this.userRepository = userRepository;
   }


   @Override
   public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {

	  Optional<User> optionalUser = userRepository.findUserByEmail (username);

	  if (optionalUser.isEmpty ()) {
		 throw new UsernameNotFoundException ("User with " + username + " not found");
	  }

	  User user = optionalUser.get ();

//	  we got user with user name  now we need to convert this user to user to user details class


	  return new CustomUserDetails (user);
   }
}
