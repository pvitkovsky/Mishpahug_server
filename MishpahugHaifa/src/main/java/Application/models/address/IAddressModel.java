package Application.models.address;

import Application.entities.AddressEntity;

import java.util.HashMap;

public interface IAddressModel {
    public AddressEntity getById(Integer Id);
    public AddressEntity update(HashMap<String, String> data);
    public AddressEntity add(AddressEntity data);
    public AddressEntity remove(Integer id);
}
