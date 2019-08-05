package application.utils.converter;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import application.models.event.EventEntity;

public interface IConverter<E, D> { 
	
	List<D> DTOListFromEntities(List<E> dto);
	List<D> DTOListFromEntities(Iterable<E> data);
	Page<D> DTOPageFromEntities(Page<E> data);
	E updateEntity(E entity, Map<String, String> untypedDto);  

}