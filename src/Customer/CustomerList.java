package Customer;

import java.util.ArrayList;

public interface CustomerList {

	public boolean add(Customer customer);

	public boolean delete(int customerID);


	public Customer retrieve(int customerID);

	public boolean update(int customerID);

	public ArrayList<Customer> retrieveAll();

}