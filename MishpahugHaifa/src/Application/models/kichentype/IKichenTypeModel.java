package Application.models.kichentype;

import Application.entities.KichenTypeItem;

import java.util.List;

public interface IKichenTypeModel {
    public KichenTypeItem getById(Integer id);
    public List<KichenTypeItem> getAll();
    public KichenTypeItem add(KichenTypeItem data);
    public KichenTypeItem update(String name);
}
