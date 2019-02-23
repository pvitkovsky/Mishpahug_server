package Application.repo;

import Application.entities.HoliDayEntity;
import Application.repo.custom.HoliDayRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HolyDayRepository extends JpaRepository<HoliDayEntity, Integer>/*, HoliDayRepositoryCustom*/ {
    @Query("SELECT hd from HoliDayEntity hd WHERE hd.name like '%:name%'")
    public List<HoliDayEntity> getByName(String name);
    @Query("SELECT hd from HoliDayEntity hd WHERE hd.name = :name")
    public List<HoliDayEntity> getByFullName(String name);
}
