package application.utils.converter;

import application.dto.EventDTO;
import application.entities.properties.AddressEntity;
import application.entities.EventEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
public class EventConverter extends ConverterBase implements IConverter<EventEntity, EventDTO> {

	public List<EventDTO> DTOListFromEntities(List<EventEntity> data) {

		List<EventDTO> res = new ArrayList<>();
		for (EventEntity x : data) {
			res.add(new EventDTO(x));
		}
		return res;

	}
public List<EventDTO> DTOListFromEntities(Iterable<EventEntity> data) {

		List<EventDTO> res = new ArrayList<>();
		for (EventEntity x : data) {
			res.add(new EventDTO(x));
		}
		return res;

	}

	public EventEntity entityFromDTO(EventDTO data) {
			EventEntity res = new EventEntity();
			res.setHoliDay(holyDayRepository.getByName(data.getHoliday()));
			res.setNameOfEvent(data.getNameOfEvent());
			res.setKitchenType(kichenTypeRepository.getByName(data.getKichenType()));
			AddressEntity addressEntity = new AddressEntity();
			addressEntity.setStreet(data.getAddressStreet());
			addressEntity.setBuilding(data.getAddressBuild());
			addressEntity.setApartment(data.getAddressApartment());
			res.setAddressEntity(addressEntity);
			//TODO
			return res;

	}

}
