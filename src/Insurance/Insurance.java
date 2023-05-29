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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDetailedCategory() {
		return detailedCategory;
	}

	public void setDetailedCategory(String detailedCategory) {
		this.detailedCategory = detailedCategory;
	}

	public HashMap<String,String> getInfo(){
		return null;
	}

	public double calculatePremium(double paymentRate){
		return 0;
	}

}