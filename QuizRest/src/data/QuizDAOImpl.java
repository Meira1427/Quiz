package data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.databind.ObjectMapper;
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
}
