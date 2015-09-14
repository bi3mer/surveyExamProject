package testSurvey;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class trueFalse extends Question
{
	private Boolean _answer;
	private Boolean userAnswer;
	private Output output;
	
	public trueFalse(String questionString, String answer, String type) 
	{
		// TODO Auto-generated constructor stub
		super(questionString,type);
		this.output = new Output();
		if(answer == null) // this means its a survey instead of a test question
		{
			this.setAnswer(null);
		}
		else {
			if(answer.equals("true"))
			{
				this.setAnswer(true);
			} 
			else 
			{
				this.setAnswer(false);
			}
		}
	}
	
	public trueFalse()
	{
		super();
	}
	
	public void setAnswer(Boolean answer) 
	{
		this._answer = answer;
	}
	public Boolean checkAnswer(Boolean answer)
	{
		if(answer == this._answer)
		{
			return true;
		}
		return false;
	}
	//sample: {"question": "Is there a god?", "answer":"true", "type": "trueFalse"}
	public String toString()
	{
		//question
		String string = "{\"question\":\"";
		string = string + this.getQuestion() + "\",";
		//answer
		string = string + "\"answer\": \""+ this.getAnswer().get(0) +"\",";
		//type
		string = string + "\"type\": \"trueFalse\"}";		
		return string;
	}
	
	public String toStringSurvey()
	{
		//question
		String string = "{\"question\":\"";
		string = string + this.getQuestion() + "\",";
		//answer
		//string = string + "\"answer\": \""+ this.getAnswer() +"\",";
		//type
		string = string + "\"type\": \"trueFalse\"}";		
		return string;
	}
	
	public void display()
	{
		this.output.println(this.getQuestion() + " True or False");
	}
	
	@Override
	public void modifyExam() 
	{
		this.modifySurvey();
		//Change Answer
		this.output.println("Answer: "+ this.getAnswer().get(0));
		String answer = this.getResponse(new Scanner(new InputStreamReader(System.in)),"Would you like to change the answer? 1 for yes, anything else for no");
		if(answer.equals("1"))
		{
			//answer = this.getResponse(new Scanner(new InputStreamReader(System.in)),"Enter 1 for true, 0 for anything elser:");
			if(this._answer == true)
			{
				this._answer = false;
			}
			else
			{
				this._answer = true;
			}
		}
	}
	
	@Override
	public void modifySurvey() 
	{
		// TODO Auto-generated method stub
		// Change Prompt
		this.output.println("Prompt: " + this.getQuestion());
		String answer = this.getResponse(new Scanner(new InputStreamReader(System.in)),"Would you like to change the prompt? 1 for yes, anything else for no");
		if(answer.equals("1"))
		{
			this.setQuestion(this.getResponse(new Scanner(new InputStreamReader(System.in)),"Enter new question:"));
		}
	}
	
	@Override
	public void makeExamQuestion() 
	{
		String questionString = this.getResponse(new Scanner(new InputStreamReader(System.in)), "Enter question for true false:");
		String answer = this.getResponse(new Scanner(new InputStreamReader(System.in)), "Enter answer 1 for true, anything else for false");
		
		if(answer.equals("1"))
		{ // true
			this.setQuestion(questionString);
			this.setType("trueFalse");
			this._answer = true;
		} 
		else 
		{ //false
			this.setQuestion(questionString);
			this.setType("trueFalse");
			this._answer = false;
		}	
	}
	@Override
	public void makeSurveyQuestion() 
	{
		String questionString = this.getResponse(new Scanner(new InputStreamReader(System.in)), "Enter question for true false:");
		this.setQuestion(questionString);
		this.setType("trueFalse");
		this._answer = null;
	}

	@Override
	public String toStringUserAnswers() 
	{
		//question
		String string = "{\"question\":\"";
		string = string + this.getQuestion() + "\",";
		//answer
		if(this._answer)
		{
			string = string + "\"answer\": \""+ "true" +"\",";
		}
		else
		{
			string = string + "\"answer\": \""+ "false" +"\",";
		}
		//user answer
		if(this.userAnswer)
		{
			string = string + "\"user_answer\": \""+ "true" +"\",";
		}
		else
		{
			string = string + "\"user_answer\": \""+ "false" +"\",";
		}
		//type
		string = string + "\"type\": \"trueFalse\"}";		
		return string;
	}

	@Override
	public void setUserAnswer(ArrayList<String> x) 
	{
		if(x.get(0).equals("true"))
		{
			this.userAnswer = true;
		}
		else
		{
			this.userAnswer = false;
		}
	}

	@Override
	public ArrayList<String> getUserAnswer() 
	{
		ArrayList<String> answers = new ArrayList<String>();
		if(this.userAnswer)
		{
			answers.add("true");
		}
		else
		{
			answers.add("false");
		}
		return answers;
	}

	@Override
	public double grade() 
	{
		if(this.userAnswer == this._answer)
		{
			return 10;
		}
		return 0;
	}

	@Override
	public void askForUserAnswer() 
	{
		this.output.println("Question (true or false): " + this.getQuestion());
		String questionString = this.getResponse(new Scanner(new InputStreamReader(System.in)), "Enter 1 for true, 0 for anything else:");
		if(questionString.equals("1"))
		{
			this.userAnswer = true;
		}
		else
		{
			this.userAnswer = false;
		}
	}

	@Override
	public String toStringSurveyUserAnswers() 
	{
		//question
		String string = "{\"question\":\"";
		string = string + this.getQuestion() + "\",";
		//user answer
		if(this.userAnswer)
		{
			string = string + "\"user_answer\": \""+ "true" +"\",";
		}
		else
		{
			string = string + "\"user_answer\": \""+ "false" +"\",";
		}
		//type
		string = string + "\"type\": \"trueFalse\"}";		
		return string;
	}

	@Override
	public Question copy() {
		//String questionString, String answer, String type
		String answer = new String();
		if(this._answer)
		{
			answer = "true";
		}
		else
		{
			answer = "false";
		}
		Question example = new trueFalse(this.getQuestion(),answer, this.getType());
		ArrayList<String> arr = new ArrayList<String>();
		answer = new String();
		if(this.userAnswer)
		{
			answer = "true";
		}
		else
		{
			answer = "false";
		}
		arr.add(answer);
		example.setUserAnswer(arr);
		example.setQuestion(this.getQuestion());
		return example;
	}
	
	public Question copSurveyy() {
		//String questionString, String answer, String type

		Question example = new trueFalse(this.getQuestion(),null, this.getType());
		ArrayList<String> arr = new ArrayList<String>();
		String answer = new String();
		if(this.userAnswer)
		{
			answer = "true";
		}
		else
		{
			answer = "false";
		}
		arr.add(answer);
		example.setUserAnswer(arr);
		example.setQuestion(this.getQuestion());
		return example;
	}

	@Override
	public ArrayList<String> getAnswer() 
	{
		ArrayList<String> blam = new ArrayList<String>();
		String answer = new String();
		if(this.userAnswer != null && this.userAnswer)
		{
			answer = "true";
		}
		else
		{
			answer = "false";
		}
		blam.add(answer);
		return blam;
	}

	@Override
	public Question copySurvey() {
		//String questionString, String answer, String type
		String answer = new String();
		Question example = new trueFalse(this.getQuestion(),null, this.getType());
		ArrayList<String> arr = new ArrayList<String>();
		answer = new String();
		if(this.userAnswer)
		{
			answer = "true";
		}
		else
		{
			answer = "false";
		}
		arr.add(answer);
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
