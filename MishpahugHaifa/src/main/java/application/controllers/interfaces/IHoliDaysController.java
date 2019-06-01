package application.controllers.interfaces;

import application.dto.forholiday.HolidayDTO;
import application.entities.HoliDayEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IHoliDaysController {

	void post(HolidayDTO[] data, HttpHeaders httpHeaders, HttpServletRequest request);

	void delete(HttpHeaders httpHeaders, HttpServletRequest request);

	void delete(Integer id, HttpHeaders httpHeaders, HttpServletRequest request);

	List<HoliDayEntity> get(HttpHeaders httpHeaders, HttpServletRequest request);

	HoliDayEntity get(Integer id, HttpHeaders httpHeaders, HttpServletRequest request);
}
