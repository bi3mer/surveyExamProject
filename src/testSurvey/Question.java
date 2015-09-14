package testSurvey;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Question {
	private String question;
	private String type; //this isn't necessarily necessary now, but added it for just in case
	private Output output;
	
	public Question(String  questionString,String type) {
		// TODO Auto-generated constructor stub
		this.setQuestion(questionString);
		this.setType(type);
		this.output = new Output();
	}
	
	public Question()
	{
		
	}
	
	
	protected String getResponse(Scanner scan, String text)
	{
		this.output.println(text);
		String answer = scan.nextLine(); //add test
		if(answer == null || answer.equals(""))
		{
			this.output.println("Invalid input");
			//this.loadSurvey();
			return getResponse(scan,text);
		}
		return answer;
	}
	
	public String getQuestion() 
	{
		return question;
	}
	public void setQuestion(String question) 
	{
		this.question = question;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public abstract ArrayList<String> getAnswer();
	public abstract ArrayList<String> getOptions();
	
	public abstract Question copy();
	public abstract Question copySurvey();
	
	public abstract String toString();
	public abstract String toStringSurvey();
	
	public abstract String toStringUserAnswers();
	public abstract String toStringSurveyUserAnswers();
	
	public abstract void display();
	
	public abstract void modifyExam();
	
	public abstract void modifySurvey();
	
	public abstract void makeExamQuestion();
	
	public abstract void makeSurveyQuestion();
	
	public abstract void setUserAnswer(ArrayList<String> x);
	
	public abstract ArrayList<String> getUserAnswer();
	
	public abstract double grade();
	
	public abstract void askForUserAnswer();
}
