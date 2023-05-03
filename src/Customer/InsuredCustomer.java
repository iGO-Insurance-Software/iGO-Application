package Customer;

import java.util.HashMap;

public class InsuredCustomer extends Customer {

	private String familyHistory;

	private String healthCertificate;
	private String employmentCertificate;
	private String inheritanceCertificate;

	private String accidentCertificate;
	private String judgmentCertificate;

	private String medicalCertificate;
	private String bankbookCopy;

	public InsuredCustomer(){

	}

	public boolean sendReceiption(){
		return false;
	}

	public boolean filloutInfo(){
		return false;
	}

	public boolean cancelReceiption(){
		return false;
	}

	public HashMap<String,String> getInfo(){
		return null;
	}

}