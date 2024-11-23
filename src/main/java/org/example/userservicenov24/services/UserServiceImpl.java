package org.example.userservicenov24.services;

import org.example.userservicenov24.exceptions.InvalidEntryException;
import org.example.userservicenov24.exceptions.UserAlreadyPresentException;
import org.example.userservicenov24.exceptions.UserNotFoundException;
import org.example.userservicenov24.models.Token;
import org.example.userservicenov24.models.User;
import org.example.userservicenov24.repositories.TokenRepository;
import org.example.userservicenov24.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

   private final UserRepository userRepository;
   private final BCryptPasswordEncoder passwordEncoder;
   private final TokenRepository tokenRepository;


   public UserServiceImpl (UserRepository userRepository,
						   BCryptPasswordEncoder passwordEncoder,
						   TokenRepository tokenRepository) {
	  this.userRepository = userRepository;
	  this.passwordEncoder = passwordEncoder;
	  this.tokenRepository = tokenRepository;
   }

   @Override
   public User signup (
		   String firstName,
		   String lastName,
		   String email,
		   String password
   ) throws UserAlreadyPresentException {
	  Optional<User> optionalUser = userRepository.findUserByEmail (email);
	  if (optionalUser.isPresent ()) {
		 throw new UserAlreadyPresentException ("User with " + email + " already exists, please login" );
	  }

	  User user = new User ();
	  user.setFirstName (firstName);
	  user.setLastName (lastName);
	  user.setUserName ((firstName + lastName).toLowerCase ());
	  user.setEmail (email);
	  user.setPassword (passwordEncoder.encode (password));
	  user.setIsVerified (true);

	  return userRepository.save (user);
   }

   public String createToken (int maxLength) {
	  StringBuilder randomToken = new StringBuilder ();

	  String alphas = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

	  Random random = new Random ();

	  for(int i = 0; i < maxLength; i++){
		 int index = random.nextInt (maxLength);
		 randomToken.append (alphas.charAt (index));
		 if(i == 9 || i == 19){
			randomToken.append ('.');
		 }
	  }
	  return randomToken.toString ();
   }

   public String formatDate(int max){
	  Date date = new Date ();
	  SimpleDateFormat formatter = new SimpleDateFormat ("ddMMyyHHmm");
	  return max + "daysfrom" + formatter.format (date);
   }

   public Token login (String email, String password)
		   throws UserNotFoundException,
						  InvalidEntryException {

	  Optional<User> optionalUser = userRepository.findUserByEmail (email);

	  if (optionalUser.isEmpty ()) {
		 throw new UserNotFoundException ("User with " + email + " does not exists, please signup");
	  }
	  User user = optionalUser.get ();


	  if (passwordEncoder.matches (password, user.getPassword ())) {
		 Token token = new Token ();

		 token.setUser (user);
		 token.setValue (createToken (30));
		 token.setExpiry (formatDate (90));

		 return tokenRepository.save (token);

	  } else {
		 throw new InvalidEntryException ("Kindly check the entered email or password and retry");
	  }
   }
}