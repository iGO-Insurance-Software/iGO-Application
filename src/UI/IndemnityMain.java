package UI;

import Accident.Accident;
import Customer.InsuredCustomer;
import util.BaseException;
import util.ErrorCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import static UI.Main.*;

public class IndemnityMain {
    public static boolean showAccidentsForIndemnity(BufferedReader inputReader) throws IOException {
        ArrayList<Accident> accidentList = new ArrayList<Accident>();
        for (Accident accident : accidentDao.retrieveAll()){
            if(accident.getStatus().equals("보상금 지급완료")||(accident.getStatus().equals("구상 처리중"))||accident.getStatus().equals("구상 완료")){
                if(accident.getExistOfDestroyer()&&accident.getCompensationEmployeeID().equals(currentEmployee.getId())) {
                    accidentList.add(accident);
                }
            }
        }
        //반환된 사건들 목록으로 출력
        if (accidentList.size() != 0) {
            for (int i = 0; i < accidentList.size(); i++) {
                Accident accident = accidentList.get(i);
                if(accident.getStatus().equals("보상금 지급완료")) System.out.println("[미구상] "+(i + 1) + ". 사고번호: " + accident.getId());
                else if(accident.getStatus().equals("구상 처리중")) System.out.println("[구상 처리중] "+(i + 1) + ". 사고번호: " + accident.getId());
                else if(accident.getStatus().equals("구상 완료")) System.out.println("[구상 완료] "+(i + 1) + ". 사고번호: " + accident.getId());
            }
            System.out.println("몇번째 사고를 조회하시겠습니까?");
            System.out.print("Choice: ");
            String userChoiceValue = inputReader.readLine().trim();
            int choice = Integer.parseInt(userChoiceValue);
            Accident choicedAccident = accidentList.get(choice - 1);
            showAccidentInfo(choicedAccident);
            showOptionsForIndemnity(choicedAccident, inputReader);
        }
        else {
            System.out.println("처리중인 사고가 없습니다.");
        }
        return true;
    }
    public static void showAccidentInfo(Accident choicedAccident){
        System.out.println("사고 번호: "+choicedAccident.getId());
        System.out.println("사고 일시: "+choicedAccident.getAccidentDateToString());
        System.out.println("사고 장소: "+choicedAccident.getAccidentPlace());
        System.out.println("사고 유형:"+choicedAccident.getAccidentType());
        System.out.println("사고 개요: "+choicedAccident.getAccidentOutline());
        InsuredCustomer insuredCustomer = insuredCustomerDao.retrieveById(choicedAccident.getCustomerID());
        System.out.println("피보험자 이름: "+insuredCustomer.getName());
        System.out.println("피보험자 전화번호: "+insuredCustomer.getPhoneNum());
        String medicalBill = choicedAccident.getMedicalBill();
        if(medicalBill.equals("null")){
            medicalBill="없음";
        }
        System.out.println("피보험자 진료비 계산서: "+medicalBill);
        String damageBill = choicedAccident.getDamageBill();
        if(damageBill.equals("null")){
            damageBill="없음";
        }
        System.out.println("피보험자 피해 계산서: "+damageBill);
        if (choicedAccident.getExistOfDestroyer()) {
            System.out.println("손괴자 이름: " + choicedAccident.getDestroyerName());
            System.out.println("손괴자 전화번호: " + choicedAccident.getDestroyerPhoneNum());
        }
        System.out.println("보상 책정금: "+choicedAccident.getCompensationMoney()+"₩");
        System.out.println("처리 상태: "+choicedAccident.getStatus());
        if(choicedAccident.getStatus().equals("구상 처리중")||choicedAccident.getStatus().equals("구상 완료")) {
            System.out.println("구상금: "+choicedAccident.getIndemnityMoney()+"₩");
            System.out.println("구상금 납부기한: "+choicedAccident.getIndemnityDueDateToString());
        }
    }
    public static boolean showOptionsForIndemnity(Accident choicedAccident, BufferedReader inputReader) throws IOException{
        int option = 0;
        System.out.println("\nx. 나가기");
        if(choicedAccident.getStatus().equals("보상금 지급완료")){
            System.out.println("1. 구상금 청구서 발송");
            option = 1;
        }
        else if(choicedAccident.getStatus().equals("구상 처리중")){
            System.out.println("1. 구상금 납부기한 연장");
            System.out.println("2. 구상 완료 처리");
            option = 2;
        }
        System.out.print("Choice: ");
        String userChoiceValue = inputReader.readLine().trim();
        if(option==1){
            switch(userChoiceValue){
                case "1":
                    System.out.println("<구상금 청구서 발송>");
                    String accidentDate = "\n사고 일시: "+choicedAccident.getAccidentDate();
                    System.out.print(accidentDate);
                    String accidentPlace = "\n사고 장소: "+choicedAccident.getAccidentPlace();
                    System.out.print(accidentPlace);
                    String accidentOutline = "\n사고 개요: "+choicedAccident.getAccidentOutline();
                    System.out.print(accidentOutline);
                    InsuredCustomer insuredCustomer = insuredCustomerDao.retrieveById(choicedAccident.getCustomerID());
                    String insuredCustomerNameMessage = "\n피해자 이름: "+insuredCustomer.getName();
                    System.out.print(insuredCustomerNameMessage);
                    String insuredCustomerPhoneNum = "\n피해자 전화번호: "+insuredCustomer.getPhoneNum();
                    System.out.print(insuredCustomerPhoneNum);
                    System.out.print("\n청구 금액: ");
                    int indemnityMoney = Integer.parseInt(inputReader.readLine().trim());
                    choicedAccident.setIndemnityMoney(indemnityMoney);
                    String indemnityMoneyMessage = "\n청구 금액: "+indemnityMoney;
                    System.out.print("명세: ");
                    String indemnityOutline = "\n명세: "+inputReader.readLine();
                    String bankAccountForPay = "\n납부 계좌: 1442321312443 신협 / 수취인: (주) 아이고";
                    System.out.println(bankAccountForPay);
                    boolean isInCorrect = true;
                    String indemnityDueDateStr = "";
                    while(isInCorrect) {
                        System.out.print("납부 기한(예: 2023-08-01): ");
                        indemnityDueDateStr = inputReader.readLine().trim();
                        if(checkValidDateType(indemnityDueDateStr)&&checkValidDateTerm(indemnityDueDateStr,choicedAccident.getAccidentDateToString()))
                            isInCorrect = false;
                    }
                    choicedAccident.setIndemnityDueDateStringToDate(indemnityDueDateStr);
                    String indemnityDueDateMessage = "\n납부기한: "+indemnityDueDateStr;
                    String message = "iGO 보험사입니다. 저희 쪽 고객님께서 피해를 입으신 사건에 대한 구상금 청구서를 아래와 같이 청구합니다."
                            +accidentDate+accidentPlace+accidentPlace+accidentOutline+insuredCustomerNameMessage+insuredCustomerPhoneNum+
                            indemnityMoneyMessage+indemnityOutline+bankAccountForPay+indemnityDueDateMessage;
                    //손괴자에게 SMS 전송
                    sendMessage(choicedAccident.getDestroyerPhoneNum(),message);
                    //해당 사건의 상태를 구상 처리중으로 업데이트, 구상금 세팅
                    updateAccidentToIndemnity(choicedAccident);
                    break;
                case "x":
                    break;
            }
        }
        else if(option==2){
            switch(userChoiceValue){
                case "1":
                    boolean isInCorrect = true;
                    String indemnityDueDateStr = "";
                    while(isInCorrect) {
                        System.out.println("<납부기한 연장>");
                        System.out.print("납부 기한(예: 2023-08-01): ");
                         indemnityDueDateStr = inputReader.readLine().trim();
                        if(checkValidDateType(indemnityDueDateStr)&&checkValidDateTerm(indemnityDueDateStr,choicedAccident.getAccidentDateToString()))
                            isInCorrect = false;
                    }
                    sendMessage(choicedAccident.getDestroyerPhoneNum(),"iGO 보험사입니다. 이전에 청구했던 피해 보상금 납부기한이 "
                            +choicedAccident.getIndemnityDueDateToString()+"에서 "+indemnityDueDateStr+"로 연장되었습니다.");
                    //Dao호출 - 납부기한 업데이트
                    extendIndemnityPeriod(choicedAccident,indemnityDueDateStr);
                    break;
                case "2":
                    System.out.println("해당 사건의 구상금 "+choicedAccident.getIndemnityMoney()+"를 입금받았습니까?\n1.예\nothers.아니오");
                    userChoiceValue = inputReader.readLine().trim();
                    if(userChoiceValue.equals("1")) {
                        finalizeIndemnity(choicedAccident);
                        System.out.println("구상 절차가 완료되었습니다.");
                    }
                    else System.out.println("먼저 구상금을 받고 구상절차를 완료해주세요.");
                    break;
                case "x":
                    break;
            }
        }
        return true;
    }
    public static boolean checkValidDateType(String date){
        boolean isValid = false;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date indemnityDueDate = formatter.parse(date);
            isValid = true;
        } catch (ParseException e) {
            String errorMessage = "날짜 형식이 올바르지 않습니다. 다시 입력해 주세요.";
            ParseException customException = new ParseException(errorMessage, e.getErrorOffset());
            System.out.println(customException.getMessage());
        }
        return isValid;
    }
    public static boolean checkValidDateTerm(String date,String accidentDate){
        try{
            LocalDate inputDate = LocalDate.parse(date);
            accidentDate = accidentDate.substring(0,10);
            LocalDate compareDate = LocalDate.parse(accidentDate);
            //사고 발생일로부터 5년 이내면 true
            if(inputDate.isBefore(compareDate.plusYears(5))){
                return true;
            }
            throw new BaseException(ErrorCode.IS_TOO_LONG_DATE);
        } catch (BaseException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public static boolean sendMessage(String phoneNumber, String message){
        System.out.println(phoneNumber+" 번호로 메세지가 전송되었습니다.");
        return true;
    }
    public static boolean updateAccidentToIndemnity(Accident choicedAccident){
        choicedAccident.setStatus("구상 처리중");
        accidentDao.update(choicedAccident);
        return true;
    }
    public static boolean extendIndemnityPeriod(Accident choicedAccident, String extendedDate){
        choicedAccident.setIndemnityDueDateStringToDate(extendedDate);
        accidentDao.update(choicedAccident);
        return true;
    }
    public static boolean finalizeIndemnity(Accident choicedAccident){
        choicedAccident.setStatus("구상 완료");
        accidentDao.update(choicedAccident);
        return true;
    }
}
