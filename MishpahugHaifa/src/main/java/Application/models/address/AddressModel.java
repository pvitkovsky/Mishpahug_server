package Application.models.address;

import Application.entities.AddressItem;
import Application.repo.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

public class AddressModel implements IAddressModel {

    @Autowired
    AddressRepository addressRepository;

    @Override
    public AddressItem getById(Integer Id) {
        return null;
    }

    @Override
    public AddressItem update(HashMap<String, String> data) {
        return null;
    }
}
