package Application.models.freeback;

import Application.entities.FreeBackItem;

import java.util.List;

public interface IFreeBackModel {
    public List<FreeBackItem> getByEvent(Integer eventId);
    public List<FreeBackItem> getByUser(Integer userId);
    public List<FreeBackItem> removeByUser(Integer userId);
    public List<FreeBackItem> removeByEvent(Integer eventId);
}
