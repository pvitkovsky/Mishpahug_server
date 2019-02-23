package Application.repo.custom;

import Application.entities.HoliDayEntity;

import java.util.List;

public interface HoliDayRepositoryCustom {
    public List<HoliDayEntity> loadFromServer();
}
