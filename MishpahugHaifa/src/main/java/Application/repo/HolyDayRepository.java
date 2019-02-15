package Application.repo;

import Application.entities.HoliDayEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HolyDayRepository extends JpaRepository<HoliDayEntity, Integer>{}
