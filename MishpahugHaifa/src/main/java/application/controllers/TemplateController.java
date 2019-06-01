package application.controllers;

import application.controllers.interfaces.ITemplateController;
import application.entities.template.TemplateEntity;
import application.exceptions.ExceptionMishpaha;
import application.models.template.ITemplateModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/template")
public class TemplateController implements ITemplateController {
    @Autowired
    ITemplateModel templateModel;


    @Override
    @GetMapping(value = "/{name}")
    public TemplateEntity get(@PathVariable(value = "name") String name
            , @RequestHeader HttpHeaders httpHeaders,
                              HttpServletRequest request){
        httpHeaders.forEach((key, value) -> {
            log.info("ReligionController -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("ReligionController -> findAllByWebQuerydsl -> Remote IP -> " + request.getRemoteAddr());
        return templateModel.getByName(name);
    }

    @Override
    @GetMapping(value = "/")
    public List<TemplateEntity> getall(@RequestHeader HttpHeaders httpHeaders,
                                       HttpServletRequest request){
        httpHeaders.forEach((key, value) -> {
            log.info("ReligionController -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("ReligionController -> findAllByWebQuerydsl -> Remote IP -> " + request.getRemoteAddr());
        return templateModel.getAll();
    }

    @Override
    @PostMapping(value = "/")
    public TemplateEntity post(@RequestBody TemplateEntity templateEntity
            , @RequestHeader HttpHeaders httpHeaders,
                               HttpServletRequest request){
        httpHeaders.forEach((key, value) -> {
            log.info("ReligionController -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("ReligionController -> findAllByWebQuerydsl -> Remote IP -> " + request.getRemoteAddr());
        return templateModel.add(templateEntity);
    }
    @Override
    @DeleteMapping(value = "/{name}")
    public void remove(@PathVariable(value = "name") String name
            , @RequestHeader HttpHeaders httpHeaders,
                       HttpServletRequest request){
        httpHeaders.forEach((key, value) -> {
            log.info("ReligionController -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("ReligionController -> findAllByWebQuerydsl -> Remote IP -> " + request.getRemoteAddr());
        templateModel.remove(name);
    }

    @Override
    @DeleteMapping(value = "/")
    public void removeall(@RequestHeader HttpHeaders httpHeaders,
                          HttpServletRequest request){
        httpHeaders.forEach((key, value) -> {
            log.info("ReligionController -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("ReligionController -> findAllByWebQuerydsl -> Remote IP -> " + request.getRemoteAddr());
        templateModel.clear();
    }


}
