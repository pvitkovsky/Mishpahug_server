package application.repositories;

import application.entities.HoliDayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HolyDayRepository extends JpaRepository<HoliDayEntity, Integer>/*, HoliDayRepositoryCustom*/ {
    public HoliDayEntity getByName(String name);
}
