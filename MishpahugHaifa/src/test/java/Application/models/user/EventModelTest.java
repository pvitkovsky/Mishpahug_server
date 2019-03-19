package Application.models.user;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import Application.entities.EventEntity;
import Application.entities.EventGuestRelation;
import Application.entities.UserEntity;
import Application.models.event.EventModel;
import Application.models.event.IEventModel;
import Application.repo.EventGuestRepository;
import Application.repo.EventRepository;
import Application.repo.UserRepository;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class EventModelTest {
	
	@TestConfiguration
	static class MPHServiceConfig{
		@Bean
		public IEventModel eventModel() {
			return new EventModel();
		}
	}
	
	@MockBean
	EventRepository eventRepo;
	@MockBean
	UserRepository userRepo;
	@MockBean
	EventGuestRepository subscriptionsRepo;
	
	@Autowired
	private IEventModel eventModel;
	

	private final UserEntity ALYSSA = new UserEntity();
	private final UserEntity BEN = new UserEntity();
	private final EventEntity GUESTING = new EventEntity();
	private final LocalDate TDATE = LocalDate.of(2190, 1, 1);
	private final LocalTime TTIME = LocalTime.of(23, 59);
	private final String TNAME = "TESTING";
	private final EventGuestRelation AGUESTING = new EventGuestRelation();
	private final Set<EventEntity> ASUBS = new HashSet<>();
	private final Set<UserEntity> GSUBS = new HashSet<>();
	
	@Before
	public void buildEntities() {
		ALYSSA.setNickname("Alyssa");
		BEN.setNickname("Ben");
		GUESTING.setDate(TDATE);
		GUESTING.setTime(TTIME);
		GUESTING.setNameOfEvent(TNAME);
		
	}
	
	
	@Test
	public void getAllByUser() { //TODO: proper named methods please

		Mockito.when(userRepo.save(BEN)).thenReturn(BEN);
		Mockito.when(userRepo.save(ALYSSA)).thenReturn(ALYSSA);
		Mockito.when(eventRepo.save(GUESTING)).thenReturn(GUESTING);
		Mockito.when(subscriptionsRepo.save(AGUESTING)).thenReturn(AGUESTING);
		Mockito.when(userRepo.getOne(ALYSSA.getId())).thenReturn(ALYSSA);
		
		BEN.makeOwner(GUESTING);
		userRepo.save(BEN);
		userRepo.save(ALYSSA);
		AGUESTING.subscribe(ALYSSA, GUESTING);
		ASUBS.add(GUESTING);
		
		assertEquals(eventModel.getAllByUser(ALYSSA.getId()), ASUBS);

	}
	
	@Test
	public void getAllSubscribed() { //TODO: proper named methods please

		Mockito.when(userRepo.save(BEN)).thenReturn(BEN);
		Mockito.when(userRepo.save(ALYSSA)).thenReturn(ALYSSA);
		Mockito.when(eventRepo.save(GUESTING)).thenReturn(GUESTING);
		Mockito.when(subscriptionsRepo.save(AGUESTING)).thenReturn(AGUESTING);
		Mockito.when(eventRepo.getOne(GUESTING.getId())).thenReturn(GUESTING);
		
		BEN.makeOwner(GUESTING);
		userRepo.save(BEN);
		userRepo.save(ALYSSA);
		AGUESTING.subscribe(ALYSSA, GUESTING);
		GSUBS.add(ALYSSA);
		
		assertEquals(eventModel.getAllSubscribed(GUESTING.getId()), GSUBS);

	}
	
}
