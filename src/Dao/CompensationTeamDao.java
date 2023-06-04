package Dao;

import Employee.Employee;
import Employee.CompensationTeam;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class CompensationTeamDao extends Dao{
    public CompensationTeamDao(){
        try {
            super.connect();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void create(Employee employee){
        CompensationTeam cpEmployee = (CompensationTeam) employee;
        String query = "INSERT INTO CompensationTeam VALUES(" +
                "'"+cpEmployee.getId()+"'" +
                ");";
        super.create(query);
    }
    public CompensationTeam retrieveById(String cpEmployeeID) {
        String query = "SELECT * " +
                "FROM Employee " +
                "INNER JOIN CompensationTeam ON Employee.id = CompensationTeam.id " +
                "WHERE CompensationTeam.id  = '"+ cpEmployeeID+"';";
        CompensationTeam cpEmployee = null;
        try {
            ResultSet resultSet = super.retrieve(query);
            while(resultSet.next()) {
                cpEmployee = new CompensationTeam();
                cpEmployee.setId(resultSet.getString("id"));
                cpEmployee.setType(resultSet.getString("type"));
                cpEmployee.setName(resultSet.getString("name"));
                cpEmployee.setAge(Integer.parseInt(resultSet.getString("age")));
                cpEmployee.setGender(resultSet.getString("gender"));
                cpEmployee.setPhoneNum(resultSet.getString("phoneNum"));
                cpEmployee.setEmail(resultSet.getString("email"));
                cpEmployee.setRank(resultSet.getString("rank"));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cpEmployee;
    }
    public ArrayList<CompensationTeam> retrieveAll() {
        String query = "SELECT Employee.id, Employee.type, Employee.name, Employee.name, Employee.age, Employee.age, Employee.gender, " +
                "Employee.phoneNum, Employee.email, Employee.rank " +
                "FROM Employee " +
                "INNER JOIN CompensationTeam ON Employee.id = CompensationTeam.id;";
        ArrayList<CompensationTeam> cpEmployeeList = null;
        try {
            ResultSet resultSet = super.retrieve(query);
            cpEmployeeList = new ArrayList<CompensationTeam>();
            while(resultSet.next()) {
                CompensationTeam cpEmployee = new CompensationTeam();
                cpEmployee.setId(resultSet.getString("id"));
                cpEmployee.setType(resultSet.getString("type"));
                cpEmployee.setName(resultSet.getString("name"));
                cpEmployee.setAge(Integer.parseInt(resultSet.getString("age")));
                cpEmployee.setGender(resultSet.getString("gender"));
                cpEmployee.setPhoneNum(resultSet.getString("phoneNum"));
                cpEmployee.setEmail(resultSet.getString("email"));
                cpEmployee.setRank(resultSet.getString("rank"));
                cpEmployeeList.add(cpEmployee);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cpEmployeeList;
    }

    public void update(Employee employee){
//        CompensationTeam cpEmployee = (CompensationTeam) employee;
//        String query = "UPDATE CompensationTeam SET " +
//                 "';";
//        super.update(query);
    }
    public void deleteById(String cpEmployeeID){
        String query = "DELETE FROM CompensationTeam WHERE id = '"+cpEmployeeID+"';";
        super.delete(query);
    }
    public void deleteAll(){
        String query = "DELETE FROM CompensationTeam;";
        super.deleteAll(query);
    }
}
