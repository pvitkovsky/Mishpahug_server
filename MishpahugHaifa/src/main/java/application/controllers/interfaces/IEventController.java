package application.controllers.interfaces;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @ResponseBody
    EventEntity findById(@PathVariable(name = "id") Integer id
            , @RequestHeader HttpHeaders httpHeaders,
                         HttpServletRequest request) throws ExceptionMishpaha;

    @PostMapping(value = "/byowner/{ownerusername}")
    List<EventDTO> getByOwner(@PathVariable(value = "ownerusername") String ownerusername);

    EventEntity setDataFromForm(EventDTO data);

    EventEntity updateDataFromForm(HashMap<String, String> data, Integer id) throws ExceptionMishpaha;

    EventEntity delete(Integer id) throws ExceptionMishpaha;

    void delete() throws ExceptionMishpaha;

}