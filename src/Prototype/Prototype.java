package Prototype;
import java.util.ArrayList;
public class Prototype {
	private String description;
	private String feedbacks;
	private String id;
	private String requirements;
	private String status;
	private String name;
	private String risks;
	private String developerID;
	private String detailedCategory;
	private String category;
	private int paymentTerm;
	private String cancelReason;
	private double price;
	public Prototype(){
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFeedbacks() {
		return feedbacks;
	}
	public void setFeedbacks(String feedbacks) {
		this.feedbacks = feedbacks;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRequirements() {
		return requirements;
	}
	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRisks() {
		return risks;
	}
	public void setRisks(String risks) {
		this.risks = risks;
	}
	public String getDeveloperID() {
		return developerID;
	}
	public void setDeveloperID(String developerID) {
		this.developerID = developerID;
	}
	public String getDetailedCategory() {
		return detailedCategory;
	}
	public void setDetailedCategory(String detailedCategory) {
		this.detailedCategory = detailedCategory;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getPaymentTerm() {return paymentTerm;}
	public void setPaymentTerm(int paymentTerm) {this.paymentTerm = paymentTerm; }
	public double getPrice() {return price;}
	public void setPrice(double price) {this.price = price;	}
	public String getCancelReason() {return cancelReason;}
	public void setCancelReason(String cancelReason) {this.cancelReason = cancelReason;}
}