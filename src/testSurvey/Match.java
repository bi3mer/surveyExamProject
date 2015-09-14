package testSurvey;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Match extends Question{
	private ArrayList<String> _options;
	private ArrayList<String> _answers;
	private ArrayList<String> userAnswers;
	private Output output;
	/*
	 * options lines up with answers. so it can be
	 * blam   	3
	 * asde		1
	 * wasd		2
	 */
	public Match(String questionString, ArrayList<String> options,ArrayList<String> answers, String type) {
		super(questionString,type);
		this.setOptions(options);
		this.set_answers(answers);
		userAnswers = new ArrayList<String>();
		this.output = new Output();
	}
	
	public Match()
	{
		super();
	}
	
	public ArrayList<String> getOptions() {
		return _options;
	}
	
	public void setOptions(ArrayList<String> options) {
		this._options = options;
	}
	
	public ArrayList<String> get_answers() {
		return _answers;
	}
	
	public void set_answers(ArrayList<String> _answers) {
		this._answers = _answers;
	}
	/*
	 {"question": "Match the following:", "options": [
				{"option": "option 1"},
				{"option": "option 2"},
				{"option": "option 3"},
				{"option": "option 4"}], 
		"type": "Match", "answers": [
				{"answer": "3"},
				{"answer": "2"},
				{"answer": "1"},
				{"answer": "4"}
			]
		}
	 */
	public String toString(){
		//question
		String string = "{\"question\":\"";
		string = string + this.getQuestion() + "\",";
		//options
		string = string + "\"options\": [";
		for(int i = 0;i<_options.size();i++){
			if(i <_options.size()-1){
				string = string + "{\"option\": \""+_options.get(i)+"\"},";
			} else{ //meaning this is the last one
				string = string + "{\"option\": \""+_options.get(i)+"\"}],";
			}
		}
		//type
		string = string + "\"type\": \"match\",";
		//answers
		string = string + "\"answers\": [";
		for(int i = 0;i<_answers.size();i++){
			if(i <_answers.size()-1){
				string = string + "{\"answer\": \""+_answers.get(i)+"\"},";
			} else{ //meaning this is the last one
				string = string + "{\"answer\": \""+_answers.get(i)+"\"}";
			}
		}
		string = string + "]}";
		return string;
	}
	
	public String toStringSurvey(){
		//question
		String string = "{\"question\":\"";
		string = string + this.getQuestion() + "\",";
		//options
		string = string + "\"options\": [";
		for(int i = 0;i<_options.size();i++){
			if(i <_options.size()-1){
				string = string + "{\"option\": \""+_options.get(i)+"\"},";
			} else{ //meaning this is the last one
				string = string + "{\"option\": \""+_options.get(i)+"\"}],";
			}
		}
		//type
		string = string + "\"type\": \"match\",";
		//answers
		string = string + "\"answers\": [";
		for(int i = 0;i<_answers.size();i++){
			if(i <_answers.size()-1){
				string = string + "{\"answer\": \""+_answers.get(i)+"\"},";
			} else{ //meaning this is the last one
				string = string + "{\"answer\": \""+_answers.get(i)+"\"}";
			}
		}
		string = string + "]}";
		return string;
	}
	public void display(){
		this.output.println(this.getQuestion() + " Match these");
		for(int i = 0;i<_options.size();i++){ // this will be more heavily randomized in the future
			this.output.println(_options.get(i) + "\t" + _answers.get(_options.size()-1-i)); 
		}
	}

	@Override
	public void modifyExam() 
	{
		// prompt
		this.output.println("prompt: " + this.getQuestion());
		String string = this.getResponse(new Scanner(new InputStreamReader(System.in)),"Would you like to change the prompt? 1 for yes, anything else for no");
		if(string.equals("1"))
		{
			this.changePrompt();
		}
		
		// options
		this.output.println(this.matchToString());
		
		string = this.getResponse(new Scanner(new InputStreamReader(System.in)),"Would you like to change these? 1 for yes, anything else for no");
		if(string.equals("1"))
		{
			this.changeOptionAnswer();
		}
		
	}
	
	private void changeOptionAnswer()
	{
		Boolean valid = true;
		ArrayList<String> options = new ArrayList<String>();
		ArrayList<String> answers = new ArrayList<String>();
		do 
		{ 
			String option = this.getResponse(new Scanner(new InputStreamReader(System.in)),"Enter option:");
			options.add(option);
			String answer = this.getResponse(new Scanner(new InputStreamReader(System.in)),"Could match with:");
			answers.add(answer);
			answer = this.getResponse(new Scanner(new InputStreamReader(System.in)),"Add another option? 1 for yes, anything else for no");
			if(! answer.equals("1"))
			{
				valid = false;
			}
		} while(valid == true);
		this._answers = answers;
		this._options = options;
	}
	
	private void changePrompt()
	{
		this.setQuestion(this.getResponse(new Scanner(new InputStreamReader(System.in)),"Enter new question:"));
	}
	
	@Override
	public void modifySurvey() 
	{
		this.modifyExam();
	}
	
	private String matchToString()
	{
		String string = new String();
		for(int i = 0; i< this._options.size();i++)
		{
			string = string + this._options.get(i) + "   " + this._answers.get(i) + "\n";
		}
		
		return string;
	}

	public void makeExamQuestion() 
	{
		this.makeSurveyQuestion();
	}

	@Override
	public void makeSurveyQuestion() 
	{
		this.changePrompt();
		this.changeOptionAnswer();
		this.setType("match");
	}

	@Override
	public String toStringUserAnswers() {
		String string = "{\"question\":\"";
		string = string + this.getQuestion() + "\",";
		//answer
		//string = string + "\"answer\": \""+ this._answer +"\",";
		string = string + "\"answers\":[";
		for(int i = 0 ;i <this._answers.size();i++)
		{
			if(i == this._answers.size()-1)
			{
				string = string + "{\"answer\": \"" + this._answers.get(i) + "\"}";
			}
			else
			{
				string = string + "{\"answer\": \"" + this._answers.get(i) + "\"},";
			}
		}
		string = string + "],";
		//user answer
		//string = string + "\"user_answer\": \""+ this.userAnswer +"\",";
		string = string + "\"user_answers\":[";
		for(int i = 0 ;i <this._answers.size();i++)
		{
			if(i == this._answers.size()-1)
			{
				string = string + "{\"answer\": \"" + this.userAnswers.get(i) + "\"}";
			}
			else
			{
				string = string + "{\"answer\": \"" + this.userAnswers.get(i) + "\"},";
			}	
		}
		string = string +"],";
		//type
		string = string + "\"type\": \"match\"}";		
		return string;
	}

	@Override
	public void setUserAnswer(ArrayList<String> x) {
		// TODO Auto-generated method stub
		this.userAnswers = x;
	}

	@Override
	public ArrayList<String> getUserAnswer() {
		// TODO Auto-generated method stub
		return this.userAnswers;
	}

	@Override
	public double grade() 
	{
		double grade = 0;
		double divisor = 10.0/(double)this._options.size();
		for(int i = 0; i < this._answers.size();i++)
		{
			if(this._answers.get(i).equals(this.userAnswers.get(i)))
			{
				grade+=divisor;
			}
		}
		return grade;
	}
	
	public void displayArray(ArrayList<String> arr)
	{
		for(int i = 0;i<arr.size();i++)
		{
			this.output.println(i+") " + arr.get(i));
		}
	}
	
	@Override
	public void askForUserAnswer() 
	{
		this.output.println("here");
		this.output.println(this.getQuestion() + " : Matching question");
		ArrayList<String> answered = new ArrayList<String>();
		ArrayList<Integer> taken = new ArrayList<Integer>();
		for(int i = 0 ;i <this._options.size();i++)
		{
			this.output.println(this._options.get(i) + " matches with:");
			this.displayArray(this._answers);
			String response = new String();
			boolean valid = false;
			while(valid == false)
			{
				response = this.getResponse(new Scanner(new InputStreamReader(System.in)),"Enter corresponding number:");
				try 
				{
					int num = Integer.parseInt(response);
					if(num < _options.size() && num >=0)
					{
						if(!taken.contains(num))
						{
							valid = true;
						}
						else
						{
							this.output.println("You've already used this option:" + this._answers.get(num));
						}
					}
					else
					{
						this.output.println("Invalid number given: " +num);
					}
				}
				catch (NumberFormatException e) 
				{
					this.output.println("Invalid: Enter number please");
				}
			}
			int num = Integer.parseInt(response);
			taken.add(num);
			answered.add(this._answers.get((num)));
		}
		this.userAnswers = answered;
	}

	@Override
	public String toStringSurveyUserAnswers() {
		String string = "{\"question\":\"";
		string = string + this.getQuestion() + "\",";
		//user answer
		string = string + "\"user_answers\":[";
		for(int i = 0 ;i <this._answers.size();i++)
		{
			if(i == this._answers.size()-1)
			{
				string = string + "{\"answer\": \"" + this.userAnswers.get(i) + "\"}";
			}
			else
			{
				string = string + "{\"answer\": \"" + this.userAnswers.get(i) + "\"},";
			}	
		}
		string = string +"],";
		//type
		string = string + "\"type\": \"match\"}";		
		return string;
	}

	@Override
	public Question copy() {
		Question example = new Match(this.getQuestion(),this._options,this._answers, this.getType());
		example.setUserAnswer(this.userAnswers);
		return example;
	}

	@Override
	public ArrayList<String> getAnswer() {
		// TODO Auto-generated method stub
		return this._answers;
	}

	@Override
	public Question copySurvey() 
	{
		Question example = new Match(this.getQuestion(),this._options,this._answers, this.getType());
		example.setUserAnswer(this.userAnswers);
		return example;
	}
}
