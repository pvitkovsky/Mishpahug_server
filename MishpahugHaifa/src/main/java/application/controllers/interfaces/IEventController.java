package application.controllers.interfaces;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;

import com.querydsl.core.types.Predicate;

import application.dto.EventDTO;
import application.entities.EventEntity;
import application.exceptions.ExceptionMishpaha;

public interface IEventController {

    Iterable<EventEntity> findAllByWebQuerydsl(Predicate predicate, @RequestHeader HttpHeaders httpHeaders,
                                               HttpServletRequest request);

    EventEntity findAll(Integer id, @RequestHeader HttpHeaders httpHeaders,
                        HttpServletRequest request) throws ExceptionMishpaha;

    EventEntity setDataFromForm(EventDTO data);

    EventEntity updateDataFromForm(HashMap<String, String> data, Integer id) throws ExceptionMishpaha;

    EventEntity delete(Integer id) throws ExceptionMishpaha;

    void delete() throws ExceptionMishpaha;

}