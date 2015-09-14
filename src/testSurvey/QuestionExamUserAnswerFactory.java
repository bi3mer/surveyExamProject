package testSurvey;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class QuestionExamUserAnswerFactory extends QuestionFactory{
	private Output output;
	
	public QuestionExamUserAnswerFactory() 
	{
		this.output = new Output();
	}
	
	@Override
	public Question getQuestion(JSONObject questionInfo) 
	{
		try {
			if(questionInfo.getString("type").equals("trueFalse"))
			{
				return this.getTrueFalse(questionInfo);
			} 
			else if (questionInfo.getString("type").equals("shortAnswer"))
			{
				return this.getShortAnswer(questionInfo);
			} 
			else if (questionInfo.getString("type").equals("essay"))
			{
				return this.getEssay(questionInfo);
			} 
			else if (questionInfo.getString("type").equals("multipleChoice"))
			{
				return this.getMultipleChoice(questionInfo);
			} 
			else if (questionInfo.getString("type").equals("match"))
			{
				return this.getMatch(questionInfo);
			} 
			else if (questionInfo.getString("type").equals("rank"))
			{
				return this.getRank(questionInfo);
			} 
			else
			{
				this.output.println("Error: exam corrupt, invalid " + questionInfo.getString("type") +" type");
				return null;
			}
		} catch (JSONException e) {
			this.corruptFilePrint();
			return null;
		}
	}

	@Override
	protected Question getMultipleChoice(JSONObject questionInfo) 
	{
		try 
		{
			MultipleChoice question = new MultipleChoice(questionInfo.getString("question"), null,questionInfo.getString("answer"),questionInfo.getString("type"));
			ArrayList<String> userAnswer = new ArrayList<String>();
			userAnswer.add(questionInfo.getString("user_answer"));
			question.setUserAnswer(userAnswer);
			return question;
		} 
		catch (JSONException e) 
		{
			this.corruptFilePrint();
			return null;
		}
	}

	@Override
	protected Question getRank(JSONObject questionInfo) 
	{
		try 
		{
			JSONArray matchArr = questionInfo.getJSONArray("answers");
			ArrayList<String> answers = new ArrayList<String>();
			for(int k = 0;k<matchArr.length();k++)
			{
				JSONObject multiObj = matchArr.getJSONObject(k);
				answers.add(multiObj.getString("answer"));
			}
			
			Rank question = new Rank(questionInfo.getString("question"), null,answers,questionInfo.getString("type"));
			
			matchArr = questionInfo.getJSONArray("user_answers");
			ArrayList<String> userAnswer = new ArrayList<String>();
			for(int k = 0;k<matchArr.length();k++)
			{
				JSONObject multiObj = matchArr.getJSONObject(k);
				userAnswer.add(multiObj.getString("answer"));
			}
			question.setUserAnswer(userAnswer);
			return question;
		} 
		catch (JSONException e) {
			// TODO Auto-generated catch block
			this.corruptFilePrint();
			return null;
		}
		
	}

	@Override
	protected Question getMatch(JSONObject questionInfo) 
	{
		try {
			JSONArray matchArr = questionInfo.getJSONArray("answers");
			ArrayList<String> answers = new ArrayList<String>();
			for(int k = 0;k<matchArr.length();k++)
			{
				JSONObject multiObj = matchArr.getJSONObject(k);
				answers.add(multiObj.getString("answer"));
			}

			Match question = new Match(questionInfo.getString("question"), null,answers,questionInfo.getString("type"));
			
			matchArr = questionInfo.getJSONArray("user_answers");
			ArrayList<String> userAnswer = new ArrayList<String>();
			for(int k = 0;k<matchArr.length();k++)
			{
				JSONObject multiObj = matchArr.getJSONObject(k);
				userAnswer.add(multiObj.getString("answer"));
			}
			question.setUserAnswer(userAnswer);
			return question;
		} 
		catch (JSONException e) 
		{
			// TODO Auto-generated catch block
			this.corruptFilePrint();
			return null;
		}
	}

	@Override
	protected Question getEssay(JSONObject questionInfo) 
	{
		try 
		{
			Essay question = new Essay(questionInfo.getString("question"),null,questionInfo.getString("type"));
			ArrayList<String> userAnswer = new ArrayList<String>();
			userAnswer.add(questionInfo.getString("user_answer"));
			question.setUserAnswer(userAnswer);
			return question;
		} 
		catch (JSONException e) 
		{
			this.corruptFilePrint();
			return null;
		}
		
	}

	@Override
	protected Question getShortAnswer(JSONObject questionInfo) 
	{
		try 
		{
			shortAnswer question = new shortAnswer(questionInfo.getString("question"),questionInfo.getString("answer"),questionInfo.getString("type"));
			ArrayList<String> userAnswer = new ArrayList<String>();
			userAnswer.add(questionInfo.getString("user_answer"));
			question.setUserAnswer(userAnswer);
			return question;
		} 
		catch (JSONException e)
		{
			// TODO Auto-generated catch block
			this.corruptFilePrint();
			return null;
		}
	}

	@Override
	protected Question getTrueFalse(JSONObject questionInfo) 
	{
		try 
		{
			trueFalse question = new trueFalse(questionInfo.getString("question"),questionInfo.getString("answer"),questionInfo.getString("type"));
			ArrayList<String> userAnswer = new ArrayList<String>();
			userAnswer.add(questionInfo.getString("user_answer"));
			question.setUserAnswer(userAnswer);
			return question;
		} 
		catch (JSONException e) 
		{
			this.corruptFilePrint();
			return null;
		}
	}
}
