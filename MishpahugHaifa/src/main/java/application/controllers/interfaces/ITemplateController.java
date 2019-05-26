package application.controllers.interfaces;

import application.entities.template.TemplateEntity;
import application.exceptions.ExceptionMishpaha;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ITemplateController {
    @GetMapping(value = "/{name}")
    TemplateEntity get(@PathVariable(value = "name") String name) throws ExceptionMishpaha;

    @GetMapping(value = "/")
    List<TemplateEntity> getall() throws ExceptionMishpaha;

    @PostMapping(value = "/")
    TemplateEntity post(@RequestBody TemplateEntity templateEntity);

    @DeleteMapping(value = "/{name}")
    void remove(@PathVariable(value = "name") String name);

    @DeleteMapping(value = "/")
    void removeall();
}
