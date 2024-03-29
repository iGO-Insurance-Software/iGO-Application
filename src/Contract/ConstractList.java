package Contract;

import java.util.ArrayList;

public interface ConstractList {

	public boolean add(Contract contract);

	public boolean delete(int contractID);

	public boolean update(int contractID);

	public Contract retrieve(int contractID);

	public ArrayList<Contract> retrieveAll();

}