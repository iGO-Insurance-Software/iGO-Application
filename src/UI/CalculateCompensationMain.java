package UI;

import Accident.Accident;
import Contract.Contract;
import Customer.InsuredCustomer;
import Insurance.Insurance;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import static UI.Main.*;

public class CalculateCompensationMain {
    public static boolean showAccidentsForCalculateCompensation(BufferedReader inputReader) throws IOException {
        deleteLateSubmitAccidents();
        //보상금 지급 여부가 승인된 사건들 중, 현재 로그인한 직원이 담당하는 사고들만 반환
        ArrayList<Accident> accidentList = new ArrayList<Accident>();
        for (Accident accident : accidentDao.retrieveAll()) {
            if (accident.getStatus().equals("지급여부 승인") || accident.getStatus().equals("책정서류 요청중") || accident.getStatus().equals("책정서류 제출됨")) {
                if (accident.getCompensationEmployeeID().equals(currentEmployee.getId())) {
                    accidentList.add(accident);
                }
            }
        }
        if (accidentList.size() != 0) {
            for (int i = 0; i < accidentList.size(); i++) {
                Accident accident = accidentList.get(i);
                if (accident.getStatus().equals("책정서류 요청중"))
                    System.out.println("[서류 요청중] " + (i + 1) + ". 사고번호: " + accident.getId());
                else if(accident.getStatus().equals("책정서류 제출됨"))
                    System.out.println("[서류 도착] " + (i + 1) + ". 사고번호: " + accident.getId());
                else System.out.println("[미책정] " + (i + 1) + ". 사고번호: " + accident.getId());
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
                case "1":
                    showCustomerInfoForCalculateCompensation(insuredCustomer, choicedAccident, inputReader);
                    break;
                case "x" :
                    break;
            }
        }
        else System.out.println("처리중인 사고가 없습니다.");
        return true;
    }
    public static boolean showCustomerInfoForCalculateCompensation(InsuredCustomer senderCustomer, Accident choicedAccident, BufferedReader inputReader) throws IOException{
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
        System.out.println("-진료비 계산서-");
        if(choicedAccident.getMedicalBill().equals("null")) System.out.println("없음");
        else System.out.println(choicedAccident.getMedicalBill());
        System.out.println("-손해 계산서-");
        if(choicedAccident.getDamageBill().equals("null")) System.out.println("없음");
        else System.out.println(choicedAccident.getDamageBill());
        System.out.println("\n<계약 정보>");
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
                System.out.println("보험료: "+contracts.get(i).getPremium());
                Insurance insurance = insuranceDao.retrieveById(contracts.get(i).getInsuranceID());
                System.out.println("보험 id: "+insurance.getId());
                System.out.println("보험 상품명: "+insurance.getName());
                System.out.println("보험 설명: "+insurance.getDescription());

            }
        }
        System.out.println("--------------------");
        boolean isRemain = true;
        while(isRemain) {
            System.out.println("1.보상금 책정하기");
            System.out.println("2.보상금 책정 관련 서류 요청하기");
            System.out.println("Others. 나가기");
            System.out.print("Choice: ");
            String userChoice = inputReader.readLine().trim();
            switch (userChoice) {
                case "1":
                    if (choicedAccident.getMedicalBill().equals("null") && choicedAccident.getDamageBill().equals("null")) {
                        System.out.println("[Warning!] 보상금 책정 관련 서류 정보가 없습니다. 먼저 서류를 요청하고 이를 검토한 후 보상금을 책정해주세요");
                        break;
                    } else {
                        int compensationMoney;
                        while (true) {
                            System.out.print("보상금을 입력하세요: ");
                            String userInput = inputReader.readLine().trim();
                            try {
                                compensationMoney = Integer.parseInt(userInput);
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("[Error!]올바른 금액을 입력하세요");
                            }
                        }
                        showMessageForCustomer(senderCustomer, "고객님이 접수하신 사고 번호 [" + choicedAccident.getId() + "] 의 보상금이 책정되었습니다." +
                                "\n보상 금액 : " + compensationMoney + "\n추가적인 문의나 이의가 있으시면 담당자에게 문의해주시기 바랍니다." +
                                "\n담당자 이름: " + currentEmployee.getName() + ", 담당자 전화번호 : " + currentEmployee.getPhoneNum());
                        decideCompensationMoney(choicedAccident, compensationMoney);
                    }
                    isRemain = false;
                    break;
                case "2":
                    showMessageForCustomer(senderCustomer, "고객님이 접수하신 사고 번호 [" + choicedAccident.getId() + "] 의 보상금이 책정되기 위한 서류 요청이 도착했습니다." +
                            "사고 조회 메뉴에서 해당 사고를 클릭하고 서류 업로드하기 버튼을 클릭해주세요.");
                    requestDocsForCalculateCompensation(choicedAccident);
                    isRemain = false;
                    break;
                default:
                    isRemain = false;
                    break;
            }
        }
        return true;
    }
    public static boolean decideCompensationMoney(Accident choicedAccident, int compensationMoney){
        choicedAccident.setCompensationMoney(compensationMoney);
        choicedAccident.setStatus("보상금 책정완료");
        accidentDao.update(choicedAccident);
        return true;
    }
    public static boolean requestDocsForCalculateCompensation(Accident choicedAccident){
        choicedAccident.setStatus("책정서류 요청중");
        accidentDao.update(choicedAccident);
        return true;
    }
    public static boolean deleteLateSubmitAccidents(){
        Date currentDate = new Date();
        for (Accident accident : accidentDao.retrieveAll()) {
            //책정서류 요청중상태에서 5년 지난 사건 삭제
            Date accidentDate = accident.getAccidentDate();
            long diffInMillies = Math.abs(currentDate.getTime() - accidentDate.getTime());
            long diffInYears = diffInMillies / (24 * 60 * 60 * 1000 * 365L);
            if (diffInYears >= 5 && accident.getStatus().equals("책정서류 요청중")) {
                showMessageForCustomer(customerDao.retrieveById(accident.getCustomerID()),
                        "고객님이 접수하신 사고 번호 ["+accident.getId()+"] 에 대한 보상금 책정 관련 서류를 5년동안 제출하지 않아 청구권 만료로 해당 사건의 보상처리가 종료되었습니다.");
                accidentDao.deleteById(accident.getId());
                return true;
            }
        }
        return false;
    }

    /*Customer's Function*/
    public static boolean submitDocsForCalculateCompensation(Accident choicedAccident, BufferedReader inputReader) throws IOException{
        System.out.println("<보상금 책정 관련 서류 업로드>");
        System.out.println("두개의 서류 중 필요한 서류를 업로드해주세요 (예시: 자동차 사고 - 다친 부분에 대한 진료비 계산서, 자동차에 대한 수리 견적이 담긴 피해 계산서 모두 첨부)");
        System.out.println("진료비 계산서가 존재합니까?");
        System.out.println("1.예\n2.아니요");
        System.out.print("Choice: ");
        String userChoice = inputReader.readLine().trim();
        if (userChoice.equals("1")) {
            while (true) {
                System.out.print("여기에 파일을 업로드: ");
                String fileName = inputReader.readLine().trim();
                fileName = "exampleDocs/"+fileName;
                String document = uploadDocument(fileName);
                //업로드에 성공했을 경우
                if (document != null) {
                    choicedAccident.setMedicalBill(document);
                    break;
                }
            }
        }
        System.out.println("피해 계산서가 존재합니까?");
        System.out.println("1.예\n2.아니요");
        System.out.print("Choice: ");
        userChoice = inputReader.readLine().trim();
        if (userChoice.equals("1")) {
            while(true) {
                System.out.print("피해 계산서: ");
                String fileName = inputReader.readLine().trim();
                fileName = "exampleDocs/" + fileName;
                String document = uploadDocument(fileName);
                //업로드에 성공했을 경우
                if (document != null) {
                    choicedAccident.setDamageBill(document);
                    break;
                }
            }
        }
        if(choicedAccident.getMedicalBill().equals("null")&&choicedAccident.getMedicalBill().equals("null")){
            System.out.println("어떠한 파일도 업로드하지 않아 메인메뉴로 이동합니다.");
            return false;
        }
        else {
            showMessageForCustomer(currentCustomer, "보상금 책정 관련 서류 요청이 완료되었습니다.");
            showMessageForEmployee(employeeDao.retrieveById(choicedAccident.getCompensationEmployeeID()),
                    "사고 번호 [" + choicedAccident.getId() + "] 에 대한 보상금 책정 관련 서류가 제출되었습니다.");
            choicedAccident.setStatus("책정서류 제출됨");
            accidentDao.update(choicedAccident);
            return true;
        }
    }
    public static String uploadDocument(String fileName){
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }
        } catch (IOException e) {
            System.out.println("잘못된 파일입니다. 다시 업로드해주세요");
            return null;
        }
        return stringBuilder.toString();
    }

}
