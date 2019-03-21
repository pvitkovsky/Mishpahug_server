package Application.models.password;

import Application.entities.PasswordEntity;
import Application.entities.UserEntity;
import Application.repo.PasswordRepository;
import Application.repo.UserRepository;
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
        UserEntity userEntity = userRepository.getByNickName(nickname);
        //PasswordEntity passwordEntity = passwordRepository.getByUserId(userEntity.getId());
        return null;//passwordEntity;
    }
}
