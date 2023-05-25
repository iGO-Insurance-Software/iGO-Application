package Employee;

import Accident.Accident;
import Contract.Contract;
import Customer.Customer;
import Prototype.Prototype;

public class Employee {

	protected int age;

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(int phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	protected String email;
	protected int id;
	protected String gender;
	protected String name;
	protected int phoneNum;
	protected String rank;

	public Employee(){

	}

	public boolean receiveMessage(String message){
		System.out.println("\n******** "+this.name+"의 화면 ********");
		System.out.println(message);
		return true;
	}

}