package Employee;

import Contract.Contract;

import java.util.ArrayList;
import java.util.HashMap;

public class UWTeam extends Employee {

	private String ffsContact;
	private ArrayList<Integer> responsibleContractIDList;
	private String bankClerkContact;

	public UWTeam(){

	}

	public HashMap<String, String> requestCustomerInformation(HashMap<String, String> basicCustomerInfo, String bankClerkContactContact){
		return null;
	}

	public ArrayList<Contract> getWaitStateContract(){
		return null;
	}

	public boolean registerReinsurance(HashMap<String, String> contractDetails, HashMap<String, String> reinsuranceCompanyManagerInfo){
		return false;
	}

}