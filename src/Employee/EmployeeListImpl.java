package Employee;

import java.util.ArrayList;
public class EmployeeListImpl implements EmployeeList {



	private ArrayList<Employee> employeeList;
	public Employee employee;

	public EmployeeListImpl(){
		employeeList = new ArrayList<Employee>();
	}


	public boolean add(Employee employee){
		this.employeeList.add(employee);
		return false;
	}

	public boolean delete(int employeeID){
		return false;
	}

	public boolean update(int employeeID){
		return false;
	}

	public Employee retrieve(int employeeID){
		return null;
	}

	public ArrayList<Employee> retrieveAll() {
		return this.employeeList;
	}

}