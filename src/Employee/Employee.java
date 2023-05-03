package Employee;

import Accident.Accident;
import Contract.Contract;
import Customer.Customer;
import Prototype.Prototype;

public class Employee {

	private int age;
	private String email;
	private int id;
	private String gender;
	private String name;
	private int phoneNum;
	private String rank;
	public Contract m_Contract;
	public Reinsurance m_Reinsurance;
	public Prototype m_Prototype;
	public Customer m_Customer;
	public Accident m_Accident;

	public Employee(){

	}

	public boolean receiveMessage(String message){
		return false;
	}

}