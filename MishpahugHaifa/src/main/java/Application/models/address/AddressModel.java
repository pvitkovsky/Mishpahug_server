package Application.models.address;

import Application.entities.AddressItem;
import Application.repo.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

public class AddressModel implements IAddressModel {

    @Autowired
    AddressRepository addressRepository;

    @Override
    public AddressItem getByUser(Integer userId) {
        return null;
    }

    @Override
    public AddressItem updateByUser(Integer userId, HashMap<String, String> data) {
        return null;
    }

    @Override
    public AddressItem getByEvent(Integer eventId) {
        return null;
    }

    @Override
    public AddressItem updateByEvent(Integer eventId, HashMap<String, String> data) {
        return null;
    }
}
