package Application.models.freeback;

import Application.entities.FreeBackItem;
import Application.repo.FreeBackRepository;

import java.util.List;

public class FreeBackModel implements IFreeBackModel {

    FreeBackRepository freeBackRepository;

    @Override
    public List<FreeBackItem> getByEvent(Integer eventId) {
        return null;
    }

    @Override
    public List<FreeBackItem> getByUser(Integer userId) {
        return null;
    }

    @Override
    public List<FreeBackItem> removeByUser(Integer userId) {
        return null;
    }

    @Override
    public List<FreeBackItem> removeByEvent(Integer eventId) {
        return null;
    }
}
