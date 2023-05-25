package Employee;

import java.io.BufferedReader;
import java.util.HashMap;

import static UI.Main.accidentList;
import static UI.Main.customerList;

public class InvestigationTeam {

	public InvestigationTeam(){

	}

	public boolean getIsDispatching() {
		return isDispatching;
	}

	public void setIsDispatching(boolean dispatching) {
		isDispatching = dispatching;
	}

	private boolean isDispatching;

	public boolean sendReceiption(HashMap<String,String> AccidentInfo, int accidentID){
		return false;
	}

	public boolean receiveUrgentReceiption(int accidentID) {
		System.out.println("\n******** 현장 조사팀 직원의 화면 ********");
		String customerID = accidentList.retrieve(accidentID).getCustomerID();
		System.out.println("(고객 id: " + customerID + ") " + customerList.retrieve(customerID).getName() + "님의 긴급 접수에 대한 출동 요청이 들어왔습니다.\n<사고 정보>");
		System.out.println("사고 일시: " + accidentList.retrieve(accidentID).getAccidentDate());
		System.out.println("사고 장소: " + accidentList.retrieve(accidentID).getAccidentPlace());
		System.out.println("접수자 이름: " + customerList.retrieve(customerID).getName());
		System.out.println("접수자 전화번호: " + customerList.retrieve(customerID).getPhoneNum());
		//System.out.println("접수자 가족 전화번호: "+customerList.retrieve(customerID).getFamillyNumber());
		//System.out.println("접수자 자택 주소: "+customerList.retrieve(customerID).getAddress());
		//System.out.println("1. 확인 및 출동");
		//System.out.print("Choice: ")
		//String userChoiceValue = inputReader.readLine().trim();
		//if(userChoiceValue == 1)
		System.out.println();
		customerList.retrieve(customerID).receiveMessage("긴급 접수가 승인 되었습니다. 현장 요원들이 고객님의 위치로 출동합니다.");
		this.isDispatching = true;
		//sendReceiption(해당사고정보받아서 미완성인 부분 완성);
		return true;
	}

	public boolean cancelDispatch(int accidentID){
		System.out.println("******** 현장 조사팀 직원의 화면 ********");
		System.out.println("사건번호 "+accidentID+"의 긴급 접수가 취소되었습니다.");
		this.isDispatching = false;
		return true;
	}
}