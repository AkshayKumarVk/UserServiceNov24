package org.example.userservicenov24.dtos;

import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.example.userservicenov24.models.Token;
import org.example.userservicenov24.models.User;

import java.util.Date;

@Getter
@Setter

public class LoginResponseDto {
   private String email;
   private String value;
   private Date expiry;


   public static LoginResponseDto from (Token token) {

	  LoginResponseDto responseDto = new LoginResponseDto ();

	  responseDto.setEmail (token.getUser ().getEmail ());
	  responseDto.setValue (token.getValue());
	  responseDto.setExpiry (token.getExpiry ());

	  return responseDto;
   }
}