package Employee;

public class InvestigationTeam extends Employee {
	private Integer accidentID;
	private boolean isDispatching;
	public InvestigationTeam(){
	}

	public Integer getAccidentID() {
		if(this.accidentID==null||this.accidentID==0) return null;
		else return accidentID;
	}

	public void setAccidentID(Integer accidentID) {
		if(accidentID==null||accidentID==0) this.accidentID = null;
		else this.accidentID = accidentID;
	}

	public boolean getIsDispatching() {
		return isDispatching;
	}

	public void setIsDispatching(boolean dispatching) {
		isDispatching = dispatching;
	}


}