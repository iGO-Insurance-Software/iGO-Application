package UI;
import Accident.Accident;
import Contract.Contract;
import Insurance.Insurance;
import Customer.InsuredCustomer;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static UI.Main.*;

public class DecideCompensationMain {
    public static boolean showAccidentsForDecideCompensation(BufferedReader inputReader) throws IOException{
        removeExpiredAccidents();
        //아직 보상 직원이 할당되지 않은 "접수 완료"인 상태의 사건들 반환
        ArrayList<Accident> accidentList = new ArrayList<Accident>();
        for (Accident accident : accidentDao.retrieveAll()){
            if(accident.getStatus().equals("접수 완료")){
                accidentList.add(accident);
            }
            //지급여부 거절/추가서류 요청된 사건들은 현재 접속중인 보상 직원이 처리한 사건들 반환
            if(accident.getStatus().equals("지급여부 거절")||accident.getStatus().contains("추가서류 요청")){
                if(accident.getCompensationEmployeeID().equals(currentEmployee.getId())){
                    accidentList.add(accident);
                }
            }
        }
        //반환된 사건들 목록으로 출력
        if (accidentList.size() != 0) {
            for (int i = 0; i < accidentList.size(); i++) {
                Accident accident = accidentList.get(i);
                if(accident.getStatus().equals("지급여부 거절")) System.out.println("[거절됨] "+(i + 1) + ". 사고번호: " + accident.getId());
                else if(accident.getStatus().contains("추가서류 요청")) System.out.println("[추가서류 요청됨] "+(i + 1) + ". 사고번호: " + accident.getId());
                else System.out.println("[미배정] "+(i + 1) + ". 사고번호: " + accident.getId());
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
            System.out.println("처리 상태: "+choicedAccident.getStatus());
            System.out.println("\nx. 나가기");
            System.out.println("1. 고객 정보 검토하기");
            System.out.print("Choice: ");
            userChoiceValue = inputReader.readLine().trim();
            switch(userChoiceValue){
                case "x":
                    break;
                case "1":
                    reviewInsuredCustomerInfo(insuredCustomer,choicedAccident,inputReader);
                    break;
            }
        }
        else {
            System.out.println("처리중인 사고가 없습니다.");
        }
        return true;
    }
    public static boolean reviewInsuredCustomerInfo(InsuredCustomer senderCustomer,Accident choicedAccident,BufferedReader inputReader) throws IOException{
        System.out.println("<고객 정보>");
        System.out.println("id: "+senderCustomer.getId());
        System.out.println("이름: "+senderCustomer.getName());
        System.out.println("주민번호 "+senderCustomer.getRrn());
        System.out.println("나이: "+senderCustomer.getAge());
        System.out.println("성별:"+senderCustomer.getGender());
        System.out.println("전화번호: "+senderCustomer.getPhoneNum());
        System.out.println("직업: "+senderCustomer.getOccupation());
        System.out.println("가족력: "+senderCustomer.getFamilyHistory());
        System.out.println("-건강검진서-");
        System.out.println(senderCustomer.getHealthCertificate());
        System.out.println("-가족관계서-");
        System.out.println(senderCustomer.getInheritanceCertificate());
        System.out.println("-재직증명서-");
        System.out.println(senderCustomer.getEmploymentCertificate());
        System.out.println("\n\n<계약 정보>");
        //고객의 계약 탐색
        ArrayList<Contract> contracts = new ArrayList<Contract>();
        for(Contract contract : contractDao.retrieveAll()){
            if(contract.getInsuredCustomerID().equals(senderCustomer.getId())){
                contracts.add(contract);
            }
        }
        if(contracts.size()!=0){
            for(int i=0; i < contracts.size(); i++){
                System.out.println("계약 id: "+contracts.get(i).getId());
                Insurance insurance = insuranceDao.retrieveById(contracts.get(i).getInsuranceID());
                System.out.println("보험 id: "+insurance.getId());
                System.out.println("보험 상품명: "+insurance.getName());
                System.out.println("보험 설명: "+insurance.getDescription());
            }
        }
        System.out.println("--------------------");
        System.out.println("1.보상금 지급 여부 승인하기");
        System.out.println("2.보상금 지급 여부 거절하기");
        System.out.println("3.추가 서류 요쳥하기");
        System.out.println("Others. 나가기");
        System.out.print("Choice: ");
        String userChoice = inputReader.readLine().trim();
        switch(userChoice){
            case "1":
                showMessageForCustomer(senderCustomer,"고객님이 접수하신 사고번호 ["+choicedAccident.getId()+"] 의 보상금 지급여부가 승인되었습니다. 이제 보상금 책정 절차에 들어갑니다.");
                approveCompensation(choicedAccident);
                break;
            case "2":
                System.out.print("거절 사유를 입력하세요: ");
                String refuseReason = inputReader.readLine();
                showMessageForCustomer(senderCustomer,"고객님이 접수하신 사고 번호 ["+choicedAccident.getId()+"] 의 보상금 지급여부가 거절되었습니다." +
                        "\n거절 사유 : "+refuseReason+"\n추가적인 문의나 이의가 있으시면 담당자에게 문의해주시기 바랍니다." +
                        "\n담당자 이름: "+currentEmployee.getName()+", 담당자 전화번호 : "+currentEmployee.getPhoneNum());
                refuseCompensation(choicedAccident);
                break;
            case "3":
                ArrayList<String> requiredDocs = new ArrayList<String>();
                System.out.println("추가적으로 요청할 서류들을 입력하세요:");
                while(true) {
                    System.out.print("서류 이름: ");
                    String userInput = inputReader.readLine();
                    requiredDocs.add(userInput);
                    System.out.println("1. 다른 서류도 요청하기");
                    System.out.println("x. 요청 완료하기");
                    System.out.print("Choice: ");
                    userChoice = inputReader.readLine().trim();
                    if(userChoice.equals("1")) continue;
                    else if(userChoice.equals("x")) break;
                }
                String message = "고객님이 접수하신 사고 번호 ["+choicedAccident.getId()+"] 의 추가적인 서류가 요청되었습니다." +
                        "추가 서류: ";
                for(String docName: requiredDocs){
                    message = message+docName+", ";
                }
                message = message.substring(0,message.length()-2);
                message = message+"\n해당 서류(들)는 "+currentEmployee.getEmail()+"로 보내주시길 바랍니다.";
                showMessageForCustomer(senderCustomer,message);
                requestMoreDocs(choicedAccident,requiredDocs);
                break;
            default:
                break;
        }
        return true;
    }
    public static boolean approveCompensation(Accident choicedAccident){
        choicedAccident.setCompensationEmployeeID(currentEmployee.getId());
        choicedAccident.setStatus("지급여부 승인");
        accidentDao.update(choicedAccident);
        return true;
    }
    public static boolean refuseCompensation(Accident choicedAccident){
        choicedAccident.setCompensationEmployeeID(currentEmployee.getId());
        choicedAccident.setStatus("지급여부 거절");
        accidentDao.update(choicedAccident);
        return true;
    }
    public static boolean requestMoreDocs(Accident choicedAccident,ArrayList<String> requiredDocs){
        choicedAccident.setCompensationEmployeeID(currentEmployee.getId());
        String status = "추가서류 요청 [";
        for(String docName: requiredDocs){
            status = status+docName+", ";
        }
        status = status.substring(0,status.length()-2);
        status = status+"]";
        choicedAccident.setStatus(status);
        accidentDao.update(choicedAccident);
        return true;
    }
    public static boolean removeExpiredAccidents() {
        Date currentDate = new Date();
        for (Accident accident : accidentDao.retrieveAll()) {
            //접수 거절 상태로 5년 지난 사건 삭제
            Date accidentDate = accident.getAccidentDate();
            long diffInMillies = Math.abs(currentDate.getTime() - accidentDate.getTime());
            long diffInYears = diffInMillies / (24 * 60 * 60 * 1000 * 365L);
            if (diffInYears >= 5 && accident.getStatus().equals("지급여부 거절")) {
                accidentDao.deleteById(accident.getId());
            }
        }
        return true;
    }
}
