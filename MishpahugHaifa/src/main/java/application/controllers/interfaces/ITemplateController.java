package application.controllers.interfaces;

import application.entities.template.TemplateEntity;
import application.exceptions.ExceptionMishpaha;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ITemplateController {
    TemplateEntity get(@PathVariable(value = "name") String name) throws ExceptionMishpaha;

    List<TemplateEntity> getall() throws ExceptionMishpaha;

    TemplateEntity post(@RequestBody TemplateEntity templateEntity);

    void remove(@PathVariable(value = "name") String name);

    void removeall();
}
