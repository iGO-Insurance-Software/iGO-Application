package Contract;

import Customer.Customer;
import Customer.InsuredCustomer;
import Insurance.Insurance;
import util.FFS;
import java.util.HashMap;

public class Contract {
	private int id;
	private String contractorID;
	private String insuranceID;
	private String insuredCustomerID;
	private String employeeID;
	private double fee;//수수료(약정 위반 시 등)
	private double premium;//보험료
	private double paymentRate;
	private int period;
	private String signedDate;
	private String expirationDate;
	private int paymentTerm;
	private int numberOfNonPayments;
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

	public String getContractorID() {
		return contractorID;
	}

	public void setContractorID(String contractorID) {
		this.contractorID = contractorID;
	}

	public String getInsuranceID() {
		return insuranceID;
	}

	public void setInsuranceID(String insuranceID) {
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

	public String getSignedDate() {
		return signedDate;
	}

	public void setSignedDate(String signedDate) {
		this.signedDate = signedDate;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
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


	public HashMap<String, String> underwrite(String ffsContact, Contract contract, Insurance insurance, InsuredCustomer insuredCustomer){
		FFS ffs = new FFS(ffsContact);
		HashMap<String, String> result = ffs.requestUW(contract, insurance, insuredCustomer);
		if(result.get("isResult").equals("true")) this.setUnderwritingState("승인");
		if(result.get("isResult").equals("false")) {
			this.setUnderwritingState("재심사 가능");
			this.setRejectionReasons("금융감독원 거절: "+result.get("rejectReason"));
		}
		return result;
	}


	public HashMap<String, String> reexamine(String ffsContact, Contract contract, Insurance insurance, InsuredCustomer insuredCustomer, String reUnderwriteReason){
		FFS ffs = new FFS(ffsContact);
		HashMap<String, String> result = ffs.requestReUW(contract, insurance, insuredCustomer, reUnderwriteReason);
		if(result.get("isResult").equals("true")) this.setUnderwritingState("승인");
		if(result.get("isResult").equals("false")) {
			this.setUnderwritingState("재심사 거절");
			this.setRejectionReasons("금융감독원 거절: "+result.get("rejectReason"));
		}
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