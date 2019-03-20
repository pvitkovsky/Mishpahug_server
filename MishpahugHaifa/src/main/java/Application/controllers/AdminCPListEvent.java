package application.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import application.entities.EventEntity;
import application.entities.UserEntity;
import application.exceptions.ExceptionMishpaha;
import application.models.event.IEventModel;


@RestController
@RequestMapping(value = "/administrator/listevent")
public class AdminCPListEvent {
    @Autowired
    IEventModel eventModel;
    @GetMapping(value="/getall")
    public List<EventEntity> getAll(){
        return eventModel.getAll();
    }
    @GetMapping(value="/getallbyuser")
    public Set<EventEntity> getAllByUser(@RequestParam(value = "userid") Integer userId){
        return eventModel.getAllByUser(userId);
    }
    @GetMapping(value="/getallsibscribed")
    public Set<UserEntity> getAllSubscribed(@RequestParam(value = "id") Integer id){
        return eventModel.getAllSubscribed(id);
    }
    @PostMapping(value = "/subscribetoevent")
    public EventEntity subscribeToEvent(@RequestParam(value = "eventid") Integer eventId,
                                             @RequestParam(value = "userid") Integer userId){
        //
        try {
            return eventModel.subscribe(eventId, userId);
        } catch (ExceptionMishpaha exceptionMishpaha) {
            exceptionMishpaha.printStackTrace();
        }
        return null;
    }

    @PostMapping(value = "/unsubscribetoevent")
    public EventEntity unsubscribeToEvent(@RequestParam(value = "eventid") Integer eventId,
                                               @RequestParam(value = "userid") Integer userId){
        //
        try {
            return eventModel.unsubscribe(eventId, userId);
        } catch (ExceptionMishpaha exceptionMishpaha) {
            exceptionMishpaha.printStackTrace();
        }
        return null;
    }

    @PostMapping(value = "/add")
    public EventEntity add(@RequestBody EventEntity data){
        return eventModel.add(data);
    }
    @PostMapping(value = "/update")
    public EventEntity update(@RequestBody HashMap<String, String> data, @RequestParam(value = "id") Integer id){
        return eventModel.update(id, data);
    }
    @DeleteMapping(value = "/remove")
    public EventEntity remove(@RequestParam(value = "id") Integer id) throws ExceptionMishpaha{ //TODO: proper exception hanling
        return eventModel.remove(id);
    }
    @GetMapping(value="/get")
    public EventEntity get(@RequestParam(value = "id") Integer id) throws ExceptionMishpaha{ //TODO: proper exception hanling
        return eventModel.getById(id);
    }
    @GetMapping(value="/getbyfilter")
    public List<EventEntity> getByFilter(@RequestBody HashMap<String, String> filter){
        return eventModel.getByFilter(filter);
    }
}
