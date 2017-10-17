package data;

import java.util.List;

import entities.Quiz;

public interface QuizDAO {
	
	public List<Quiz> getAll();
	public Quiz getById(int id);
	public Quiz createNew(String quizJSON);
	public Quiz updateQuiz(int id, Quiz quiz);
	public boolean destroyQuiz(int id);
	

}
