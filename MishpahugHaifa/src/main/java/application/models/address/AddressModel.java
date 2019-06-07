package application.models.address;

import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import application.exceptions.NotFoundEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

import application.entities.AddressEntity;
import application.entities.CityEntity;
import application.repositories.AddressRepository;
import application.repositories.CityRepository;

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
            cityEntity = cityRepository.getByName(data.get("cityname"));
        }
        cityEntity.addAddress(addressEntity);
        return addressRepository.save(addressEntity);
    }

    @Override
    public AddressEntity deleteByID(Integer id){
        try {
            AddressEntity cityEntity = addressRepository.getOne(id);
            addressRepository.deleteById(id);
            return cityEntity;
        } catch (Exception e) {
            throw new NotFoundEntityException("Error");
        }
    }

    @Override
    public void deleteAll() {
        addressRepository.deleteAll();
    }

    @Override
    public AddressEntity add(AddressEntity data) {
        return addressRepository.save(data);
    }

    @Override
    public List<AddressEntity> getAll() {
        return addressRepository.findAll();
    }

    @Override
    public Iterable<AddressEntity> getAll(Predicate predicate) {
        return addressRepository.findAll(predicate);
    }


    @Override
    public AddressEntity remove(Integer id) {
        if (id >= 0) {
            addressRepository.deleteById(id);
            return addressRepository.getOne(id);
        } else {
            new NotFoundEntityException("Error");
            return null;
        }

    }
}
