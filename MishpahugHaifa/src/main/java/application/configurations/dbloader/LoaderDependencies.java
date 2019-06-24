package application.configurations.dbloader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import application.repositories.EventRepository;
import application.repositories.SubscriptionRepository;
import application.repositories.UserRepository;

@Service
public class LoaderDependencies { //TODO: stability risk with public access modifier on all repositories;
	@Autowired
	public Environment env;
	
	@Autowired
	public UserRepository userRepository;
	@Autowired
	public EventRepository eventRepository;
	@Autowired
	public SubscriptionRepository eventGuestRepository;

	public LoaderDependencies() {
	}
}