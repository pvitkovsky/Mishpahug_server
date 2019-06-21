package application.configurations.dbloader.loaders;

import java.io.BufferedReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import application.configurations.dbloader.LoaderDependencies;
import application.entities.EventEntity;
import application.entities.SubscriptionEntity;
import application.entities.UserEntity;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Loads guests
 * 
 */

@Slf4j

public class GuestsLoader implements ILoader {

	@Autowired

	LoaderDependencies data;

	private BufferedReader br;

	public GuestsLoader(BufferedReader br) {

		this.br = br;

	}

	@Override

	public void load() {
		try {

			this.data.eventGuestRepository.deleteAll();	
			
			UserEntity guest = this.data.userRepository.findAll().get(0); // TODO: rewrite with better reusiability
			EventEntity event = this.data.eventRepository.findAll().get(1);
			SubscriptionEntity subscription = new SubscriptionEntity(guest, event);
			this.data.eventGuestRepository.save(subscription);

			log.warn("DBLoadTest -> GuestsLoader -> In repository " + this.data.eventGuestRepository.findAll().size()
					+ " records");
			br.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}