package Survey;

import java.util.ArrayList;

public interface SurveyList {

	public boolean add();


	public boolean delete(int surveyNumber);


	public boolean update(int surveyNumber);


	public Survey retrieve(int surveyNumber);

	public ArrayList<Survey> retrieveAll();

}