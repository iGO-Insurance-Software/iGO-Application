package Employee;

import java.util.ArrayList;

public interface EmployeeList {

	public boolean add();

	public boolean delete(int employeeID);

	public boolean update(int employeeID);


	public Employee retrieve(int employeeID);

	public ArrayList<Employee> retrieveAll();

}