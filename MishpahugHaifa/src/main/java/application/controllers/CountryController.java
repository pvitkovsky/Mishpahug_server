package application.controllers;

import java.util.ArrayList;
import java.util.List;

import application.controllers.interfaces.ICountryController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import application.entities.CountryEntity;
import application.models.country.ICountryModel;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping(value = "/country")
public class CountryController implements ICountryController {
    @Autowired
    ICountryModel countryModel;

    @Override
    @PostMapping(value = "/")
    public void post(@RequestBody CountryEntity data
                    , @RequestHeader HttpHeaders httpHeaders,
                    HttpServletRequest request) {
        httpHeaders.forEach((key, value) -> {
            log.info("CountryController -> post -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("CountryController -> post -> Remote IP -> " + request.getRemoteAddr());
        countryModel.addCountry(data);
    }

    @Override
    @PutMapping(value = "/")
    public void put(@RequestParam(name = "id") Integer id,
                    @RequestParam(name = "name") String name
                    , @RequestHeader HttpHeaders httpHeaders,
                    HttpServletRequest request) {
        httpHeaders.forEach((key, value) -> {
            log.info("CountryController -> put{"+ id +"} -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("CountryController -> put{" + id + "} -> Remote IP -> " + request.getRemoteAddr());
        countryModel.updateName(id, name);
    }

    @Override
    @DeleteMapping(value = "/")
    public void delete(@RequestHeader HttpHeaders httpHeaders,
                       HttpServletRequest request) {
        httpHeaders.forEach((key, value) -> {
            log.info("CountryController -> delete -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("CountryController -> delete -> Remote IP -> " + request.getRemoteAddr());
        countryModel.deleteAll();
    }

    @Override
    @DeleteMapping(value = "/{name}")
    public void delete(@PathVariable(name = "name") String name
                      ,@RequestHeader HttpHeaders httpHeaders,
                       HttpServletRequest request) {
        httpHeaders.forEach((key, value) -> {
            log.info("CountryController -> delete -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("CountryController -> delete -> Remote IP -> " + request.getRemoteAddr());
        countryModel.deleteByName(name);
    }

    @Override
    @GetMapping(value = "/")
    public List<String> get(@RequestHeader HttpHeaders httpHeaders,
                                   HttpServletRequest request) {
        httpHeaders.forEach((key, value) -> {
            log.info("CountryController -> get -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("CountryController -> get -> Remote IP -> " + request.getRemoteAddr());
        List<String> res = new ArrayList<>();
        List<CountryEntity> countryEntityList = countryModel.getAll();
        countryEntityList.forEach(item->res.add(item.getName()));
        return res;
    }

    @Override
    @GetMapping(value = "/{id}")
    public String get(@PathVariable(name = "id") Integer id
                            ,@RequestHeader HttpHeaders httpHeaders,
                             HttpServletRequest request) {
        httpHeaders.forEach((key, value) -> {
            log.info("CountryController -> get -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("CountryController -> get -> Remote IP -> " + request.getRemoteAddr());
        return countryModel.getById(id).getName();
    }

}
