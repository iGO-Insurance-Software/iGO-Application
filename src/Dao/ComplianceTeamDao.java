package Dao;

import Employee.Employee;
import Employee.ComplianceTeam;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ComplianceTeamDao extends Dao {


    public ComplianceTeamDao() {
        try {
            super.connect();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void create(Employee employee) {
        ComplianceTeam complianceEmployee = (ComplianceTeam) employee;
        String query = "INSERT INTO ComplianceTeam VALUES(" +
                "'" + complianceEmployee.getId() + "'" +
                ");";
        super.create(query);
    }
    public ComplianceTeam retrieveById(String complianceEmployeeID) {
        String query = "SELECT Employee.id, Employee.type, Employee.name, Employee.name, Employee.age, Employee.age, Employee.gender, " +
                "Employee.phoneNum, Employee.email, Employee.rank " +
                "FROM Employee " +
                "INNER JOIN ComplianceTeam ON Employee.id = ComplianceTeam.id " +
                "WHERE ComplianceTeam.id = '" + complianceEmployeeID + "';";
        ComplianceTeam employee = null;
        try {
            ResultSet resultSet = super.retrieve(query);
            while (resultSet.next()) {
                employee = new ComplianceTeam();
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
    public ArrayList<ComplianceTeam> retrieveAll() {
        String query = "SELECT Employee.id, Employee.type, Employee.name, Employee.name, Employee.age, Employee.age, Employee.gender, " +
                "Employee.phoneNum, Employee.email, Employee.rank " +
                "FROM Employee " +
                "INNER JOIN ComplianceTeam ON Employee.id = ComplianceTeam.id;";
        ArrayList<ComplianceTeam> employeeList = null;
        try {
            ResultSet resultSet = super.retrieve(query);
            employeeList = new ArrayList<>();
            while (resultSet.next()) {
                ComplianceTeam employee = new ComplianceTeam();
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
    public void update(Employee employee) {
        ComplianceTeam complianceEmployee = (ComplianceTeam) employee;
        String query = "";
        //super.update(query);
    }
    public void deleteById(String complianceEmployeeID) {
        String query = "DELETE FROM ComplianceTeam WHERE id = '" + complianceEmployeeID + "';";
        super.delete(query);
    }
    public void deleteAll() {
        String query = "DELETE FROM ComplianceTeam;";
        super.deleteAll(query);
    }
}


