package test;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entities.Quiz;


public class QuizTest {
	
	private EntityManagerFactory emf = null;
	private EntityManager em = null;
	
	@Before
	public void setUp() throws Exception {
		emf = Persistence.createEntityManagerFactory("Quiz");
		em = emf.createEntityManager();
	}
	
	@After
	public void tearDown() throws Exception {
		em.close();
		emf.close();
	}
	
	@Test
	public void SmokeTest() {
	  boolean pass = true;
	  assertEquals(pass, true);
	}
	
	@Test
	public void test_quiz_returns_quiz1 () {
		Quiz test = em.find(Quiz.class, 1);
		assertEquals(test.getName(), "quiz1");
	}

}
