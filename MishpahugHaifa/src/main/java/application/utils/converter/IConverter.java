package application.utils.converter;

import java.util.ArrayList;
import java.util.List;

import application.entities.interfaces.NamedProperty;

public interface IConverter<E, D> { 

	static List<String> PropertyToStringList(List<? extends NamedProperty> all){
		List<String> res = new ArrayList<>();
		for (NamedProperty x : all) {
			res.add(x.getName());
		}
		return res;
	}
	
	List<D> DTOListFromEntities(Iterable<E> data);
	
	E entityFromDTO(D data);

}