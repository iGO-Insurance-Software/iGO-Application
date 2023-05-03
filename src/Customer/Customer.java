package Customer;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Customer {

	private int age;
	private String bankAccount;
	private Date birthDate;
	private String gender;
	private int id;
	private String name;
	private String phoneNum;

	private String rrn;
	private String occupation;

	public Customer(){

	}


	public boolean filloutInfo(){
		return false;
	}

	public HashMap<String,String> getInfo(){
		return null;
	}

	public String showAd(){
		return "";
	}

	public boolean showInsurances(){
		return false;
	}

	public boolean receiveMessage(String message){
		return false;
	}


	public HashMap<String, String> submitDocs(ArrayList<String> requiredDocs){
		return null;
	}

}