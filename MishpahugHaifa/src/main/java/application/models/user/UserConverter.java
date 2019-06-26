package application.models.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import application.dto.UserDTO;
import application.utils.converter.ConverterBase;
import application.utils.converter.IConverter;

@Service
public class UserConverter extends ConverterBase implements IConverter<UserEntity, UserDTO> {

	public List<UserDTO> DTOListFromEntities(List<UserEntity> data) {
		List<UserDTO> res = new ArrayList<>();
		for (UserEntity x : data) {
			res.add(new UserDTO(x));
		}
		return res;
	}

	public List<UserDTO> DTOListFromEntities(Iterable<UserEntity> data) {
		List<UserDTO> res = new ArrayList<>();
		for (UserEntity x : data) {
			res.add(new UserDTO(x));
		}
		return res;
	}

	public UserEntity entityFromDTO(UserDTO data) { // TODO: optional DTO
		System.out.println("Arrived DTO " + data);
		UserEntity res = new UserEntity(data.getUserName(), data.getEMail());
		System.out.println("NEW USER " + res );
		res.setFirstName(data.getFirstName());
		res.setLastName(data.getLastName());
		res.setEncrytedPassword(data.getEncryptedPassword());
		res.setPhoneNumber(data.getPhoneNumber());
		res.setDateOfBirth(data.getDayOfBirth());
		res.setChoices(Optional.ofNullable(data.getChoices()).map(c -> new UserChoices(c)).orElse(new UserChoices()));
		return res;
	}

}
