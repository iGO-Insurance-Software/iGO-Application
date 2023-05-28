package Dao;

import Employee.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDao extends Dao {
    private AccidentReceptionTeamDao accidentReceptionTeamDao;
    public EmployeeDao(){
        accidentReceptionTeamDao = new AccidentReceptionTeamDao();
        try {
            super.connect();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void create(Employee employee){
        String query = "INSERT INTO Employee VALUES(" +
                "'"+employee.getId()+"'," +
                "'"+employee.getType()+"'," +
                "'"+employee.getName()+"'," +
                employee.getAge()+"," +
                "'"+employee.getGender()+"'," +
                "'"+employee.getPhoneNum()+"'," +
                "'"+employee.getEmail()+"'," +
                "'"+employee.getRank()+"'"+
                ");";
        super.create(query);
        if(employee.getType().equals("AccidentReception")) accidentReceptionTeamDao.create(employee);
    }
    public Employee retrieveById(String employeeID) {
        String query = "SELECT * FROM Employee WHERE id  = '"+
                employeeID+"';";
        Employee emp = null;
        try {
            ResultSet resultSet = super.retrieve(query);
            while(resultSet.next()) {
                emp = new Employee();
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
    public ArrayList<Employee> retrieveAll() {
        String query = "SELECT * FROM Employee;";
        ArrayList<Employee> employeeList = null;
        try {
            ResultSet resultSet = super.retrieve(query);
            employeeList = new ArrayList<Employee>();
            while(resultSet.next()) {
                Employee emp = new Employee();
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
    public ArrayList<Employee> retrieveAllEmployee() {
        String query = "SELECT * FROM Employee;";
        ArrayList<Employee> employeeList = null;
        try {
            ResultSet resultSet = super.retrieve(query);
            employeeList = new ArrayList<Employee>();
            while(resultSet.next()) {
                String type = resultSet.getString("type");
                if(!type.equals("Employee")) continue;//하위 테이블일 경우 여기서 조회하지 않고 하위 Dao로 넘김
                Employee emp = new Employee();
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
            employeeList.addAll(accidentReceptionTeamDao.retrieveAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employeeList;
    }
    public void update(Employee employee){
        String query = "UPDATE Employee SET " +
                "type = '" + employee.getType() + "', " +
                "name = '" + employee.getName() + "', " +
                "age = " + employee.getAge() + ", " +
                "gender = '" + employee.getGender() + "', " +
                "phoneNum = '" + employee.getPhoneNum() + "', " +
                "email = '" + employee.getEmail() + "', " +
                "`rank` = '" + employee.getRank() + "' " +
                "WHERE id = '" + employee.getId() + "';";
        super.update(query);
        if(employee.getType().equals("AccidentReception")) accidentReceptionTeamDao.update(employee);
    }
    public void deleteById(String employeeID){
        String query = "DELETE FROM Employee WHERE id = '"+employeeID+"';";
        super.delete(query);
    }
    public void deleteAll(){
        String query = "DELETE FROM Employee;";
        super.deleteAll(query);
    }
}
