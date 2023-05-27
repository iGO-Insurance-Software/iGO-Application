package UI;

import Accident.Accident;
import Customer.InsuredCustomer;
import Employee.Employee;
import Employee.AccidentReceiptionTeam;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static UI.Main.*;

public class AccidentReceiptionFunctions {
    public static HashMap<String,String> sendReceiption(BufferedReader inputReader) throws IOException {
        HashMap accidentInfo = new HashMap<String, String>();
        System.out.println("<사고 정보 입력창>");
        System.out.print("사고 발생 년-월-일(예시: 2023-01-01): ");
        String accidentYMD = inputReader.readLine().trim();
        System.out.print("\n사고 발생 시:분 (예시: 17:53): ");
        String accidentHM = inputReader.readLine().trim();
        String accidentDateStr = accidentYMD + " " + accidentHM;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date accidentDate;
        try {
            accidentDate = formatter.parse(accidentDateStr);
        } catch (ParseException e) {
            throw new IllegalArgumentException("날짜 형식이 올바르지 않습니다.");
        }
        accidentInfo.put("customerID", currentCustomer.getId());
        accidentInfo.put("accidentDate", accidentDateStr);
        System.out.print("\n사고 장소 (예시: 거북골로 3-2길 횡단보도): ");
        String accidentPlace = inputReader.readLine();
        accidentInfo.put("accidentPlace", accidentPlace);
        System.out.print("\n사고 유형 (예시: 교통사고): ");
        String accidentType = inputReader.readLine();
        accidentInfo.put("accidentType", accidentType);
        System.out.print("\n사고 개요 (예시: 초록불에 횡단보도를 건너다가 마티즈 차량이 저를 쳤어요): ");
        String accidentOutline = inputReader.readLine();
        accidentInfo.put("accidentOutline", accidentOutline);
        System.out.println("\n<손괴자 여부 체크>\n해당 사고에 손괴자가 존재하나요?\n1.네\n2.아니요");
        System.out.print("\nChoice: ");
        String userChoiceValue = inputReader.readLine().trim();
        switch (userChoiceValue) {
            case "1":
                System.out.print("\n손괴자 이름을 입력하세요 (예시: 홍길동): ");
                String destoryerName = inputReader.readLine().trim();
                accidentInfo.put("destoryerName", destoryerName);
                System.out.print("\n손괴자 전화번호를 입력하세요 (예시: 01012341234): ");
                String destoryerPhoneNum = inputReader.readLine().trim();
                accidentInfo.put("destroyerPhonNum", destoryerPhoneNum);
                break;
            case "2":
                break;
            default:
                System.out.println("\n Please select from the menu");
                break;
        }
        System.out.println("1. 접수하기\n2. 긴급 접수하기\nx. 접수 취소하기 ");
        System.out.print("\nChoice: ");
        userChoiceValue = inputReader.readLine().trim();
        switch (userChoiceValue) {
            case "1":
                accidentInfo.put("urgentLevel", "normal");
                break;
            case "2":
                accidentInfo.put("urgentLevel", "urgent");
                break;
            case "x":
                return null;
            default:
                System.out.println("\n Please select from the menu");
                break;
        }
        //Exception: 5년이 지난 사고일 경우
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(accidentDate);
        calendar.add(Calendar.YEAR, 5);
        if (calendar.getTime().before(new Date())) {
            accidentInfo = null;
            try {
                throw new RuntimeException("발생한지 5년이 넘은 사고는 접수할 수 없습니다.");
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
        return accidentInfo;
    }
    public static boolean searchReception(BufferedReader inputReader) throws IOException {
        String userChoiceValue;
        ArrayList<Accident> myAccidentList = new ArrayList<Accident>();
        for (Accident acdt : accidentList.retrieveAll()) {
            if (acdt.getCustomerID().equals(currentCustomer.getId())) {
                myAccidentList.add(acdt);
            }
        }
        if (myAccidentList.size() != 0) {
            for (int i = 0; i < myAccidentList.size(); i++) {
                Accident myAcdt = myAccidentList.get(i);
                System.out.println((i + 1) + ". 사고번호: " + myAcdt.getId());
            }
            System.out.println("몇번째 사고를 조회하시겠습니까?");
            System.out.print("Choice: ");
            userChoiceValue = inputReader.readLine().trim();
            int choice = Integer.parseInt(userChoiceValue);
            Accident choicedAccident = myAccidentList.get(choice - 1);
            System.out.println("\n사고번호 [" + choicedAccident.getId() + "] 사고 선택.");
            System.out.println("사고 일시: " + choicedAccident.getAccidentDate().toString());
            System.out.println("사고 장소: " + choicedAccident.getAccidentPlace());
            System.out.println("사고 유형: " + choicedAccident.getAccidentType());
            if (choicedAccident.isExistOfDestroyer()) {
                System.out.println("손괴자 이름: " + choicedAccident.getDestroyerName());
                System.out.println("손괴자 전화번호: " + choicedAccident.getDestroyerPhoneNum());
            }
            System.out.println("처리 상태: " + choicedAccident.getStatus());
            System.out.println("\nx. 나가기");
            if (choicedAccident.getStatus().contains("접수")) {
                System.out.println("1. 접수 취소하기");
                System.out.print("Choice: ");
                userChoiceValue = inputReader.readLine().trim();
                if (userChoiceValue.equals("x")) ;
                else if (userChoiceValue.equals("1")) {
                    if (choicedAccident.getStatus().contains("긴급")) {
                        if (((AccidentReceiptionTeam) currentEmployee).getInvestigationTeam().getIsDispatching()) {
                            //이미 출동중이면
                            System.out.println("\n이미 현장 조사 직원이 출발하여 취소시 수수료가 발생합니다.");
                            System.out.println("1.확인");
                            System.out.println("2.취소");
                            userChoiceValue = inputReader.readLine().trim();
                            if (userChoiceValue.equals("1")) {
                                currentEmployee.receiveMessage("사건번호 " + choicedAccident.getId() + "의 긴급 접수가 취소되었습니다.");
                                currentCustomer.receiveMessage("사건번호 " + choicedAccident.getId() + "의 긴급 접수가 취소되었습니다.");
                                //customer.cancelReceiption이 위의 로직들임 - 생략
                                ((AccidentReceiptionTeam) currentEmployee).cancelUrgentReceiption(choicedAccident.getId());
                                //Contract 객체에다 수수료 30000원 부과하는 로직 작성
                                //해당 사건 삭제
                                accidentList.delete(choicedAccident.getId());
                                return true;
                            } else return true; //다른 버튼 클릭시(접수 취소를 취소 시)
                        } else { //아직 출동중이 아니면
                            currentEmployee.receiveMessage("사건번호 " + choicedAccident.getId() + "의 긴급 접수가 취소되었습니다.");
                            currentCustomer.receiveMessage("사건번호 " + choicedAccident.getId() + "의 긴급 접수가 취소되었습니다.");
                            //해당 사건 삭제
                            accidentList.delete(choicedAccident.getId());
                        }
                    } else {//일반 접수 취소시
                        currentEmployee.receiveMessage("사건번호 " + choicedAccident.getId() + "의 접수가 취소되었습니다.");
                        currentCustomer.receiveMessage("사건번호 " + choicedAccident.getId() + "의 접수가 취소되었습니다.");
                        //해당 사건 삭제
                        accidentList.delete(choicedAccident.getId());
                    }
                }
            }

        } else {
            System.out.println("처리중인 사고가 없습니다.");
        }
        return true;
    }
    public static boolean receiveReceiption(HashMap<String,String> accidentInfo, BufferedReader inputReader) throws IOException {
        String senderCustomerID = accidentInfo.get("customerID").toString();
        System.out.println(senderCustomerID);
        InsuredCustomer senderCustomer = insuredCustomerDao.retrieveById(senderCustomerID);
        boolean isAccepted = false;
        boolean isUrgent = false;
        //무작위의 접수직원에게 접수를 전송
        Random random = new Random();
        Employee arEmployee = accidentReceiptionTeamDao.retrieveAll().get(random.nextInt(accidentReceiptionTeamDao.retrieveAll().size()));
        //사건 생성
        Accident acdt = new Accident(accidentInfo);
        System.out.println("******** " + arEmployee.getName() + " 사원님의 화면 ********");
        switch (accidentInfo.get("urgentLevel")) {
            case "normal":
                acdt.setIsUrgent(false);
                System.out.println("(고객 id: " + senderCustomerID + ") " + insuredCustomerDao.retrieveById(senderCustomerID).getName() + "님의 사고가 접수되었습니다.\n<사고 정보>");
                System.out.println("사고 일시: " + accidentInfo.get("accidentDate"));
                System.out.println("사고 장소: " + accidentInfo.get("accidentPlace"));
                System.out.println("사고 유형: " + accidentInfo.get("accidentType"));
                System.out.println("사고 개요: " + accidentInfo.get("accidentOutline"));
                if (accidentInfo.get("손괴자 이름") != null && accidentInfo.get("손괴자 이름").equals("") == false) {
                    System.out.println("손괴자 이름: " + accidentInfo.get("destroyerName"));
                    System.out.println("손괴자 전화번호: " + accidentInfo.get("destroyerPhonNum"));
                    acdt.setExistOfDestroyer(true);
                    acdt.setDestroyerName(accidentInfo.get("destroyerName"));
                    acdt.setDestroyerPhoneNum(accidentInfo.get("destroyerPhonNum"));
                }
                System.out.println("1. 확인(접수 승인)");
                System.out.println("2. 거절");
                System.out.print("Choice: ");
                String userChoiceValue = inputReader.readLine().trim();
                System.out.println();
                if (userChoiceValue.equals("1")) {
                    showMessageForCustomer(senderCustomer, "접수가 완료되었습니다. 접수 번호: " + acdt.getId() +
                            ", 담당자 이름: " + arEmployee.getName() + ", 담당자 연락처: " + arEmployee.getPhoneNum() + " / " + arEmployee.getEmail());
                    acdt.setStatus("접수 완료");
                } else if (userChoiceValue.equals("2")) {
                    System.out.print("거절 사유를 입력하세요: ");
                    String refusalReason = inputReader.readLine();
                    showMessageForCustomer(senderCustomer, "고객님의 사고 접수 요청이 거절되었습니다. 거절 사유: " + refusalReason);
                    acdt.setStatus("접수 거절");
                }
                //accidentDao.create(acdt);
                break;
            case "urgent":
                acdt.setIsUrgent(true);
                System.out.println("(고객 id: " + senderCustomerID + ") " + customerList.retrieve(senderCustomerID).getName() + "님의 사고가 긴급 접수되었습니다.\n<사고 정보>");
                System.out.println("사고 일시: " + accidentInfo.get("accidentDate"));
                System.out.println("사고 장소: " + accidentInfo.get("accidentPlace"));
                System.out.println("접수자 이름: " + customerList.retrieve(senderCustomerID).getName());
                System.out.println("접수자 전화번호: " + customerList.retrieve(senderCustomerID).getPhoneNum());


                System.out.println("1. 확인(접수 승인)");
                System.out.print("Choice: ");
                userChoiceValue = inputReader.readLine().trim();
                if (userChoiceValue.equals("1")) {
                    acdt.setStatus("접수 완료(긴급)");
                }
                break;
        }
        //AccidentDao.create(acdt)
        return true;
    }


}
