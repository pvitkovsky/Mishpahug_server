package Application.models.holyday;

import Application.entities.HolyDayItem;

import java.util.List;

public interface IHolyDayModel {
    public List<HolyDayItem> getByReligion(Integer religionId);
    public List<HolyDayItem> getByYear(Integer year);
    public void updateFromServer();
}
