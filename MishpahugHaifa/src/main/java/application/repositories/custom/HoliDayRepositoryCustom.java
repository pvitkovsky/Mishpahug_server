package application.repositories.custom;

import application.entities.properties.HoliDayEntity;

import java.util.List;

public interface HoliDayRepositoryCustom {
    public List<HoliDayEntity> loadFromServer();
}
