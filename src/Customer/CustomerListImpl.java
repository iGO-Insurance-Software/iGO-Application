package Customer;

import java.util.ArrayList;

public class CustomerListImpl implements CustomerList {

	private ArrayList<Customer> customerList;
	public Customer customer;
	public CustomerListImpl(){customerList = new ArrayList<Customer>();
	}

	public boolean add(Customer customer){
		customer.setId(customerList.size()+1);
		customerList.add(customer);
		return true;
	}

	public boolean delete(int customerID){
		return false;
	}

	public Customer retrieve(int customerID){
		for(Customer cust : this.customerList){
			if(cust.getId()==customerID){
				return cust;
			}
		}
		return null;
	}

	public boolean update(int customerID){
		return false;
	}

	public ArrayList<Customer> retrieveAll(){
		return customerList;
	}

}