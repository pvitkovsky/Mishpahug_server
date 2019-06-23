package application.utils.converter;

import application.dto.PropertyDTO;
import application.entities.interfaces.NamedProperty;

import java.util.ArrayList;
import java.util.List;

public interface IConverter<E, D> { 

	static List<PropertyDTO> PropertyToDTOList(List<? extends NamedProperty> all){
		List<PropertyDTO> res = new ArrayList<>();
		for (NamedProperty x : all) {
			res.add(new PropertyDTO(x.getId(),x.getName()));
		}
		return res;
	}
	
	List<D> DTOListFromEntities(List<E> data);
	List<D> DTOListFromEntities(Iterable<E> data);
	E entityFromDTO(D data);

}