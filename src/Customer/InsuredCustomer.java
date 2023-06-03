package Customer;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class InsuredCustomer extends Customer {
	private String familyHistory;
	private String healthCertificate;
	private String employmentCertificate;
	private String inheritanceCertificate;

	public InsuredCustomer(){

	}

	public String getFamilyHistory() {
		return familyHistory;
	}

	public void setFamilyHistory(String familyHistory) {
		this.familyHistory = familyHistory;
	}

	public String getHealthCertificate() {
		return healthCertificate;
	}

	public void setHealthCertificate(String healthCertificate) {
		this.healthCertificate = healthCertificate;
	}

	public String getEmploymentCertificate() {
		return employmentCertificate;
	}

	public void setEmploymentCertificate(String employmentCertificate) {
		this.employmentCertificate = employmentCertificate;
	}

	public String getInheritanceCertificate() {
		return inheritanceCertificate;
	}

	public void setInheritanceCertificate(String inheritanceCertificate) {
		this.inheritanceCertificate = inheritanceCertificate;
	}
}