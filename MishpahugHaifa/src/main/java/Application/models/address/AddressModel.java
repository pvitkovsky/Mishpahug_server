package Application.models.address;

import Application.entities.AddressEntity;
import Application.repo.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
@Service
public class AddressModel implements IAddressModel {

    @Autowired
    AddressRepository addressRepository;

    @Override
    public AddressEntity getById(Integer Id) {
        return null;
    }

    @Override
    public AddressEntity update(HashMap<String, String> data) {
        return null;
    }

    @Override
    public AddressEntity add(AddressEntity data) {
        return null;
    }

    @Override
    public AddressEntity remove(Integer id) {
        return null;
    }
}
