package application.models.holyday;

import application.dto.forholiday.HolidayDTO;
import application.entities.HoliDayEntity;

import java.util.List;

public interface IHolyDayModel {
    public HoliDayEntity getByReligion(Integer religionId);
    public List<HoliDayEntity> getByYear(Integer year);

    List<HoliDayEntity> getAll();

    HoliDayEntity getByName(String name);

    public void updateFromServer(HolidayDTO dto);
}
