package org.example.userservicenov24.advices;

import org.example.userservicenov24.dtos.ErrorDto;
import org.example.userservicenov24.exceptions.GeneratedTokenCountException;
import org.example.userservicenov24.exceptions.InvalidEntryException;
import org.example.userservicenov24.exceptions.UserAlreadyPresentException;
import org.example.userservicenov24.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.View;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

   private final View error;

   public ControllerAdvice (View error) {
	  this.error = error;
   }

   @ExceptionHandler
   ResponseEntity<ErrorDto> handleInvalidEntryException (InvalidEntryException exception) {

	  ErrorDto errorDto = new ErrorDto ();
	  errorDto.setMessage (exception.getMessage ());

	  return new ResponseEntity<> (errorDto, HttpStatus.UNAUTHORIZED);
   }

   @ExceptionHandler
   ResponseEntity<ErrorDto> handleUserNotFoundException (UserNotFoundException exception) {

	  ErrorDto errorDto = new ErrorDto ();

	  errorDto.setMessage (exception.getMessage ());

	  return new ResponseEntity<> (errorDto, HttpStatus.NOT_FOUND);
   }

   @ExceptionHandler
   ResponseEntity<ErrorDto> handleUserAlreadyPresentException (UserAlreadyPresentException exception) {
	  ErrorDto dto = new ErrorDto ();

	  dto.setMessage (exception.getMessage ());
	  return new ResponseEntity<> (dto, HttpStatus.ALREADY_REPORTED);
   }

   @ExceptionHandler
   ResponseEntity<ErrorDto> handleGeneratedTokenCountException (GeneratedTokenCountException generatedTokenCountException) {
	  ErrorDto errorDto = new ErrorDto ();

	   errorDto.setMessage (generatedTokenCountException.getMessage ());
	   return new ResponseEntity<> (errorDto, HttpStatus.NOT_ACCEPTABLE);
   }
}
