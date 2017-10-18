package data;

import java.util.List;
import java.util.Set;

import entities.Question;
import entities.Quiz;

public interface QuizDAO {
	
	public List<Quiz> getAll();
	public Quiz getById(int id);
	public Quiz createNew(String quizJSON);
	public Quiz updateQuiz(int id, Quiz quiz);
	public boolean destroyQuiz(int id);
	public Set<Question> showQuestions(int id);
	public Question createQuestion(int id, Question q);
	public boolean destroyQuestion(int id, int questid);

}
