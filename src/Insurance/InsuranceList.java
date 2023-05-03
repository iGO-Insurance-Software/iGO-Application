package Insurance;

import java.util.ArrayList;

public interface InsuranceList {

	public boolean add();

	public boolean delete(int insuranceID);


	public boolean update(int insuranceID);


	public Insurance retrieve(int insuranceID);

	public ArrayList<Insurance> retrieveAll();

}