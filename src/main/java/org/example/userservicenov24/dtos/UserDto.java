package org.example.userservicenov24.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.userservicenov24.models.User;

@Getter
@Setter

public class UserDto {
   private String username;
   private String email;
   private String status;


   public static UserDto from(User user){
	  UserDto userDto = new UserDto ();

	  userDto.setUsername (user.getUserName ());
	  userDto.setEmail (user.getEmail ());
	  userDto.setStatus ("Created");

	  return userDto;
   }
}
