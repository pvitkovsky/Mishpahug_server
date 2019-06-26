package application.models.event;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import application.dto.EventDTO;
import application.utils.converter.ConverterBase;
import application.utils.converter.IConverter;

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

	public EventEntity entityFromDTO(EventDTO data) { //TODO: optional DTO
		EventEntity res = new EventEntity();
		res.setNameOfEvent(data.getNameOfEvent());
		return res;

	}

}
