package Dao;

import Employee.UWTeam;
import Employee.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UWTeamDao extends Dao{
    public UWTeamDao(){
        try {
            super.connect();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void create(Employee employee){
        UWTeam uwEmployee = (UWTeam) employee;
        String query = "INSERT INTO UWTeam VALUES("+
                "'"+uwEmployee.getId()+"',"+
                "'"+uwEmployee.getFfsContact()+"',"+
                "'"+uwEmployee.getBankClerkContact()+"'"+
                ");";
        super.create(query);
    }
    public UWTeam retrieveById(String uwEmployeeID) {
        String query = "SELECT * " +
                "FROM Employee " +
                "INNER JOIN UWTeam ON Employee.id = UWTeam.id " +
                "WHERE UWTeam.id  = '"+ uwEmployeeID+"';";
        UWTeam emp = null;
        try {
            ResultSet resultSet = super.retrieve(query);
            while(resultSet.next()) {
                emp = new UWTeam();
                emp.setId(resultSet.getString("id"));
                emp.setType(resultSet.getString("type"));
                emp.setName(resultSet.getString("name"));
                emp.setAge(Integer.parseInt(resultSet.getString("age")));
                emp.setGender(resultSet.getString("gender"));
                emp.setPhoneNum(resultSet.getString("phoneNum"));
                emp.setEmail(resultSet.getString("email"));
                emp.setRank(resultSet.getString("rank"));
                emp.setFfsContact(resultSet.getString("ffsContact"));
                emp.setBankClerkContact(resultSet.getString("bankClerkContact"));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return emp;
    }
    public ArrayList<UWTeam> retrieveAll() {
        String query = "SELECT * " +
                "FROM Employee " +
                "INNER JOIN UWTeam ON Employee.id = UWTeam.id;";
        ArrayList<UWTeam> employeeList = null;
        try {
            ResultSet resultSet = super.retrieve(query);
            employeeList = new ArrayList<UWTeam>();
            while(resultSet.next()) {
                UWTeam emp = new UWTeam();
                emp.setId(resultSet.getString("id"));
                emp.setType(resultSet.getString("type"));
                emp.setName(resultSet.getString("name"));
                emp.setAge(Integer.parseInt(resultSet.getString("age")));
                emp.setGender(resultSet.getString("gender"));
                emp.setPhoneNum(resultSet.getString("phoneNum"));
                emp.setEmail(resultSet.getString("email"));
                emp.setRank(resultSet.getString("rank"));
                emp.setFfsContact(resultSet.getString("ffsContact"));
                emp.setBankClerkContact(resultSet.getString("bankClerkContact"));
                employeeList.add(emp);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employeeList;
    }
    public void update(Employee employee){
        UWTeam arEmployee = (UWTeam) employee;
        String query = "";
        //super.update(query);
    }
    public void deleteById(String uwEmployeeID){
        String query = "DELETE FROM UWTeam WHERE id = '"+uwEmployeeID+"';";
        super.delete(query);
    }
    public void deleteAll(){
        String query = "DELETE FROM UWTeam;";
        super.deleteAll(query);
    }
}
