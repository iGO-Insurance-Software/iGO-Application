package Customer;

import java.util.ArrayList;

public interface CustomerList {

	public boolean add();

	public boolean delete(int customerID);


	public Customer retireve(int customerID);

	public boolean update(int customerID);

	public ArrayList<Customer> retrieveAll();

}