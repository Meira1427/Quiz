package data;

import java.util.List;

import entities.Quiz;

public interface QuizDAO {
	
	public List<Quiz> getAll();
	public Quiz getById(int id);
	

}
