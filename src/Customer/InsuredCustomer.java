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
	private String accidentCertificate;
	private String judgmentCertificate;
	private String medicalCertificate;
	private String bankbookCopy;
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

	public String getAccidentCertificate() {
		return accidentCertificate;
	}

	public void setAccidentCertificate(String accidentCertificate) {
		this.accidentCertificate = accidentCertificate;
	}

	public String getJudgmentCertificate() {
		return judgmentCertificate;
	}

	public void setJudgmentCertificate(String judgmentCertificate) {
		this.judgmentCertificate = judgmentCertificate;
	}

	public String getMedicalCertificate() {
		return medicalCertificate;
	}

	public void setMedicalCertificate(String medicalCertificate) {
		this.medicalCertificate = medicalCertificate;
	}

	public String getBankbookCopy() {
		return bankbookCopy;
	}

	public void setBankbookCopy(String bankbookCopy) {
		this.bankbookCopy = bankbookCopy;
	}

}