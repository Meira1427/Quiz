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
public class Answer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	//many answers for one question - multiple choice
	@JsonBackReference(value="answersForQuestion")
	@ManyToOne
	@JoinColumn(name="question_id")
	private Question question;
	
	@Column(name="answer_text")
	private String answerText;
	
	@Column(name="is_correct")
	private boolean isCorrect;

	public int getId() {
		return id;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getAnswerText() {
		return answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Answer [id=");
		builder.append(id);
		builder.append(", answerText=");
		builder.append(answerText);
		builder.append("]");
		return builder.toString();
	}

}
