package application.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;

/*
 * Convention for the AOP to work:
 * HttpHeaders is the first argument, HttpServletRequest is the second
 */	
public interface IChoiceController {
	
	public Map<String, String[]> getAllowedChoices(HttpHeaders httpHeaders, HttpServletRequest request);
	
}
