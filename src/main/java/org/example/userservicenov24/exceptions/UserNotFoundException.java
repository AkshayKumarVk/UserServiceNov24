package org.example.userservicenov24.exceptions;

import org.example.userservicenov24.models.User;

public class UserNotFoundException extends  Exception{
   public UserNotFoundException(String message){
	  super(message);
   }
}
