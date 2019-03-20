package application.models.address;

import application.entities.AddressEntity;
import application.entities.CityEntity;
import application.exceptions.ExceptionMishpaha;
import application.repo.AddressRepository;
import application.repo.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import javax.transaction.Transactional;
@Service
@Transactional
public class AddressModel implements IAddressModel {

    @Autowired
    AddressRepository addressRepository;
    @Autowired
    CityRepository cityRepository;

    @Override
    public AddressEntity getById(Integer id) {
        return addressRepository.getOne(id);
    }

    @Override
    public AddressEntity update(HashMap<String, String> data, Integer id) {
        AddressEntity addressEntity = addressRepository.getOne(id);
        if (data.containsKey("build"))
            addressEntity.setBuilding(Integer.getInteger(data.get("build")));
        if (data.containsKey("apartament"))
            addressEntity.setApartment(Integer.getInteger(data.get("apartament")));
        if (data.containsKey("street"))
            addressEntity.setBuilding(Integer.getInteger(data.get("street")));
        CityEntity cityEntity = null;
        if (data.containsKey("cityname")) {
            cityEntity = cityRepository.getByFullName(data.get("cityname"));
        }
        cityEntity.addAddress(addressEntity);
        return addressRepository.save(addressEntity);
    }

    @Override
    public AddressEntity add(AddressEntity data) {
        return addressRepository.save(data);
    }

    @Override
    public AddressEntity remove(Integer id) {
        if (id >= 0){
            addressRepository.deleteById(id);
            return addressRepository.getOne(id);
        }
        else {
            new ExceptionMishpaha("Error! Index is a negatite",null);
            return null;
        }

    }
}
