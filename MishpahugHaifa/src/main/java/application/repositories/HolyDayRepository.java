package application.repositories;

import application.entities.HoliDayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HolyDayRepository extends JpaRepository<HoliDayEntity, Integer>/*, HoliDayRepositoryCustom*/ {
    @Query("SELECT hd from HoliDayEntity hd WHERE hd.name LIKE :name")
    public HoliDayEntity getByName(String name);
}
