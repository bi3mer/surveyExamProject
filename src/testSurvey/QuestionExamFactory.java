package testSurvey;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class QuestionExamFactory extends QuestionFactory{
	private Output output;
	
	public QuestionExamFactory() 
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
			JSONArray multichoiceArr = questionInfo.getJSONArray("answers");
			ArrayList<String> answers = new ArrayList<String>();
			for(int j = 0;j<multichoiceArr.length();j++){
				JSONObject multiObj = multichoiceArr.getJSONObject(j);
				answers.add(multiObj.getString("answer"));
			}
			MultipleChoice question = new MultipleChoice(questionInfo.getString("question"), answers,questionInfo.getString("answer"),questionInfo.getString("type"));
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
			for(int j = 0;j<matchArr.length();j++)
			{
				JSONObject multiObj = matchArr.getJSONObject(j);
				answers.add(multiObj.getString("answer"));
			}
			matchArr = questionInfo.getJSONArray("options");
			ArrayList<String> options = new ArrayList<String>();
			for(int j = 0;j<matchArr.length();j++)
			{
				JSONObject multiObj = matchArr.getJSONObject(j);
				options.add(multiObj.getString("option"));
			}
			Rank question = new Rank(questionInfo.getString("question"), options,answers,questionInfo.getString("type"));
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
			for(int j = 0;j<matchArr.length();j++){
				JSONObject multiObj = matchArr.getJSONObject(j);
				answers.add(multiObj.getString("answer"));
			}
			matchArr = questionInfo.getJSONArray("options");
			ArrayList<String> options = new ArrayList<String>();
			for(int j = 0;j<matchArr.length();j++){
				JSONObject multiObj = matchArr.getJSONObject(j);
				options.add(multiObj.getString("option"));
			}
			Match question = new Match(questionInfo.getString("question"), options,answers,questionInfo.getString("type"));
			return question;
		} catch (JSONException e) {
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
			Essay question = new Essay(questionInfo.getString("question"),questionInfo.getString("answer"),questionInfo.getString("type"));
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
			return question;
		} 
		catch (JSONException e) {
			// TODO Auto-generated catch block
			this.corruptFilePrint();
			return null;
		}
	}

	@Override
	protected Question getTrueFalse(JSONObject questionInfo) 
	{
		trueFalse question;
		try 
		{
			question = new trueFalse(questionInfo.getString("question"),questionInfo.getString("answer"),questionInfo.getString("type"));
			return question;
		} 
		catch (JSONException e) 
		{
			this.corruptFilePrint();
			return null;
		}
	}
}
