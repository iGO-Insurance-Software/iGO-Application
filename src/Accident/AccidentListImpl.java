package Accident;

import Customer.Customer;

import java.util.ArrayList;

public class AccidentListImpl implements AccidentList {

	private ArrayList<Accident> accidentList;
	public Accident accident;

	public AccidentListImpl(){
		accidentList = new ArrayList<Accident>();
	}

	public Accident retrieve(int accidentID){
		for(Accident acdt : accidentList){
			if(acdt.getId()==accidentID){
				return acdt;
			}
		}
		return null;
	}

	public boolean add(Accident accident){
		accident.setId(accidentList.size()+1);
		accidentList.add(accident);
		return true;
	}

	public boolean update(int accidentID){
		return false;
	}

	public boolean delete(int accidentID){
		for(Accident acdt : accidentList){
			if(acdt.getId()==accidentID){
				accidentList.remove(acdt);
				return true;
			}
		}
		return false;
	}

	public ArrayList<Accident> retrieveAll(){
		return this.accidentList;
	}

}