package application.controllers;

import application.controllers.interfaces.IKichenTypeController;
import application.entities.properties.KitchenTypeEntity;
import application.models.properties.kichentype.IKichenTypeModel;
import application.utils.converter.IConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/kichentype")
public class KichenTypeController implements IKichenTypeController {
	@Autowired
	IKichenTypeModel kichenTypeModel;

	@Override
	@PostMapping(value = "/")
	public void post(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request,
			@RequestBody KitchenTypeEntity data) {
		kichenTypeModel.add(data);
	}

	@Override
	@PutMapping(value = "/")
	public void put(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request,
			@RequestParam(name = "id") Integer id, @RequestParam(name = "name") String name) {
		kichenTypeModel.updateName(id, name);
	}

	@Override
	@DeleteMapping(value = "/")
	public void delete(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request) {
		kichenTypeModel.deleteAll();
	}

	@Override
	@DeleteMapping(value = "/{name}")
	public void delete(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request,
			@PathVariable(name = "name") String name) {
		kichenTypeModel.deleteByName(name);
	}

	@Override
	@GetMapping(value = "/")
	public List<String> get(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request) {
		return IConverter.PropertyToStringList(kichenTypeModel.getAll());
	}

	@Override
	@GetMapping(value = "/{id}")
	public String get(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request,
			@PathVariable(name = "id", required = false) Integer id) {
		return kichenTypeModel.getById(id).getName();
	}

}
