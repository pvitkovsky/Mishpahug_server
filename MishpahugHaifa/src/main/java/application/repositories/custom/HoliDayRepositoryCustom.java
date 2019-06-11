package application.repositories.custom;

import application.entities.HoliDayEntity;

import java.util.List;

public interface HoliDayRepositoryCustom {
    public List<HoliDayEntity> loadFromServer();
}
