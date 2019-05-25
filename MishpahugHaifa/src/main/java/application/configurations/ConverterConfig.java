package application.configurations;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import application.dto.EventDTO;
import application.dto.UserDTO;
import application.entities.EventEntity;
import application.entities.UserEntity;
import application.utils.EventConverter;
import application.utils.IConverter;
import application.utils.UserConverter;

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