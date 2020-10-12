package com.webscraping.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.webscraping.dto.ResponseDTO;

@ControllerAdvice
public class WebScrappingExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NoWebsiteFoundException.class)
	public final ResponseEntity<ResponseDTO> noWebsiteFoundException(Exception ex) {
		return new ResponseEntity<>(new ResponseDTO(ex.toString()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(WebScrappingException.class)
	public final ResponseEntity<ResponseDTO> webScrappingException(Exception ex) {
		return new ResponseEntity<>(new ResponseDTO(ex.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ResponseDTO> handleException(Exception ex) {
		return new ResponseEntity<>(new ResponseDTO(ex.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
