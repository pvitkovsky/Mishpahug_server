package application.controllers;

import application.dto.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @GetMapping(value="/getall")
    public UserDTOLists getDataForAddForm(){
        return null;

    }
    @PostMapping(value="/addPage2")
    public UserDTODetail setDataFromFormDetail(){
        return null;

    }

    @PostMapping(value="/addPage1")
    public UserDTO setDataFromForm(){
        return null;

    }
}
