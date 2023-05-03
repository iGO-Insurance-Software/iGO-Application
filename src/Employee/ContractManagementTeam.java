package Employee;

import Customer.Customer;

import java.util.ArrayList;

public class ContractManagementTeam extends Employee {

	public ContractManagementTeam(){

	}

	public boolean examineRevivalOfInsurance(){
		return false;
	}

	public boolean informNonPayment(){
		return false;
	}

	public boolean informContractExpiration(){
		return false;
	}

	public boolean informVoidInsurance(){
		return false;
	}

	public ArrayList<Customer> getUnpaidCustomer(){
		return null;
	}

	public ArrayList<Customer> getContractExpirationCustomer(){
		return null;
	}

	public ArrayList<Customer> getCustomerApplyingInsuranceRevival(){
		return null;
	}

}