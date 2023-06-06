package Insurance;

import javax.management.monitor.StringMonitor;
import java.util.HashMap;

public class Insurance {
	protected String id;
	protected String description;
	protected String name;
	protected double price;
	private String detailedCategory;
	private int paymentTerm;

	public Insurance(){

	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public int getPaymentTerm() {
		return paymentTerm;
	}

	public void setPaymentTerm(int id) {
		this.paymentTerm = paymentTerm;
	}

	public HashMap<String,String> getInfo(){
		return null;
	}

	public double calculatePremium(double paymentRate){
		return 0;
	}

}