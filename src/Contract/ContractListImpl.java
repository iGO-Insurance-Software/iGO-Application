package Contract;

import Accident.Accident;

import java.util.ArrayList;

public class ContractListImpl implements ConstractList {

	private ArrayList<Contract> contractList;
	public Contract contract;

	public ContractListImpl(){
		contractList = new ArrayList<Contract>();
	}


	public boolean add(Contract contract){
		return false;
	}


	public boolean delete(int contractID){
		return false;
	}


	public Contract retrieve(int contractID){
		for(Contract contract : contractList){
			if(contract.getId()==contractID){
				return contract;
			}
		}
		return null;
	}


	public boolean update(int contractID){
		return false;
	}


	public ArrayList<Contract> retrieveAll(){
		return this.contractList;
	}

}