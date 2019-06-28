package application.utils.converter;

import java.util.List;
import java.util.Map;

public interface IConverter<E, D> { 
	
	List<D> DTOListFromEntities(List<E> dto);
	List<D> DTOListFromEntities(Iterable<E> dto);
	E updateEntity(E entity, Map<String, String> untypedDto);  

}