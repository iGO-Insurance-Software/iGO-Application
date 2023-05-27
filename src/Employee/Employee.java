package Employee;

import Accident.Accident;
import Contract.Contract;
import Customer.Customer;
import Prototype.Prototype;

public class Employee {
	protected String id;
	protected String type;
	protected String name;
	protected int age;
	protected String gender;
	protected String phoneNum;
	protected String email;
	protected String rank;

	public Employee(){

	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public boolean receiveMessage(String message){
		System.out.println("\n******** "+this.name+"의 화면 ********");
		System.out.println(message);
		return true;
	}

}