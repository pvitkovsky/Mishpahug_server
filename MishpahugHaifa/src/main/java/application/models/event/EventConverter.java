package application.models.event;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import application.dto.EventDTO;
import application.models.user.UserEntity;
import application.utils.converter.IWeakEntityConverter;

@Service
public class EventConverter implements IWeakEntityConverter<EventEntity, UserEntity, EventDTO> {

	@Override
	public List<EventDTO> DTOListFromEntities(List<EventEntity> data) {

		List<EventDTO> res = new ArrayList<>();
		for (EventEntity x : data) {
			res.add(new EventDTO(x));
		}
		return res;

	}

	@Override
	public List<EventDTO> DTOListFromEntities(Iterable<EventEntity> data) {

		List<EventDTO> res = new ArrayList<>();
		for (EventEntity x : data) {
			res.add(new EventDTO(x));
		}
		return res;

	}
	
	@Override
	public Page<EventDTO> DTOPageFromEntities(Page<EventEntity> data) {
		return data.map(ee -> new EventDTO(ee));
	}
	

	@Override
	public EventEntity entityFromDTO(EventDTO eventDTO, UserEntity owner) {
		EventEntity event = new EventEntity(owner, eventDTO.getDate(), eventDTO.getTime());
		event.setNameOfEvent(Optional.ofNullable(eventDTO.getNameOfEvent()).orElse(""));		
		return event;
	}
	

	@Override
	public EventEntity updateEntity(EventEntity entity, Map<String, String> untypedDto) {
		if (untypedDto.containsKey("nameOfEvent")) { 
			entity.setNameOfEvent(untypedDto.get("nameOfEvent"));
		}
		return entity;
	}

	

	

}
