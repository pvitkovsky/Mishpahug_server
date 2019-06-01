package application.controllers.interfaces;

import application.dto.EventDTO;
import application.entities.EventEntity;
import application.entities.UserEntity;
import application.exceptions.ExceptionMishpaha;
import com.querydsl.core.types.Predicate;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

public interface IEventController {
    /* (non-Javadoc)
     * @see application.controllers.intarfaces.IEventController#findAllByWebQuerydsl(com.querydsl.core.types.Predicate)
     */
    @RequestMapping(method = RequestMethod.GET, value = "/")
    @ResponseBody
    List<EventDTO> findAllByWebQuerydsl(@QuerydslPredicate(root = EventEntity.class) Predicate predicate
            , @RequestHeader HttpHeaders httpHeaders,
                                        HttpServletRequest request);

    /* (non-Javadoc)
     * @see application.controllers.intarfaces.IEventController#findAll(java.lang.Integer)
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @ResponseBody
    EventEntity findById(@PathVariable(name = "id") Integer id
            , @RequestHeader HttpHeaders httpHeaders,
                         HttpServletRequest request) throws ExceptionMishpaha;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/guests")
                                @ResponseBody
    List<UserEntity> findGestByEventId(@PathVariable(name = "id") Integer id
            , @RequestHeader HttpHeaders httpHeaders,
                                       HttpServletRequest request) throws ExceptionMishpaha;

    @GetMapping(value = "/byowner/{ownerusername}")
    List<EventDTO> getByOwner(@PathVariable(value = "ownerusername") String ownerusername,
                              @RequestHeader HttpHeaders httpHeaders,
                              HttpServletRequest request);

    /* (non-Javadoc)
     * @see application.controllers.intarfaces.IEventController#setDataFromForm(application.dto.EventDTO)
     */
    @PostMapping(value = "/")
    EventEntity setDataFromForm(@RequestBody EventDTO data,
                                @RequestHeader HttpHeaders httpHeaders,
                                HttpServletRequest request);

    /* (non-Javadoc)
     * @see application.controllers.intarfaces.IEventController#updateDataFromForm(java.util.HashMap, java.lang.Integer)
     */
    @PutMapping(value = "/{id}")
    EventEntity updateDataFromForm(@RequestBody HashMap<String, String> data,
                                   @PathVariable(value = "id") Integer id,
                                   @RequestHeader HttpHeaders httpHeaders,
                                   HttpServletRequest request) throws ExceptionMishpaha;

    /* (non-Javadoc)
     * @see application.controllers.intarfaces.IEventController#delete(java.lang.Integer)
     */
    @DeleteMapping(value = "/{id}")
    EventEntity delete(@PathVariable(value = "id") Integer id,
                       @RequestHeader HttpHeaders httpHeaders,
                       HttpServletRequest request) throws ExceptionMishpaha;

    /* (non-Javadoc)
     * @see application.controllers.intarfaces.IEventController#delete()
     */
    @DeleteMapping(value = "/")
    void delete(@RequestHeader HttpHeaders httpHeaders,
                HttpServletRequest request) throws ExceptionMishpaha;
}
