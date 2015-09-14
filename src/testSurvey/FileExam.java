package testSurvey;
import org.json.*; //note this is needed and included in the file. Add to build path.

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

/* notes
 * Change the dependency on filename so test name is the filename
 * This is for tests only not survey!
 */
public class FileExam 
{
	private Output output;
	
	public FileExam() 
	{
		this.output = new Output();
	}
	
	public Exam getExam(String fileName){ 
		Exam exam = new Exam(fileName); 
		String content;
		try {
			BufferedReader br = new BufferedReader(new FileReader("t_"+fileName));     
			if (br.readLine() == null) 
			{
			    this.output.println("Error: File is empty");
			    return null;
			}
			content = new Scanner(new File("t_"+fileName)).useDelimiter("\\Z").nextLine();//"t_" is to signal tests
			try {
				JSONObject test = new JSONObject(content);
				JSONObject json_exam = test.getJSONObject("Exam"); // get Exam in JSON
				exam.setTitle(fileName);
				JSONArray json_quesitons = json_exam.getJSONArray("questions"); // get question array in JSON
				//JSONArray testing = verify.getJSONArray("menuitem");
				for(int i = 0; i < json_quesitons.length(); i++){
					JSONObject questionInfo = json_quesitons.getJSONObject(i); // get individual question info in JSON
					//generate question and place into exam
					// Once again can't use switch statement for strings on this compiler
					// This tests for which type and then places the question type into the exam taking advantage of inheritance and casting
					QuestionFactory factory = new QuestionExamFactory();
					Question question = factory.getQuestion(questionInfo);
					if(question == null)
					{
						return null;
					}
					exam.add(question);
				}
				
				JSONArray json_userAnswers = test.getJSONArray("user_answers"); // get question array in JSON
				for(int i = 0; i < json_userAnswers.length(); i++)
				{
					JSONArray questions = json_userAnswers.getJSONArray(i); 
					ArrayList<Question> user_answers = new ArrayList<Question>();
					for(int j = 0; j < questions.length();j++)
					{
						// generate question and place into exam
						// Once again can't use switch statement for strings on this compiler
						// This tests for which type and then places the question type into the exam taking advantage of inheritance and casting
						JSONObject questionInfo = questions.getJSONObject(j);;
						QuestionFactory factory = new QuestionExamUserAnswerFactory();
						Question question = factory.getQuestion(questionInfo);
						if(question == null)
						{
							return null;
						}
						user_answers.add(question);
					}
					exam.addNewExam(user_answers);
				}
			} catch (JSONException e) {
				this.output.println("Error: exam is corrupt");
				//e.printStackTrace();
				return null;
			}
		} catch (FileNotFoundException e) {
			this.output.println("Error: exam not found");
			return null;
			//e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			this.output.println("Error: file is corrupt");
			return null;
		}
		return exam;
	}
	
	@SuppressWarnings("resource")
	public void saveExam(String fileName, Exam exam){
		fileName = "t_"+fileName;
		File file = new File(fileName);
		if(file.isDirectory()){
			this.output.println("The tests is invalid since a directory under the same name exists, please enter new name:");
			Scanner scan =  new Scanner(new InputStreamReader(System.in));
			saveExam(scan.nextLine(),exam);
			//exam.changeName(scan.nextLine);
			//saveExam(exam);
			return;
		}
		if (file.isFile()){
			this.output.println("This test already exists, are you sure you want to overwrite? (1 for yes, 0 for no)");
			Scanner scan =  new Scanner(new InputStreamReader(System.in));
			String x = scan.nextLine();
			if(x.equals("0")){
				this.output.println("Enter new test name: ");
				//exam.changeName(scan.nextLine);
				//saveExam(exam);
				saveExam(scan.nextLine(),exam);
				return;
			}
		}
		file = null; // end usage of file
		PrintWriter writer; // this method deletes previous content in file
		this.saveExamOverWrite(fileName, exam);
	}
	
	public void saveExamOverWrite(String fileName,Exam exam)
	{
		PrintWriter writer; // this method deletes previous content in file
		try {
			writer = new PrintWriter(fileName, "UTF-8"); 
			//writer.println(Exam.toString());
			String examString = exam.toString();
			if(examString == null)
			{
				this.output.println("Corrupt file");
				return;
			}
			writer.println(exam.toString());
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			this.output.println("Exam not found, please try again.");
			//e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			this.output.println("Encoding issue, please restart and try again.");
		}
	}
}
// ------------------------- Stuff I save for just in case moments -------------------------
/*test json

{"menu": {
"id": "file",
"value": "File",
"popup": {
  "menuitem": [
    {"value": "New", "onclick": "CreateNewDoc()"},
    {"value": "Open", "onclick": "OpenDoc()"},
    {"value": "Close", "onclick": "CloseDoc()"}
  ]
}
}}
*/
//this.output.println(questionInfo.getString("question"));
//this.output.println(questionInfo.getString("answer"));
//this.output.println(questionInfo.getString("type"));