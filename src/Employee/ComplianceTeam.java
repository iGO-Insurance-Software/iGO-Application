package Employee;

import Prototype.Prototype;

public class ComplianceTeam extends Employee {

	private ComplianceFeedback complianceFeedback;
	private boolean complianceStatus;

	public ComplianceTeam(){
		 complianceFeedback = new ComplianceFeedback();
	}

	public boolean ensureCompliance(){
		return false;
	}

	public ComplianceFeedback reviewPrototype(Prototype prototype){
		return null;
	}

}