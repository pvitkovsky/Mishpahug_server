package application.models.template;

import application.entities.template.TemplateEntity;
import application.repositories.template.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class TemlateModel implements ITemplateModel {

    @Autowired
    TemplateRepository templateRepository;

    @Override
    public TemplateEntity getByName(String name) {
        return templateRepository.getByName(name);
    }

    @Override
    public void clear() {

    }

    @Override
    public void remove(String name) {
        templateRepository.removeByName(name);
    }

    @Override
    public void removedata(String name) {
        //TODO
    }

    @Override
    public void update(String oldName, String newName) {
        TemplateEntity templateEntity = templateRepository.getByName(oldName);
        templateRepository.removeByName(oldName);
        templateEntity.setName(newName);
        templateRepository.save(templateEntity);
    }
}
