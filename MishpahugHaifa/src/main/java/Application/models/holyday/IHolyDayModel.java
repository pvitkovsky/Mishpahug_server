package Application.models.holyday;

import Application.entities.HoliDayEntity;

import java.util.List;

public interface IHolyDayModel {
    public List<HoliDayEntity> getByReligion(Integer religionId);
    public List<HoliDayEntity> getByYear(Integer year);
    public void updateFromServer();
}
