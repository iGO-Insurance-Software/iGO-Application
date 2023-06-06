package Dao;

import Employee.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDao extends Dao {
    private AccidentReceptionTeamDao accidentReceptionTeamDao;
    private InvestigationTeamDao investigationTeamDao;
    private UWTeamDao uwTeamDao;
    private CompensationTeamDao compensationTeamDao;
    private ComplianceTeamDao complianceTeamDao;
    private ProductDevelopmentTeamDao productDevelopmentTeamDao;

    public EmployeeDao() {
        accidentReceptionTeamDao = new AccidentReceptionTeamDao();
        investigationTeamDao = new InvestigationTeamDao();
        uwTeamDao = new UWTeamDao();
        compensationTeamDao = new CompensationTeamDao();
        complianceTeamDao = new ComplianceTeamDao();
        productDevelopmentTeamDao = new ProductDevelopmentTeamDao();
        try {
            super.connect();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void create(Employee employee) {
        String query = "INSERT INTO Employee VALUES(" +
                "'" + employee.getId() + "'," +
                "'" + employee.getType() + "'," +
                "'" + employee.getName() + "'," +
                employee.getAge() + "," +
                "'" + employee.getGender() + "'," +
                "'" + employee.getPhoneNum() + "'," +
                "'" + employee.getEmail() + "'," +
                "'" + employee.getRank() + "'" +
                ");";
        super.create(query);
        if (employee.getType().equals("AccidentReception")) accidentReceptionTeamDao.create(employee);
        else if (employee.getType().equals("UW")) uwTeamDao.create(employee);
        else if (employee.getType().equals("Investigation")) investigationTeamDao.create(employee);
        else if (employee.getType().equals("Compensation")) compensationTeamDao.create(employee);
        else if (employee.getType().equals("ProductDevelopment")) productDevelopmentTeamDao.create(employee);
        else if (employee.getType().equals("Compliance")) complianceTeamDao.create(employee);
    }
    public Employee retrieveById(String employeeID) {
        String query = "SELECT * FROM Employee WHERE id  = '"+
                employeeID+"';";
        Employee employee = null;
        try {
            ResultSet resultSet = super.retrieve(query);
            while(resultSet.next()) {
                employee = new Employee();
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
    public ArrayList<Employee> retrieveAll() {
        String query = "SELECT * FROM Employee;";
        ArrayList<Employee> employeeList = null;
        try {
            ResultSet resultSet = super.retrieve(query);
            employeeList = new ArrayList<Employee>();
            while(resultSet.next()) {
                Employee employee = new Employee();
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
    public ArrayList<Employee> retrieveAllEmployee() {
        String query = "SELECT * FROM Employee;";
        ArrayList<Employee> employeeList = null;
        try {
            ResultSet resultSet = super.retrieve(query);
            employeeList = new ArrayList<Employee>();
            while(resultSet.next()) {
                String type = resultSet.getString("type");
                if(!type.equals("Employee")) continue;//하위 테이블일 경우 여기서 조회하지 않고 하위 Dao로 넘김
                Employee employee = new Employee();
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
            employeeList.addAll(accidentReceptionTeamDao.retrieveAll());
            employeeList.addAll(investigationTeamDao.retrieveAll());
            employeeList.addAll(uwTeamDao.retrieveAll());
            employeeList.addAll(compensationTeamDao.retrieveAll());
            employeeList.addAll(complianceTeamDao.retrieveAll());
            employeeList.addAll(productDevelopmentTeamDao.retrieveAll());
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
        else if(employee.getType().equals("UW")) uwTeamDao.update(employee);
        else if(employee.getType().equals("Investigation")) investigationTeamDao.update(employee);
        else if(employee.getType().equals("Compensation")) compensationTeamDao.update(employee);
        else if (employee.getType().equals("ProductDevelopment")) productDevelopmentTeamDao.update(employee);
        else if (employee.getType().equals("Compliance")) complianceTeamDao.update(employee);
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
