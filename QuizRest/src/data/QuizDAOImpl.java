package data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entities.Quiz;

@Transactional
@Repository
public class QuizDAOImpl implements QuizDAO {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Quiz> getAll() {
		String queryString = "Select q from quiz q";
		return em.createQuery(queryString, Quiz.class)
				 .getResultList();
	}

	@Override
	public Quiz getById(int id) {
		return em.find(Quiz.class, id);
	}

}
