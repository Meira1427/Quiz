package data;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.databind.ObjectMapper;

import entities.Question;
import entities.Quiz;

@Transactional
@Repository
public class QuizDAOImpl implements QuizDAO {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Quiz> getAll() {
		String queryString = "Select q from Quiz q";
		return em.createQuery(queryString, Quiz.class)
				 .getResultList();
	}

	@Override
	public Quiz getById(int id) {
		return em.find(Quiz.class, id);
	}

	@Override
	public Quiz createNew(String quizJSON) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			Quiz mappedQuiz = mapper.readValue(quizJSON, Quiz.class);
			em.persist(mappedQuiz);
			em.flush();
			return mappedQuiz;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Quiz updateQuiz(int id, Quiz quiz) {
		Quiz managedQuiz = em.find(Quiz.class, id);
		if(managedQuiz != null) {
			if(quiz.getName() != null && quiz.getName().length() > 0 ) {
				managedQuiz.setName(quiz.getName());
			}
			if(quiz.getQuestions().size() > 0) {
				managedQuiz.setQuestions(quiz.getQuestions());
			}
		}
		return quiz;
	}

	@Override
	public boolean destroyQuiz(int id) {
		Quiz managedQuiz = em.find(Quiz.class, id);
		if(managedQuiz==null) {
			return false;
		}
		em.remove(managedQuiz);
		return true;
	}

	@Override
	public Set<Question> showQuestions(int id) {
		Quiz q = em.find(Quiz.class, id);
		return q.getQuestions();
	}

	@Override
	public Question createQuestion(int id, Question q) {
		Quiz quiz = em.find(Quiz.class, id);
		Set<Question> questions = showQuestions(id);
		em.persist(q);
		em.flush();
		questions.add(q);
		quiz.setQuestions(questions);
		updateQuiz(id, quiz);
		return q;
	}

	@Override
	public boolean destroyQuestion(int id, int questid) {
		Quiz quiz = em.find(Quiz.class, id);
		Question q = em.find(Question.class, questid);
		if(quiz==null || q==null) {
			return false;
		}
		em.remove(q);
		return true;
	}
}
