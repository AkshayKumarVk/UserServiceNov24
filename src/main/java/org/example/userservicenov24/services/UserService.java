package org.example.userservicenov24.services;

import org.example.userservicenov24.exceptions.InvalidEntryException;
import org.example.userservicenov24.exceptions.UserAlreadyPresentException;
import org.example.userservicenov24.exceptions.UserNotFoundException;
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
					InvalidEntryException;
}
