package testSurvey;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Exam {
	private int score;
	private String title; 
	protected int questionNumber;
	private ArrayList<Question> questions;
	private ArrayList<ArrayList<Question>> takenExams;
	private int examIndex;
	private Output output;
	
	public Exam(String _title) {
		setScore(0);
		setTitle(_title);
		this.questionNumber = 0;
		setQuestions(new ArrayList<Question>());
		this.takenExams = new ArrayList<ArrayList<Question>>();
		this.output = new Output();
	}
	
	public Object getQuestion(){
		this.questionNumber +=1; //check array of questions if at end
		// must have switch case for true/flase, multiple choice, etc.
		//Question question = new Question("String to be replaced","to be repalced!!!"); //replace etc.
		//note switch case should be added so it can return the true/false etc.
		return null;
	}
	
	public void addToCurrentExam(Question question)
	{
		this.takenExams.get(this.examIndex).add(question);
	}
	
	public void addNewExam(ArrayList<Question> answers)
	{
		this.takenExams.add(answers);
	}
	
	public void iterateIndex()
	{
		this.examIndex++;
	}
	
	public void resetIndex()
	{
		this.examIndex = 0;
	}
	
	// Title methods
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	private void populateExam()
	{
		if(this.takenExams.size() > 0)
		{
			for(int i = 0; i <this.takenExams.get(0).size();i++)
			{
				for(int j = 0; j<this.takenExams.size();j++)
				{
					this.questions.get(i).setUserAnswer(this.takenExams.get(j).get(i).getUserAnswer());
					Question question = questions.get(i).copy();
					this.takenExams.get(j).set(i, question);
					//this.output.println(this.takenExams.get(j).get(i).toStringUserAnswers());
				}
			}
		}
	}
	
	public void tabulate()
	{
		if(this.takenExams.size()<1)
		{
			this.output.println("No surveys to tabulate");
			return;
		}
		this.populateExam();
		for(int i = 0; i<this.questions.size(); i++)
		{
			if(this.takenExams.get(0).get(i).getType().equals("trueFalse"))
			{
				this.tabulateTrueFalse(i);
			}
			else if(this.takenExams.get(0).get(i).getType().equals("essay"))
			{
				this.tabulateEssay(i);
			}
			else if(this.takenExams.get(0).get(i).getType().equals("shortAnswer"))
			{
				this.tabulateShortAnswer(i);
			}
			else if(this.takenExams.get(0).get(i).getType().equals("multipleChoice"))
			{
				this.tabulateMultipleChoice(i);
			}
			else if(this.takenExams.get(0).get(i).getType().equals("rank"))
			{
				this.tabulateRankChoice(i);
			}
			else if(this.takenExams.get(0).get(i).getType().equals("match"))
			{
				this.tabulateRankChoice(i);
			}
			System.out.println();
		}
	}
	
	public void tabulateRankChoice(int i)
	{
		Map<String, Integer> map = new HashMap<String, Integer>();
		for(int j = 0; j<this.takenExams.size();j++)
		{
			//construct key
			String keyForMap = new String();
			for(int k = 0; k<this.takenExams.get(j).get(i).getUserAnswer().size();k++)
			{
				keyForMap += this.takenExams.get(j).get(i).getUserAnswer().get(k) + "-->" +  this.takenExams.get(j).get(i).getAnswer().get(k) +"\n";
			}
			//use key
			if(map.get(keyForMap) != null)
			{
				map.put(keyForMap, map.get(keyForMap) + 1);
			}
			else
			{
				map.put(keyForMap, 1);
			}
		}
		  
		this.output.println(this.questions.get(i).getQuestion());
		for (String key : map.keySet()) 
		{
		    this.output.println(key.toString() + " had " + map.get(key) + " people choose it");
		}
	}
	
	public void tabulateMultipleChoice(int i)
	{
		Map<String, Integer> map = new HashMap<String, Integer>();
		for(int j = 0; j<this.takenExams.size();j++)
		{
			if(map.get(this.takenExams.get(j).get(i).getUserAnswer().get(0)) != null)
			{
				map.put(this.takenExams.get(j).get(i).getUserAnswer().get(0), map.get(this.takenExams.get(j).get(i).getUserAnswer().get(0)) + 1);
			}
			else
			{
				map.put(this.takenExams.get(j).get(i).getUserAnswer().get(0), 1);
			}
		}
		  
		this.output.println(this.questions.get(i).getQuestion());
		for (String key : map.keySet()) 
		{
		    this.output.println(key.toString() + " had " + map.get(key) + " people choose it");
		}
	}
	
	public void tabulateShortAnswer(int i)
	{
		this.tabulateMultipleChoice(i);
	}
	
	private void tabulateEssay(int i)
	{
		this.output.println(this.questions.get(i).getQuestion());
		for(int j = 0; j<this.takenExams.size();j++)
		{
			this.output.println(this.takenExams.get(j).get(i).getUserAnswer().toString());
		}
	}
	
	private void tabulateTrueFalse(int i)
	{
		int trueA = 0;
		int falseA = 0;
		for(int j = 0; j<this.takenExams.size();j++)
		{
			if(this.takenExams.get(j).get(i).getUserAnswer().get(0).equals("true"))
			{
				trueA++;
			}
			else
			{
				falseA++;
			}
		}
		this.output.println(this.questions.get(i).getQuestion() + ": " + trueA + " people respond true and " + falseA +" responded false.");
	}
	
	// Score methods
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void addToScore(int score) {
		this.score += score;
	}

	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}
	
	public void add(Question question){
		this.questions.add(question);
	}
	
	public String toString(){ //create Exam into JSON string, unformatted. One line
		//Questions
		this.populateExam();
		String string  =  "{\"Exam\":{\"questions\": [";
		for(int i = 0; i < questions.size();i++){
			//trueFalse test = (trueFalse) questions.get(i);
			//string = string + "," + questions.get(i).getQuestion();//test.getAnswer();
			// ONce again switch statements are no dice
			if(questions.get(i).toString() == null)
			{
				this.output.println("Error: corrupt file");
				return null;
			}
			string = string + questions.get(i).toString();
			
			if(i <questions.size()-1){
				string = string + ",";
			}
		}
		string = string + "]},";
		//Answers
		string = string + "\"user_answers\":[";
		//for(ArrayList<Question> questions : this.takenExams)
		for(int i = 0; i < this.takenExams.size(); i++)
		{
			string = string + "[";
			for(int j = 0;j<this.takenExams.get(i).size();j++)
			{
				if(j == this.takenExams.get(i).size()-1)
				{
					string = string + this.takenExams.get(i).get(j).toStringUserAnswers();
				}
				else
				{
					string = string + this.takenExams.get(i).get(j).toStringUserAnswers() + ",";
				}
			}
			if(i == this.takenExams.size()-1)
			{
				string = string +"]";
			}
			else
			{
				string = string + "],";
			}
		}
		string = string + "]}";
		return string; 
	}
	
	public void grade(ArrayList<Question> questions)
	{
		this.populateExam();
		double score = 0;
		double total = 0;
		int essays = 0;
		for(Question question : questions)
		{
			double grade = question.grade();
			if(grade != -10) // if negative 10, it means that it was an essay question
			{
				score += grade;
				total += 10;
			}
			else
			{
				essays++;
			}
		}
		this.output.print("You scored: " + score + "/" +total);
		if(essays >=0)
		{
			this.output.println(" with " + essays + " ungraded essay question.");
		}
		this.output.println("");
	}

	private double average()
	{
		double score = 0;
		for(ArrayList<Question> questions : this.takenExams)
		{
			for(Question question : questions)
			{
				double grade = question.grade();
				if(grade != -10) // if negative 10, it means that it was an essay question
				{
					score += grade;
				}
			}
		}
		score /= this.takenExams.size();
		return score;
	}
	
	public void gradeAll()
	{
		for(int i = 0; i < this.takenExams.size(); i++)
		{
			this.grade(this.takenExams.get(i));
		}
		this.output.println("Average: " + this.average());
	}
	
	public void display()
	{
		for(int i = 0; i < questions.size();i++){
			questions.get(i).display();
		}
	}
	
	public void modify()
	{
		String answer = this.getResponse(new Scanner(new InputStreamReader(System.in)),"By answering yes, you will delete all previously taken exams: 1 for yes, antying else for no");
		if(!answer.equals("1"))
		{
			return;
		}
		else
		{
			this.takenExams = new ArrayList<ArrayList<Question>>();
		}
		// delete questions
		this.modifyDelete();
		// add questions
		this.modifyAdd();
		// modify questions
		this.modifyQuestions();
		// save?
		answer = this.getResponse(new Scanner(new InputStreamReader(System.in)),"Would you like to save? 1 for yes, antying else for no");
		if(answer.equals("1"))
		{
			FileExam file_exam = new FileExam();
			file_exam.saveExam(this.getTitle(), this);
		}
	}
	
	private String getResponse(Scanner scan, String text)
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
	
	private void modifyQuestions()
	{
		String string = this.getResponse(new Scanner(new InputStreamReader(System.in)),"Would you like to modify any questions? 1 for yes, antying else for no");
		if(string.equals("1"))
		{
			for(int i = 0;i < this.questions.size();i++)
			{
				this.output.println("Question: " + this.questions.get(i).getQuestion());
				string = this.getResponse(new Scanner(new InputStreamReader(System.in)),"Would you like to modify this question? 1 for yes, antying else for no");
				if(string.equals("1"))
				{
					this.questions.get(i).modifyExam();
				}
			}
		}
	}
	
	private void modifyAdd()
	{
		String string = this.getResponse(new Scanner(new InputStreamReader(System.in)),"Would you like to add any questions? 1 for yes, antying else for no");
		if(string.equals("1"))
		{
			Boolean valid = true;
			while(valid)
			{
				this.addQuestion();
				string = this.getResponse(new Scanner(new InputStreamReader(System.in)),"Would you like to add another question? 1 for yes, antying else for no");
				if(! string.equals("1"))
				{
					valid = false;
				}
			}
		}
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
			Question question = new trueFalse();
			question.makeExamQuestion();
			this.add(question);
		} 
		else if(answer.equals("2"))
		{
			this.output.println("add short answer (fill in the blank) question");
			Question question = new shortAnswer();
			question.makeExamQuestion();
			this.add(question);
		} 
		else if(answer.equals("3"))
		{
			this.output.println("add essay question");
			Question question = new Essay();
			question.makeExamQuestion();
			this.add(question);
		} 
		else if(answer.equals("4"))
		{
			this.output.println("add matching question");
			Question question = new Match();
			question.makeExamQuestion();
			this.add(question);
		} 
		else if(answer.equals("5"))
		{
			this.output.println("add ranking question");
			Question question = new Rank();
			question.makeExamQuestion();
			this.add(question);
		} 
		else if(answer.equals("6"))
		{
			this.output.println("add multiple choice question");
			Question question = new MultipleChoice();
			question.makeExamQuestion();
			this.add(question);
		}
		else 
		{
			this.output.print("invalid input");
			this.addQuestion();
			return;
		}
	}
	
	private void modifyDelete()
	{
		String string = this.getResponse(new Scanner(new InputStreamReader(System.in)),"Would you like to delete any questions? 1 for yes, antying else for no");
		if(string.equals("1"))
		{
			Boolean endLoop = false;
			do
			{
				for(int i = 0; i < this.questions.size();i++)
				{
					this.output.println(i + ": " + this.questions.get(i).getQuestion());
				}
				string = this.getResponse(new Scanner(new InputStreamReader(System.in)),"Which number would you like to delete?");
				try
				{
					int num = Integer.parseInt(string);
					if(num < this.questions.size() && num >=0)
					{
						this.questions.remove(num);
					}
					else
					{
						this.output.println("Number doen't fit paramters");
					}
					string = this.getResponse(new Scanner(new InputStreamReader(System.in)),"Would you like to delete another? 1 for yes, anything else for no.");
					if(!string.equals("1"))
					{
						endLoop = true;
					}
				}
				catch (NumberFormatException e) 
				{
					this.output.println("Invalid: Enter number please");
				}
			} while(endLoop == false);
		}
	}
	
	public void takeExam()
	{
		ArrayList<Question> newExam = new ArrayList<Question>();
		// take exam
		for(Question question : this.questions)
		{
			question.askForUserAnswer();
			newExam.add(question.copy());
		}
		this.takenExams.add(newExam);
		// auto save, with overwrite
		FileExam fileExam = new FileExam();
		fileExam.saveExamOverWrite("t_"+this.getTitle(), this);
		this.grade(newExam);
	}
}
/* 
Example output of toString():
{"Exam":{"questions": [{"question":"Is there a god?","answer": "true","type": "trueFalse"},{"question":"Fuck the ______","answer": "police","type": "shortAnswer"},{"question":"Is god human?","answer": "None", "type": "essay"},{"question":"Which number is largest?","answer": "20","type": "multipleChoice","answers": [{"answer": "20"},{"answer": "10"},{"answer": "3"},{"answer": "4"}]},{"question":"Rank the following:","options": [{"answer": "option 1"},{"answer": "option 2"},{"answer": "option 3"},{"answer": "option 4"}],"type": "matchRank","answers": [{"answer": "3"},{"answer": "2"},{"answer": "1"},{"answer": "4"}]}]}}
Yes all on one line on purpose.
Things i add for just in case
if(question.getType().equals("trueFalse")){
//trueFalse true_false = (trueFalse) question;
questions.add(question);
} else {
this.output.println("add question class not added: "+question.getClass());
}
*/
/*
if(questions.get(i).getType().equals("trueFalse"))
{
	trueFalse question = (trueFalse) questions.get(i);
	string = string + question.toString();
} 
else if(questions.get(i).getType().equals("multipleChoice"))
{
	MultipleChoice question = (MultipleChoice) questions.get(i);
	string = string + question.toString();
}
else if(questions.get(i).getType().equals("match"))
{
	Match question = (Match) questions.get(i);
	string = string + question.toString();
}
else if(questions.get(i).getType().equals("rank"))
{
	Rank question = (Rank) questions.get(i);
	string = string + question.toString();
}
else if(questions.get(i).getType().equals("shortAnswer"))
{
	shortAnswer question = (shortAnswer) questions.get(i);
	string = string + question.toString();
}
else if(questions.get(i).getType().equals("essay"))
{
	Essay question = (Essay) questions.get(i);
	string = string + question.toString();
}
else 
{
	this.output.println("Error: File corrupt");
}
*/
/*
if(questions.get(i).getType().equals("trueFalse"))
{
	trueFalse question = (trueFalse) questions.get(i);
	question.display();
} 
else if(questions.get(i).getType().equals("multipleChoice"))
{
	MultipleChoice question = (MultipleChoice) questions.get(i);
	question.display();
}
else if(questions.get(i).getType().equals("match"))
{
	Match question = (Match) questions.get(i);
	question.display();
}
else if(questions.get(i).getType().equals("rank"))
{
	Rank question = (Rank) questions.get(i);
	question.display();
}
else if(questions.get(i).getType().equals("shortAnswer"))
{
	shortAnswer question = (shortAnswer) questions.get(i);
	question.display();
}
else if(questions.get(i).getType().equals("essay"))
{
	Essay question = (Essay) questions.get(i);
	question.display();
}
else 
{
	this.output.println("Error: File corrupt");
}
*/