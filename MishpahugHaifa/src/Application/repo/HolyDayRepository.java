package Application.repo;

import Application.entities.HolyDayItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HolyDayRepository extends JpaRepository<HolyDayItem, Integer>{}
