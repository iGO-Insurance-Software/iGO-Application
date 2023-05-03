package Prototype;

import java.util.ArrayList;

public interface PrototypeList {

	public boolean add();

	public boolean delete(int prototypeID);

	public boolean retrieve(int prototypeID);

	public ArrayList<Prototype> retrieveAll();

	public boolean update(int prototypeID);

}