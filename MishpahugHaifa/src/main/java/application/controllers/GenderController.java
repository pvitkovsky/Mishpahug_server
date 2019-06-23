package application.controllers;

import application.controllers.interfaces.IGenderController;
import application.dto.PropertyDTO;
import application.entities.properties.GenderEntity;
import application.models.properties.gender.IGenderModel;
import application.utils.converter.IConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
		genderModel.delete(name);
	}

	@Override
	@DeleteMapping(value = "/{id}")
	public void delete(HttpHeaders httpHeaders, HttpServletRequest request,@PathVariable(name = "id") Integer id) {
		genderModel.delete(id);
	}

	@Override
	@DeleteMapping(value = "/")
	public void delete(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request) {
		genderModel.deleteAll();
	}

	@Override
	@GetMapping(value = "/")
	public List<PropertyDTO> get(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request) {
		return IConverter.PropertyToDTOList(genderModel.getAll());
	}

	@Override
	@GetMapping(value = "/name/{name}")
	public GenderEntity get(@RequestHeader HttpHeaders httpHeaders,
			HttpServletRequest request, @PathVariable(name = "name") String name) {
		return genderModel.getByName(name);
	}

	@Override
	@GetMapping(value = "/{id}")
	public PropertyDTO get(@RequestHeader HttpHeaders httpHeaders,
			HttpServletRequest request, @PathVariable(name = "id") Integer id) {
		GenderEntity genderEntity = genderModel.getById(id);
		return new PropertyDTO(genderEntity.getId(),genderEntity.getName());
	}
}