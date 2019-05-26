package application.controllers;

import application.entities.template.TemplateEntity;
import application.exceptions.ExceptionMishpaha;
import application.models.template.ITemplateModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/template")
public class TemplateController {
    @Autowired
    ITemplateModel templateModel;


    @GetMapping(value = "/{name}")
    public TemplateEntity get(@PathVariable(value = "name") String name) throws ExceptionMishpaha {
        return templateModel.getByName(name);
    }

    @GetMapping(value = "/")
    public List<TemplateEntity> getall() throws ExceptionMishpaha {
        return templateModel.getAll();
    }

    @PostMapping(value = "/")
    public TemplateEntity post(@RequestBody TemplateEntity templateEntity){
        return templateModel.add(templateEntity);
    }
    @DeleteMapping(value = "/{name}")
    public void remove(@PathVariable(value = "name") String name){
        templateModel.remove(name);
    }

    @DeleteMapping(value = "/")
    public void removeall(){
        templateModel.clear();
    }
}
