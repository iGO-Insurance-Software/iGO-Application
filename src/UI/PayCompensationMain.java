package UI;
import Accident.Accident;
import Customer.InsuredCustomer;
import util.BaseException;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import static UI.Main.*;
public class PayCompensationMain {
    public static boolean showAccidentsForPayCompensation(BufferedReader inputReader) throws IOException {
        ArrayList<Accident> accidentList = new ArrayList<Accident>();
        for (Accident accident : accidentDao.retrieveAll()){
            if(accident.getStatus().equals("보상금 책정완료")||(accident.getStatus().equals("보상금 지급보류"))){
                if(accident.getCompensationEmployeeID().equals(currentEmployee.getId())) {
                    accidentList.add(accident);
                }
            }
        }
        //반환된 사건들 목록으로 출력
        if (accidentList.size() != 0) {
            for (int i = 0; i < accidentList.size(); i++) {
                Accident accident = accidentList.get(i);
                if(accident.getStatus().equals("보상금 책정완료")) System.out.println("[미지급] "+(i + 1) + ". 사고번호: " + accident.getId());
                else if(accident.getStatus().equals("보상금 지급보류")) System.out.println("[지급 보류] "+(i + 1) + ". 사고번호: " + accident.getId());
            }
            System.out.println("몇번째 사고를 조회하시겠습니까?");
            System.out.print("Choice: ");
            String userChoiceValue = inputReader.readLine().trim();
            int choice = Integer.parseInt(userChoiceValue);
            Accident choicedAccident = accidentList.get(choice - 1);
            System.out.println("사고 번호: "+choicedAccident.getId());
            System.out.println("사고 일시: "+choicedAccident.getAccidentDateToString());
            System.out.println("사고 장소: "+choicedAccident.getAccidentPlace());
            System.out.println("사고 유형:"+choicedAccident.getAccidentType());
            System.out.println("사고 개요: "+choicedAccident.getAccidentOutline());
            InsuredCustomer insuredCustomer = insuredCustomerDao.retrieveById(choicedAccident.getCustomerID());
            System.out.println("피보험자 이름: "+insuredCustomer.getName());
            System.out.println("피보험자 전화번호: "+insuredCustomer.getPhoneNum());
            if (choicedAccident.getExistOfDestroyer()) {
                System.out.println("손괴자 이름: " + choicedAccident.getDestroyerName());
                System.out.println("손괴자 전화번호: " + choicedAccident.getDestroyerPhoneNum());
            }
            System.out.println("보상 책정금: "+choicedAccident.getCompensationMoney()+"₩");
            System.out.println("처리 상태: "+choicedAccident.getStatus());
            System.out.println("\nx. 나가기");
            System.out.println("1. 보상금 지급하기");
            System.out.println("2. 보상금 지급 보류하기");
            System.out.println("3. 보상금 재책정하기");
            System.out.print("Choice: ");
            userChoiceValue = inputReader.readLine().trim();
            switch(userChoiceValue){
                case "1":
                    System.out.println("<보상금 지금>");
                    System.out.println("받을 사람: "+insuredCustomer.getName());
                    System.out.println("받을 계좌번호: "+insuredCustomer.getBankAccount());
                    System.out.println("보내는 사람: (주)iGO");
                    System.out.println("보내는 계좌번호: 94320200299734");
                    System.out.println("비고: iGO 보상처리팀 "+currentEmployee.getName()+" "+currentEmployee.getRank());
                    System.out.println("\n송금하시겠습니까?\n1.예\nx.취소하기");
                    System.out.println("Choice: ");
                    String userChoice = inputReader.readLine().trim();
                    if(userChoice.equals("1")) {
                        if(payCompensationMoney(choicedAccident)){
                            System.out.println("입금이 완료되었습니다.");
                            showMessageForCustomer(insuredCustomer,"고객님의 사건번호 ["+choicedAccident.getId()+"] 에 대한 보상금 "
                                    +choicedAccident.getCompensationMoney()+"가 고객님의 계좌 "+insuredCustomer.getBankAccount()+"로 입급되었습니다.");
                        }
                    }
                    break;
                case "2":
                    System.out.println("<보상금 지금 보류>");
                    System.out.print("보류 사유를 입력하세요: ");
                    String deferReason = inputReader.readLine().trim();
                    System.out.print("보류 예상일을 입력하세요: ");
                    String deferDate = inputReader.readLine().trim();
                    if(deferPayment(choicedAccident))
                    {
                        showMessageForCustomer(insuredCustomer,"고객님의 사건번호 ["+choicedAccident.getId()+"] 에 대한 보상금 지급이 보류되었습니다." +
                                "보류 사유: "+deferReason+", "+"보류 예상일: "+deferDate);
                    }
                    break;
                case "3":
                    if(setBackToCalculateCompensation(choicedAccident)){
                        showMessageForCustomer(insuredCustomer,"고객님의 사건번호 ["+choicedAccident.getId()+"] 에 대한 보상금이 다시 책정될 예정입니다.");
                    }
                    break;
                case "x":
                    break;
            }
        }
        else {
            System.out.println("처리중인 사고가 없습니다.");
        }
        return true;
    }
    public static boolean payCompensationMoney(Accident choicedAccident){
        //Exception : 은행 점검시간(00:00~01:30)일 경우
        LocalTime now = LocalTime.now();
        LocalTime midnight = LocalTime.MIDNIGHT;
        LocalTime oneThirty = LocalTime.of(1, 30);
        if ((now.equals(midnight) || now.isAfter(midnight)) && now.isBefore(oneThirty)) {
            try {
                throw new BaseException("\n[Error!]: 은행 점검 시간입니다.(00:00~01:30) 점검이 끝나면 다시 시도해주세요");
            } catch (BaseException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        choicedAccident.setStatus("보상금 지급완료");
        accidentDao.update(choicedAccident);
        return true;
    }
    public static boolean deferPayment(Accident choicedAccident){
        choicedAccident.setStatus("보상금 지급보류");
        accidentDao.update(choicedAccident);
        return true;
    }
    public static boolean setBackToCalculateCompensation(Accident choicedAccident){
        choicedAccident.setStatus("지급여부 승인");
        accidentDao.update(choicedAccident);
        return true;
    }
}
