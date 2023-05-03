package Accident;

import java.util.Date;
import java.util.HashMap;


public class Accident {

	private Date accidentDate;
	private String accidentOutline;
	private String accidentPlace;
	private String accidentType;
	private int customerID;
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