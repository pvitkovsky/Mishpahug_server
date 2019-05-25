package application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import application.entities.HoliDayEntity;

public interface HolyDayRepository extends JpaRepository<HoliDayEntity, Integer>/*, HoliDayRepositoryCustom*/ {
    public HoliDayEntity getByName(String name);
}
