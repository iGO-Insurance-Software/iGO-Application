package Dao;
import Employee.Employee;
import Employee.AccidentReceptionTeam;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccidentReceptionTeamDao extends Dao{
    public AccidentReceptionTeamDao(){
        try {
            super.connect();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void create(Employee employee){
        AccidentReceptionTeam arEmployee = (AccidentReceptionTeam) employee;
        String query = "INSERT INTO AccidentReceptionTeam VALUES("+
                "'"+arEmployee.getId()+"'"+
                ");";
        super.create(query);
    }
    public AccidentReceptionTeam retrieveById(String arEmployeeID) {
        String query = "SELECT Employee.id, Employee.type, Employee.name, Employee.name, Employee.age, Employee.age, Employee.gender, " +
                "Employee.phoneNum, Employee.email, Employee.rank " +
                "FROM Employee " +
                "INNER JOIN AccidentReceptionTeam ON Employee.id = AccidentReceptionTeam.id " +
                "WHERE AccidentReceptionTeam.id  = '"+ arEmployeeID+"';";
        AccidentReceptionTeam arEmployee = null;
        try {
            ResultSet resultSet = super.retrieve(query);
            while(resultSet.next()) {
                arEmployee = new AccidentReceptionTeam();
                arEmployee.setId(resultSet.getString("id"));
                arEmployee.setType(resultSet.getString("type"));
                arEmployee.setName(resultSet.getString("name"));
                arEmployee.setAge(Integer.parseInt(resultSet.getString("age")));
                arEmployee.setGender(resultSet.getString("gender"));
                arEmployee.setPhoneNum(resultSet.getString("phoneNum"));
                arEmployee.setEmail(resultSet.getString("email"));
                arEmployee.setRank(resultSet.getString("rank"));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return arEmployee;
    }
    public ArrayList<AccidentReceptionTeam> retrieveAll() {
        String query = "SELECT Employee.id, Employee.type, Employee.name, Employee.name, Employee.age, Employee.age, Employee.gender, " +
                "Employee.phoneNum, Employee.email, Employee.rank " +
                "FROM Employee " +
                "INNER JOIN AccidentReceptionTeam ON Employee.id = AccidentReceptionTeam.id;";
        ArrayList<AccidentReceptionTeam> arEmployeeList = null;
        try {
            ResultSet resultSet = super.retrieve(query);
            arEmployeeList = new ArrayList<AccidentReceptionTeam>();
            while(resultSet.next()) {
                AccidentReceptionTeam arEmployee = new AccidentReceptionTeam();
                arEmployee.setId(resultSet.getString("id"));
                arEmployee.setType(resultSet.getString("type"));
                arEmployee.setName(resultSet.getString("name"));
                arEmployee.setAge(Integer.parseInt(resultSet.getString("age")));
                arEmployee.setGender(resultSet.getString("gender"));
                arEmployee.setPhoneNum(resultSet.getString("phoneNum"));
                arEmployee.setEmail(resultSet.getString("email"));
                arEmployee.setRank(resultSet.getString("rank"));
                arEmployeeList.add(arEmployee);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return arEmployeeList;
    }
    public void update(Employee employee){
        AccidentReceptionTeam arEmployee = (AccidentReceptionTeam) employee;
        String query = "";
        //super.update(query);
    }
    public void deleteById(String arEmployeeID){
        String query = "DELETE FROM AccidentReceptionTeam WHERE id = '"+arEmployeeID+"';";
        super.delete(query);
    }
    public void deleteAll(){
        String query = "DELETE FROM AccidentReceptionTeam;";
        super.deleteAll(query);
    }
}
