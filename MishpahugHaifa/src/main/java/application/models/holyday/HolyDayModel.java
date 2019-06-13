package application.models.holyday;

import application.dto.forholiday.HolidayDTO;
import application.entities.properties.HoliDayEntity;
import application.repositories.HolyDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

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
    public List<HoliDayEntity> getAll() {
        return holyDayRepository.findAll();
    }

    @Override
    public HoliDayEntity getByName(String name) {
        return holyDayRepository.getByName(name);
    }

    @Override
    public HoliDayEntity getById(Integer id) {
        return holyDayRepository.getOne(id);
    }

    @Override
    public void deleteByID(Integer id) {
        holyDayRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        holyDayRepository.deleteAll();
    }

    @Override
    public void updateFromServer(HolidayDTO dto) {
        System.out.println(dto);
        HoliDayEntity holiDayEntity = new HoliDayEntity();
        holiDayEntity.setName(dto.getName());
        holiDayEntity.setDescription(dto.getDescription());
        holiDayEntity.setDate(LocalDate.parse(dto.getDate()));
        holyDayRepository.save(holiDayEntity);
    }
}
