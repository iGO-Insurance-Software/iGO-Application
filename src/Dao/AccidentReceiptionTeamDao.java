package Dao;
import Employee.Employee;
import Employee.AccidentReceiptionTeam;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccidentReceiptionTeamDao extends Dao{
    public AccidentReceiptionTeamDao(){
        try {
            super.connect();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void create(Employee employee){
        AccidentReceiptionTeam arEmployee = (AccidentReceiptionTeam) employee;
        String query = "INSERT INTO AccidentReceiptionTeam VALUES("+
                "'"+arEmployee.getId()+"'"+
                ");";
        super.create(query);
    }
    public AccidentReceiptionTeam retrieveById(String arEmployeeID) {
        String query = "SELECT Employee.id, Employee.type, Employee.name, Employee.name, Employee.age, Employee.age, Employee.gender, " +
                "Employee.phoneNum, Employee.email, Employee.rank " +
                "FROM Employee " +
                "INNER JOIN AccidentReceiptionTeam ON Employee.id = AccidentReceiptionTeam.id " +
                "WHERE AccidentReceiptionTeam.id  = '"+ arEmployeeID+"';";
        AccidentReceiptionTeam emp = null;
        try {
            ResultSet resultSet = super.retrieve(query);
            while(resultSet.next()) {
                emp = new AccidentReceiptionTeam();
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
    public ArrayList<AccidentReceiptionTeam> retrieveAll() {
        String query = "SELECT Employee.id, Employee.type, Employee.name, Employee.name, Employee.age, Employee.age, Employee.gender, " +
                "Employee.phoneNum, Employee.email, Employee.rank " +
                "FROM Employee " +
                "INNER JOIN AccidentReceiptionTeam ON Employee.id = AccidentReceiptionTeam.id;";
        ArrayList<AccidentReceiptionTeam> employeeList = null;
        try {
            ResultSet resultSet = super.retrieve(query);
            employeeList = new ArrayList<AccidentReceiptionTeam>();
            while(resultSet.next()) {
                AccidentReceiptionTeam emp = new AccidentReceiptionTeam();
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
        AccidentReceiptionTeam arEmployee = (AccidentReceiptionTeam) employee;
        String query = "";
        //super.update(query);
    }
    public void deleteById(String arEmployeeID){
        String query = "DELETE FROM AccidentReceiptionTeam WHERE id = '"+arEmployeeID+"';";
        super.delete(query);
    }
    public void deleteAll(){
        String query = "DELETE FROM Employee;";
        super.deleteAll(query);
    }
}
