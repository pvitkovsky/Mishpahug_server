package application.models.user;

import java.util.HashMap;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

import application.dto.UserDTO;
import application.models.user.commands.UserDeleted;
import application.repositories.EventRepository;
import application.repositories.SubscriptionRepository;
import application.repositories.UserRepository;
import application.utils.converter.IConverter;

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
	IConverter<UserEntity, UserDTO> converterUser;

	@Override
	public UserEntity getByUsernameAndPassword(String username, String password) {
		return userRepository.findByUserNameAndAndEncrytedPassword(username, password);
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
	public Iterable<UserEntity> getAll(Predicate predicate) {
		return userRepository.findAll(predicate);
	}

	@Override
	public UserEntity add(UserEntity data) {
		return userRepository.save(data);
	}

	@Override
	public UserEntity update(Integer userId, HashMap<String, String> data) {
		UserEntity user = userRepository.getOne(userId);
		converterUser.updateEntity(user, data);
		return user;
	}

	/**
	 * Deletes the user skipping checks; 
	 */
	@Override
	public void deleteByID(Integer userId) {
		
	    UserDeleted userDeleted = new UserDeleted(userId);
		applicationEventPublisher.publishEvent(userDeleted);
		
		UserEntity usr = userRepository.getOne(userId);
		usr.putIntoDeletionQueue();
		userRepository.delete(usr);

	}
}
