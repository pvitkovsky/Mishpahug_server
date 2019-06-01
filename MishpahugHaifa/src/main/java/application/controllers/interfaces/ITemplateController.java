package application.controllers.interfaces;

import application.entities.template.TemplateEntity;
import application.exceptions.ExceptionMishpaha;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ITemplateController {
    @GetMapping(value = "/{name}")
    TemplateEntity get(@PathVariable(value = "name") String name
            , @RequestHeader HttpHeaders httpHeaders,
                       HttpServletRequest request) throws ExceptionMishpaha;

    @GetMapping(value = "/")
    List<TemplateEntity> getall(@RequestHeader HttpHeaders httpHeaders,
                                HttpServletRequest request) throws ExceptionMishpaha;

    @PostMapping(value = "/")
    TemplateEntity post(@RequestBody TemplateEntity templateEntity
            , @RequestHeader HttpHeaders httpHeaders,
                        HttpServletRequest request);

    @DeleteMapping(value = "/{name}")
    void remove(@PathVariable(value = "name") String name
            , @RequestHeader HttpHeaders httpHeaders,
                HttpServletRequest request);

    @DeleteMapping(value = "/")
    void removeall(@RequestHeader HttpHeaders httpHeaders,
                   HttpServletRequest request);
}
