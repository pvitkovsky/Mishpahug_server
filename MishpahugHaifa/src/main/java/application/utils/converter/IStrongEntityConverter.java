package application.utils.converter;

public interface IStrongEntityConverter<E, D> extends IConverter<E, D> {
	
	E entityFromDTO(D data);

}
