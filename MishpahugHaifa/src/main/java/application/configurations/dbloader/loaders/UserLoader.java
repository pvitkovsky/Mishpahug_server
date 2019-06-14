package application.configurations.dbloader.loaders;

import application.configurations.dbloader.LoaderDependencies;
import application.entities.UserEntity;
import application.entities.properties.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

/**
 * Loads users
 */
@Slf4j
public class UserLoader implements ILoader {

	@Autowired 
	LoaderDependencies data;

	private BufferedReader br;

	public UserLoader(BufferedReader br) {
		this.br = br;
	}
	
	@Override
	public void load() {
		try {
			List<MaritalStatusEntity> maritalStatusEntityList = this.data.maritalStatusRepository.findAll();
			List<GenderEntity> genderEntityList = this.data.genderRepository.findAll();
			List<AddressEntity> addressEntityList = this.data.addressRepository.findAll();
			List<ReligionEntity> religionEntityList = this.data.religionRepository.findAll();
			List<KitchenTypeEntity> kitchenTypeEntityList = this.data.kichenTypeRepository.findAll();
			this.data.logsDataRepository.deleteAll();
			this.data.userRepository.findAll().forEach(UserEntity::putIntoDeletionQueue);
			this.data.userRepository.deleteAll();
			this.data.userRepository.flush();
			//do we need flush here?
			// need
			// https://stackoverflow.com/questions/49595852/deleteall-in-repository-randomly-causes-constraintviolationexception
			String detail;
			while ((detail = br.readLine()) != null) {
				String[] data = detail.split("!");
				UserEntity user = new UserEntity(data[0].split("@")[0], data[0]);
				user.setFirstName(data[1]);
				user.setLastName(data[2]);
				user.setDateOfBirth(LocalDate.parse(data[3]));
				Random ran = new Random();
				user.setPhoneNumber(data[4]+ ran.nextInt(9)+ ran.nextInt(9)+ ran.nextInt(9)+ ran.nextInt(9)+ ran.nextInt(9)+ ran.nextInt(9)+ ran.nextInt(9));
				user.setEncrytedPassword(DigestUtils.md5Hex(data[0].split("@")[0]));
				user.activate();
				user.setGender(genderEntityList.get(ran.nextInt(genderEntityList.size() - 1)));
				user.setReligion(religionEntityList.get(ran.nextInt(religionEntityList.size() - 1)));
				user.setAddressEntity(addressEntityList.get(ran.nextInt(addressEntityList.size() - 1)));
				user.setKitchenType(kitchenTypeEntityList.get(ran.nextInt(kitchenTypeEntityList.size() - 1)));
				user.setMaritalStatus(maritalStatusEntityList.get(ran.nextInt(maritalStatusEntityList.size() - 1)));
				log.debug("DBLoadTest -> UserLoader -> userentity = " + user);
				this.data.userRepository.save(user);
			}
			log.debug("DBLoadTest -> UserLoader -> In repository " + this.data.userRepository.findAll().size() + " records");
			br.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}