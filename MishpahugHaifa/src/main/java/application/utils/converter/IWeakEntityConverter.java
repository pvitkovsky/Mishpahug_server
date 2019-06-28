package application.utils.converter;

public interface IWeakEntityConverter<E, O, D> extends IConverter<E, D> {
	
	E entityFromDTO(D data, O owner);

}
