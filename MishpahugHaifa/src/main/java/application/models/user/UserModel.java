package application.models.user;

import application.entities.*;
import application.exceptions.ExceptionMishpaha;
import application.repositories.*;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class UserModel implements IUserModel {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserEntity getByUsernameAndPassword(String username, String password){
		return userRepository.findByUserNameAndAndEncrytedPassword(username, password);
	}

	@Override
	public List<UserEntity> getAll() {
		return userRepository.findAll();
	}
	
	@Override
	public Iterable<UserEntity> getAll(Predicate predicate){
		return userRepository.findAll(predicate);
	}

	@Override
	public UserEntity getById(Integer userId) {
		return userRepository.getOne(userId);
	}
	
	@Override
	public UserEntity getByName(String name) {
		return userRepository.findByUserName(name);
	}

	@Override
	public UserEntity add(UserEntity data) {
		return userRepository.save(data);
	}

	/*
	 * keys for update nickname firstname lastname email address.build address.city
	 * address.apartment address.street telephone
	 */
	/*
	 * нужно протестировать не уверен в работе кода между !!!. проверен вариант
	 * зактнуть логику в сущноть... не подойдет, так как нужен поиск города по имени
	 */
	@Override
	public UserEntity update(Integer userId, HashMap<String, String> data) throws ExceptionMishpaha {
		try {
			UserEntity user = userRepository.getOne(userId);
			return userRepository.update(user, data);
		} catch (Exception e) {
			throw new ExceptionMishpaha("Error! Not found user with id " + userId, null);
		}
	}

	/**
	 * Deletes the user skipping checks
	 */
	@Override
	public UserEntity deleteByID(Integer userId) {
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
		for(UserEntity user : all) {
			user.putIntoDeletionQueue();
		}
		userRepository.deleteAll();
		return all;
	}
	
	
	/**
	 * Activates the user, activating all his "deactivated" events and subscriptions;
	 */
	@Override
	public UserEntity activateByID(Integer userId) throws ExceptionMishpaha {
		UserEntity usr = userRepository.getOne(userId);
		usr.activate();
		return usr;
	}

	/**
	 * Deactivates the user, all his events and subscriptions;
	 */
	@Override
	public UserEntity deactivateByID(Integer userId) throws ExceptionMishpaha {
		UserEntity usr = userRepository.getOne(userId);
	    usr.deactivate();
		return usr;
	}

	/**
	 * Puts the user into deletion queue;
	 */
	@Override
	public UserEntity prepareForDeletionByID(Integer userId) throws ExceptionMishpaha {
		UserEntity usr = userRepository.getOne(userId);
	    usr.putIntoDeletionQueue();
		return null;
	}


}