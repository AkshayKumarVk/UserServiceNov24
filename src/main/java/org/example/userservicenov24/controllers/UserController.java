package org.example.userservicenov24.controllers;

import org.example.userservicenov24.dtos.*;
import org.example.userservicenov24.exceptions.*;
import org.example.userservicenov24.models.Token;
import org.example.userservicenov24.models.User;
import org.example.userservicenov24.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
   private final UserService userService;

   public UserController (UserService userService) {
	  this.userService = userService;
   }

   @PostMapping("/signup")
   public ResponseEntity<UserDto> signup (@RequestBody SignUpRequestDto requestDto)
		   throws UserNotFoundException,
						  InvalidEntryException,
						  UserAlreadyPresentException {
	  User user = userService.signup (
			  requestDto.getFirstName (),
			  requestDto.getLastName (),
			  requestDto.getEmail (),
			  requestDto.getPassword ()
	  );
	  return new ResponseEntity<> (UserDto.from (user), HttpStatus.CREATED);
   }

   @PostMapping("/login")
   public ResponseEntity<LoginResponseDto> login (@RequestBody LoginRequestDto requestDto)
		   throws UserNotFoundException,
						  InvalidEntryException,
						  GeneratedTokenCountException {

	  Token token = userService.login (
			  requestDto.getEmail (),
			  requestDto.getPassword ()
	  );

	  return new ResponseEntity<> (LoginResponseDto.from (token), HttpStatus.ACCEPTED);
   }


   @GetMapping("/logout")
   public ResponseEntity<Void> logout (@RequestBody TokenRequestDto requestDto)
		   throws ValidTokenNotFoundException {

	  userService.logout (requestDto.getTokenValue ());
	  return new ResponseEntity<> (HttpStatus.OK);
   }


   @GetMapping("/validateToken/{tokenValue}")
   public ResponseEntity<UserDto> validateToken (@PathVariable("tokenValue") String tokenValue)
		   throws ValidTokenNotFoundException {

	  try {
		 User user = userService.validateToken (tokenValue);
		 return new ResponseEntity<> (UserDto.from (user), HttpStatus.OK);
	  }
	  catch (ValidTokenNotFoundException exception){
		 return null;
	  }
   }
}
