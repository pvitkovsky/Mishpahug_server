package application.models.holyday;

import application.dto.forholiday.HolidayDTO;
import application.entities.HoliDayEntity;

import java.net.URISyntaxException;
import java.util.List;

public interface IHolyDayModel {
    public HoliDayEntity getByReligion(Integer religionId);
    public List<HoliDayEntity> getByYear(Integer year);
    public List<HoliDayEntity> getAll();
    public HoliDayEntity getByName(String name);
    public void updateFromServer(HolidayDTO dto);
}
