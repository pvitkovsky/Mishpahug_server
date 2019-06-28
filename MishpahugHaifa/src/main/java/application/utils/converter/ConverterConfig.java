package application.utils.converter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import application.dto.EventDTO;
import application.dto.UserDTO;
import application.models.event.EventConverter;
import application.models.event.EventEntity;
import application.models.user.UserConverter;
import application.models.user.UserEntity;

@Configuration
public class ConverterConfig {

	@Bean(name = "userConverter")
	public IStrongEntityConverter<UserEntity, UserDTO> userConverter() {
		return new UserConverter();
	}

	@Bean(name = "eventConverter")
	public IWeakEntityConverter<EventEntity, UserEntity, EventDTO> eventConverter() {
		return new EventConverter();
	}

}