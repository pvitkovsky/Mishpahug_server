package application.controllers;

import java.util.List;

import application.controllers.interfaces.IGenderController;
import application.exceptions.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import application.entities.GenderEntity;
import application.models.gender.IGenderModel;
import application.utils.converter.IConverter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/gender")
public class GenderController implements IGenderController {
	@Autowired
	IGenderModel genderModel;

	@Override
	@PostMapping(value = "/")
	public void post(@RequestBody GenderEntity data, @RequestHeader HttpHeaders httpHeaders,
			HttpServletRequest request) {

	}

	@Override
	@PutMapping(value = "/")
	public void put(@RequestParam(name = "id") Integer id, @RequestParam(name = "name") String name,
			@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request) {

		genderModel.updateName(id, name);
	}

	@Override
	@DeleteMapping(value = "/{name}")
	public void delete(@PathVariable(name = "name") String name, @RequestHeader HttpHeaders httpHeaders,
			HttpServletRequest request) {

		genderModel.deleteByName(name);
	}

	@Override
	@DeleteMapping(value = "/")
	public void delete(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request) {

		genderModel.deleteAll();

	}

	@Override
	@GetMapping(value = "/")
	public List<String> get(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request) {

		return IConverter.PropertyToStringList(genderModel.getAll());
	}

	@Override
	@GetMapping(value = "/name/{name}")
	public GenderEntity get(@PathVariable(name = "name") String name, @RequestHeader HttpHeaders httpHeaders,
			HttpServletRequest request) {

		return genderModel.getByName(name);

	}

	@Override
	@GetMapping(value = "/{id}")
	public String get(@PathVariable(name = "id") Integer id, @RequestHeader HttpHeaders httpHeaders,
			HttpServletRequest request) {

		GenderEntity genderEntity = genderModel.getById(id);
		return genderEntity.getName();
	}

}
