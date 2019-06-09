package application.models.user;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

import application.entities.UserEntity;
import application.exceptions.NotFoundEntityException;
import application.repositories.UserRepository;
import application.utils.converter.IUpdates;

@Service
@Transactional
public class UserModel implements IUserModel {

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    IUpdates updates; 

    @Override
    public UserEntity getByUsernameAndPassword(String username, String password) {
        return userRepository.findByUserNameAndAndEncrytedPassword(username, password);
    }

    @Override
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Iterable<UserEntity> getAll(Predicate predicate) {
        return userRepository.findAll(predicate);
    }

    @Override
    public UserEntity getById(Integer userId) {
        return userRepository.getOne(userId);
    }

    @Override
    public UserEntity getByUserName(String name) {
        return userRepository.findByUserName(name);
    }

    @Override
    public UserEntity add(UserEntity data) {
        return userRepository.save(data);
    }

    @Override
    public UserEntity update(Integer userId,
                             HashMap<String, String> data){
        if (!userRepository.existsById(userId)) throw new NotFoundEntityException("");
        UserEntity user = userRepository.getOne(userId);
        updates.updateUser(user, data);
        userRepository.save(user);
        return user;
    }

    /**
     * Deletes the user skipping checks
     */
    @Override
    public UserEntity deleteByID(Integer userId) {
        if (!userRepository.existsById(userId)) throw new NotFoundEntityException("");
        UserEntity usr = userRepository.getOne(userId);
            usr.putIntoDeletionQueue();
        userRepository.deleteById(userId);
        return usr;
    }

    /**
     * Deletes all users skipping checks
     */
    @Override
    public List<UserEntity> deleteAll() {
        List<UserEntity> all = userRepository.findAll();
        for (UserEntity user : all) {
            user.putIntoDeletionQueue();
        }
        userRepository.deleteAll();
        return all;
    }

    /**
     * Activates the user, activating all his "deactivated" events and subscriptions;
     */
    @Override
    public UserEntity activateByID(Integer userId) {
        if (!userRepository.existsById(userId)) throw new NotFoundEntityException("");
        UserEntity usr = userRepository.getOne(userId);
        usr.activate();
        return usr;
    }

    /**
     * Deactivates the user, all his events and subscriptions;
     */
    @Override
    public UserEntity deactivateByID(Integer userId) {
        if (!userRepository.existsById(userId)) throw new NotFoundEntityException("");
        UserEntity usr = userRepository.getOne(userId);
        usr.deactivate();
        return usr;
    }

    /**
     * Puts the user into deletion queue;
     */
    @Override
    public UserEntity prepareForDeletionByID(Integer userId) {
        if (!userRepository.existsById(userId)) throw new NotFoundEntityException("");
        UserEntity usr = userRepository.getOne(userId);
        usr.putIntoDeletionQueue();
        return null;
    }
}
