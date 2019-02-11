package Application.entities;

import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import Application.repo.UserRepository;

/**
 * Testing creation and deletion of entities to ensure relations work as
 * intended;
 * 
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@Transactional
public class UserTest {

	// TODO: create-delete author and book please

//	private static final String GERMANY = "Germany";
//	private static final String RED_SEA = "Red Sea";
//	private static final List<Integer> JOYCEMARKS = Arrays.asList(new Integer[]{5, 5, 4});
//	private static final AuthorId JOYCE = new AuthorId("James", "Joyce");
//	private static final Author[] authorArray = { new Author(JOYCE, JOYCEMARKS) };
//	private static final Set<Author> AUTHORS = new HashSet<Author>(Arrays.asList(authorArray));
//	private static final Publisher RED_SEA_GERMANY = new Publisher(RED_SEA, new Country(GERMANY));
//	private static final Book ULYSSES = new Book(1l, AUTHORS, "ULYSSES", RED_SEA_GERMANY, LocalDate.of(1919, 3, 15),
//			30., new ArrayList<BookLogs>());
//	private static final Book EXILES = new Book(1l, AUTHORS, "Exiles and poetry", RED_SEA_GERMANY, LocalDate.of(1918, 1, 1),
//			30., new ArrayList<BookLogs>());
//	private static final BookLogs log1 = new BookLogs(LocalDate.now(), LocalTime.now(), UserActions.STATUS_CHANGE, "test_log");
//	
	private static final UserItem ALYSSA = new UserItem();
	
	{
		
		
	}

	
	@PersistenceContext 
	private EntityManager em;
	
	@Autowired
	UserRepository userRepo;


	@Before
	public void clear() {
		userRepo.deleteAll();
	}
	
	@Before 
	public void buildUser() {
		
		ALYSSA.setFirstName("Alyssa");
		
	}

	

	/**
	 * Testing add, with explicit saving
	 */
	@Test
	public void addUser() {
		UserItem createdUser = ALYSSA;
		

		userRepo.save(createdUser);
		userRepo.flush();


		UserItem persistedUser = userRepo.findById(createdUser.getId()).get();
		System.out.println(persistedUser);
		assertTrue(persistedUser.equals(createdUser));
	}
}
