package Customer;

import Dao.CustomerDao;
import Employee.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerListImpl implements CustomerList {
	private CustomerDao customerDao;
	public Customer customer;
	public CustomerListImpl(){
		 customerDao = new CustomerDao();
	}

	public boolean add(Customer customer){
		customerDao.create(customer);
		return true;
	}

	public boolean delete(String customerID){
		customerDao.deleteById(customerID);
		return true;
	}

	public Customer retrieve(String customerID){
		return customerDao.retrieveById(customerID);
	}

	public boolean update(String customerID){
		return false;
	}

	public ArrayList<Customer> retrieveAll(){
		return customerDao.retrieveAll();
	}

	public ArrayList<Customer> retrieveAllCustomer(){
		return customerDao.retrieveAllCustomer();
	}

}