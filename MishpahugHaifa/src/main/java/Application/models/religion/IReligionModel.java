package Application.models.religion;

import Application.entities.ReligionItem;

import java.util.List;

public interface IReligionModel {
    public ReligionItem getById(Integer id);
    public List<ReligionItem> getAll();
    public ReligionItem add(ReligionItem data);
    public ReligionItem update(String name);
}
