package org.example.userservicenov24.services;

import org.apache.commons.text.RandomStringGenerator;
import org.example.userservicenov24.exceptions.GeneratedTokenCountException;
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
import java.util.Calendar;
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
		 throw new UserAlreadyPresentException ("User with " + email + " already exists, please login");
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

	  for (int i = 0; i < maxLength; i++) {
		 int index = random.nextInt (maxLength);
		 randomToken.append (alphas.charAt (index));
		 if (i == 9 || i == 19) {
			randomToken.append ('.');
		 }
	  }
	  return randomToken.toString ();
   }

   public String formatDate (int max) {
	  Date date = new Date ();
	  SimpleDateFormat formatter = new SimpleDateFormat ("ddMMyyHHmm");
	  return max + "daysfrom" + formatter.format (date);
   }

   public Date calculateExpiry (int days) {
	  Calendar calendar = Calendar.getInstance ();
	  calendar.add (Calendar.DAY_OF_MONTH, 30);
	  return calendar.getTime ();
   }

   public String generateRandomAlphaNumeric (int length) {
	  RandomStringGenerator randomStringGenerator = new RandomStringGenerator.Builder ()
															.withinRange ('0', 'z')
															.filteredBy (Character::isLetterOrDigit)
															.build ();
	  return randomStringGenerator.generate (length);
   }


   public Token login (String email, String password)
		   throws UserNotFoundException,
						  InvalidEntryException,
						  GeneratedTokenCountException {
	  /*
	  1. Find user in repo by email.
	  2. If the user is null, throw User didn't find exception.
	  3. Check the give email and password matches with repo email and password.
	  4. If no, throw invalid credentials' error.
	  5. Generate token for the user.
	  6. Verify only 2 tokens are valid for one user.
	   */

	  Optional<User> optionalUser = userRepository.findUserByEmail (email);

	  if (optionalUser.isEmpty ()) {
		 throw new UserNotFoundException ("User with " + email + " is not existing please sign");
	  }

	  User user = optionalUser.get ();

	  if (tokenRepository.tokenCount (user) > 2) {
		 throw new GeneratedTokenCountException ("Please logout form any of the 2 logins.");
	  }
//	  Check user password matches with raw or not.

	  if (!passwordEncoder.matches (password, user.getPassword ())) {
		 throw new InvalidEntryException ("Invalid entry please check email and password.");
	  }

	  Token token = new Token ();
	  token.setUser (user);
	  token.setExpiry (calculateExpiry (30));
	  token.setValue (generateRandomAlphaNumeric (25));

	  return tokenRepository.save (token);
   }
}