package Contract;

import java.util.ArrayList;

public class ContractListImpl implements ConstractList {

	private ArrayList<Contract> contractList;
	public Contract m_Contract;

	public ContractListImpl(){

	}


	public boolean add(){
		return false;
	}


	public boolean delete(int contractID){
		return false;
	}


	public Contract retrieve(int contractID){
		return null;
	}


	public boolean update(int contractID){
		return false;
	}


	public ArrayList<Contract> retrieveAll(){
		return null;
	}

}