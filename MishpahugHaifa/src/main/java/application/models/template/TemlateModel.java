package application.models.template;

import application.entities.template.TemplateEntity;
import application.exceptions.EntityExistsException;
import application.exceptions.NotFoundEntityException;
import application.repositories.template.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TemlateModel implements ITemplateModel {

    @Autowired
    TemplateRepository templateRepository;

    @Override
    public TemplateEntity getByName(String name) {
        if (!templateRepository.existsByName(name)) throw new NotFoundEntityException("");
        return templateRepository.getByName(name);
    }

    @Override
    public void clear() {

    }

    @Override
    public void remove(String name) {
        if (!templateRepository.existsByName(name)) throw new NotFoundEntityException("");
        templateRepository.removeByName(name);
    }

    @Override
    public void update(String oldName, String newName) {
        if (templateRepository.existsByName(newName)) throw new EntityExistsException("");
        TemplateEntity templateEntity = templateRepository.getByName(oldName);
        templateRepository.removeByName(oldName);
        templateEntity.setName(newName);
        templateRepository.save(templateEntity);
    }

    @Override
    public List<TemplateEntity> getAll() {
        return templateRepository.findAll();
    }

    @Override
    public TemplateEntity add(TemplateEntity templateEntity) {
        return templateRepository.save(templateEntity);
    }
}
