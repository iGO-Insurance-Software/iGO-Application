package Prototype;

import Employee.ComplianceFeedback;

import java.util.ArrayList;

public class Prototype {

	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<ComplianceFeedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(ArrayList<ComplianceFeedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<String> getRequirements() {
		return requirements;
	}

	public void setRequirements(ArrayList<String> requirements) {
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

	public ArrayList<String> getRisks() {
		return risks;
	}

	public void setRisks(ArrayList<String> risks) {
		this.risks = risks;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getDeveloperID() {
		return developerID;
	}

	public void setDeveloperID(int developerID) {
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

	private ArrayList<ComplianceFeedback> feedbacks;
	private int id;
	private ArrayList<String> requirements;
	private String status;
	private String name;
	private ArrayList<String> risks;
	private double price;
	private int developerID;
	private String detailedCategory;
	private String category;


	public Prototype(){

	}


	public boolean addFeedback(ComplianceFeedback feedback){
		return false;
	}

	public boolean launch(){
		return false;
	}

}