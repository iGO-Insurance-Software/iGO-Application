package Employee;

import Accident.Accident;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static UI.Main.*;

public class AccidentReceiptionTeam extends Employee {
	private InvestigationTeam investigationTeam;
	public AccidentReceiptionTeam(){
		this.investigationTeam = new InvestigationTeam();
	}
	public InvestigationTeam getInvestigationTeam() {
		return investigationTeam;
	}

	public boolean receiveReceiption(HashMap<String,String> accidentInfomations, String customerID, BufferedReader inputReader) throws IOException {
		return false;
	}


	public boolean acceptReceiption(int accidentID){
		Accident accident = accidentList.retrieve(accidentID);
		if(accident.getIsUrgent()){
			accident.setStatus("접수 완료(긴급)");
			dispatchInvestigation(accidentID);
		}
		else{//긴급접수를 수락했을 경우
			customerList.retrieve(accident.getCustomerID()).receiveMessage("접수가 완료되었습니다. 접수 번호: "+accident.getId()+
					", 담당자 이름: "+this.getName()+", 담당자 연락처: "+this.getPhoneNum()+" / "+this.getEmail());
			accident.setStatus("접수 완료");
		}

		return true;
	}

	public boolean refuseReceiption(int accidentID){
		Accident accident = accidentList.retrieve(accidentID);
		accident.setStatus("접수 거절");
		return true;
	}

	public boolean cancelUrgentReceiption(int accidentID){
		this.investigationTeam.cancelDispatch(accidentID);
		return true;
	}


	public boolean dispatchInvestigation(int accidentID){
		investigationTeam.receiveUrgentReceiption(accidentID);
		return true;
	}

}