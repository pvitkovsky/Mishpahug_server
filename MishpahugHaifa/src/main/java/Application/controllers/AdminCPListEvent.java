package Application.controllers;

import Application.entities.EventEntity;
import Application.entities.UserEntity;
import Application.models.event.IEventModel;
import Application.models.user.IUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

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
    public List<EventEntity> getAllByUser(@RequestParam(value = "userid") Integer userId){
        return eventModel.getAllByUser(userId);
    }
    @GetMapping(value="/getallsibscribed")
    public List<UserEntity> getAllSubscribed(@RequestParam(value = "id") Integer id){
        return eventModel.getAllSubscribed(id);
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
    public EventEntity remove(@RequestParam(value = "id") Integer id){
        return eventModel.remove(id);
    }
    @GetMapping(value="/get")
    public EventEntity get(@RequestParam(value = "id") Integer id){
        return eventModel.getById(id);
    }
    @GetMapping(value="/getbyfilter")
    public List<EventEntity> getByFilter(@RequestBody HashMap<String, String> filter){
        return eventModel.getByFilter(filter);
    }
}
