package test;

import static org.junit.Assert.assertEquals;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import entities.Answer;
import entities.Question;
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
	
	@Test
	public void test_get_list_questions_for_quiz() {
		Quiz ten =em.find(Quiz.class, 10);
		Set<Question> questions = ten.getQuestions();
		assertEquals(questions.size(), 5);
	}
	@Test
	public void test_get_list_answers_for_questions() {
		Question one =em.find(Question.class, 1);
		List<Answer> answers = one.getAnswers();
		assertEquals(answers.size(), 4);
	}
	
	@Test
	public void test_get_quiz_for_question () {
		Question one =em.find(Question.class, 1);
		Quiz q = one.getQuiz();
		assertEquals(q.getId(), 10);
	}

}
