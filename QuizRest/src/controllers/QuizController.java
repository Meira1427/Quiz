package controllers;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import data.QuizDAO;
import entities.Question;
import entities.Quiz;

@RestController
public class QuizController {	
	
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
	public Quiz show(@PathVariable int id, HttpServletResponse res) {
		Quiz q = quizDao.getById(id);
		if(!(q==null)) {
			res.setStatus(200); //okay found
		}
		else {
			res.setStatus(404); //not found
		}
		return q;
	}
	
	@RequestMapping(path="quizzes", method=RequestMethod.POST)
	public Quiz create(@RequestBody String quizJSON, HttpServletResponse res) {
		res.setStatus(201);
		return quizDao.createNew(quizJSON);
	}
	
	@RequestMapping(path="quizzes/{id}", method=RequestMethod.PUT) 
	public Quiz update(int id, @RequestBody String quizJSON, HttpServletResponse res) {
		ObjectMapper mapper = new ObjectMapper();
		Quiz mappedQuiz = null;
		try {
			mappedQuiz = mapper.readValue(quizJSON, Quiz.class);
			quizDao.updateQuiz(id, mappedQuiz);
			res.setStatus(202);
		} catch (IOException e) {
			res.setStatus(400);
			e.printStackTrace();
		}
		return mappedQuiz;
	}
	
	@RequestMapping(path="quizzes/{id}", method=RequestMethod.DELETE)
	public boolean destroy(int id) {
		return quizDao.destroyQuiz(id);
	}
	
	public Set<Question> showQuestions(int id, HttpServletResponse res) {
		return null;
	}
	
	public Question createQuestions(int id, String questionJson, HttpServletResponse res) {
		return null;
	}
	
	public boolean destroyQuestions(int id, int questid, HttpServletResponse res) {
		return false;
	}

}
