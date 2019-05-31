package application.controllers.interfaces;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import application.entities.UserEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import com.querydsl.core.types.Predicate;

import application.dto.EventDTO;
import application.entities.EventEntity;
import application.exceptions.ExceptionMishpaha;

public interface IEventController {

    Iterable<EventDTO> findAllByWebQuerydsl(Predicate predicate, @RequestHeader HttpHeaders httpHeaders,
                                               HttpServletRequest request);


    /* (non-Javadoc)
     * @see application.controllers.intarfaces.IEventController#findAll(java.lang.Integer)
     */
    EventEntity findById(@PathVariable(name = "id") Integer id
            , @RequestHeader HttpHeaders httpHeaders,
                         HttpServletRequest request) throws ExceptionMishpaha;

    List<UserEntity> findGestByEventId(@PathVariable(name = "id") Integer id
            , @RequestHeader HttpHeaders httpHeaders,
                                       HttpServletRequest request) throws ExceptionMishpaha;

    List<EventDTO> getByOwner(@PathVariable(value = "ownerusername"
    ) String ownerusername,
                              @RequestHeader HttpHeaders httpHeaders,
                              HttpServletRequest request);

    EventEntity setDataFromForm(EventDTO data,
                                @RequestHeader HttpHeaders httpHeaders,
                                HttpServletRequest request);

    EventEntity updateDataFromForm(HashMap<String, String> data, Integer id,
                                   @RequestHeader HttpHeaders httpHeaders,
                                   HttpServletRequest request) throws ExceptionMishpaha;

    EventEntity delete(Integer id,
                       @RequestHeader HttpHeaders httpHeaders,
                       HttpServletRequest request) throws ExceptionMishpaha;

    void delete(@RequestHeader HttpHeaders httpHeaders,
                HttpServletRequest request) throws ExceptionMishpaha;

}