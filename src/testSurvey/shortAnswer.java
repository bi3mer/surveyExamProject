package testSurvey;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class shortAnswer extends Question{
	private String _answer;
	private String userAnswer;
	private Output output;
	
	public shortAnswer(String questionString, String answer, String type) {
		// TODO Auto-generated constructor stub
		super(questionString, type);
		this.setAnswer(answer);
		this.output = new Output();
	}
	
	public shortAnswer(){
		super();
	}

	public void setAnswer(String answer) {
		this._answer = answer;
	}
	public Boolean checkAnswer(String uAnswer){
		if(uAnswer.equals(this._answer)){
			return true;
		}
		return false;
	}
	//sample: {"question": "Fuck the ______", "answer":"police", "type": "shortAnswer"}
	public String toString(){
		//question
		String string = "{\"question\":\"";
		string = string + this.getQuestion() + "\",";
		//answer
		string = string + "\"answer\": \""+ this.getAnswer().get(0) +"\",";
		//type
		string = string + "\"type\": \"shortAnswer\"}";		
		return string;
	}
	
	public String toStringSurvey(){
		//question
		String string = "{\"question\":\"";
		string = string + this.getQuestion() + "\",";
		//answer
		//string = string + "\"answer\": \""+ this.getAnswer() +"\",";
		//type
		string = string + "\"type\": \"shortAnswer\"}";		
		return string;
	}
	
	public void display(){
		this.output.println(this.getQuestion() + " Fill in the blank.");
	}
	
	@Override
	public void modifyExam() {
		this.modifySurvey();
		//Change Answer
		this.output.println("Answer: "+ this.getAnswer().get(0));
		String answer = this.getResponse(new Scanner(new InputStreamReader(System.in)),"Would you like to change the answer? 1 for yes, anything else for no");
		if(answer.equals("1"))
		{
			this.changeAnswer();
		}
		// no else case, answer doesn't get changed
	}
	
	@Override
	public void modifySurvey() {
		// Change Prompt
		this.output.println("Prompt: " + this.getQuestion());
		String answer = this.getResponse(new Scanner(new InputStreamReader(System.in)),"Would you like to change the prompt? 1 for yes, anything else for no");
		if(answer.equals("1"))
		{
			this.changePrompt();
		}
	}
	
	private void changeAnswer()
	{
		this._answer = this.getResponse(new Scanner(new InputStreamReader(System.in)),"Enter new answer:");
	}
	
	private void changePrompt()
	{
		this.setQuestion(this.getResponse(new Scanner(new InputStreamReader(System.in)),"Enter new prompt:"));
	}
	
	@Override
	public void makeExamQuestion() 
	{
		this.makeSurveyQuestion();
		this.changeAnswer();
	}
	
	@Override
	public void makeSurveyQuestion() 
	{
		this.changePrompt();
		this._answer = null;
		this.setType("shortAnswer");
	}

	@Override
	public String toStringUserAnswers() {
		//question
		String string = "{\"question\":\"";
		string = string + this.getQuestion() + "\",";
		//answer
		string = string + "\"answer\": \""+ this._answer +"\",";
		//user answer
		string = string + "\"user_answer\": \""+ this.userAnswer +"\",";
		//type
		string = string + "\"type\": \"shortAnswer\"}";		
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
		ArrayList<String> answers = new ArrayList<String>();
		answers.add(this.userAnswer);
		return answers;
	}

	@Override
	public double grade() 
	{
		if(this._answer.equals(this.userAnswer))
		{
			return 10;
		}
		return 0;
	}

	@Override
	public void askForUserAnswer() 
	{
		this.output.println("Question (fill in the blank or short answer): " + this.getQuestion());
		String questionString = this.getResponse(new Scanner(new InputStreamReader(System.in)), "Fill in the blank:");
		this.userAnswer = questionString;
	}

	@Override
	public String toStringSurveyUserAnswers() 
	{
		//question
		String string = "{\"question\":\"";
		string = string + this.getQuestion() + "\",";
		//user answer
		string = string + "\"user_answer\": \""+ this.userAnswer +"\",";
		//type
		string = string + "\"type\": \"shortAnswer\"}";		
		return string;
	}

	@Override
	public Question copy() 
	{
		//String questionString, String answer, String type
		Question example = new shortAnswer(this.getQuestion(),this._answer, this.getType());
		ArrayList<String> arr = new ArrayList<String>();
		arr.add(this.userAnswer);
		example.setUserAnswer(arr);
		example.setQuestion(this.getQuestion());
		return example;
	}

	@Override
	public ArrayList<String> getAnswer() 
	{
		ArrayList<String> blam = new ArrayList<String>();
		blam.add(this._answer);
		return blam;
	}

	@Override
	public Question copySurvey() 
	{
		//String questionString, String answer, String type
		Question example = new shortAnswer(this.getQuestion(),this._answer, this.getType());
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
