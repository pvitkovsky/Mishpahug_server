package application.models.template;

import application.entities.template.TemplateEntity;

public interface ITemplateModel {
    public TemplateEntity getByName(String name);
    public void clear();
    public void remove(String name);
    public void removedata(String name);
    public void update(String oldName, String newName);

}
