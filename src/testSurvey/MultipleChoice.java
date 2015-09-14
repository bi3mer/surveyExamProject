package testSurvey;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class MultipleChoice extends Question{
	private ArrayList<String> options;
	private String _answer;
	private String userAnswer;
	private Output output;
	
	public MultipleChoice(String questionString, ArrayList<String> options, String answer, String type) {
		// TODO Auto-generated constructor stub
		super(questionString,type);
		this.setOptions(options);
		this.setAnswer(answer);
		this.output = new Output();
	}
	
	public MultipleChoice()
	{
		super();
	}
	
	public ArrayList<String> getOptions() {
		return options;
	}
	public void setOptions(ArrayList<String> options) {
		this.options = options;
	}
	
	public void setAnswer(String answer) {
		this._answer = answer;
	}
	/*
	 sample:
	 {"question": "Which number is largest?", "answer": "20", "type": "multipleChoice", "answers": [
				{"answer": "20"},
				{"answer": "10"},
				{"answer": "3"},
				{"answer": "4"}
			]
		}
	 */
	public String toString(){
		//question
		String string = "{\"question\":\"";
		string = string + this.getQuestion() + "\",";
		//answer
		string = string + "\"answer\": \""+ this.getAnswer().get(0) +"\",";
		//type
		string = string + "\"type\": \"multipleChoice\",";
		//answers
		string = string + "\"answers\": [";
		for(int i = 0;i<options.size();i++){
			if(i <options.size()-1){
				string = string + "{\"answer\": \""+options.get(i)+"\"},";
			} else{ //meaning this is the last one
				string = string + "{\"answer\": \""+options.get(i)+"\"}";
			}
		}
		string = string + "]}";
		return string;
	}
	
	public String toStringSurvey(){
		//question
		String string = "{\"question\":\"";
		string = string + this.getQuestion() + "\",";
		//answer
		//string = string + "\"answer\": \""+ this.getAnswer() +"\",";
		//type
		string = string + "\"type\": \"multipleChoice\",";
		//answers
		string = string + "\"answers\": [";
		for(int i = 0;i<options.size();i++){
			if(i <options.size()-1){
				string = string + "{\"answer\": \""+options.get(i)+"\"},";
			} else{ //meaning this is the last one
				string = string + "{\"answer\": \""+options.get(i)+"\"}";
			}
		}
		string = string + "]}";
		return string;
	}
	
	public void display(){
		this.output.println(this.getQuestion() + " Multiple choice");
		for(int i = 0;i<options.size();i++){
			this.output.println(options.get(i));
		}
	}
	
	public String optionsToString()
	{
		String optionString = new String();
		for(int i = 0; i< this.options.size();i++)
		{
			optionString = optionString + i + ") " + this.options.get(i) + "\n";
		}
		return optionString;
	}
	
	private void changeAnswer()
	{
		for(int i = 0; i < options.size();i++)
		{
			this.output.println(i + ": " + options.get(i));
		}
		String response = this.getResponse(new Scanner(new InputStreamReader(System.in)),"Enter number corresponding correct answer to multiple choice");
		try 
		{
			int num = Integer.parseInt(response);
			if(num < options.size() && num >=0)
			{
				// we now have all the values needed if we're in here
				this.setAnswer(options.get(num));
			} 
			else 
			{
				this.output.println("Invalid number: "+ num +" doesn't fit in parameters given above");
				this.changeAnswer();
			}
		} 
		catch (NumberFormatException e) 
		{
			this.output.println("Invalid: Enter number please");
		}
	}
	
	private void changeOptions()
	{
		Boolean valid = true;
		ArrayList<String> options = new ArrayList<String>();
		do 
		{ //ensures that atleast one option is prevalent
			String option = this.getResponse(new Scanner(new InputStreamReader(System.in)),"Add option:");
			options.add(option);
			option = this.getResponse(new Scanner(new InputStreamReader(System.in)),"Add another option? 1 for yes, anything else for no");
			if(!option.equals("1"))
			{
				valid = false;
			}
		} while(valid == true);
		this.options = options;
	}
	
	@Override
	public void modifyExam() 
	{
		// can run modify survey since if options are changed answer must be changed
		// change answer
		this.output.println("Prompt: " + this.getQuestion());
		String answer = this.getResponse(new Scanner(new InputStreamReader(System.in)),"Would you like to change the prompt that is above? 1 for yes, anything else for no");
		if(answer.equals("1"))
		{
			this.changePrompt();;
		}
		// change options
		this.output.println(this.optionsToString());
		answer = this.getResponse(new Scanner(new InputStreamReader(System.in)),"Would you like to change the options that are above? 1 for yes, anything else for no");
		if(answer.equals("1"))
		{
			this.changeOptions();
			//change answer
			this.changeAnswer();
		}
		else
		{
			// check if wants to change answer
			this.output.println("Answer: " + this._answer);
			String option = answer = this.getResponse(new Scanner(new InputStreamReader(System.in)),"Would you like to change the answer? 1 for yes, anything else for no.");
			// if yes change
			if(option.equals("1"))
			{
				this.changeAnswer();
			}
		}
	}
	
	@Override
	public void modifySurvey() 
	{
		// change prompt
		this.output.println("Prompt: " + this.getQuestion());
		String answer = this.getResponse(new Scanner(new InputStreamReader(System.in)),"Would you like to change the prompt that is above? 1 for yes, anything else for no");
		if(answer.equals("1"))
		{
			this.changePrompt();
		}
		// change options
		this.output.println(this.optionsToString());
		answer = this.getResponse(new Scanner(new InputStreamReader(System.in)),"Would you like to change the options that are above? 1 for yes, anything else for no");
		if(answer.equals("1"))
		{
			this.changeOptions();
		}
	}
	
	private void changePrompt()
	{
		this.setQuestion(this.getResponse(new Scanner(new InputStreamReader(System.in)),"Enter new question:"));
	}
	
	@Override
	public void makeExamQuestion() 
	{
		this.changePrompt();
		this.changeOptions();
		this.changeAnswer();
		this.setType("multipleChoice");
	}
	
	@Override
	public void makeSurveyQuestion() 
	{
		// TODO Auto-generated method stub
		this.changePrompt();
		this.changeOptions();
		this._answer = null;
		this.setType("multipleChoice");
	}

	@Override
	public String toStringUserAnswers() 
	{
		//question
		String string = "{\"question\":\"";
		string = string + this.getQuestion() + "\",";
		//answer
		string = string + "\"answer\": \""+ this._answer +"\",";
		//user answer
		string = string + "\"user_answer\": \""+ this.userAnswer +"\",";
		//type
		string = string + "\"type\": \"multipleChoice\"}";		
		return string;
	}


	@Override
	public void setUserAnswer(ArrayList<String> x) 
	{
		this.userAnswer = x.get(0);
	}

	@Override
	public ArrayList<String> getUserAnswer() {
		ArrayList<String> ans = new ArrayList<String>();
		ans.add(this.userAnswer);
		return ans;
	}

	@Override
	public double grade() 
	{
		if(this.userAnswer.equals(this._answer))
		{
			return 10;
		}
		return 0;
	}

	@Override
	public void askForUserAnswer() 
	{
		this.output.println(this.getQuestion());
		this.output.println(this.optionsToString());
		String response = this.getResponse(new Scanner(new InputStreamReader(System.in)),"Enter number corresponding correct answer to multiple choice");
		try 
		{
			int num = Integer.parseInt(response);
			if(num < options.size() && num >=0)
			{
				// we now have all the values needed if we're in here
				this.userAnswer = options.get(num);
			} 
			else 
			{
				this.output.println("Invalid number: "+ num +" doesn't fit in parameters given above");
				this.askForUserAnswer();
			}
		} 
		catch (NumberFormatException e) 
		{
			this.output.println("Invalid: Enter number please");
			this.askForUserAnswer();
		}
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
		string = string + "\"type\": \"multipleChoice\"}";		
		return string;
	}

	@Override
	public Question copy() 
	{
		Question example = new MultipleChoice(this.getQuestion(),this.options,this._answer, this.getType());
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
	public Question copySurvey() {
		Question example = new MultipleChoice(this.getQuestion(),this.options,this._answer, this.getType());
		ArrayList<String> arr = new ArrayList<String>();
		arr.add(this.userAnswer);
		example.setUserAnswer(arr);
		example.setQuestion(this.getQuestion());
		return example;
	}

}
