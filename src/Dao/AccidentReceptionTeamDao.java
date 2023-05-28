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
        AccidentReceptionTeam emp = null;
        try {
            ResultSet resultSet = super.retrieve(query);
            while(resultSet.next()) {
                emp = new AccidentReceptionTeam();
                emp.setId(resultSet.getString("id"));
                emp.setType(resultSet.getString("type"));
                emp.setName(resultSet.getString("name"));
                emp.setAge(Integer.parseInt(resultSet.getString("age")));
                emp.setGender(resultSet.getString("gender"));
                emp.setPhoneNum(resultSet.getString("phoneNum"));
                emp.setEmail(resultSet.getString("email"));
                emp.setRank(resultSet.getString("rank"));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return emp;
    }
    public ArrayList<AccidentReceptionTeam> retrieveAll() {
        String query = "SELECT Employee.id, Employee.type, Employee.name, Employee.name, Employee.age, Employee.age, Employee.gender, " +
                "Employee.phoneNum, Employee.email, Employee.rank " +
                "FROM Employee " +
                "INNER JOIN AccidentReceptionTeam ON Employee.id = AccidentReceptionTeam.id;";
        ArrayList<AccidentReceptionTeam> employeeList = null;
        try {
            ResultSet resultSet = super.retrieve(query);
            employeeList = new ArrayList<AccidentReceptionTeam>();
            while(resultSet.next()) {
                AccidentReceptionTeam emp = new AccidentReceptionTeam();
                emp.setId(resultSet.getString("id"));
                emp.setType(resultSet.getString("type"));
                emp.setName(resultSet.getString("name"));
                emp.setAge(Integer.parseInt(resultSet.getString("age")));
                emp.setGender(resultSet.getString("gender"));
                emp.setPhoneNum(resultSet.getString("phoneNum"));
                emp.setEmail(resultSet.getString("email"));
                emp.setRank(resultSet.getString("rank"));
                employeeList.add(emp);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employeeList;
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
