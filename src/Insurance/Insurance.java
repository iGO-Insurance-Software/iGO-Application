package Insurance;

import java.util.HashMap;

public class Insurance {

	protected String description;
	protected int id;

	protected String name;

	protected double price;
	private String detailedCategory;

	public Insurance(){

	}

	public HashMap<String,String> getInfo(){
		return null;
	}

	public double calculatePremium(double paymentRate){
		return 0;
	}

}