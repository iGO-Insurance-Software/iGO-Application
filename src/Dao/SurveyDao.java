package Dao;

import Employee.Employee;
import Employee.SalesTeam;
import Survey.Survey;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SurveyDao extends Dao{
    public SurveyDao()   {
        try {
            super.connect();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void create(Survey survey)   {
        String query = "INSERT INTO Survey(surveyQuestionNum, customerChoice, surveyQuestionContent, " +
                "selectedCount) VALUES("+
                ""+survey.getSurveyQuestionNum()+","+
                ""+survey.getCustomerChoice()+","+
                "'"+survey.getSurveyQuestionContent()+"',"+
                ""+survey.getSelectedCount()+""+
                ");";
        super.create(query);
    }
    public Survey retrieveById(int surveyID) {
        String query = "SELECT * " +
                "FROM Survey " +
                "WHERE Survey.id  = '"+ surveyID+"';";
        Survey survey = null;
        try {
            ResultSet resultSet = super.retrieve(query);
            while(resultSet.next()) {
                survey = new Survey();
                survey.setId(resultSet.getInt("id"));
                survey.setSurveyQuestionNum(resultSet.getInt("surveyQuestionNum"));
                survey.setCustomerChoice(resultSet.getInt("customerChoice"));
                survey.setSurveyQuestionContent(resultSet.getString("surveyQuestionContent"));
                survey.setSelectedCount(resultSet.getInt("selectedCount"));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return survey;
    }
    public ArrayList<Survey> retrieveAll() {
        String query = "SELECT * FROM Survey;";
        ArrayList<Survey> surveyList = null;
        try {
            ResultSet resultSet = super.retrieve(query);
            surveyList = new ArrayList<Survey>();
            while(resultSet.next()) {
                Survey survey = new Survey();
                survey.setSurveyQuestionNum(resultSet.getInt("SurveyQuestionNum"));
                survey.setCustomerChoice(resultSet.getInt("CustomerChoice"));
                survey.setSurveyQuestionContent(resultSet.getString("SurveyQuestionContent"));
                survey.setSelectedCount(resultSet.getInt("SelectedCount"));
                surveyList.add(survey);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return surveyList;
    }
    public void update(Survey survey){
        String query = "UPDATE Survey SET " +
                "selectedCount = " + survey.getSelectedCount() + " " +
                "WHERE id = " + survey.getId() + ";";
        super.update(query);
    }
    public void deleteAll(){
        String query = "DELETE FROM Survey;";
        super.deleteAll(query);
    }
}

