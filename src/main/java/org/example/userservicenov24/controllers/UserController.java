package org.example.userservicenov24.controllers;

import org.example.userservicenov24.dtos.LoginRequestDto;
import org.example.userservicenov24.dtos.LoginResponseDto;
import org.example.userservicenov24.dtos.SignUpRequestDto;
import org.example.userservicenov24.dtos.UserDto;
import org.example.userservicenov24.exceptions.GeneratedTokenCountException;
import org.example.userservicenov24.exceptions.InvalidEntryException;
import org.example.userservicenov24.exceptions.UserAlreadyPresentException;
import org.example.userservicenov24.exceptions.UserNotFoundException;
import org.example.userservicenov24.models.Token;
import org.example.userservicenov24.models.User;
import org.example.userservicenov24.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
