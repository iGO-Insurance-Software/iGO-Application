package Accident;

import Customer.Customer;
import Employee.Employee;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

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

	public boolean update(){
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