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
	public void post(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request,
			@RequestBody GenderEntity data) {
		genderModel.add(data);
	}

	@Override
	@PutMapping(value = "/")
	public void put(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request,
			@RequestParam(name = "id") Integer id, @RequestParam(name = "name") String name) {
		genderModel.updateName(id, name);
	}

	@Override
	@DeleteMapping(value = "/{name}")
	public void delete(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request,
			@PathVariable(name = "name") String name) {
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
	public GenderEntity get(@RequestHeader HttpHeaders httpHeaders,
			HttpServletRequest request, @PathVariable(name = "name") String name) {
		return genderModel.getByName(name);
	}

	@Override
	@GetMapping(value = "/{id}")
	public String get(@RequestHeader HttpHeaders httpHeaders,
			HttpServletRequest request, @PathVariable(name = "id") Integer id) {
		GenderEntity genderEntity = genderModel.getById(id);
		return genderEntity.getName();
	}

}
