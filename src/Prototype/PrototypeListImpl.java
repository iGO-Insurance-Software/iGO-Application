package Prototype;

import Accident.Accident;

import java.util.ArrayList;

public class PrototypeListImpl implements PrototypeList {

	private ArrayList<Prototype> prototypeList;
	public Prototype m_Prototype;

	public PrototypeListImpl(){
		this.prototypeList = new ArrayList<Prototype>();
	}


	public boolean add(){
		return false;
	}

	public boolean delete(int prototypeID){
		return false;
	}

	public Prototype retrieve(int prototypeID){
		for(Prototype prototype : prototypeList){
			if(prototype.getId()==prototypeID){
				return prototype;
			}
		}
		return null;
	}

	public ArrayList<Prototype> retrieveAll() {
		return this.prototypeList;
	}

	public boolean update(int prototypeID){
		return false;
	}

}