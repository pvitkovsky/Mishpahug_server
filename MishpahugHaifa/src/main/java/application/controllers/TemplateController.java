package application.controllers;

import application.controllers.interfaces.ITemplateController;
import application.entities.template.TemplateEntity;
import application.models.template.ITemplateModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/template")
public class TemplateController implements ITemplateController {
    @Autowired
    ITemplateModel templateModel;


    @Override
    @GetMapping(value = "/{name}")
    public TemplateEntity get(@RequestHeader HttpHeaders httpHeaders,
                              HttpServletRequest request, @PathVariable(value = "name") String name){
        return templateModel.getByName(name);
    }

    @Override
    @GetMapping(value = "/")
    public List<TemplateEntity> getall(@RequestHeader HttpHeaders httpHeaders,
                                       HttpServletRequest request){
        return templateModel.getAll();
    }

    @Override
    @PostMapping(value = "/")
    public TemplateEntity post(@RequestHeader HttpHeaders httpHeaders,
                               HttpServletRequest request, @RequestBody TemplateEntity templateEntity){
        return templateModel.add(templateEntity);
    }
    
    @Override
    @DeleteMapping(value = "/{name}")
    public void remove(@RequestHeader HttpHeaders httpHeaders,
                       HttpServletRequest request, @PathVariable(value = "name") String name){
        templateModel.remove(name);
    }

    @Override
    @DeleteMapping(value = "/")
    public void removeall(@RequestHeader HttpHeaders httpHeaders,
                          HttpServletRequest request){
        templateModel.clear();
    }


}
