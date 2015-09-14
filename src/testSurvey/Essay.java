package testSurvey;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Essay extends Question{
	private String _answer;
	private String userAnswer;
	private Output output;
	
	public Essay(String questionString, String answer, String type) {
		// TODO Auto-generated constructor stub
		super(questionString,type);
		this.setAnswer(answer);
		this.output = new Output();
	}
	
	public Essay()
	{
		super();
	}
	

	public void setAnswer(String answer) {
		this._answer = answer;
	}
	//sample:{"question": "Is god human?", "answer": "None", "type": "essay"}
	public String toString(){
		//Question
		String string = "{\"question\":\"";
		string = string + this.getQuestion() + "\",";
		//answer and essay
		string = string + "\"answer\": \"None\", \"type\": \"essay\"}";
		return string;
	}
	
	public String toStringSurvey(){
		//Question
		String string = "{\"question\":\"";
		string = string + this.getQuestion() + "\",";
		//answer and essay
		string = string + "\"type\": \"essay\"}"; // removed "\"answer\": \"None\", 
		return string;
	}
	
	public void display(){
		this.output.println(this.getQuestion() + " Essay question");
	}
	
	@Override
	public void modifyExam() 
	{
		this.output.println("prompt: "+this.getQuestion());
		String in = this.getResponse(new Scanner(new InputStreamReader(System.in)), "Would you like to change this? 1 for yes, anything else for no");
		if(in.equals("1"))
		{ // change prompt
			this.output.println("Enter new prompt:");
			this.setQuestion(this.getResponse(new Scanner(new InputStreamReader(System.in)), "Would you like to change this? 1 for yes, anything else for no"));
		}
		// no case for else just leave and go back
	}
	
	@Override
	public void modifySurvey() 
	{
		this.modifyExam();
	}
	
	@Override
	public void makeExamQuestion() 
	{
		this.makeSurveyQuestion();
	}
	@Override
	public void makeSurveyQuestion() 
	{
		String question = this.getResponse(new Scanner(new InputStreamReader(System.in)), "Enter open ended question for essay:");
		this.setQuestion(question);
		this.setType("essay");
		this._answer = null;
	}

	@Override
	public String toStringUserAnswers() 
	{
		//question
		String string = "{\"question\":\"";
		string = string + this.getQuestion() + "\",";
		//user answer
		string = string + "\"user_answer\": \""+ this.userAnswer +"\",";
		//type
		string = string + "\"type\": \"essay\"}";		
		return string;
	}

	@Override
	public void setUserAnswer(ArrayList<String> x) 
	{
		this.userAnswer = x.get(0);
	}

	@Override
	public ArrayList<String> getUserAnswer() 
	{
		ArrayList<String> a = new ArrayList<String>();
		a.add(this.userAnswer);
		return a;
	}

	@Override
	public double grade() {
		// TODO Auto-generated method stub
		return -10;
	}

	@Override
	public void askForUserAnswer() 
	{
		this.userAnswer = this.getResponse(new Scanner(new InputStreamReader(System.in)), this.getQuestion() + " (Essay):");
	}

	@Override
	public String toStringSurveyUserAnswers() 
	{
		return this.toStringUserAnswers();
	}

	@Override
	public Question copy() {
		//String questionString, String answer, String type
		Question example = new Essay(this.getQuestion(),this._answer, this.getType());
		ArrayList<String> arr = new ArrayList<String>();
		arr.add(this.userAnswer);
		example.setUserAnswer(arr);
		example.setQuestion(this.getQuestion());
		return example;
	}

	@Override
	public ArrayList<String> getAnswer() 
	{
		return null;
	}

	@Override
	public Question copySurvey() 
	{
		Question example = new Essay(this.getQuestion(),this._answer, this.getType());
		ArrayList<String> arr = new ArrayList<String>();
		arr.add(this.userAnswer);
		example.setUserAnswer(arr);
		example.setQuestion(this.getQuestion());
		return example;
	}

	@Override
	public ArrayList<String> getOptions() {
		// TODO Auto-generated method stub
		return null;
	}
}
