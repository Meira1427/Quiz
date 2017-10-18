package entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Question {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	//one question has many answers - multiple choice
	@JsonManagedReference(value="answersForQuestion")
	@OneToMany(mappedBy="question", fetch=FetchType.EAGER)
	List<Answer> answers;
	
	//many questions on one quiz
	@JsonBackReference(value="questionsOnQuiz")
	@ManyToOne
	@JoinColumn(name="quiz_id")
	private Quiz quiz;
	
	@Column(name="question_text")
	private String questionText;

	public int getId() {
		return id;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Question [id=");
		builder.append(id);
		builder.append(", quiz=");
		builder.append(quiz);
		builder.append(", questionText=");
		builder.append(questionText);
		builder.append("]");
		return builder.toString();
	}

}
