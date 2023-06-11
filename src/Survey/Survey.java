package Survey;

import Customer.Customer;

import java.util.HashMap;

public class Survey {
	private int id;
	private int surveyQuestionNum;
	private int customerChoice;
	private String surveyQuestionContent;
	private int selectedCount;

	public Survey(){
	}
	public int getId()	{ return id;}
	public void setId(int id) { this.id = id; }
	public int getSurveyQuestionNum() {
		return surveyQuestionNum;
	}
	public void setSurveyQuestionNum(int surveyQuestionNum) { this.surveyQuestionNum = surveyQuestionNum; }
	public int getCustomerChoice() {
		return customerChoice;
	}
	public void setCustomerChoice(int customerChoice) {
		this.customerChoice = customerChoice;
	}
	public String getSurveyQuestionContent() {
		return surveyQuestionContent;
	}
	public void setSurveyQuestionContent(String surveyQuestionContent) { this.surveyQuestionContent = surveyQuestionContent; }
	public int getSelectedCount() {
		return selectedCount;
	}
	public void setSelectedCount(int selectedCount) {
		this.selectedCount = selectedCount;
	}
	public boolean storeSurveyResult(int age, int how, int forWhat){
		return false;
	}

}