package application.configurations.dbloader.loaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import application.configurations.dbloader.LoaderDependencies;
import application.entities.EventEntity;
import application.entities.SubscriptionEntity;
import application.entities.UserEntity;
import application.entities.values.FeedBackValue;
import application.utils.RandomString;
import lombok.extern.slf4j.Slf4j;

/**
 * Loads guests
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
			String detail;
			this.data.eventGuestRepository.deleteAll();
			Random gen = new Random();
			while ((detail = br.readLine()) != null) {
				HashSet<UserEntity> userEntities = new HashSet<>();
				String[] data = detail.split(",");
				String[] items = data[1].split(";");
				for (String x:items) {
					if (!"".equals(x)) userEntities.add(this.data.userRepository.getOne(Integer.parseInt(x) + 1));
				}
					EventEntity event = this.data.eventRepository.getOne(Integer.parseInt(data[0]) + 1);
					userEntities.forEach(item ->{
						if (!event.getUserEntityOwner().equals(item)) {
							SubscriptionEntity subscription = new SubscriptionEntity(item, event);
							FeedBackValue feedBackValue = new FeedBackValue();
							feedBackValue.setComment(RandomString.genText());
							feedBackValue.setRating(gen.nextInt(10));
							feedBackValue.setDateTime(LocalDateTime.of(2000 + gen.nextInt(20),
									1 + gen.nextInt(11),
									1 + gen.nextInt(27), // MAX 28 for February
									1 + gen.nextInt(23),
									1 + gen.nextInt(58)));
							subscription.setFeedback(feedBackValue);
						}
					});
			}
			log.debug("DBLoadTest -> GuestsLoader -> In repository " + this.data.eventGuestRepository.findAll().size() + " records");
			br.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}