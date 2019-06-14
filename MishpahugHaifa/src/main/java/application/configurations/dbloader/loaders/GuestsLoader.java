package application.configurations.dbloader.loaders;

import java.io.BufferedReader;
import java.io.IOException;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import application.configurations.dbloader.LoaderDependencies;
import application.entities.EventEntity;
import application.entities.SubscriptionEntity;
import application.entities.UserEntity;
import lombok.extern.slf4j.Slf4j;

/**
 * Loads guests
 */
@Slf4j
@Transactional
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
			this.data.eventGuestRepository.flush();
			
			UserEntity guest = this.data.userRepository.findAll().get(0);
			EventEntity event  = this.data.eventRepository.findAll().get(1);
			@SuppressWarnings("unused") //NICE DESIGN! DOESN'T NEED EXPLICIT SAVE!
			SubscriptionEntity subscription = new SubscriptionEntity(guest, event); 
			
			/*	for(UserEntity guest : userEntities) { // TODO: rewrite with better reusiability
				for(EventEntity event : eventEntities) {
					if(!event.getUserEntityOwner().equals(guest)) {
						SubscriptionEntity subscription = new SubscriptionEntity(guest, event);
						this.data.eventGuestRepository.save(subscription);
					}
				}
			}	 */
			
			log.debug("DBLoadTest -> GuestsLoader -> In repository " + this.data.eventGuestRepository.findAll().size() + " records");
			br.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}