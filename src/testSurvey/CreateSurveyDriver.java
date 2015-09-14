package testSurvey;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class CreateSurveyDriver {
	protected Survey _survey;
	private Output output;
	public CreateSurveyDriver() 
	{
		this.output = new Output();
	}
	
	public Survey makeSurvey(){
		// Get exam title
		this.output.println("Enter name of Survey:");
		Scanner scan = new Scanner(new InputStreamReader(System.in));
		String file_name = null;
		while(file_name == null || file_name.equals("")){
			file_name = scan.nextLine();
			if(file_name == null || file_name.equals("")){
				this.output.println("Invalid, please enter new Survey name");
			}
		}
		
		_survey = new Survey(file_name);
		// add questions to exam
		Boolean valid = false;
		do { //forces atleast one question to be in the exam
			this.addQuestion();
			this.output.println("Enter 1 to add another question:");
			if(!scan.nextLine().equals("1")){
				valid = true;
			}
		} while(valid == false);
		return _survey;
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

		trueFalse question = new trueFalse(questionString,null,"trueFalse");
		*/
		Question question = new trueFalse();
		question.makeSurveyQuestion();
		_survey.add(question);
	}
	
	public void addShortAnswer(){
		// add short answer to _exam
		this.output.println("Enter question and make sure to indicate blank area with \"_____\":");
		Scanner scan = new Scanner(new InputStreamReader(System.in));
		String questionString = scan.nextLine();
		while(questionString.equals("") || questionString.equals(null)){
			questionString = scan.nextLine();
		}

		shortAnswer question = new shortAnswer(questionString,null,"shortAnswer");
		_survey.add(question);
	}
	
	public void addEssay(){
		// add essay to _exam
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
		_survey.add(essay);
	}
	
	public void addMultipleChoice(){
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

		MultipleChoice question = new MultipleChoice(questionString,options,null,"multipleChoice");
		_survey.add(question);
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
			this.output.println("Could match with:");
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
		_survey.add(question);
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
		Rank question = new Rank(questionString,options,null,"rank");
		*/
		Question question = new Rank();
		question.makeSurveyQuestion();
		_survey.add(question);
	}
	
}
