package Contract;

import java.util.Date;

public class Contract {

	private int insuredCustomerID;
	private int employeeID;
	private Date expirationDate;
	private int id;
	private int insuranceID;
	private double lossRatio;
	private int period;
	private String underwritingState;
	private Date signedDate;
	private String rejectionReasons;
	private int contractorID;
	private double premium;
	private int paymentTerm;
	private double paymentRate;
	private double fee;
	private int numberOfNonPayments;

	public Contract(){

	}

	public boolean underwrite(int customerID, int insuranceID, int employeeID, String ffsContact){
		return false;
	}


	public boolean reexamine(String underwritingState, int employeeID, int insuranceID, int customerID, String ffsContact){
		return false;
	}

	public boolean calculateLossRatio(int insuranceID, int customerID){
		return false;
	}

	public double calculatePremium(int insuranceID, double paymentRate){
		return 0;
	}

}