package Contract;

import Customer.Customer;
import Insurance.Insurance;

import java.util.HashMap;

public class Reinsurance {

	private int id;
	//private HashMap<String, String> reinsuranceContractDetails;
	private int period;
	private double paymentAmount;
	private double contractRate;
	private double lossRatio;
	//private HashMap<String, String> reinsuranceCompanyManagerInfo;
	private String reinsuranceCompanyName;
	private String reinsuranceCompanyManagerName;
	private String reinsuranceCompanyManagerContract;
	private int contractID;
	private String contractResult;

	private String rejectionReasons;

	public double getContractRate() {
		return contractRate;
	}

	public void setContractRate(double contractRate) {
		this.contractRate = contractRate;
	}

	public double getLossRatio() {
		return lossRatio;
	}

	public void setLossRatio(double lossRatio) {
		this.lossRatio = lossRatio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getReinsuranceCompanyName() {
		return reinsuranceCompanyName;
	}

	public void setReinsuranceCompanyName(String reinsuranceCompanyName) {
		this.reinsuranceCompanyName = reinsuranceCompanyName;
	}

	public String getReinsuranceCompanyManagerName() {
		return reinsuranceCompanyManagerName;
	}

	public void setReinsuranceCompanyManagerName(String reinsuranceCompanyManagerName) {
		this.reinsuranceCompanyManagerName = reinsuranceCompanyManagerName;
	}

	public String getReinsuranceCompanyManagerContract() {
		return reinsuranceCompanyManagerContract;
	}

	public void setReinsuranceCompanyManagerContract(String reinsuranceCompanyManagerContract) {
		this.reinsuranceCompanyManagerContract = reinsuranceCompanyManagerContract;
	}

	public int getContractID() {
		return contractID;
	}

	public void setContractID(int contractID) {
		this.contractID = contractID;
	}

	public String getContractResult() {
		return contractResult;
	}

	public void setContractResult(String contractResult) {
		this.contractResult = contractResult;
	}

	public String getRejectionReasons() {
		return rejectionReasons;
	}

	public void setRejectionReasons(String rejectionReasons) {
		this.rejectionReasons = rejectionReasons;
	}

	public Reinsurance(){

	}
	public HashMap<String, String> calculateLossRatio(Contract contract, Insurance insurance){
		HashMap<String, String> result = new HashMap<>();
		double estimatedEarning, estimatedPayment;
		estimatedEarning = (contract.getPeriod()/contract.getPaymentTerm()) * contract.getPremium();
		estimatedPayment = (contract.getPaymentRate() * insurance.getPrice() * (1-this.contractRate)) + ((this.period) * this.paymentAmount);
		double ratio = estimatedPayment/estimatedEarning;
		this.lossRatio = Math.round(ratio * 100.0) / 100.0;
		result.put("estimatedEarning", Double.toString(estimatedEarning));
		result.put("estimatedPayment", Double.toString(estimatedPayment));
		result.put("lossRatio", (this.lossRatio*100)+"%");
		result.put("isResult", "true");
		return result;
	}
	public HashMap<String, String> contract(HashMap<String, String> contractDetails, String reinsuracneCompanyManagerContact, HashMap<String, String> employeeInfo){
		return null;
	}

}