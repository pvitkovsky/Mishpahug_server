package application.models.holyday;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.entities.HoliDayEntity;
import application.repositories.HolyDayRepository;
@Service
@Transactional
public class HolyDayModel implements IHolyDayModel {

    @Autowired
    HolyDayRepository holyDayRepository;

    @Override
    public HoliDayEntity getByReligion(Integer religionId) {
        return holyDayRepository.getOne(religionId);
    }

    @Override
    public List<HoliDayEntity> getByYear(Integer year) {
        return null;//TODO
    }

    @Override
    public List<HoliDayEntity> getAll(){
        return holyDayRepository.findAll();
    }

    @Override
    public HoliDayEntity getByName(String name){
        return holyDayRepository.getByFullName(name);
    }

    @Override
    public void updateFromServer() {
        //TODO
    }
}
