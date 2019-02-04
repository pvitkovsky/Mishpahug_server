package Application.models.holyday;

import Application.entities.HolyDayItem;
import Application.repo.HolyDayRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class HolyDayModel implements IHolyDayModel {

    @Autowired
    HolyDayRepository holyDayRepository;

    @Override
    public List<HolyDayItem> getByReligion(Integer religionId) {
        return null;
    }

    @Override
    public List<HolyDayItem> getByYear(Integer year) {
        return null;
    }

    @Override
    public void updateFromServer() {

    }
}
