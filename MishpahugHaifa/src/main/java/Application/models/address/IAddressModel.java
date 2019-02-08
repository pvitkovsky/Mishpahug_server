package Application.models.address;

import Application.entities.AddressItem;

import java.util.HashMap;

public interface IAddressModel {
    public AddressItem getById(Integer Id);
    public AddressItem update(HashMap<String, String> data);
}
