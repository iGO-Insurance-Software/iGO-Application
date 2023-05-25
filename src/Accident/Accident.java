package Accident;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;


public class Accident {

	private Date accidentDate;
	private String accidentOutline;
	private String accidentPlace;
	private String accidentType;
	private String customerID;
	private String destroyerName;
	private String destroyerPhoneNum;
	private boolean existOfDestroyer;
	private int id;
	private String contractID;
	private HashMap<String,String> relatedDocs;
	private double compensationMoney;
	private String status;
	private double indemnityMoney;
	private boolean isUrgent;
	private Date indemnityDueDate;
	private boolean isWinLawsuit;
	private double lawsuitCost;
	private int winOrLoseMoney;

	public Accident(HashMap<String,String> accidentInfo,boolean isUrgent, boolean existOfDestroyer) {
		this.customerID = accidentInfo.get("customerID");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		this.accidentType = accidentInfo.get("사고 유형");
		this.accidentOutline = accidentInfo.get("사고 개요");
		try {
			this.accidentDate = formatter.parse(accidentInfo.get("사고 일시"));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		this.accidentPlace = accidentInfo.get("사고 장소");
		this.isUrgent = isUrgent;
		this.existOfDestroyer = existOfDestroyer;
		if(existOfDestroyer){
			this.destroyerName = accidentInfo.get("손괴자 이름");
			this.destroyerPhoneNum = accidentInfo.get("손괴자 전화번호");
		}

	}


	public Date getAccidentDate() {
		return accidentDate;
	}

	public void setAccidentDate(Date accidentDate) {
		this.accidentDate = accidentDate;
	}

	public String getAccidentOutline() {
		return accidentOutline;
	}

	public void setAccidentOutline(String accidentOutline) {
		this.accidentOutline = accidentOutline;
	}

	public String getAccidentPlace() {
		return accidentPlace;
	}

	public void setAccidentPlace(String accidentPlace) {
		this.accidentPlace = accidentPlace;
	}

	public String getAccidentType() {
		return accidentType;
	}

	public void setAccidentType(String accidentType) {
		this.accidentType = accidentType;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getDestroyerName() {
		return destroyerName;
	}

	public void setDestroyerName(String destroyerName) {
		this.destroyerName = destroyerName;
	}

	public String getDestroyerPhoneNum() {
		return destroyerPhoneNum;
	}

	public void setDestroyerPhoneNum(String destroyerPhoneNum) {
		this.destroyerPhoneNum = destroyerPhoneNum;
	}

	public boolean isExistOfDestroyer() {
		return existOfDestroyer;
	}

	public void setExistOfDestroyer(boolean existOfDestroyer) {
		this.existOfDestroyer = existOfDestroyer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContractID() {
		return contractID;
	}

	public void setContractID(String contractID) {
		this.contractID = contractID;
	}

	public HashMap<String, String> getRelatedDocs() {
		return relatedDocs;
	}

	public void setRelatedDocs(HashMap<String, String> relatedDocs) {
		this.relatedDocs = relatedDocs;
	}

	public double getCompensationMoney() {
		return compensationMoney;
	}

	public void setCompensationMoney(double compensationMoney) {
		this.compensationMoney = compensationMoney;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getIndemnityMoney() {
		return indemnityMoney;
	}

	public void setIndemnityMoney(double indemnityMoney) {
		this.indemnityMoney = indemnityMoney;
	}

	public boolean isUrgent() {
		return isUrgent;
	}

	public void setUrgent(boolean urgent) {
		isUrgent = urgent;
	}

	public Date getIndemnityDueDate() {
		return indemnityDueDate;
	}

	public void setIndemnityDueDate(Date indemnityDueDate) {
		this.indemnityDueDate = indemnityDueDate;
	}

	public boolean isWinLawsuit() {
		return isWinLawsuit;
	}

	public void setWinLawsuit(boolean winLawsuit) {
		isWinLawsuit = winLawsuit;
	}

	public double getLawsuitCost() {
		return lawsuitCost;
	}

	public void setLawsuitCost(double lawsuitCost) {
		this.lawsuitCost = lawsuitCost;
	}

	public int getWinOrLoseMoney() {
		return winOrLoseMoney;
	}

	public void setWinOrLoseMoney(int winOrLoseMoney) {
		this.winOrLoseMoney = winOrLoseMoney;
	}


	public Accident(){

	}

	public void finalizeAccident() {

	}

	public HashMap<String,String> getInfo(){
		return null;
	}


	public boolean updateStatus(String status){
		return false;
	}

}