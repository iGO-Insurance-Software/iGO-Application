package Employee;

import java.util.ArrayList;

public class LegalTeam extends Employee {

	private ArrayList<Integer> accidentsInCharge;

	public LegalTeam(){

	}

	public boolean acceptLawsuitRequest(int accidentID){
		return false;
	}

	public boolean refuseLawsuitRequest(int accidentID){
		return false;
	}

	public boolean deferLawsuitRequest(int accidentID){
		return false;
	}

	public boolean sendLawsuitResult(int accidentID){
		return false;
	}

	public boolean resumeLawsuitResult(int accidentID){
		return false;
	}

}