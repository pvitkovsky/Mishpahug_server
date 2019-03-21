package application.models.address;

import application.entities.AddressEntity;

import java.util.HashMap;

public interface IAddressModel {
    public AddressEntity getById(Integer id);
    public AddressEntity update(HashMap<String, String> data, Integer id);
    public AddressEntity add(AddressEntity data);
    public AddressEntity remove(Integer id);
}
