package testSurvey;

import java.util.ArrayList;

public class Main 
{
	public static void main(String[] args) 
	{	
		// driver automatically runs and gets things going
		Driver driver = new Driver(); 
	}
}
/*	Test Code:

//code used to test classes and array of questions
Question question = new Essay();
question.makeExamQuestion();
question.askForUserAnswer();
ArrayList<ArrayList<Question>> takenExams = new ArrayList<ArrayList<Question>>();
ArrayList<Question> blam = new ArrayList<Question>();
blam.add(question);
blam.add(question);
blam.add(question);
blam.add(question);
blam.add(question);
takenExams.add(blam);
for(ArrayList<Question> row : takenExams)
{
	for(Question usrQuestion : row)
	{
		System.out.println("Grade: " + usrQuestion.grade());
		System.out.println("String: " + usrQuestion.toStringUserAnswers());
	}
}





CreateSurveyDriver create = new CreateSurveyDriver();
		Survey survey = create.makeSurvey();
		System.out.println(survey.toString());
Question match = new Match();
		match.makeSurveyQuestion();
		match.modifySurvey();
		System.out.println(match.toStringSurvey());
Question match = new shortAnswer();
		
ArrayList<Integer> blam = new ArrayList<Integer>();
for(int i = 0;i<10;i++)
{
	blam.add(i);
}
for(int i = 0;i<blam.size();i++)
{
	System.out.println(blam.get(i));
}
blam.remove(3);
System.out.println("\n---------\n");
for(int i = 0;i<blam.size();i++)
{
	System.out.println(blam.get(i));
}
*/