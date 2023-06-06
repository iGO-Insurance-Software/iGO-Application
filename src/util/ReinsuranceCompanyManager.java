package util;

import Contract.Contract;
import Contract.Reinsurance;
import Customer.InsuredCustomer;
import Insurance.Insurance;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class ReinsuranceCompanyManager {
    BufferedReader inputReader;
    public ReinsuranceCompanyManager(String reinsuranceCompanyManagerContract) {
        this.inputReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public HashMap<String, String> requestRegisterReinsurance(Reinsurance reinsurance, Contract contract, Insurance insurance, InsuredCustomer insuredCustomer)  throws BaseException{
        int responseTime = (int)Math.random()*10+1;
        if(responseTime == 10) throw new BaseException(ErrorCode.NO_RESPONSE_TO_CURRENT_CUSTOMER_INFO_REQ);
        System.out.println("\n____________재보험 회사 담당자____________");
        System.out.println("----재보험 계약 내용----");
        System.out.println("--재보험 대상 계약 내용--");
        System.out.println("   -고객 이름: " + insuredCustomer.getName());
        System.out.println("   -고객 주민등록번호: " + insuredCustomer.getRrn());
        System.out.println("   -보험 이름: " + insurance.getName());
        System.out.println("   -지급 금액: " + insurance.getPrice());
        System.out.println("   -계약 금액: " + contract.getPremium());
        System.out.println("   -보험료 납부 주기: " + contract.getPeriod());
        System.out.println("-재보험 계약 기간: " + reinsurance.getPeriod());
        System.out.println("-재보험 계약금: " + reinsurance.getPaymentAmount());
        System.out.println("-재보험 계약 비율: " + reinsurance.getContractRate());
        System.out.println("\n1.승인 2.거절");
        System.out.print("\nChoice: ");
        HashMap<String, String> result = new HashMap<>();
        try {
            String managerChoiceValue=inputReader.readLine().trim();
            if(managerChoiceValue.equals("1")) result.put("isResult", "true");
            else if(managerChoiceValue.equals("2")){
                result.put("isResult", "false");
                System.out.print("거절 사유: "); result.put("rejectReason", inputReader.readLine().trim());
                System.out.println("\n1. 전송하기"); System.out.print("\nChoice: "); inputReader.readLine().trim();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
