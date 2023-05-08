package Employee;

import Accident.Accident;
import Customer.CustomerList;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static UI.Main.*;

public class AccidentReceiptionTeam extends Employee {

	private InvestigationTeam investigationTeam;
	private ArrayList<Integer> accidentsInCharge;


	public AccidentReceiptionTeam(){
		this.investigationTeam = new InvestigationTeam();
		accidentsInCharge = new ArrayList<Integer>();
	}

	public boolean receiveReceiption(HashMap<String,String> accidentInfomations, int customerID, BufferedReader inputReader) throws IOException {
		boolean isUrgent = false;
		boolean existOfDestroyer = false;
		System.out.println("******** "+this.name+"직원의 화면 ********");
		switch(accidentInfomations.get("긴급 여부")){
			case "일반" :
				System.out.println("(고객 id: "+customerID+") "+customerList.retrieve(customerID).getName()+"님의 사고가 접수되었습니다.\n<사고 정보>");
				System.out.println("사고 일시: "+accidentInfomations.get("사고 일시"));
				System.out.println("사고 장소: "+accidentInfomations.get("사고 장소"));
				System.out.println("사고 유형: "+accidentInfomations.get("사고 유형"));
				System.out.println("사고 개요: "+accidentInfomations.get("사고 유형"));
				if(accidentInfomations.get("손괴자 이름")!=null&&accidentInfomations.get("손괴자 이름").equals("")==false){
					existOfDestroyer = true;
					System.out.println("손괴자 이름: "+accidentInfomations.get("손괴자 이름"));
					System.out.println("손괴자 전화번호: "+accidentInfomations.get("손괴자 전화번호"));
				}
				//Generate Accident
				Accident acdt = new Accident(accidentInfomations,isUrgent,existOfDestroyer);
				accidentList.add(acdt);

				System.out.println("1. 확인(접수 승인)");
				System.out.println("2. 거절");
				System.out.print("Choice: ");
				String userChoiceValue = inputReader.readLine().trim();
				System.out.println();
				if(userChoiceValue.equals("1")) acceptReceiption(acdt.getId());
				else if(userChoiceValue.equals("2")) {
					System.out.print("거절 사유를 입력하세요: ");
					String refusalReason = inputReader.readLine();
					refuseReceiption(acdt.getId());
					customer.receiveMessage("고객님의 사고 접수 요청이 거절되었습니다. 거절 사유: "+refusalReason);
				}
				break;
			case "긴급" :
				break;
		}

		return true;
	}


	public boolean acceptReceiption(int accidentID){
		Accident accident = accidentList.retrieve(accidentID);
		customer.receiveMessage("사고 접수가 완료되었습니다. 접수 번호: "+accident.getId()+
				", 담당자 이름: "+this.getName()+", 담당자 연락처: "+this.getPhoneNum()+" / "+this.getEmail());
		accident.setStatus("접수 완료");
		return true;
	}

	public boolean refuseReceiption(int accidentID){
		Accident accident = accidentList.retrieve(accidentID);
		accident.setStatus("접수 거절");
		return true;
	}

	public boolean cancelUrgentReceiption(int accidentID){
		return false;
	}


	public void dispatchInvestigation(int accidentID){

	}

}