package util;

import Contract.Contract;
import Customer.InsuredCustomer;
import Insurance.Insurance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class FFS {
    BufferedReader inputReader;
    public FFS(String ffsContact) {
        this.inputReader = new BufferedReader(new InputStreamReader(System.in));
    }


    public HashMap<String, String> requestUW(Contract contract, Insurance insurance, InsuredCustomer insuredCustomer) {
        try {
            String userChoiceValue=printContractInfo(contract, insurance, insuredCustomer, null);
            HashMap<String, String> uwResult = new HashMap<>();
            if(userChoiceValue.equals("1")){
                uwResult.put("isResult", "true");
            }
            else if(userChoiceValue.equals("2")){
                uwResult.put("isResult", "false");
                System.out.print("거절 사유: "); uwResult.put("rejectReason", inputReader.readLine().trim());
                System.out.println("1. 전송하기"); System.out.print("\nChoice: "); inputReader.readLine().trim();
            }
            return uwResult;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public HashMap<String, String> requestReUW(Contract contract, Insurance insurance, InsuredCustomer insuredCustomer, String reUnderwriteReason) {
        try {
            String userChoiceValue=printContractInfo(contract, insurance, insuredCustomer, reUnderwriteReason);
            HashMap<String, String> reUWResult = new HashMap<>();
            if(userChoiceValue.equals("1")){
                reUWResult.put("isResult", "true");
            }
            else if(userChoiceValue.equals("2")){
                reUWResult.put("isResult", "false");
                System.out.print("거절 사유: "); reUWResult.put("rejectReason", inputReader.readLine().trim());
                System.out.println("1. 전송하기"); System.out.print("\nChoice: "); inputReader.readLine().trim();
            }
            return reUWResult;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private String printContractInfo(Contract contract, Insurance insurance, InsuredCustomer insuredCustomer, String reUnderwriteReason){
        System.out.println("____________금융감독원____________");
        System.out.println("-피보험자 정보");
        System.out.println("이름: " + insuredCustomer.getName() +
                "\n주민등록번호: " + insuredCustomer.getRrn());
        System.out.println("-보험 정보");
        System.out.println("이름: " + insurance.getName() +
                "\n지급 금액: " + insurance.getPrice() +
                "\n보험 설명: " + insurance.getDescription());
        System.out.println("-계약 내용");
        System.out.println("계약 금액: " + contract.getPremium() +
                "\n보험료 납부 주기: " + contract.getPaymentTerm() +
                "\n요율: " + contract.getPaymentRate() +
                "\n계약 기간: " + contract.getPeriod() +
                "\n조건부 추가 수수료: " + contract.getFee());
        if(reUnderwriteReason != null){
            System.out.println("재심사 사유: "+reUnderwriteReason);
        }
        System.out.println("1.승인  2.거절");
        System.out.print("Choice: ");
        try {
            String userChoiceValue = inputReader.readLine().trim();
            return userChoiceValue;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
