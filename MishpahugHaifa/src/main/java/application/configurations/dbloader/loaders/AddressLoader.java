package application.configurations.dbloader.loaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import application.configurations.dbloader.DB_test_loader_fixed;
import application.configurations.dbloader.LoaderDependencies;
import application.entities.properties.AddressEntity;
import application.entities.properties.CityEntity;
import lombok.extern.slf4j.Slf4j;

/**
 * Loads address
 */
@Slf4j
public class AddressLoader implements ILoader {

	@Autowired 
	LoaderDependencies data;
	
	private BufferedReader br;

	public AddressLoader(BufferedReader br) {
		this.br = br;
	}

	@Override
	public void load() {
		try {
			List<CityEntity> cityEntityList = this.data.cityRepository.findAll();
			for (CityEntity city : cityEntityList) {
				city.clearAddresses();
			}
			Random rr = new Random();
			Integer cityEntityListSize = cityEntityList.size() - 1;
			String detail;
			while ((detail = br.readLine()) != null) {
				AddressEntity addressEntity = new AddressEntity();
				addressEntity.setStreet(detail);
				addressEntity.setBuilding(rr.nextInt(100));
				addressEntity.setApartment(rr.nextInt(50));
				cityEntityList.get(rr.nextInt(cityEntityListSize)).addAddress(addressEntity);

			}
			log.debug("DBLoadTest -> AddressLoader -> In repository " + this.data.addressRepository.findAll().size() + " records");
			br.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}