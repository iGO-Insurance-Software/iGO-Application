package Dao;

import Employee.Employee;
import Employee.MarketingTeam;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MarketingTeamDao extends Dao{
    public MarketingTeamDao()   {
        try {
            super.connect();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void create(Employee employee)   {
        MarketingTeam marketingEmployee = (MarketingTeam) employee;
        String query = "INSERT INTO MarketingTeam VALUES("+
                "'"+marketingEmployee.getId()+"'"+
                ");";
        super.create(query);
    }
    public MarketingTeam retrieveById(String marketingEmployeeID) {
        String query = "SELECT * " +
                "FROM Employee " +
                "INNER JOIN MarketingTeam ON Employee.id = MarketingTeam.id " +
                "WHERE MarketingTeam.id  = '"+ marketingEmployeeID+"';";
        MarketingTeam employee = null;
        try {
            ResultSet resultSet = super.retrieve(query);
            while(resultSet.next()) {
                employee = new MarketingTeam();
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
    public ArrayList<MarketingTeam> retrieveAll() {
        String query = "SELECT * " +
                "FROM Employee " +
                "INNER JOIN MarketingTeam ON Employee.id = MarketingTeam.id;";
        ArrayList<MarketingTeam> employeeList = null;
        try {
            ResultSet resultSet = super.retrieve(query);
            employeeList = new ArrayList<MarketingTeam>();
            while(resultSet.next()) {
                MarketingTeam employee = new MarketingTeam();
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
        MarketingTeam marketingEmployee = (MarketingTeam) employee;
        String query = "";
        //super.update(query);
    }
    public void deleteById(String marketingEmployeeID){
        String query = "DELETE FROM MarketingTeam WHERE id = '"+marketingEmployeeID+"';";
        super.delete(query);
    }
    public void deleteAll(){
        String query = "DELETE FROM MarketingTeam;";
        super.deleteAll(query);
    }
}

