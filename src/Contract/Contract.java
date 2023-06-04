package Contract;

import Customer.Customer;
import Customer.InsuredCustomer;
import Insurance.Insurance;

import java.util.Date;
import java.util.HashMap;

public class Contract {
	private int id;
	private int contractorID;
	private int insuranceID;
	private String insuredCustomerID;
	private String employeeID;
	private double fee;//수수료(약정 위반 시 등)
	private double premium;//보험료
	private double paymentRate;
	private int period;
	private Date signedDate;
	private Date expirationDate;
	private int paymentTerm;
	private double lossRatio;
	private String underwritingState;
	private String rejectionReasons;
	public Contract(){

	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getContractorID() {
		return contractorID;
	}

	public void setContractorID(int contractorID) {
		this.contractorID = contractorID;
	}

	public int getInsuranceID() {
		return insuranceID;
	}

	public void setInsuranceID(int insuranceID) {
		this.insuranceID = insuranceID;
	}

	public String getInsuredCustomerID() {
		return insuredCustomerID;
	}

	public void setInsuredCustomerID(String insuredCustomerID) {
		this.insuredCustomerID = insuredCustomerID;
	}

	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public double getPremium() {
		return premium;
	}

	public void setPremium(double premium) {
		this.premium = premium;
	}

	public double getPaymentRate() {
		return paymentRate;
	}

	public void setPaymentRate(double paymentRate) {
		this.paymentRate = paymentRate;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public Date getSignedDate() {
		return signedDate;
	}

	public void setSignedDate(Date signedDate) {
		this.signedDate = signedDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public int getPaymentTerm() {
		return paymentTerm;
	}

	public void setPaymentTerm(int paymentTerm) {
		this.paymentTerm = paymentTerm;
	}

	public double getLossRatio() {
		return lossRatio;
	}

	public void setLossRatio(double lossRatio) {
		this.lossRatio = lossRatio;
	}

	public String getUnderwritingState() {
		return underwritingState;
	}

	public void setUnderwritingState(String underwritingState) {
		this.underwritingState = underwritingState;
	}

	public String getRejectionReasons() {
		return rejectionReasons;
	}

	public void setRejectionReasons(String rejectionReasons) {
		this.rejectionReasons = rejectionReasons;
	}

	public int getNumberOfNonPayments() {
		return numberOfNonPayments;
	}

	public void setNumberOfNonPayments(int numberOfNonPayments) {
		this.numberOfNonPayments = numberOfNonPayments;
	}

	private int numberOfNonPayments;


	public HashMap<String, String> underwrite(Contract contract, Insurance insurance, InsuredCustomer insuredCustomer){
		HashMap<String, String> result = new HashMap<>();
		
		return result;
	}


	public HashMap<String, String> reexamine(Contract contract, Insurance insurance, InsuredCustomer insuredCustomer, String reUnderwriteReason){
		HashMap<String, String> result = new HashMap<>();

		return result;
	}

	public HashMap<String, String> calculateLossRatio(Insurance insurance, Customer customer){
		HashMap<String, String> result = new HashMap<>();
		double estimatedEarning, estimatedPayment;
		estimatedEarning = (this.period/this.paymentTerm) * this.premium;
		estimatedPayment = this.paymentRate * insurance.getPrice();
		this.lossRatio = estimatedPayment/estimatedEarning;
		result.put("estimatedEarning", Double.toString(estimatedEarning));
		result.put("estimatedPayment", Double.toString(estimatedPayment));
		result.put("lossRatio", Double.toString(this.lossRatio));
		result.put("isResult", "true");
		return result;
	}

	public double calculatePremium(int insuranceID, double paymentRate){
		return 0;
	}

}