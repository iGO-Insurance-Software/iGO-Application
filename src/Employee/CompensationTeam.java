package Employee;

import java.util.ArrayList;
import java.util.HashMap;

public class CompensationTeam extends Employee {
	private double accountBalances;

	private String bankAccount;

	public CompensationTeam(){

	}
	public double getAccountBalances() {
		return accountBalances;
	}

	public void setAccountBalances(double accountBalances) {
		this.accountBalances = accountBalances;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public HashMap<String,String> requestDocs(int accidentID){
		return null;
	}

	public boolean decideCompensation(int accdentID){
		return false;
	}

	public double calCompensationMoney(int accidentID){
		return 0;
	}

	public boolean payCompensation(int accidentID){
		return false;
	}

	public boolean demandIndemnity(int accidentID){
		return false;
	}

	public boolean finalizeAccident(int accidentID){
		return false;
	}

	public boolean requestLawsuit(int accidentID){
		return false;
	}

	public boolean addFund(double fund){
		return false;
	}

	public boolean requestFund(){
		return false;
	}

	public HashMap<String,String> searchCustomerInfo(int accidentID){
		return null;
	}

	public HashMap<String,String> searchAccidentInfo(int accidentID){
		return null;
	}

	public HashMap<String,String> searchInsuranceInfo(int accidentID){
		return null;
	}

}