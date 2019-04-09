package application.models.user;

import application.entities.EventEntity;
import application.entities.SubscriptionEntity;
import application.entities.SubscriptionEntity.EventGuestId;
import application.entities.UserEntity;
import application.exceptions.ExceptionMishpaha;
import application.models.event.EventModel;
import application.models.event.IEventModel;
import application.repositories.*;
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

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

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
	SubscriptionRepository subscriptionsRepo;
	@MockBean
	ReligionRepository religionRepo;
	@MockBean
	KichenTypeRepository kichenTypeRepo;
	@MockBean
	HolyDayRepository holyDayRepo;

	
	@Autowired
	private IEventModel eventModel;
	

	private final UserEntity ALYSSA = new UserEntity();
	private final UserEntity BEN = new UserEntity();
	private final LocalDate TDATE = LocalDate.of(2190, 1, 1);
	private final LocalTime TTIME = LocalTime.of(23, 59);
	private EventEntity GUESTING;
	private SubscriptionEntity AGUESTING;
	private final Set<EventEntity> ASUBS = new HashSet<>();
	private final Set<UserEntity> GSUBS = new HashSet<>();
	
	
	@Before
	public void buildEntities() {
		
		ALYSSA.setEMail("p_hacker@sicp.edu");
		BEN.setEMail("bitdiddle@sicp.edu");
		userRepo.save(BEN);
		userRepo.save(ALYSSA);		
		GUESTING = new EventEntity(BEN, TDATE, TTIME);
		eventRepo.save(GUESTING);
		AGUESTING  = new SubscriptionEntity(ALYSSA, GUESTING);
		
	}
	
	
	@Test
	public void getAllByUser() { //TODO: proper named methods please

		Mockito.when(userRepo.save(BEN)).thenReturn(BEN);
		Mockito.when(userRepo.save(ALYSSA)).thenReturn(ALYSSA);
		Mockito.when(eventRepo.save(GUESTING)).thenReturn(GUESTING);
		Mockito.when(subscriptionsRepo.save(AGUESTING)).thenReturn(AGUESTING);
		Mockito.when(userRepo.getOne(ALYSSA.getId())).thenReturn(ALYSSA);
		
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
		
		GSUBS.add(ALYSSA);
		
		assertEquals(eventModel.getAllSubscribed(GUESTING.getId()), GSUBS);

	}
	
	@Test
	public void subscribe() {
		
		Mockito.when(userRepo.save(BEN)).thenReturn(BEN);
		Mockito.when(userRepo.save(ALYSSA)).thenReturn(ALYSSA);
		Mockito.when(eventRepo.save(GUESTING)).thenReturn(GUESTING);
		Mockito.when(subscriptionsRepo.save(AGUESTING)).thenReturn(AGUESTING);
		
		ALYSSA.setId(2); //TODO: generated Id with Mockito please
		BEN.setId(1);
		GUESTING.setId(1);
		
		EventGuestId idAG = new EventGuestId(ALYSSA.getId(), GUESTING.getId()); 
		Mockito.when(subscriptionsRepo.getOne(idAG)).thenReturn(AGUESTING);
		SubscriptionEntity subAtoG = subscriptionsRepo.getOne(idAG); 
		
		Mockito.when(eventRepo.getOne(GUESTING.getId())).thenReturn(GUESTING);
		Mockito.when(userRepo.getOne(ALYSSA.getId())).thenReturn(ALYSSA);
		Mockito.when(userRepo.getOne(BEN.getId())).thenReturn(BEN);

		try {
			assertEquals(eventModel.subscribe(GUESTING.getId(), ALYSSA.getId()), GUESTING);
			assertTrue(ALYSSA.getSubscriptions().contains(subAtoG));
			assertTrue(GUESTING.getSubscriptions().contains(subAtoG));
		} catch (ExceptionMishpaha e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void unsubscribe() { //TODO: fix me please
		
		Mockito.when(userRepo.save(BEN)).thenReturn(BEN);
		Mockito.when(userRepo.save(ALYSSA)).thenReturn(ALYSSA);
		Mockito.when(eventRepo.save(GUESTING)).thenReturn(GUESTING);
		Mockito.when(subscriptionsRepo.save(AGUESTING)).thenReturn(AGUESTING);
		
		ALYSSA.setId(2); //TODO: generated Id with Mockito please
		BEN.setId(1);
		GUESTING.setId(1);
		
		EventGuestId idAG = new EventGuestId(ALYSSA.getId(), GUESTING.getId()); 
		Mockito.when(subscriptionsRepo.getOne(idAG)).thenReturn(AGUESTING);
		SubscriptionEntity subAtoG = subscriptionsRepo.getOne(idAG); 
		
		Mockito.when(eventRepo.getOne(GUESTING.getId())).thenReturn(GUESTING);
		Mockito.when(userRepo.getOne(ALYSSA.getId())).thenReturn(ALYSSA);
		Mockito.when(userRepo.getOne(BEN.getId())).thenReturn(BEN);
		Mockito.when(subscriptionsRepo.existsById(idAG)).thenReturn(true);
		
		try {
			assertEquals(eventModel.unsubscribe(GUESTING.getId(), ALYSSA.getId()), GUESTING);
			Mockito.when(subscriptionsRepo.getOne(idAG)).thenReturn(AGUESTING);
			subAtoG = subscriptionsRepo.getOne(idAG); 
			assertFalse(ALYSSA.getSubscriptions().contains(subAtoG));
			assertFalse(GUESTING.getSubscriptions().contains(subAtoG));
		} catch (ExceptionMishpaha e) {
			e.printStackTrace();
		}
	}
	
	//TODO: tests on deactivate and cancel pls
}
