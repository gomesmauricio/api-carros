package com.example.carros.api.exception;

import com.example.carros.domain.service.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> ObjectNotFound(ObjectNotFoundException ex, HttpServletRequest request) {
		StandardError error = new StandardError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), ex.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity accessDenied(AccessDeniedException ex, HttpServletRequest request) {
		StandardError error = new StandardError(LocalDateTime.now(), HttpStatus.FORBIDDEN.value(), ex.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);

	}


}
