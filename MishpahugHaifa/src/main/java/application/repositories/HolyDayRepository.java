package application.repositories;

import application.entities.properties.HoliDayEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HolyDayRepository extends JpaRepository<HoliDayEntity, Integer>/*, HoliDayRepositoryCustom*/ {
    public HoliDayEntity getByName(String name);
}
