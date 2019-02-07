package Application.models.address;

import Application.entities.AddressItem;

import java.util.HashMap;

public interface IAddressModel {
    public AddressItem getByUser(Integer userId);
    public AddressItem updateByUser(Integer userId, HashMap<String, String> data);
    public AddressItem getByEvent(Integer eventId);
    public AddressItem updateByEvent(Integer eventId, HashMap<String, String> data);
}
