package Accident;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;


public class Accident {
	private int id;
	private String customerID;
	private String receptionEmployeeID;
	private String compensationEmployeeID;
	private Date accidentDate;
	private String accidentPlace;
	private String accidentType;
	private String accidentOutline;
	private boolean existOfDestroyer;
	private String destroyerName;
	private String destroyerPhoneNum;
	private boolean isUrgent;
	private String status;
	private double compensationMoney;
	private double indemnityMoney;
	private Date indemnityDueDate;
	private Boolean isWinLawsuit;
	private double lawsuitCost;
	private int winOrLoseMoney;
	public Accident(){

	}
	public Accident(HashMap<String,String> accidentInfo) {
		setCustomerID(accidentInfo.get("customerID"));
		setAccidentType(accidentInfo.get("accidentType"));
		setAccidentOutline(accidentInfo.get("accidentOutline"));
		setAccidentDateStringtoDate((accidentInfo.get("accidentDate")));
		setAccidentPlace(accidentInfo.get("accidentPlace"));

	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getReceptionEmployeeID() {
		return receptionEmployeeID;
	}

	public void setReceptionEmployeeID(String receiptionEmployeeID) {
		this.receptionEmployeeID = receiptionEmployeeID;
	}

	public String getCompensationEmployeeID() {
		return compensationEmployeeID;
	}

	public void setCompensationEmployeeID(String compensationEmployeeID) {
		this.compensationEmployeeID = compensationEmployeeID;
	}
	public Date getAccidentDate() {
		return accidentDate;
	}
	public void setAccidentDate(Date accidentDate) {
		this.accidentDate = accidentDate;
	}
	public String getAccidentDateToString(){
		if(this.accidentDate!=null) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			return formatter.format(this.accidentDate);
		}
		else return null;
	}
	public void setAccidentDateStringtoDate(String accidentDate){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			this.accidentDate = formatter.parse(accidentDate);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
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

	public String getAccidentOutline() {
		return accidentOutline;
	}

	public void setAccidentOutline(String accidentOutline) {
		this.accidentOutline = accidentOutline;
	}

	public boolean getExistOfDestroyer() {
		return existOfDestroyer;
	}

	public void setExistOfDestroyer(boolean existOfDestroyer) {
		this.existOfDestroyer = existOfDestroyer;
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

	public boolean getIsUrgent() {
		return isUrgent;
	}

	public void setIsUrgent(boolean urgent) {
		isUrgent = urgent;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getCompensationMoney() {
		return compensationMoney;
	}

	public void setCompensationMoney(double compensationMoney) {
		this.compensationMoney = compensationMoney;
	}

	public double getIndemnityMoney() {
		return indemnityMoney;
	}

	public void setIndemnityMoney(double indemnityMoney) {
		this.indemnityMoney = indemnityMoney;
	}

	public Date getIndemnityDueDate() {
		return indemnityDueDate;
	}
	public void setIndemnityDueDate(Date indemnityDueDate) {
		this.indemnityDueDate = indemnityDueDate;
	}
	public String getIndemnityDueDateToString(){
		if(this.indemnityDueDate!=null) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			return formatter.format(this.indemnityDueDate);
		}
		else return null;
	}
	public void setIndemnityDueDateStringToDate(String indemnityDueDate){
		if(!indemnityDueDate.equals("null")&&indemnityDueDate!=null){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			try {
				this.indemnityDueDate = formatter.parse(indemnityDueDate);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		}
		else return;
	}

	public Boolean getIsWinLawsuit() {
		return isWinLawsuit;
	}

	public void setIsWinLawsuit(Boolean winLawsuit) {
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
}