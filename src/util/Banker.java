package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Banker {
    BufferedReader inputReader;
    public Banker(String bankClerkContact) {
        this.inputReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public HashMap<String, String> requestInfo(HashMap<String, String> basicCustomerInfo) throws BaseException{
        int responseTime = (int)Math.random()*10+1;
        if(responseTime == 10) throw new BaseException("현재 고객 정보 요청에 대한 응답이 없어 재요청 하였습니다.");
        System.out.println("____________은행원____________\n요청한 고객 정보" +
                "\n이름: "+basicCustomerInfo.get("name")+"\n주민등록번호: "+basicCustomerInfo.get("ssn")+"\n요청 사유: "+basicCustomerInfo.get("requestReason")+
                "\n1. 요청 수락, 2. 요청 거절");
        System.out.print("\nChoice: ");
        String bankerChoiceValue=null;
        HashMap<String, String> responseInfo = null;
        try {bankerChoiceValue = inputReader.readLine().trim();
            switch (bankerChoiceValue){
                case "1":
                    responseInfo = inputCustomerInfo(basicCustomerInfo);
                    break;
                case "2":
                    responseInfo = inputRejectReason();
                    break;
            }
        }catch (IOException e) {throw new RuntimeException(e);}
        return responseInfo;
    }
    private HashMap<String, String> inputCustomerInfo(HashMap<String, String> basicCustomerInfo) throws IOException {
        HashMap<String, String> responseInfo = new HashMap<>();
        System.out.println("1. 고객 정보 입력하기, 2. 고객 정보가 존재하지 않음");
        System.out.print("\nChoice: ");
        String bankerChoiceValue = inputReader.readLine().trim();
        if(bankerChoiceValue.equals("2")) return inputRejectReason();
        else{
            System.out.print("이름: "); responseInfo.put("name", inputReader.readLine().trim());
            System.out.print("\n나이: "); responseInfo.put("age", inputReader.readLine().trim());
            System.out.print("\n주소: "); responseInfo.put("address", inputReader.readLine().trim());
            System.out.print("\n재산: "); responseInfo.put("property", inputReader.readLine().trim());
            System.out.print("\n신용점수: "); responseInfo.put("creditScore", inputReader.readLine().trim());
            System.out.print("\n조회내역: "); responseInfo.put("viewHistory", inputReader.readLine().trim());
            System.out.print("\n변동내역: "); responseInfo.put("changeHistory", inputReader.readLine().trim());
            System.out.print("\n월 카드 사용금액: "); responseInfo.put("monthlyCardUsageAmount", inputReader.readLine().trim());
            System.out.print("\n대출내역: "); responseInfo.put("loanDetails", inputReader.readLine().trim());
            System.out.print("\n연체내역: "); responseInfo.put("overdueDetails", inputReader.readLine().trim());
            System.out.print("\n연대보증 내역: "); responseInfo.put("jointWarrantyDetails", inputReader.readLine().trim());
            System.out.println("1. 전송하기"); System.out.print("\nChoice: "); inputReader.readLine().trim();
            // 고객 정보를 요청하다 E2 test code/////////////////////////////
            int responseTime = (int)Math.random()*10+1;
            if(responseTime == 10) {
                System.out.println("고객 정보 전송에 실패하였습니다. 잠시 후 다시 시도해주세요.");
                System.out.println("1. 전송하기"); System.out.print("\nChoice: "); inputReader.readLine().trim();
            }
            /////////////////////////////////////////////////////////////
            return responseInfo;
        }
    }
    private HashMap<String, String> inputRejectReason() throws IOException {
        HashMap<String, String> responseInfo = new HashMap<>();
        System.out.print("거절 사유: "); responseInfo.put("rejectReason", inputReader.readLine().trim());
        System.out.println("1. 전송하기"); System.out.print("\nChoice: "); inputReader.readLine().trim();
        return responseInfo;
    }


}
