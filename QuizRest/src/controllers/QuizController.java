package controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import data.QuizDAO;
import entities.Quiz;

@RestController
public class QuizController {
	
//	public String ping();
//	public List<Quiz> index(HttpServletResponse res);
//	public Quiz show(int id, HttpServletResponse res);
//	public Quiz create(String quizJSON, HttpServletResponse res);
//	public Quiz update(int id, String quizJSON, HttpServletResponse res);
//	public boolean destroy(int id, HttpServletResponse res);
//	public Set<Question> showQuestions(int id, HttpServletResponse res);
//	public Question createQuestions(int id, String questionJson, HttpServletResponse res);
//	public  boolean destroyQuestions(int id, int questid, HttpServletResponse res);
	
	@Autowired
	QuizDAO quizDao;
	
	@RequestMapping(path="ping", method=RequestMethod.GET)
	public String ping() {
		return "pong";
	}
	
	@RequestMapping(path="quizzes", method=RequestMethod.GET)
	public List<Quiz> index(HttpServletResponse res) {
		return quizDao.getAll();
	}
	
	@RequestMapping(path="quizzes/{id}", method=RequestMethod.GET)
	public Quiz index(@PathVariable int id, HttpServletResponse res) {
		Quiz q = quizDao.getById(id);
		if(!(q==null)) {
			res.setStatus(200); //okay found
		}
		else {
			res.setStatus(404); //not found
		}
		return q;
	}

}
