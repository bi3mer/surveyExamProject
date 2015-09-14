package testSurvey;

import org.json.JSONObject;

public abstract class QuestionFactory {

	public QuestionFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public void corruptFilePrint()
	{
		System.out.println("Error: file is corrupt");
	}
	
	public abstract Question getQuestion(JSONObject questionInfo);
	
	protected abstract Question getMultipleChoice(JSONObject questionInfo);
	
	protected abstract Question getRank(JSONObject questionInfo);
	
	protected abstract Question getMatch(JSONObject questionInfo);
	
	protected abstract Question getEssay(JSONObject questionInfo);
	
	protected abstract Question getShortAnswer(JSONObject questionInfo);
	
	protected abstract Question getTrueFalse(JSONObject questionInfo);

}
