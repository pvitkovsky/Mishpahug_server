package application.models.password;

import application.entities.PasswordEntity;
import application.entities.UserEntity;
import application.repositories.PasswordRepository;
import application.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PasswordModel { //TODO: interface



    @Autowired
    PasswordRepository passwordRepository;
    @Autowired
    UserRepository userRepository;
    public PasswordEntity getByUserNickName(String username) {
        UserEntity userEntity = userRepository.getByUserName(username);
        //PasswordEntity passwordEntity = passwordRepository.getByUserId(userEntity.getId());
        return null;//passwordEntity;
    }
}
