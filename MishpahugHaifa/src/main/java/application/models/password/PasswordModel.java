package application.models.password;

import application.entities.PasswordEntity;
import application.entities.UserEntity;
import application.repo.PasswordRepository;
import application.repo.UserRepository;
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
    public PasswordEntity getByUserNickName(String nickname) {
        UserEntity userEntity = userRepository.getByNickname(nickname);
        //PasswordEntity passwordEntity = passwordRepository.getByUserId(userEntity.getId());
        return null;//passwordEntity;
    }
}
