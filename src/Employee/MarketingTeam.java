package Employee;

import java.util.HashMap;

public class MarketingTeam extends Employee {
	private String adName;
	private String adDescription;

	public MarketingTeam(){
	}
	public String getAdName() {
		return adName;
	}
	public void setAdName(String adName) {
		this.adName = adName;
	}
	public String getAdDescription() {
		return adDescription;
	}
	public void setAdDescription(String adDescription) { this.adDescription = adDescription; }

	public boolean registAd(int adID, String description){
		return false;
	}

}