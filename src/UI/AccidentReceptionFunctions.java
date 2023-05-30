package UI;

import Accident.Accident;
import Customer.InsuredCustomer;
import Employee.Employee;
import Employee.AccidentReceptionTeam;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static UI.Main.*;

public class AccidentReceptionFunctions {
    public static HashMap<String,String> sendReceiption(BufferedReader inputReader) throws IOException {
        HashMap accidentInfo = new HashMap<String, String>();
        System.out.println("1. 일반 접수하기\n2. 긴급 접수하기\nx. 접수 취소하기 ");
        System.out.print("\nChoice: ");
        String userChoiceValue = inputReader.readLine().trim();
        switch (userChoiceValue) {
            case "1":
                accidentInfo.put("customerID", currentCustomer.getId());
                accidentInfo.put("urgentLevel", "normal");
                System.out.println("<사고 정보 입력창>");
                String accidentDateStr = null;
                Date accidentDate = null;
                boolean isInCorrect = true;
                while(isInCorrect){
                    System.out.print("사고 발생 년-월-일(예시: 2023-01-01): ");
                    String accidentYMD = inputReader.readLine().trim();
                    System.out.print("\n사고 발생 시:분 (예시: 17:53): ");
                    String accidentHM = inputReader.readLine().trim();
                    accidentDateStr = accidentYMD + " " + accidentHM;
                    //Exception : 올바르지 않은 날짜 형식인 경우
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    try {
                        accidentDate = formatter.parse(accidentDateStr);
                        isInCorrect=false;
                    } catch (ParseException e) {
                        String errorMessage = "날짜 형식이 올바르지 않습니다. 다시 접수해 주세요.";
                        ParseException customException = new ParseException(errorMessage, e.getErrorOffset());
                        System.out.println(customException.getMessage());
                    }
                }
                //Exception: 5년이 지난 사고일 경우
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(accidentDate);
                calendar.add(Calendar.YEAR, 5);
                if (calendar.getTime().before(new Date())) {
                    try {
                        throw new RuntimeException("\n발생한지 5년이 넘은 사고는 접수할 수 없습니다.");
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                        return null;
                    }
                }
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
                userChoiceValue = inputReader.readLine().trim();
                switch (userChoiceValue) {
                    case "1":
                        accidentInfo.put("existOfDestroyer","true");
                        System.out.print("\n손괴자 이름을 입력하세요 (예시: 홍길동): ");
                        String destoryerName = inputReader.readLine().trim();
                        accidentInfo.put("destroyerName", destoryerName);
                        System.out.print("\n손괴자 전화번호를 입력하세요 (예시: 01012341234): ");
                        String destoryerPhoneNum = inputReader.readLine().trim();
                        accidentInfo.put("destroyerPhoneNum", destoryerPhoneNum);
                        break;
                    case "2":
                        accidentInfo.put("existOfDestroyer","false");
                        break;
                    default:
                        System.out.println("\n Please select from the menu");
                        break;
                }
                break;
            case "2":
                accidentInfo.put("customerID", currentCustomer.getId());
                accidentInfo.put("urgentLevel", "urgent");
                //현재 시각을 사고 발생 일자로 자동 기입
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                String nowTime = now.format(formatter2);
                accidentInfo.put("accidentDate",nowTime);
                //GPS 정보 위치로 사고 장소를 자동 기입한다고 가정
                accidentInfo.put("accidentPlace","[GPS Address]");
                break;
            case "x":
                return null;
            default:
                System.out.println("\n Please select from the menu");
                break;
        }
        return accidentInfo;
    }
    //고객의 사고 조회 메소드
    public static boolean searchReception(BufferedReader inputReader) throws IOException {
        String userChoiceValue;
        ArrayList<Accident> myAccidentList = new ArrayList<Accident>();
        for (Accident acdt : accidentDao.retrieveAll()) {
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
            System.out.println("사고 일시: " + choicedAccident.getAccidentDate().toString());
            System.out.println("사고 장소: " + choicedAccident.getAccidentPlace());
            System.out.println("사고 유형: " + choicedAccident.getAccidentType());
            if (choicedAccident.getExistOfDestroyer()) {
                System.out.println("손괴자 이름: " + choicedAccident.getDestroyerName());
                System.out.println("손괴자 전화번호: " + choicedAccident.getDestroyerPhoneNum());
            }
            System.out.println("처리 상태: " + choicedAccident.getStatus());
            System.out.println("\nx. 나가기");
            if (choicedAccident.getStatus().contains("접수")) {
                System.out.println("1. 접수 취소하기");
                System.out.print("Choice: ");
                userChoiceValue = inputReader.readLine().trim();
                if (userChoiceValue.equals("x")) return true;
                else if (userChoiceValue.equals("1")) {
                    //해당 사고를 접수한 직원 반환
                    AccidentReceptionTeam arEmployee = accidentReceptionTeamDao.retrieveById(choicedAccident.getReceptionEmployeeID());
                    if (choicedAccident.getStatus().contains("긴급")) {
                        if (arEmployee.getInvestigationTeam().getIsDispatching()) {
                            //이미 출동중이면
                            System.out.println("\n이미 현장 조사 직원이 출발하여 취소시 수수료가 발생합니다.");
                            System.out.println("1.확인");
                            System.out.println("2.취소");
                            userChoiceValue = inputReader.readLine().trim();
                            if (userChoiceValue.equals("1")) {
                                arEmployee.cancelUrgentReceiption(choicedAccident.getId());
                                //Contract 객체에다 수수료 30000원 부과하는 로직 작성
                                return true;
                            } else return true; //다른 버튼 클릭시(접수 취소를 취소 시)
                        }
                        showMessageForEmployee(arEmployee,"사건번호 " + choicedAccident.getId() + "의 긴급 접수가 취소되었습니다.");
                        showMessageForCustomer(currentCustomer,"사건번호 " + choicedAccident.getId() + "의 긴급 접수가 취소되었습니다.");
                        accidentDao.deleteById(choicedAccident.getId());//해당 사건 삭제
                    }
                    else {//일반 접수 취소시
                        showMessageForEmployee(arEmployee,"사건번호 " + choicedAccident.getId() + "의 접수가 취소되었습니다.");
                        showMessageForCustomer(currentCustomer,"사건번호 " + choicedAccident.getId() + "의 접수가 취소되었습니다.");
                        accidentDao.deleteById(choicedAccident.getId());
                    }
                }
            }
        } else {
            System.out.println("처리중인 사고가 없습니다.");
        }
        return true;
    }
    public static boolean receiveReceiption(HashMap<String,String> accidentInfo, BufferedReader inputReader) throws IOException {
        String senderCustomerID = accidentInfo.get("customerID");
        //접수한 고객 반환
        InsuredCustomer senderCustomer = insuredCustomerDao.retrieveById(senderCustomerID);
        //무작위의 접수직원에게 접수를 전송
        int randomIndex = ThreadLocalRandom.current().nextInt(0, accidentReceptionTeamDao.retrieveAll().size());
        Employee arEmployee = accidentReceptionTeamDao.retrieveAll().get(randomIndex);
        //사건 생성
        Accident acdt = new Accident(accidentInfo);
        acdt.setReceptionEmployeeID(arEmployee.getId());
        System.out.println("******** " + arEmployee.getName() + " 사원님의 화면 ********");
        switch (accidentInfo.get("urgentLevel")) {
            case "normal":
                acdt.setIsUrgent(false);
                System.out.println("(고객 id: " + senderCustomer.getId() + ") " + senderCustomer.getName() + "님의 사고가 접수되었습니다.\n<사고 정보>");
                System.out.println("사고 일시: " + accidentInfo.get("accidentDate"));
                System.out.println("사고 장소: " + accidentInfo.get("accidentPlace"));
                System.out.println("사고 유형: " + accidentInfo.get("accidentType"));
                System.out.println("사고 개요: " + accidentInfo.get("accidentOutline"));
                if (accidentInfo.get("existOfDestroyer").equals("true")) {
                    System.out.println("손괴자 이름: " + accidentInfo.get("destroyerName"));
                    System.out.println("손괴자 전화번호: " + accidentInfo.get("destroyerPhoneNum"));
                    acdt.setExistOfDestroyer(true);
                    acdt.setDestroyerName(accidentInfo.get("destroyerName"));
                    acdt.setDestroyerPhoneNum(accidentInfo.get("destroyerPhoneNum"));
                }
                else acdt.setExistOfDestroyer(false);
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
                break;
            case "urgent":
                acdt.setIsUrgent(true);
                System.out.println("(고객 id: " + senderCustomerID + ") " + senderCustomer.getName() + "님의 사고가 긴급 접수되었습니다.\n<사고 정보>");
                System.out.println("사고 일시: " + accidentInfo.get("accidentDate"));
                System.out.println("사고 장소: " + accidentInfo.get("accidentPlace"));
                System.out.println("접수자 이름: " + senderCustomer.getName());
                System.out.println("접수자 전화번호: " + senderCustomer.getPhoneNum());
                System.out.println("1. 확인(접수 승인)");
                System.out.print("Choice: ");
                userChoiceValue = inputReader.readLine().trim();
                if (userChoiceValue.equals("1")) {
                    acdt.setStatus("접수 완료(긴급)");
                }
                break;
        }
        accidentDao.create(acdt);
        return true;
    }


}
