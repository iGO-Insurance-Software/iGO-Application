package Dao;

import Employee.Employee;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Employee.ProductDevelopmentTeam;

public class ProductDevelopmentTeamDao extends Dao {
    public ProductDevelopmentTeamDao() {
        try {
            super.connect();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void create(Employee employee) {
        ProductDevelopmentTeam productDevelopmentEmployee = (ProductDevelopmentTeam) employee;
        String query = "INSERT INTO ProductDevelopmentTeam VALUES(" +
                "'" + productDevelopmentEmployee.getId() + "'" +
                ");";
        super.create(query);
    }
    public ProductDevelopmentTeam retrieveById(String productDevelopmentTeamEmployeeID) {
        String query = "SELECT Employee.id, Employee.type, Employee.name, Employee.name, Employee.age, Employee.age, Employee.gender, " +
                "Employee.phoneNum, Employee.email, Employee.rank " +
                "FROM Employee " +
                "INNER JOIN ProductDevelopmentTeam ON Employee.id = ProductDevelopmentTeam.id " +
                "WHERE Employee.id = '" + productDevelopmentTeamEmployeeID + "';";
        ProductDevelopmentTeam employee = null;
        try {
            ResultSet resultSet = super.retrieve(query);
            while (resultSet.next()) {
                employee = new ProductDevelopmentTeam();
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
    public ArrayList<ProductDevelopmentTeam> retrieveAll() {
        String query = "SELECT Employee.id, Employee.type, Employee.name, Employee.name, Employee.age, Employee.age, Employee.gender, " +
                "Employee.phoneNum, Employee.email, Employee.rank " +
                "FROM Employee " +
                "INNER JOIN ProductDevelopmentTeam ON Employee.id = ProductDevelopmentTeam.id;";
        ArrayList<ProductDevelopmentTeam> employeeList = null;
        try {
            ResultSet resultSet = super.retrieve(query);
            employeeList = new ArrayList<>();
            while (resultSet.next()) {
                ProductDevelopmentTeam employee = new ProductDevelopmentTeam();
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
        ProductDevelopmentTeam productDevelopmentTeamEmployee = (ProductDevelopmentTeam) employee;
        String query = "";
        super.update(query);
    }
    public void deleteById(String productDevelopmentTeamEmployeeID) {
        String query = "DELETE FROM ComplianceTeam WHERE id = '" + productDevelopmentTeamEmployeeID + "';";
        super.delete(query);
    }
    public void deleteAll() {
        String query = "DELETE FROM ProductDevelopmentTeam;";
        super.execute(query);
    }
}

