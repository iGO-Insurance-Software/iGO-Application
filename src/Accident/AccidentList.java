package Accident;

import java.util.ArrayList;

public interface AccidentList {

	public boolean add();

	public boolean delete(int accident_ID);

	public Accident retrieve(int accident_ID);

	public boolean update(int accident_ID);

	public ArrayList<Accident> retireveAll();

}