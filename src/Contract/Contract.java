package Contract;

import java.util.Date;

public class Contract {

	private int insuredCustomerID;

	public int getInsuredCustomerID() {
		return insuredCustomerID;
	}

	public void setInsuredCustomerID(int insuredCustomerID) {
		this.insuredCustomerID = insuredCustomerID;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getInsuranceID() {
		return insuranceID;
	}

	public void setInsuranceID(int insuranceID) {
		this.insuranceID = insuranceID;
	}

	public double getLossRatio() {
		return lossRatio;
	}

	public void setLossRatio(double lossRatio) {
		this.lossRatio = lossRatio;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public String getUnderwritingState() {
		return underwritingState;
	}

	public void setUnderwritingState(String underwritingState) {
		this.underwritingState = underwritingState;
	}

	public Date getSignedDate() {
		return signedDate;
	}

	public void setSignedDate(Date signedDate) {
		this.signedDate = signedDate;
	}

	public String getRejectionReasons() {
		return rejectionReasons;
	}

	public void setRejectionReasons(String rejectionReasons) {
		this.rejectionReasons = rejectionReasons;
	}

	public int getContractorID() {
		return contractorID;
	}

	public void setContractorID(int contractorID) {
		this.contractorID = contractorID;
	}

	public double getPremium() {
		return premium;
	}

	public void setPremium(double premium) {
		this.premium = premium;
	}

	public int getPaymentTerm() {
		return paymentTerm;
	}

	public void setPaymentTerm(int paymentTerm) {
		this.paymentTerm = paymentTerm;
	}

	public double getPaymentRate() {
		return paymentRate;
	}

	public void setPaymentRate(double paymentRate) {
		this.paymentRate = paymentRate;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public int getNumberOfNonPayments() {
		return numberOfNonPayments;
	}

	public void setNumberOfNonPayments(int numberOfNonPayments) {
		this.numberOfNonPayments = numberOfNonPayments;
	}

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