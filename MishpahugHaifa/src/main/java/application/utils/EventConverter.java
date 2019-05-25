package application.utils;

import java.util.List;

import org.springframework.stereotype.Service;

import application.dto.EventDTO;
import application.entities.EventEntity;

@Service
public class EventConverter extends ConverterBase implements IConverter<EventEntity, EventDTO> {

	public List<EventDTO> DTOListFromEntities(Iterable<EventEntity> data) {

		return null; // TODO:stub

	}

	public EventEntity entityFromDTO(EventDTO data) {
			EventEntity res = new EventEntity();


			return res;

	}

}
