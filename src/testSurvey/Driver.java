package testSurvey;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
	private ArrayList<Exam> exams;
	private ArrayList<Survey> surveys;
	private Output output;
	
	public Driver() {
		this.setExams(new ArrayList<Exam>());
		this.setSurveys(new ArrayList<Survey>());
		this.output = new Output();
		this.run(); // Immediately start driver once instantiated.
	}
	
	public void run()
	{
		this.output.println("Menu:");
		this.output.println("1) Create a new survey");
		this.output.println("2) Create a new Test");
		this.output.println("3) Display a Survey");
		this.output.println("4) Display a Test");
		this.output.println("5) Load a Survey");
		this.output.println("6) Load a Test");
		this.output.println("7) Save a Survey");
		this.output.println("8) Save a Test");
		this.output.println("9) Modify an Existing Survey");
		this.output.println("10) Modify an Existing Exam");
		this.output.println("11) Take a Survey");
		this.output.println("12) Take an Exam");
		this.output.println("13) Grade a Exam");
		this.output.println("14) Tabulate a Survey");
		this.output.println("15) Tabulate a Exam");
		this.output.println("16) Quit");
		Scanner scan = new Scanner(new InputStreamReader(System.in));
		this.output.print("Enter: ");
		String answer = scan.nextLine();
		//would've done switch but not possible with strings in java with certain compilers
		if(answer.equals("1")){
			this.output.println("create new survey");
			this.createSurvey();
		} 
		else if(answer.equals("2"))
		{
			this.output.println("create new test");
			this.createTest();
		} 
		else if(answer.equals("3"))
		{
			this.output.println("display a survey");
			this.displaySurvey();
		} 
		else if(answer.equals("4"))
		{
			this.output.println("display a test");
			this.displayTest();
		} 
		else if(answer.equals("5"))
		{
			this.output.println("load a survey");
			this.loadSurvey();
		} 
		else if(answer.equals("6"))
		{
			this.output.println("load a test");
			this.loadTest();
		} 
		else if(answer.equals("7"))
		{
			this.output.println("save a survey");
			this.saveSurvey();
		} 
		else if(answer.equals("8"))
		{
			this.output.println("save a test");
			this.saveTest();
		} 
		else if(answer.equals("9"))
		{
			this.output.println("modify existing survey");
			this.modifySurvey();
		} 
		else if(answer.equals("10"))
		{
			this.output.println("modify existing test");
			this.modifyExam();
		} 
		else if(answer.equals("11"))
		{
			this.output.println("take a survey");
			this.takeSurvey();
		} 
		else if(answer.equals("12"))
		{
			this.output.println("take a test");
			this.takeExam();
		} 
		else if(answer.equals("13"))
		{
			this.output.println("grade a test");
			this.gradeExam();
		} 
		else if(answer.equals("14"))
		{
			this.output.println("tabulate a survey");
			this.tabulateSurvey();
		} 
		else if(answer.equals("15"))
		{
			this.output.println("tabulate a test");
			this.tabulateExam();
		} 
		else if(answer.equals("16"))
		{
			this.output.println("Thank you for using the program.");
			System.exit(0);
		} 
		else
		{
			this.output.println(answer + ": invalid input. Please try again.");
			this.run();
			return;
		}
		//continue? add!
		this.run();
		//this.output.println("Program terminated, thank you for using.");
	}
	
	public void continueDriver()
	{ // no longer used but saved in case it's needed for later
		this.output.println("Would you like to terminate program? (1 for yes, 0 for no)");
		Scanner scan = new Scanner(new InputStreamReader(System.in));
		String answer = scan.nextLine(); //add test
		if(answer.equals("0"))
		{
			this.run();
		}
		else if (answer.equals("1"))
		{
			this.output.println("Program terminated, thank you for using.");
		} 
		else 
		{
			this.output.println("invalid input");
			this.continueDriver();
		}
	}
	
	public void createSurvey()
	{
		CreateSurveyDriver create = new CreateSurveyDriver();
		Survey survey = create.makeSurvey();
		surveys.add(survey);
		//this.output.println("createSurvey: being implemented");
	}
	
	public void createTest()
	{
		CreateExamDriver create = new CreateExamDriver();
		Exam exam = create.makeExam();
		exams.add(exam);
	}
	
	public void displaySurvey()
	{
		if(surveys.size() == 0)
		{
			this.output.println("No Surveys has been loaded");
			return;
		} 
//		this.output.println("Which exam would you like to save:");
		String answer = new String();
		int index = 0;
		int checker = 0;
		Boolean valid = false;
		// --- get valid exam name --->
		while(valid == false)
		{ 
			checker++;
			if(checker > 3)
			{
				this.output.println("invalid input");
				return;
			}
			for(int i = 0;i< surveys.size();i++)
			{
				this.output.println(surveys.get(i).getTitle());
			}
			Scanner scan = new Scanner(new InputStreamReader(System.in));
//			answer = scan.nextLine();
			answer = this.getResponse(scan, "Which survey would you like to display:");
			for(int i = 0;i< surveys.size();i++)
			{
				if(surveys.get(i).getTitle().equals(answer))
				{
					valid = true;
					index = i;
				}
			}
		}
		// --- display exam ---------->
		surveys.get(index).display();
		//this.output.println("displaySurvey: being implemented");
	}
	
	public void displayTest()
	{
		if(exams.size() == 0)
		{
			this.output.println("No Exam has been loaded");
			return;
		} 
//		this.output.println("Which exam would you like to save:");
		String answer = new String();
		int index = 0;
		int checker = 0;
		Boolean valid = false;
		// --- get valid exam name --->
		while(valid == false)
		{ 
			if(checker > 3)
			{
				this.output.println("invalid input");
				return;
			}
			for(int i = 0;i< exams.size();i++)
			{
				this.output.println(exams.get(i).getTitle());
			}
			Scanner scan = new Scanner(new InputStreamReader(System.in));
//			answer = scan.nextLine();
			answer = this.getResponse(scan, "Which exam would you like to display:");
			for(int i = 0;i< exams.size();i++)
			{
				if(exams.get(i).getTitle().equals(answer))
				{
					valid = true;
					index = i;
				}
			}
		}
		// --- display exam ---------->
		exams.get(index).display();
	}
	
	public void loadSurvey()
	{
//		this.output.print("Enter survey name: ");
		Scanner scan = new Scanner(new InputStreamReader(System.in));
//		String answer = scan.nextLine(); //add test
		String answer = this.getResponse(scan, "Enter survey name to load:");
		for(Survey survey : surveys)
		{
			if(survey.getTitle().equals(answer))
			{
				this.output.println("Survey already loaded");
				return;
			}
		}
		FileSurvey file_survey = new FileSurvey();
		Survey gotSurvey = file_survey.getSurvey(answer);
		//save to local arraylist of exams
		if(gotSurvey == null){
			this.output.println("Survey not loaded");
		} else {
			this.surveys.add(gotSurvey);
			this.output.println(answer + " Loaded");
		}
		//this.output.println("loadSurvey: being implemented");
	}
	
	public void loadTest()
	{
//		this.output.print("Enter test name: ");
		Scanner scan = new Scanner(new InputStreamReader(System.in));
//		String answer = scan.nextLine(); //add test
		String answer = this.getResponse(scan, "Enter test name to load:");
		for(Exam exam : exams)
		{
			if(exam.getTitle().equals(answer))
			{
				this.output.println("Exam already loaded");
				return;
			}
		}
		FileExam file_exam = new FileExam();
		Exam gotExam = file_exam.getExam(answer);
		//save to local arraylist of exams
		if(gotExam == null){
			this.output.println("Exam not loaded");
		} else {
			this.exams.add(gotExam);
			this.output.println(answer + " Loaded");
		}
	}
	
	public void saveSurvey(){
		if(surveys.size() == 0)
		{
			this.output.println("No surveys loaded");
			return;
		}
//		this.output.println("Which survey would you like to save:");
		String answer = new String();
		int index = 0;
		Boolean valid = false;
		// --- get valid exam name --->
		while(valid == false){ 
			for(int i = 0;i< surveys.size();i++){
				this.output.println(surveys.get(i).getTitle());
			}
			Scanner scan = new Scanner(new InputStreamReader(System.in));
			answer = this.getResponse(scan, "Which survey would you like to save:");
			for(int i = 0;i< surveys.size();i++)
			{
				if(surveys.get(i).getTitle().equals(answer))
				{
					valid = true;
					index = i;
				}
			}
		}
		// --- save exam ------------->
		FileSurvey file_survey = new FileSurvey();
		file_survey.saveSurvey(surveys.get(index).getTitle(), surveys.get(index));
		this.output.println("Survey saved.");
		// --------------------------->
		//this.output.println("saveSurvey: being implemented");
	}
	
	public void saveTest()
	{
		if(exams.size() == 0)
		{
			this.output.println("No exams loaded");
			return;
		}
//		this.output.println("Which exam would you like to save:");
		String answer = new String();
		int index = 0;
		Boolean valid = false;
		// --- get valid exam name --->
		while(valid == false){ 
			for(int i = 0;i< exams.size();i++){
				this.output.println(exams.get(i).getTitle());
			}
			Scanner scan = new Scanner(new InputStreamReader(System.in));
//			answer = scan.nextLine();
			answer = this.getResponse(scan, "Which exam would you like to save:");
			for(int i = 0;i< exams.size();i++)
			{
				if(exams.get(i).getTitle().equals(answer))
				{
					valid = true;
					index = i;
				}
			}
		}
		// --- save exam ------------->
		FileExam file_exam = new FileExam();
		file_exam.saveExam(exams.get(index).getTitle(), exams.get(index));
		// --------------------------->
		this.output.println("Exam saved");
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
	
	public ArrayList<Exam> getExams() 
	{
		return exams;
	}

	public void setExams(ArrayList<Exam> exams) {
		this.exams = exams;
	}

	public ArrayList<Survey> getSurveys() {
		return surveys;
	}

	public void setSurveys(ArrayList<Survey> surveys) {
		this.surveys = surveys;
	}
	
	public void modifySurvey()
	{
		for(int i = 0;i<this.surveys.size();i++)
		{
			this.output.println(i + ") " + this.surveys.get(i).getTitle());
		}

		String answer = this.getResponse( new Scanner(new InputStreamReader(System.in)), "Which survey would you like to modify:");
		// get the index and run modify
		try 
		{
			int num = Integer.parseInt(answer);
			if(num < this.surveys.size() && num >=0)
			{
				// we now have all the values needed if we're in here
				this.surveys.get(num).modify();
			} 
			else 
			{
				this.output.println("Invalid number: "+ num +" doesn't fit in parameters given above");
				this.modifyExam();
			}
		} 
		catch (NumberFormatException e) 
		{
			this.output.println("Invalid: bad input");
			this.modifyExam();
		}
		//this.output.println("Modify survery being implemented");
	}
	
	public void modifyExam()
	{
		//pick exam to modify
		for(int i = 0;i<this.exams.size();i++)
		{
			this.output.println(i + ") " + this.exams.get(i).getTitle());
		}

		String answer = this.getResponse( new Scanner(new InputStreamReader(System.in)), "Which exam would you like to modify:");
		// get the index and run modify
		try 
		{
			int num = Integer.parseInt(answer);
			if(num < this.exams.size() && num >=0)
			{
				// we now have all the values needed if we're in here
				this.exams.get(num).modify();
			} 
			else 
			{
				this.output.println("Invalid number: "+ num +" doesn't fit in parameters given above");
				this.modifyExam();
			}
		} 
		catch (NumberFormatException e) 
		{
			this.output.println("Invalid: bad input");
			this.modifyExam();
		}
	}
	
	public void takeSurvey()
	{
		//get exam 
		if(this.surveys.size() == 0)
		{
			this.output.println("No survey has been loaded");
			return;
		} 

		String answer = new String();
		int index = 0;
		int checker = 0;
		Boolean valid = false;
		// --- get valid exam name --->
		while(valid == false)
		{ 
			if(checker > 3)
			{
				this.output.println("invalid input");
				return;
			}
			for(int i = 0;i< this.surveys.size();i++)
			{
				this.output.println(this.surveys.get(i).getTitle());
			}
			Scanner scan = new Scanner(new InputStreamReader(System.in));
			answer = this.getResponse(scan, "Which survey would you like to take?");
			for(int i = 0;i< this.surveys.size();i++)
			{
				if(this.surveys.get(i).getTitle().equals(answer))
				{
					valid = true;
					index = i;
				}
			}
			checker++;
		}
		//run take
		this.surveys.get(index).takeSurvey();
		//this.output.println("Take survery being implemented");
	}

	public void takeExam()
	{
		//get exam 
		if(exams.size() == 0)
		{
			this.output.println("No Exam has been loaded");
			return;
		} 
//		this.output.println("Which exam would you like to save:");
		String answer = new String();
		int index = 0;
		int checker = 0;
		Boolean valid = false;
		// --- get valid exam name --->
		while(valid == false)
		{ 
			if(checker > 3)
			{
				this.output.println("invalid input");
				return;
			}
			for(int i = 0;i< exams.size();i++)
			{
				this.output.println(exams.get(i).getTitle());
			}
			Scanner scan = new Scanner(new InputStreamReader(System.in));
//			answer = scan.nextLine();
			answer = this.getResponse(scan, "Which exam would you like to take?");
			for(int i = 0;i< exams.size();i++)
			{
				if(exams.get(i).getTitle().equals(answer))
				{
					valid = true;
					index = i;
				}
			}
			checker++;
		}
		//run take
		this.exams.get(index).takeExam();
	}
	
	public void gradeExam()
	{
		int index = this.examIndex();
		if(index == -1)
		{
			return;
		}
		this.exams.get(index).gradeAll();
	}
	
	public void tabulateSurvey()
	{
		int index = this.surveyIndex();
		if(index == -1)
		{
			return;
		}
		this.surveys.get(index).tabulate();
	}
	
	public void tabulateExam()
	{
		int index = this.examIndex();
		if(index == -1)
		{
			return;
		}
		this.exams.get(index).tabulate();
	}
	
	private int examIndex()
	{
		if(exams.size() == 0)
		{
			this.output.println("No Exam has been loaded");
			return -1;
		} 
//		this.output.println("Which exam would you like to save:");
		String answer = new String();
		int index = 0;
		int checker = 0;
		Boolean valid = false;
		// --- get valid exam name --->
		while(valid == false)
		{ 
			if(checker > 3)
			{
				this.output.println("invalid input");
				return-1;
			}
			for(int i = 0;i< exams.size();i++)
			{
				this.output.println(exams.get(i).getTitle());
			}
			Scanner scan = new Scanner(new InputStreamReader(System.in));
//			answer = scan.nextLine();
			answer = this.getResponse(scan, "Which exam would you like to take?");
			for(int i = 0;i< exams.size();i++)
			{
				if(exams.get(i).getTitle().equals(answer))
				{
					valid = true;
					index = i;
				}
			}
			checker++;
		}
		return index;
	}
	private int surveyIndex()
	{
		if(this.surveys.size() == 0)
		{
			this.output.println("No survey has been loaded");
			return -1;
		} 
//		this.output.println("Which exam would you like to save:");
		String answer = new String();
		int index = 0;
		int checker = 0;
		Boolean valid = false;
		// --- get valid exam name --->
		while(valid == false)
		{ 
			if(checker > 3)
			{
				this.output.println("invalid input");
				return-1;
			}
			for(int i = 0;i< this.surveys.size();i++)
			{
				this.output.println(this.surveys.get(i).getTitle());
			}
			Scanner scan = new Scanner(new InputStreamReader(System.in));
//			answer = scan.nextLine();
			answer = this.getResponse(scan, "Which survey would you like to take?");
			for(int i = 0;i< this.surveys.size();i++)
			{
				if(this.surveys.get(i).getTitle().equals(answer))
				{
					valid = true;
					index = i;
				}
			}
			checker++;
		}
		return index;
	}
}