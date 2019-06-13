package application.controllers;

import application.controllers.interfaces.IHoliDaysController;
import application.dto.forholiday.HolidayDTO;
import application.entities.properties.HoliDayEntity;
import application.models.holyday.IHolyDayModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/holiday")
public class HoliDaysController implements IHoliDaysController {

	@Autowired
	IHolyDayModel holyDayModel;

	@Override
	@PostMapping(value = "/")
	public void post(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request,
			@RequestBody HolidayDTO[] data) {
		for (HolidayDTO s : data) {
			holyDayModel.updateFromServer(s);
		}
	}

	@Override
	@DeleteMapping(value = "/")
	public void delete(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request) {
		holyDayModel.deleteAll();
	}

	@Override
	@DeleteMapping(value = "/{id}")
	public void delete(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request,
			@PathVariable(name = "id") Integer id) {
		holyDayModel.deleteByID(id);
	}

	@Override
	@GetMapping(value = "/")
	public List<HoliDayEntity> get(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request) {
		return holyDayModel.getAll();
	}

	@Override
	@GetMapping(value = "/{id}")
	public HoliDayEntity get(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request,
			@PathVariable(name = "id") Integer id) {
		return holyDayModel.getById(id);
	}
}
