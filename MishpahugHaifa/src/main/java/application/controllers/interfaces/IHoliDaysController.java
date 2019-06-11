package application.controllers.interfaces;

import application.dto.forholiday.HolidayDTO;
import application.entities.HoliDayEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IHoliDaysController {
	void post(HttpHeaders httpHeaders, HttpServletRequest request, HolidayDTO[] data);
	void delete(HttpHeaders httpHeaders, HttpServletRequest request);
	void delete(HttpHeaders httpHeaders, HttpServletRequest request, Integer id);
	List<HoliDayEntity> get(HttpHeaders httpHeaders, HttpServletRequest request);
	HoliDayEntity get(HttpHeaders httpHeaders, HttpServletRequest request, Integer id);
}
