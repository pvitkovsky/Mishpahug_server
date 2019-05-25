package application.repositories.custom;

import java.util.List;

import application.entities.HoliDayEntity;

public interface HoliDayRepositoryCustom {
    public List<HoliDayEntity> loadFromServer();
}
