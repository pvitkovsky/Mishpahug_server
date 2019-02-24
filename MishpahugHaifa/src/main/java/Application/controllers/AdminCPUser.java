package Application.controllers;

import Application.entities.UserEntity;
import Application.models.user.IUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/administrator/user")
public class AdminCPUser {
    @Autowired
    IUserModel userModel;
    public List<UserEntity> getAll(){
        return userModel.getAll();
    }
}
