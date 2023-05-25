package Customer;

import java.util.ArrayList;

public interface CustomerList {

	public boolean add(Customer customer);

	public boolean delete(String customerID);


	public Customer retrieve(String customerID);

	public boolean update(String customerID);

	public ArrayList<Customer> retrieveAll();

	public ArrayList<Customer> retrieveAllCustomer();

}