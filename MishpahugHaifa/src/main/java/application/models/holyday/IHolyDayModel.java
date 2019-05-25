package application.models.holyday;

import java.util.List;

import application.dto.forholiday.HolidayDTO;
import application.entities.HoliDayEntity;

public interface IHolyDayModel {
    public HoliDayEntity getByReligion(Integer religionId);

    public List<HoliDayEntity> getByYear(Integer year);

    public List<HoliDayEntity> getAll();

    public HoliDayEntity getByName(String name);

    public HoliDayEntity getById(Integer id);

    public void deleteByID(Integer id);

    public void deleteAll();

    public void updateFromServer(HolidayDTO dto);
}
