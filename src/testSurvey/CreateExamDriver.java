package testSurvey;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class CreateExamDriver {
	protected Exam _exam;
	private Output output;
	public CreateExamDriver() 
	{
		this.output = new Output();
	}
	
	public Exam makeExam(){
		// Get exam title
		this.output.println("Enter name of exam:");
		Scanner scan = new Scanner(new InputStreamReader(System.in));
		String file_name = null;
		while(file_name == null || file_name.equals("")){
			file_name = scan.nextLine();
			if(file_name == null || file_name.equals("")){
				this.output.println("Invalid, please enter new exam name");
			}
		}
		
		_exam = new Exam(file_name);
		// add questions to exam
		Boolean valid = false;
		do { //forces atleast one question to be in the exam
			this.addQuestion();
			this.output.println("Enter 1 to add another question:");
			if(!scan.nextLine().equals("1")){
				valid = true;
			}
		} while(valid == false);
		return _exam;
	}
	
	public void addQuestion(){
		this.output.println("Choose among the following:");
		this.output.println("1) add true/false question");
		this.output.println("2) add short answer (fill in the blank) question");
		this.output.println("3) add essay question");
		this.output.println("4) add matching question");
		this.output.println("5) add ranking question");
		this.output.println("6) add multiple choice question");
		Scanner scan = new Scanner(new InputStreamReader(System.in));
		String answer = scan.nextLine();
		//would've done switch but not possible with strings in java with certain compilers
		if(answer.equals("1"))
		{
			this.output.println("add true/false question");
			this.addTrueFalse();
		} 
		else if(answer.equals("2"))
		{
			this.output.println("add short answer (fill in the blank) question");
			this.addShortAnswer();
		} 
		else if(answer.equals("3"))
		{
			this.output.println("add essay question");
			this.addEssay();
		} 
		else if(answer.equals("4"))
		{
			this.output.println("add matching question");
			this.addMatching();
		} 
		else if(answer.equals("5"))
		{
			this.output.println("add ranking question");
			this.addRanking();
		} 
		else if(answer.equals("6"))
		{
			this.output.println("add multiple choice question");
			this.addMultipleChoice();
		}
		else 
		{
			this.output.print("invalid input");
			this.addQuestion();
			return;
		}
	}
	// While loops are to make sure bad input isn't given like null or "" 
	public void addTrueFalse(){
		// add true false question to exam
		/*
		this.output.println("Enter question for true false:");
		Scanner scan = new Scanner(new InputStreamReader(System.in));
		String questionString = scan.nextLine();
		while(questionString.equals("") || questionString.equals(null)){
			questionString = scan.nextLine();
		}
		this.output.println("Enter answer 1 for true, anything else for false");
		String answer = scan.nextLine();
		if(answer.equals("1"))
		{ // true
			trueFalse question = new trueFalse(questionString,"true","trueFalse");
			_exam.add(question);
		} 
		else 
		{ //false
			trueFalse question = new trueFalse(questionString,"false","trueFalse");
			_exam.add(question);
		}
		*/
		Question question = new trueFalse();
		question.makeExamQuestion();
		_exam.add(question);
	}
	
	public void addShortAnswer(){
		// add short answer to _exam
		this.output.println("Enter question and make sure to indicate blank area with \"_____\":");
		Scanner scan = new Scanner(new InputStreamReader(System.in));
		String questionString = scan.nextLine();
		while(questionString.equals("") || questionString.equals(null)){
			questionString = scan.nextLine();
		}
		this.output.println("Enter answer to fill in the blank");
		String answer = scan.nextLine();
		while(answer.equals("") || answer.equals(null)){
			answer = scan.nextLine();
		}
		shortAnswer question = new shortAnswer(questionString,answer,"shortAnswer");
		_exam.add(question);
	}
	
	public void addEssay(){
		/*
		this.output.println("Enter question open ended question for essay:");
		Scanner scan = new Scanner(new InputStreamReader(System.in));
		String questionString = scan.nextLine();
		while(questionString.equals("") || questionString.equals(null)){
			questionString = scan.nextLine();
		}
		Essay question = new Essay(questionString, null,"essay");
		*/
		Question essay = new Essay();
		essay.makeSurveyQuestion();
		_exam.add(essay);
	}
	
	public void addMultipleChoice(){
		// add multiple choice to _exam
		// questions
		this.output.println("Enter question for multiple choice:");
		Scanner scan = new Scanner(new InputStreamReader(System.in));
		String questionString = scan.nextLine();
		while(questionString.equals("") || questionString.equals(null)){
			questionString = scan.nextLine();
		}
		// options
		Boolean valid = true;
		ArrayList<String> options = new ArrayList<String>();
		do 
		{ //ensures that atleast one option is prevalent
			this.output.println("Add option:");
			String option = scan.nextLine();
			while(questionString.equals("") || questionString.equals(null))
			{
				option = scan.nextLine();
			}
			options.add(option);
			this.output.println("Add another option? 1 for yes, anything else for no");
			if(!scan.nextLine().endsWith("1"))
			{
				valid = false;
			}
		} while(valid);
		// answer
		valid = false;
		while(valid == false)
		{
			this.output.println("Enter number corresponding correct answer to multiple choice");
			for(int i = 0; i < options.size();i++)
			{
				this.output.println(i + ": " + options.get(i));
			}
			String response = scan.nextLine();
			try 
			{
				int num = Integer.parseInt(response);
				if(num < options.size() && num >=0)
				{
					// we now have all the values needed if we're in here
					MultipleChoice question = new MultipleChoice(questionString,options,options.get(num),"multipleChoice");
					_exam.add(question);
					valid = true;
				} 
				else 
				{
					this.output.println("Invalid number: "+ num +" doesn't fit in parameters given above");
				}
			} 
			catch (NumberFormatException e) 
			{
				this.output.println("Invalid: Enter number please");
			}
		}
	}
	
	public void addMatching(){
		this.output.println("Enter question for matching:");
		Scanner scan = new Scanner(new InputStreamReader(System.in));
		String questionString = scan.nextLine();
		while(questionString.equals("") || questionString.equals(null)){
			questionString = scan.nextLine();
		}
		// options
		Boolean valid = true;
		ArrayList<String> options = new ArrayList<String>();
		ArrayList<String> answers = new ArrayList<String>();
		do 
		{ //ensures that atleast one option is prevalent
			this.output.println("Add option:");
			String option = scan.nextLine();
			while(option.equals("") || option.equals(null))
			{
				option = scan.nextLine();
			}
			options.add(option);
			this.output.println("Matches with:");
			String answer = scan.nextLine();
			while(answer.equals("") || answer.equals(null))
			{
				answer = scan.nextLine();
			}
			answers.add(answer);
			this.output.println("Add another option? 1 for yes, anything else for no");
			if(!scan.nextLine().endsWith("1"))
			{
				valid = false;
			}
		} while(valid);
		
		Match question = new Match(questionString,options,answers,"match");
		_exam.add(question);
	}
	
	public void addRanking(){
		/*
		this.output.println("Enter question for ranking:");
		Scanner scan = new Scanner(new InputStreamReader(System.in));
		String questionString = scan.nextLine();
		while(questionString.equals("") || questionString.equals(null)){
			questionString = scan.nextLine();
		}
		// options
		Boolean valid = true;
		ArrayList<String> options = new ArrayList<String>();
		do 
		{ //ensures that atleast one option is prevalent
			this.output.println("Add option:");
			String option = scan.nextLine();
			while(options.equals("") || options.equals(null))
			{
				option = scan.nextLine();
			}
			options.add(option);
			this.output.println("Add another option? 1 for yes, anything else for no");
			if(!scan.nextLine().endsWith("1"))
			{
				valid = false;
			}
		} while(valid);
		// answer
		valid = false;
		ArrayList<String> answers = new ArrayList<String>();
		this.output.println("Provide ranking answers for the following:");
		for(int i = 0; i < options.size();i++)
		{
			this.output.println(options.get(i));
			this.output.print("Rank:");
			String response = scan.nextLine();
			try 
			{
				int num = Integer.parseInt(response);
				if(num < options.size() && num >=0)
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
		Rank question = new Rank(questionString,options,answers,"rank");
		*/
		Question question = new Rank();
		question.makeExamQuestion();
		_exam.add(question);
	}
	
}
