package Dao;

import Employee.Employee;
import Employee.InvestigationTeam;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InvestigationTeamDao extends Dao{
    public InvestigationTeamDao(){
        try {
            super.connect();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void create(Employee employee){
        InvestigationTeam igEmployee = (InvestigationTeam) employee;
        String query = "INSERT INTO InvestigationTeam VALUES(" +
                "'"+igEmployee.getId()+"'," +
                igEmployee.getAccidentID()+"," +
                igEmployee.getIsDispatching()+
                ");";
        super.create(query);
    }
    public InvestigationTeam retrieveById(String igEmployeeID) {
        String query = "SELECT Employee.id, Employee.type, Employee.name, Employee.name, Employee.age, Employee.age, Employee.gender, " +
                "Employee.phoneNum, Employee.email, Employee.rank, InvestigationTeam.accidentID, InvestigationTeam.isDispatching " +
                "FROM Employee " +
                "INNER JOIN InvestigationTeam ON Employee.id = InvestigationTeam.id " +
                "WHERE InvestigationTeam.id  = '"+ igEmployeeID+"';";
        InvestigationTeam igEmployee = null;
        try {
            ResultSet resultSet = super.retrieve(query);
            while(resultSet.next()) {
                igEmployee = new InvestigationTeam();
                igEmployee.setId(resultSet.getString("id"));
                igEmployee.setType(resultSet.getString("type"));
                igEmployee.setName(resultSet.getString("name"));
                igEmployee.setAge(Integer.parseInt(resultSet.getString("age")));
                igEmployee.setGender(resultSet.getString("gender"));
                igEmployee.setPhoneNum(resultSet.getString("phoneNum"));
                igEmployee.setEmail(resultSet.getString("email"));
                igEmployee.setRank(resultSet.getString("rank"));
                igEmployee.setAccidentID(resultSet.getInt("accidentID"));
                igEmployee.setIsDispatching(resultSet.getBoolean("isDispatching"));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return igEmployee;
    }
    public ArrayList<InvestigationTeam> retrieveAll() {
        String query = "SELECT Employee.id, Employee.type, Employee.name, Employee.name, Employee.age, Employee.age, Employee.gender, " +
                "Employee.phoneNum, Employee.email, Employee.rank, InvestigationTeam.accidentID, InvestigationTeam.isDispatching " +
                "FROM Employee " +
                "INNER JOIN InvestigationTeam ON Employee.id = InvestigationTeam.id;";
        ArrayList<InvestigationTeam> igEmployeeList = null;
        try {
            ResultSet resultSet = super.retrieve(query);
            igEmployeeList = new ArrayList<InvestigationTeam>();
            while(resultSet.next()) {
                InvestigationTeam igEmployee = new InvestigationTeam();
                igEmployee.setId(resultSet.getString("id"));
                igEmployee.setType(resultSet.getString("type"));
                igEmployee.setName(resultSet.getString("name"));
                igEmployee.setAge(Integer.parseInt(resultSet.getString("age")));
                igEmployee.setGender(resultSet.getString("gender"));
                igEmployee.setPhoneNum(resultSet.getString("phoneNum"));
                igEmployee.setEmail(resultSet.getString("email"));
                igEmployee.setRank(resultSet.getString("rank"));
                igEmployee.setAccidentID(resultSet.getInt("accidentID"));
                igEmployee.setIsDispatching(resultSet.getBoolean("isDispatching"));
                igEmployeeList.add(igEmployee);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return igEmployeeList;
    }

    public void update(Employee employee){
        InvestigationTeam igEmployee = (InvestigationTeam) employee;
        String query = "UPDATE InvestigationTeam SET " +
                "accidentID = " + igEmployee.getAccidentID() + ", " +
                "isDispatching = " + igEmployee.getIsDispatching() + " " +
                "WHERE id = '" + igEmployee.getId() + "';";
        super.update(query);
    }
    public void deleteById(String igEmployeeID){
        String query = "DELETE FROM InvestigationTeam WHERE id = '"+igEmployeeID+"';";
        super.delete(query);
    }
    public void deleteAll(){
        String query = "DELETE FROM InvestigationTeam;";
        super.deleteAll(query);
    }

}
