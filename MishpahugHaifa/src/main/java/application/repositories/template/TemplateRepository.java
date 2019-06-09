package application.repositories.template;

import application.entities.template.TemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemplateRepository extends JpaRepository<TemplateEntity, Integer> {
    public TemplateEntity getByName(String name);
    //TODO удаление записи по координатам
    public void removeByName(String name);
    public Boolean existsByName(String name);
}
