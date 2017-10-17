package test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class QuizTest {
	
	private EntityManagerFactory emf = null;
	private EntityManager em = null;
	
	@Before
	public void setUp() {
		emf = Persistence.createEntityManagerFactory("Quiz");
		em = emf.createEntityManager();
	}
	
	@After
	public void tearDown() {
		em.close();
		emf.close();
	}
	
	@Test
	public void SmokeTest() {
	  boolean pass = true;
	  assertEquals(pass, true);
	}

}
