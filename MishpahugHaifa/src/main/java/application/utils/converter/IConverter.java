package application.utils.converter;

import java.util.List;

public interface IConverter<E, D> { 
	
	List<D> DTOListFromEntities(List<E> data);
	List<D> DTOListFromEntities(Iterable<E> data);
	E entityFromDTO(D data);

}