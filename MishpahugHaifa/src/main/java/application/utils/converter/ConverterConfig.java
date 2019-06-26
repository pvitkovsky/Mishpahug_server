package application.utils.converter;

import application.dto.EventDTO;
import application.dto.UserDTO;
import application.models.event.EventConverter;
import application.models.event.EventEntity;
import application.models.user.UserConverter;
import application.models.user.UserEntity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConverterConfig {

	@Bean(name = "userConverter")
	public IConverter<UserEntity, UserDTO> userConverter() {
		return new UserConverter();
	}

	@Bean(name = "eventConverter")
	public IConverter<EventEntity, EventDTO> eventConverter() {
		return new EventConverter();
	}

}