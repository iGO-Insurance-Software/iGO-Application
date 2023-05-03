package Employee;

public class ProductDevelopmentTeam extends Employee {

	public ProductDevelopmentTeam(){

	}
	public boolean createPrototype(){
		return false;
	}

	public boolean createRiskReport(int prototypeID, String risk){
		return false;
	}

	public boolean lunchProduct(int prototypeID){
		return true;
	}

	public boolean updatePrototypeStatus(int prototypeID, String status){
		return true;
	}

	public boolean updatePrototype(){
		return false;
	}

	public boolean defineProduct(){
		return false;
	}

}