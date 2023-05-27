package Employee;

import java.util.ArrayList;

public interface EmployeeList {

	public boolean add(Employee employee);

	public boolean delete(String employeeID);

	public boolean update(String employeeID);


	public Employee retrieve(String employeeID);

	public ArrayList<Employee> retrieveAll();

}