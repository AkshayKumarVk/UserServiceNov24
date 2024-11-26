package org.example.userservicenov24.services;

import org.example.userservicenov24.exceptions.*;
import org.example.userservicenov24.models.Token;
import org.example.userservicenov24.models.User;

public interface UserService {
   User signup (
		   String firstName,
		   String secondName,
		   String email,
		   String password
   ) throws UserAlreadyPresentException;

   Token login (
		   String email,
		   String password
   ) throws UserNotFoundException,
					InvalidEntryException,
					GeneratedTokenCountException;

   void logout (String tokenValue) throws ValidTokenNotFoundException;

   User validateToken(String tokenValue) throws ValidTokenNotFoundException;
}
