package Employee;

import Contract.Contract;
import util.Banker;
import util.BaseException;

import java.util.ArrayList;
import java.util.HashMap;

public class UWTeam extends Employee {

	private String ffsContact;
	private ArrayList<Integer> responsibleContractIDList;
	private String bankClerkContact;

	public String getFfsContact() {
		return ffsContact;
	}
	public void setFfsContact(String ffsContact) {
		this.ffsContact = ffsContact;
	}
	public ArrayList<Integer> getResponsibleContractIDList() {
		return responsibleContractIDList;
	}
	public void setResponsibleContractIDList(ArrayList<Integer> responsibleContractIDList) {
		this.responsibleContractIDList = responsibleContractIDList;
	}
	public String getBankClerkContact() {
		return bankClerkContact;
	}
	public void setBankClerkContact(String bankClerkContact) {
		this.bankClerkContact = bankClerkContact;
	}

	public UWTeam(){
	}

	public HashMap<String, String> requestCustomerInformation(HashMap<String, String> basicCustomerInfo, String bankClerkContact) throws BaseException {
		Banker banker = new Banker(bankClerkContact);
		HashMap<String, String> responseInfo = null;
		try {
			responseInfo = banker.requestInfo(basicCustomerInfo);
		} catch (BaseException e) {
			if(e.getMessage().equals("현재 고객 정보 요청에 대한 응답이 없어 재요청 하였습니다.")) responseInfo = banker.requestInfo(basicCustomerInfo);
		}
		return responseInfo;
	}

	public boolean registerReinsurance(HashMap<String, String> contractDetails, HashMap<String, String> reinsuranceCompanyManagerInfo){
		return false;
	}

}