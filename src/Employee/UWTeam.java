package Employee;

import Contract.Contract;
import Contract.Reinsurance;
import Customer.InsuredCustomer;
import Insurance.Insurance;
import util.Banker;
import util.BaseException;
import util.ErrorCode;
import util.ReinsuranceCompanyManager;

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
			if(e.equals(ErrorCode.NO_RESPONSE_TO_CURRENT_CUSTOMER_INFO_REQ)) responseInfo = banker.requestInfo(basicCustomerInfo);
		}
		return responseInfo;
	}

	public HashMap<String, String> registerReinsurance(Reinsurance reinsurance, Contract contract, Insurance insurance, InsuredCustomer insuredCustomer) throws BaseException {
		ReinsuranceCompanyManager reinsuranceCompanyManager = new ReinsuranceCompanyManager(reinsurance.getReinsuranceCompanyManagerContract());
		HashMap<String, String> responseInfo = null;
		try {
			responseInfo = reinsuranceCompanyManager.requestRegisterReinsurance(reinsurance, contract, insurance, insuredCustomer);
			if(responseInfo.get("isResult").equals("false")) reinsurance.setRejectionReasons(responseInfo.get("rejectReason"));
		} catch (BaseException e) {
			if(e.equals(ErrorCode.NO_RESPONSE_TO_REGISTER_REINSURANCE_INFO_REQ)) responseInfo = reinsuranceCompanyManager.requestRegisterReinsurance(reinsurance, contract, insurance, insuredCustomer);
		}
		return responseInfo;
	}

}