package application.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import application.utils.choices.ChoiceCategories;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/choices")
public class ChoiceController implements IChoiceController{

	private final Map<String, String[]> allowedChoices = new HashMap<>();
	
	{
		allowedChoices.put(ChoiceCategories.GENDER.toString(), new String[]{"Male", "Female", "Not Specified"});
		allowedChoices.put(ChoiceCategories.KITCHEN.toString(), new String[]{"Alcoholic", "Kosher", "Non-Alcoholic", "Vegan", "Not Specified"});
		allowedChoices.put(ChoiceCategories.MARITALSTATUS.toString(), new String[]{"Married", "Single", "Not Specified"});
		allowedChoices.put(ChoiceCategories.RELIGION.toString(), new String[]{"Religious", "Secular", "Not Specified"});
	}
	
	@GetMapping(value = "/")
	public Map<String, String[]> getAllowedChoices(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request) {
		return allowedChoices;
	}
	
}



