package Accident;

import java.util.ArrayList;

public interface AccidentList {

	public boolean add(Accident accident);

	public boolean delete(int accidentID);

	public Accident retrieve(int accidentID);

	public boolean update();

	public ArrayList<Accident> retrieveAll();

}