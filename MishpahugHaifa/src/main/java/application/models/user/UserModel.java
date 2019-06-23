package application.models.user;

import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

import application.models.event.EventDeleted;
import application.models.event.EventEntity;
import application.models.relation.SubscriptionEntity;
import application.repositories.EventRepository;
import application.repositories.SubscriptionRepository;
import application.repositories.UserRepository;
import application.utils.converter.IUpdates;

@Service
@Transactional
public class UserModel implements IUserModel {

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Autowired
	UserRepository userRepository;

	@Autowired
	EventRepository eventRepository;

	@Autowired
	SubscriptionRepository subscriptionRepository;

	@Autowired
	IUpdates updates;

	@Override
	public UserEntity getByUsernameAndPassword(String username, String password) {
		return userRepository.findByUserNameAndAndEncrytedPassword(username, password);
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
	public UserEntity update(Integer userId, HashMap<String, String> data) {
		UserEntity user = userRepository.getOne(userId);
		updates.updateUser(user, data);
		return user;
	}

	/**
	 * Deletes the user skipping checks; 
	 */
	@Override
	public UserEntity deleteByID(Integer userId) {
		
	    UserDeleted userDeleted = new UserDeleted(userId);
		applicationEventPublisher.publishEvent(userDeleted);
		
		UserEntity usr = userRepository.getOne(userId);
		usr.putIntoDeletionQueue();
		userRepository.delete(usr);
		
		return usr;
	}

	/**
	 * Deletes all users skipping checks
	 */
	@Override
	public List<UserEntity> deleteAll() {
		List<UserEntity> all = userRepository.findAll();
		all.forEach(user -> this.deleteByID(user.getId()));
		return all;
	}

	/**
	 * Activates the user, activating all his "deactivated" events and
	 * subscriptions;
	 */
	@Override
	public UserEntity activateByID(Integer userId) {
		UserEntity usr = userRepository.getOne(userId);
		usr.activate();
		return usr;
	}

	/**
	 * Deactivates the user, all his events and subscriptions;
	 */
	@Override
	public UserEntity deactivateByID(Integer userId) {
		UserEntity usr = userRepository.getOne(userId);
		usr.deactivate();
		return usr;
	}

	/**
	 * Puts the user into deletion queue;
	 */
	@Override
	public UserEntity prepareForDeletionByID(Integer userId) {
		UserEntity usr = userRepository.getOne(userId);
		usr.putIntoDeletionQueue();
		return null;
	}
}
