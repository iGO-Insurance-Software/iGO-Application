package Employee;

import java.util.ArrayList;
import java.util.HashMap;

public class AccidentReceiptionTeam extends Employee {

	private InvestigationTeam investigationTeam;
	private ArrayList<Integer> accidentsInCharge;
	public InvestigationTeam m_InvestigationTeam;

	public AccidentReceiptionTeam(){
		this.investigationTeam = new InvestigationTeam();
	}

	public boolean refuseReceiption(int accidentID){
		return false;
	}


	public boolean receiveReceiption(HashMap<String,String> accidentInfomations, int customerID){
		return false;
	}


	public void acceptReceiption(int accidentID){

	}

	public boolean cancelUrgentReceiption(int accidentID){
		return false;
	}


	public void dispatchInvestigation(int accidentID){

	}

}