package application.controllers.interfaces;

import application.dto.LoginDTO;
import application.dto.LoginResponse;
import application.dto.UserDTO;
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

public interface IUserController {
    /* (non-Javadoc)
     * @see application.controllers.intarfaces.IUserController#get(java.lang.Integer)
     */
    @GetMapping(value = "/{id}")
    UserDTO get(@PathVariable(value = "id") Integer id) throws ExceptionMishpaha;

    /* (non-Javadoc)
     * @see application.controllers.intarfaces.IUserController#get(java.lang.Integer)
     */
    @GetMapping(value = "/current")
    UserDTO getByToken(HttpServletRequest request) throws ExceptionMishpaha;

    @GetMapping(value = "/currentsubscribes")
    // TODO спрятать строки кода в одну из моделей?
    List<EventEntity> getEventsByToken(HttpServletRequest request) throws ExceptionMishpaha;

    @GetMapping(value = "/{id}/subscribes")
 // TODO спрятать строки кода в одну из моделей?
    List<EventEntity> getEventsById(@PathVariable(value = "id") Integer id) throws ExceptionMishpaha;

    @GetMapping(value = "/all")
    List<UserDTO> getall() throws ExceptionMishpaha;

    @PostMapping(value = "/login")
    LoginResponse login(@RequestBody LoginDTO loginDTO, @RequestHeader HttpHeaders httpHeaders,
                        HttpServletRequest request);

    @PostMapping(value = "/logout")
    void logout(@RequestHeader(name = "Authorization", required = false) String token);

    @PostMapping(value = "/register")
    void add(@RequestBody UserDTO userDTO) throws ExceptionMishpaha;

    @PutMapping(value = "/{id}")
    UserDTO update(@RequestBody HashMap<String, String> data,
                   @PathVariable(value = "id") Integer id,
                   @RequestHeader HttpHeaders httpHeaders,
                   HttpServletRequest request) throws ExceptionMishpaha;

    /* (non-Javadoc)
     * @see application.controllers.intarfaces.IUserController#delete(java.lang.Integer)
     */
    @DeleteMapping(value = "/{id}")
    UserDTO delete(@PathVariable(value = "id") Integer id) throws ExceptionMishpaha;

    /* (non-Javadoc)
     * @see application.controllers.intarfaces.IUserController#delete()
     */
    @DeleteMapping(value = "/")
    void deleteAll() throws ExceptionMishpaha;

    /* (non-Javadoc)
     * @see application.controllers.intarfaces.IUserController#setDataFromForm(application.dto.UserDTO)
     */
    @PostMapping(value = "/addPage") //TODO: not-restful name; better is viewPage1
    void setDataFromForm(@RequestBody UserDTO data) throws ExceptionMishpaha;

    /* (non-Javadoc)
     * @see application.controllers.intarfaces.IUserController#setDataFromFormDetail(application.dto.UserDTODetail, java.lang.String)
     */
    @PutMapping(value = "/addPage") //TODO: not-restful name; better is viewPage2
    void setDataFromFormDetail(@RequestBody UserDTO data,
                               @RequestParam(name = "username") String userName) throws ExceptionMishpaha;

    /* (non-Javadoc)
     * @see application.controllers.intarfaces.IUserController#findAllByWebQuerydsl(com.querydsl.core.types.Predicate)
     */
    @RequestMapping(method = RequestMethod.GET, value = "/")
    @ResponseBody
    List<UserDTO> findAllByWebQuerydsl(
            @QuerydslPredicate(root = UserEntity.class) Predicate predicate);
}
