package application.models.user;

import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.entities.GenderEntity;
import application.entities.KitchenTypeEntity;
import application.entities.MaritalStatusEntity;
import application.entities.ReligionEntity;
import application.entities.UserEntity;
import application.exceptions.ExceptionMishpaha;
import application.repositories.CityRepository;
import application.repositories.CountryRepository;
import application.repositories.GenderRepository;
import application.repositories.KichenTypeRepository;
import application.repositories.MaritalStatusRepository;
import application.repositories.ReligionRepository;
import application.repositories.UserRepository;

@Service
@Transactional
public class UserModel implements IUserModel {

	@Autowired
	UserRepository userRepository;

	@Autowired
	CityRepository cityRepository;

	@Autowired
	CountryRepository countryRepository;

	@Autowired
	ReligionRepository religionRepository;
	
	@Autowired
	KichenTypeRepository kitchenTypeRepository;

	@Autowired
	GenderRepository genderRepository;

	@Autowired
	MaritalStatusRepository maritalStatusRepository;


	@Override
	public List<UserEntity> getAll() {
		return userRepository.findAll();
	}

	@Override
	public UserEntity getById(Integer userId) {
		return userRepository.getOne(userId);
	}

	@Override
	public List<UserEntity> getByFilter(HashMap<String, String> filter) {
		return userRepository.searchByFilter(filter);
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

	@Override
	public UserEntity deleteByID(Integer userId) {
		UserEntity usr = userRepository.getOne(userId);
		usr.unsubscribeAll();
		userRepository.deleteById(userId);
		return usr;
	}

	@Override
	public UserEntity getByName(String name) {
		return userRepository.findByUserName(name);
	}

	@Override
	public List<UserEntity> deleteAll() {
		List<UserEntity> all = userRepository.findAll();
		userRepository.deleteAll();
		return all;
	}

	@Override
	public List<UserEntity> getByReligion(String religion) {
		ReligionEntity religionEntity = religionRepository.getByName(religion); //TODO: exceptions if not found
		return userRepository.findByReligion(religionEntity);
	}

	@Override
	public List<UserEntity> getByGender(String gender){
		GenderEntity genderEntity = genderRepository.getByName(gender); //TODO: exceptions if not found
		return userRepository.findByGender(genderEntity);
	}

	@Override
	public List<UserEntity> getByKitchenType(String kitchenType) {
		KitchenTypeEntity kitchenTypeEntity = kitchenTypeRepository.getByName(kitchenType); //TODO: exceptions if not found
		return userRepository.findByKitchenType(kitchenTypeEntity);
	}

	@Override
	public List<UserEntity> getByMaritalStatus(String maritalStatus) {
		MaritalStatusEntity maritalStatusEntity = maritalStatusRepository.getByName(maritalStatus); //TODO: exceptions if not found
		return userRepository.findByMaritalStatus(maritalStatusEntity);
	}

}