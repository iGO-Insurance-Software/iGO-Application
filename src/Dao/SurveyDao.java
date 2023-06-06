package Dao;

import Employee.Employee;
import Employee.SalesTeam;

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
    public void create(Employee employee)   {
        SalesTeam salesEmployee = (SalesTeam) employee;
        String query = "INSERT INTO SalesTeam VALUES("+
                "'"+salesEmployee.getId()+"'"+
                ");";
        super.create(query);
    }
    public SalesTeam retrieveById(String salesEmployeeID) {
        String query = "SELECT * " +
                "FROM Employee " +
                "INNER JOIN SalesTeam ON Employee.id = SalesTeam.id " +
                "WHERE SalesTeam.id  = '"+ salesEmployeeID+"';";
        SalesTeam employee = null;
        try {
            ResultSet resultSet = super.retrieve(query);
            while(resultSet.next()) {
                employee = new SalesTeam();
                employee.setId(resultSet.getString("id"));
                employee.setType(resultSet.getString("type"));
                employee.setName(resultSet.getString("name"));
                employee.setAge(Integer.parseInt(resultSet.getString("age")));
                employee.setGender(resultSet.getString("gender"));
                employee.setPhoneNum(resultSet.getString("phoneNum"));
                employee.setEmail(resultSet.getString("email"));
                employee.setRank(resultSet.getString("rank"));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employee;
    }
    public ArrayList<SalesTeam> retrieveAll() {
        String query = "SELECT * " +
                "FROM Employee " +
                "INNER JOIN SalesTeam ON Employee.id = SalesTeam.id;";
        ArrayList<SalesTeam> employeeList = null;
        try {
            ResultSet resultSet = super.retrieve(query);
            employeeList = new ArrayList<SalesTeam>();
            while(resultSet.next()) {
                SalesTeam employee = new SalesTeam();
                employee.setId(resultSet.getString("id"));
                employee.setType(resultSet.getString("type"));
                employee.setName(resultSet.getString("name"));
                employee.setAge(Integer.parseInt(resultSet.getString("age")));
                employee.setGender(resultSet.getString("gender"));
                employee.setPhoneNum(resultSet.getString("phoneNum"));
                employee.setEmail(resultSet.getString("email"));
                employee.setRank(resultSet.getString("rank"));
                employeeList.add(employee);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employeeList;
    }
    public void update(Employee employee){
        SalesTeam salesEmployee = (SalesTeam) employee;
        String query = "";
        //super.update(query);
    }
    public void deleteById(String salesEmployeeID){
        String query = "DELETE FROM SalesTeam WHERE id = '"+salesEmployeeID+"';";
        super.delete(query);
    }
    public void deleteAll(){
        String query = "DELETE FROM SalesTeam;";
        super.deleteAll(query);
    }
}

