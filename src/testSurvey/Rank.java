package testSurvey;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Rank extends Question{
	private ArrayList<String> _options;
	private ArrayList<String> _answers;
	private ArrayList<String> userAnswers;
	private Boolean changed;
	private Output output;
	
	/*
	 * options lines up with answers. so it can be
	 * blam   	3
	 * asde		1
	 * wasd		2
	 */
	public Rank(String questionString, ArrayList<String> options,ArrayList<String> answers, String type) {
		super(questionString,type);
		this.setOptions(options);
		this.set_answers(answers);
		this.changed = false;
		this.userAnswers = new ArrayList<String>();
		this.output = new Output();
	}
	
	public Rank()
	{
		super();
		this.changed = false;
	}
	
	public ArrayList<String> getOptions() 
	{
		return _options;
	}
	
	public void setOptions(ArrayList<String> options) 
	{
		this._options = options;
	}
	
	public ArrayList<String> get_answers() 
	{
		return _answers;
	}
	
	public void set_answers(ArrayList<String> _answers) 
	{
		this._answers = _answers;
	}
	/*
	 {"question": "Rank the following:", "options": [
				{"option": "option 1"},
				{"option": "option 2"},
				{"option": "option 3"},
				{"option": "option 4"}], 
		"type": "Rank", "answers": [
				{"answer": "3"},
				{"answer": "2"},
				{"answer": "1"},
				{"answer": "4"}
			]
		}
	 */
	public String toString()
	{
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
		string = string + "\"type\": \"rank\",";
		//answers
		string = string + "\"answers\": [";
		for(int i = 0;i<this._answers.size();i++)
		{
			if(i <_answers.size()-1)
			{
				string = string + "{\"answer\": \""+_answers.get(i)+"\"},";
			} else
			{ //meaning this is the last one
				string = string + "{\"answer\": \""+_answers.get(i)+"\"}";
			}
		}
		string = string + "]}";
		return string;
	}
	
	public String toStringSurvey()
	{
		//question
		String string = "{\"question\":\"";
		string = string + this.getQuestion() + "\",";
		//options
		string = string + "\"options\": [";
		for(int i = 0;i<_options.size();i++)
		{
			if(i <_options.size()-1)
			{
				string = string + "{\"option\": \""+_options.get(i)+"\"},";
			} 
			else
			{ //meaning this is the last one
				string = string + "{\"option\": \""+_options.get(i)+"\"}],";
			}
		}
		//type
		string = string + "\"type\": \"rank\""; // removed ","
		//answers
		string = string + "}";
		return string;
	}
	
	public void display()
	{
		this.output.println(this.getQuestion() + " Rank these options");
		for(int i = 0;i<_options.size();i++)
		{
			this.output.println(_options.get(i)); //Doesn't print out answers because we are just displaying the test
		}
	}
	
	public String displayAnswers() // display choices to answers in pleasant manner for modifyExam()
	{
		String string = new String();
		for(int i = 0; i< _options.size();i++)
		{
			string = string + _answers.get(i) + "-->" + _options.get(i) + "\n";
		}
		return string;
	}
	
	@Override
	public void modifyExam() 
	{
		this.modifySurvey();
		if(this.changed == true)
		{
			this.modifyAnswers();
		}
		else
		{
			String in = this.getResponse(new Scanner(new InputStreamReader(System.in)), "Would you like to change the answers: 1 for yes, anything else for no\n"+this.displayAnswers() );
			if(in.equals("1"))
			{
				this.modifyAnswers();
			}
		}
	}

	@Override
	public void modifySurvey() 
	{
		this.changePrompt();
		this.changeOptions();
	}
	
	public void changePrompt()
	{
		this.output.println("Prompt: " + this.getQuestion());
		String answer = this.getResponse(new Scanner(new InputStreamReader(System.in)),"Would you like to change the prompt? 1 for yes, anything else for no");
		if(answer.equals("1"))
		{
			this.changePromptString();
		}
	}
	
	private String optionsToString()
	{
		String string = new String();
		for(int i = 0;i<this._options.size();i++)
		{
			string = string + this._options.get(i) + "\n";
		}
		return string;
	}
	
	public void changeOptions()
	{
		//make it run modifyANswers and run throught the code to make sure it looks good!
		this.output.println("Options:\n" + this.optionsToString());
		String answer = this.getResponse(new Scanner(new InputStreamReader(System.in)),"Would you like to change the options? 1 for yes, anything else for no");
		if(answer.equals("1"))
		{
			this.changed = true;
			this.changeAllOptions();
		}
	}
	
	private void changePromptString()
	{
		this.setQuestion(this.getResponse(new Scanner(new InputStreamReader(System.in)),"Enter new question for ranking:"));
	}
	
	private void changeAllOptions()
	{
		// options
		Boolean valid = true;
		ArrayList<String> options = new ArrayList<String>();
		do 
		{ //ensures that atleast one option is prevalent
			String option = this.getResponse(new Scanner(new InputStreamReader(System.in)), "Add option:");

			options.add(option);
			
			if(!this.getResponse(new Scanner(new InputStreamReader(System.in)), "Add another option? 1 for yes, anything else for no").equals("1"))
			{
				valid = false;
			}
		} while(valid);
		
		this._options = options;
	}
	
	public void modifyAnswers()
	{
		// changing ranking
		ArrayList<String> answers = new ArrayList<String>();
		this.output.println("Provide ranking answers for the following:");
		for(int i = 0; i < _options.size();i++)
		{
			this.output.println(_options.get(i));
			String response = this.getResponse(new Scanner(new InputStreamReader(System.in)), "Rank:");
			try 
			{
				int num = Integer.parseInt(response);
				if(num < _options.size() && num >=0)
				{
					//make sure it answer isn't already in array
					Boolean found = false;
					for(int j = 0; j< answers.size();j++)
					{
						if(answers.get(j).equals(response)){
			 				found = true;
							j = answers.size();
						}
					}
					if(found)
					{
						i--;
					}
					else
					{
						answers.add(response);
					}
				} 
				else 
				{
					this.output.println("Invalid number: "+ num +" doesn't fit in parameters given above");
					i--;
				}
			} 
			catch (NumberFormatException e) 
			{
				this.output.println("Invalid: Enter number please");
				i--;
			}
		}
		_answers = answers;
	}
	
	public String getResponse(Scanner scan, String text)
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

	@Override
	public void makeExamQuestion() 
	{
		this.makeSurveyQuestion();
		this.modifyAnswers();
		this.changed = false;
	}

	@Override
	public void makeSurveyQuestion() 
	{
		this.changePromptString();
		
		Boolean valid = true;
		ArrayList<String> options = new ArrayList<String>();
		do 
		{ //ensures that atleast one option is prevalent
			String option = this.getResponse(new Scanner(new InputStreamReader(System.in)), "Add option:");
			options.add(option);
			if(! this.getResponse(new Scanner(new InputStreamReader(System.in)), "Add another option? 1 for yes, anything else for no").equals("1"))
			{
				valid = false;
			}
		} while(valid);
		// answer
		this._answers = null;
		this.setType("rank");
		this._options = options;
	}

	@Override
	public String toStringUserAnswers() 
	{
		//question
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
		for(int i = 0 ;i <this.userAnswers.size();i++)
		{
			if(i == this.userAnswers.size()-1)
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
		string = string + "\"type\": \"rank\"}";		
		return string;
	}

	@Override
	public void setUserAnswer(ArrayList<String> x) 
	{
		this.userAnswers=x;
	}

	@Override
	public ArrayList<String> getUserAnswer() 
	{
		return this.userAnswers;
	}

	@Override
	public double grade() 
	{
		double grade = 0;
		double divisor = 10.0/this._answers.size();
		for(int i = 0; i < this._answers.size();i++)
		{
			if(this._answers.get(i).equals(this._answers.get(Integer.parseInt(this.userAnswers.get(i)))))
			{
				grade+=divisor;
			}
			else
			{
				this.output.println(this.userAnswers.get(i));
			}
		}
		return grade;
	}

	@Override
	public void askForUserAnswer() 
	{
		this.display();
		ArrayList<String> answers = new ArrayList<String>();
		this.output.println("Provide ranking answers for the following:");
		for(int i = 0; i < _options.size();i++)
		{
			this.output.println(_options.get(i));
			String response = this.getResponse(new Scanner(new InputStreamReader(System.in)), "Rank:");
			try 
			{
				int num = Integer.parseInt(response);
				if(num < _options.size() && num >=0)
				{
					//make sure it answer isn't already in array
					Boolean found = false;
					for(int j = 0; j< answers.size();j++)
					{
						if(answers.get(j).equals(response)){
			 				found = true;
							j = answers.size();
						}
					}
					if(found)
					{
						i--;
					}
					else
					{
						answers.add(response);
					}
				} 
				else 
				{
					this.output.println("Invalid number: "+ num +" doesn't fit in parameters given above");
					i--;
				}
			} 
			catch (NumberFormatException e) 
			{
				this.output.println("Invalid: Enter number please");
				i--;
			}
		}
		this.userAnswers = answers;
	}

	@Override
	public String toStringSurveyUserAnswers() 
	{
		//question
		String string = "{\"question\":\"";
		string = string + this.getQuestion() + "\",";
		//user answer
		//string = string + "\"user_answer\": \""+ this.userAnswer +"\",";
		string = string + "\"user_answers\":[";
		for(int i = 0 ;i <this.userAnswers.size();i++)
		{
			if(i == this.userAnswers.size()-1)
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
		string = string + "\"type\": \"rank\"}";		
		return string;
	}

	@Override
	public Question copy() {
		Question example = new Rank(this.getQuestion(),this._options,this._answers, this.getType());
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
		Question example = new Rank(this.getQuestion(),this._options,this._answers, this.getType());
		example.setUserAnswer(this.userAnswers);
		return example;
	}
}



/*
string = string + "\"answers\": [";
for(int i = 0;i<_answers.size();i++){
	if(i <_answers.size()-1){
		string = string + "{\"answer\": \""+_answers.get(i)+"\"},";
	} else{ //meaning this is the last one
		string = string + "{\"answer\": \""+_answers.get(i)+"\"}";
	}
}
*/