package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Question {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
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
