package Application.models.holyday;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Application.entities.HoliDayEntity;
import Application.repo.HolyDayRepository;
@Service
@Transactional
public class HolyDayModel implements IHolyDayModel {

    @Autowired
    HolyDayRepository holyDayRepository;

    @Override
    public List<HoliDayEntity> getByReligion(Integer religionId) {
        return null;
    }

    @Override
    public List<HoliDayEntity> getByYear(Integer year) {
        return null;
    }

    @Override
    public void updateFromServer() {

    }
}
