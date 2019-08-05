package application.models.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import application.dto.EventDTO;
import application.dto.UserDTO;
import application.utils.converter.IStrongEntityConverter;

@Service
public class UserConverter implements IStrongEntityConverter<UserEntity, UserDTO> {
	
	@Override
	public List<UserDTO> DTOListFromEntities(List<UserEntity> data) {
		List<UserDTO> res = new ArrayList<>();
		for (UserEntity x : data) {
			res.add(new UserDTO(x));
		}
		return res;
	}

	@Override
	public List<UserDTO> DTOListFromEntities(Iterable<UserEntity> data) {
		List<UserDTO> res = new ArrayList<>();
		for (UserEntity x : data) {
			res.add(new UserDTO(x));
		}
		return res;
	}

	@Override
	public Page<UserDTO> DTOPageFromEntities(Page<UserEntity> data) {
		return data.map(ue -> new UserDTO(ue));
	}

	
	@Override
	public UserEntity entityFromDTO(UserDTO data) { 
		UserEntity res = new UserEntity(data.getUserName(), data.getEMail());
		res.setFirstName(data.getFirstName());
		res.setLastName(data.getLastName());
		res.setEncrytedPassword(DigestUtils.md5Hex(data.getEncryptedPassword())); //TODO: password encryption on the frontend please; 
		res.setPhoneNumber(data.getPhoneNumber());
		res.setDateOfBirth(data.getDayOfBirth());
		res.setChoices(Optional.ofNullable(data.getChoices()).map(c -> new UserChoices(c)).orElse(new UserChoices()));
		return res;
	}

	@Override
	public UserEntity updateEntity(UserEntity entity, Map<String, String> data) {
		if (data.containsKey("firstName")) {
			entity.setFirstName(data.get("firstName"));
		}
		if (data.containsKey("lastName")) {
			entity.setLastName(data.get("lastName"));
		}
		if (data.containsKey("email")) {
			entity.setEMail(data.get("email"));
		}
		return entity;
	}

	
}
