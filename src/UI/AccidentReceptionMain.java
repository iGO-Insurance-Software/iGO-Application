package UI;

import Accident.Accident;
import Contract.Contract;
import Customer.Customer;
import Customer.InsuredCustomer;
import Dao.ContractDao;
import Employee.Employee;
import Employee.AccidentReceptionTeam;
import Employee.InvestigationTeam;
import util.BaseException;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import static UI.Main.*;
import static UI.CalculateCompensationMain.submitDocsForCalculateCompensation;

public class AccidentReceptionMain {
    /*Customer's Functions*/
    public static HashMap<String,String> sendReception(BufferedReader inputReader) throws IOException {
        HashMap accidentInfo = new HashMap<String, String>();
        System.out.println("1. 일반 접수하기\n2. 긴급 접수하기\nx. 뒤로가기 ");
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
                        throw new BaseException("\n발생한지 5년이 넘은 사고는 접수할 수 없습니다.");
                    } catch (BaseException e) {
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
                        String destroyerName = inputReader.readLine().trim();
                        accidentInfo.put("destroyerName", destroyerName);
                        System.out.print("\n손괴자 전화번호를 입력하세요 (예시: 01012341234): ");
                        String destroyerPhoneNum = inputReader.readLine().trim();
                        accidentInfo.put("destroyerPhoneNum", destroyerPhoneNum);
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
                //한명이 여러개의 긴급접수를 보내는 것을 막음
                Accident duplicatedReception = null;
                for(Accident accident : accidentDao.retrieveAll()){
                    if(accident.getCustomerID().equals(currentCustomer.getId())){
                        if(accident.getStatus().equals("접수 완료(출동 대기)")||accident.getStatus().equals("접수 완료(출동 중)")){
                            showMessageForCustomer(currentCustomer,"고객님이 접수한 긴급 사건번호 "+ accident.getId()
                                    +"에 대한 긴급 접수가 아직 처리 중입니다. 이전의 사건이 접수 완료되면 긴급 접수해주세요.");
                            return null;
                        }
                    }
                }
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
    public static boolean showAccidentsForCustomer(BufferedReader inputReader) throws IOException {
        String userChoiceValue;
        ArrayList<Accident> myAccidentList = new ArrayList<Accident>();
        for (Accident accident : accidentDao.retrieveAll()) {
            if (accident.getCustomerID().equals(currentCustomer.getId())) {
                myAccidentList.add(accident);
            }
        }
        if (myAccidentList.size() != 0) {
            for (int i = 0; i < myAccidentList.size(); i++) {
                Accident myAccident = myAccidentList.get(i);
                System.out.println((i + 1) + ". 사고번호: " + myAccident.getId());
            }
            System.out.println("몇번째 사고를 조회하시겠습니까?");
            System.out.print("Choice: ");
            userChoiceValue = inputReader.readLine().trim();
            int choice = Integer.parseInt(userChoiceValue);
            Accident choicedAccident = myAccidentList.get(choice - 1);
            System.out.println("사고 일시: " + choicedAccident.getAccidentDate().toString());
            System.out.println("사고 장소: " + choicedAccident.getAccidentPlace());
            if(!choicedAccident.getIsUrgent()) {
                System.out.println("사고 유형: " + choicedAccident.getAccidentType());
                System.out.println("사고 개요: " + choicedAccident.getAccidentOutline());
            }
            if (choicedAccident.getExistOfDestroyer()) {
                System.out.println("손괴자 이름: " + choicedAccident.getDestroyerName());
                System.out.println("손괴자 전화번호: " + choicedAccident.getDestroyerPhoneNum());
            }
            System.out.println("처리 상태: " + choicedAccident.getStatus());
            if (choicedAccident.getCompensationMoney()!=0){
                System.out.println("보상금: "+choicedAccident.getCompensationMoney());
            }
            System.out.println("\nx. 나가기");
            if (choicedAccident.getStatus().contains("접수") || choicedAccident.getStatus().equals("할당 대기중")) {
                System.out.println("1. 접수 취소하기");
                System.out.print("Choice: ");
                userChoiceValue = inputReader.readLine().trim();
                if (userChoiceValue.equals("x")) return true;
                else if (userChoiceValue.equals("1")) {
                    cancelReception(choicedAccident,inputReader);
                }
            }
            else if (choicedAccident.getStatus().equals("책정서류 요청중")){
                System.out.println("1. 책정 관련 서류 제출하기");
                System.out.print("Choice: ");
                userChoiceValue = inputReader.readLine().trim();
                if (userChoiceValue.equals("x")) return true;
                else if (userChoiceValue.equals("1")) {
                    submitDocsForCalculateCompensation(choicedAccident,inputReader);
                }
            }
        } else {
            System.out.println("처리중인 사고가 없습니다.");
        }
        return true;
    }
    private static boolean cancelReception(Accident choicedAccident, BufferedReader inputReader) throws IOException {
        //해당 사고를 접수한 직원 반환
        AccidentReceptionTeam arEmployee = accidentReceptionTeamDao.retrieveById(choicedAccident.getReceptionEmployeeID());
        if (choicedAccident.getStatus().equals("접수 완료(출동 중)")) {
            //해당 사고를 할당받은 현장조사팀 직원 반환
            InvestigationTeam igEmployee = null;
            for(InvestigationTeam investigationEmployee : investigationTeamDao.retrieveAll()){
                if(investigationEmployee.getAccidentID()== choicedAccident.getId()){
                    igEmployee = investigationEmployee;
                    break;
                }
            }
            if(igEmployee!=null){
                if (igEmployee.getIsDispatching()) {
                    //이미 출동중이면
                    System.out.println("\n이미 현장 조사 직원이 출발하여 취소시 수수료가 발생합니다.");
                    System.out.println("1.확인");
                    System.out.println("Others.나가기");
                    String userChoiceValue = inputReader.readLine().trim();
                    if (userChoiceValue.equals("1")) {
                        chargeCancelFee(currentCustomer,choicedAccident);
                    } else return false; //다른 버튼 클릭시(접수 취소를 취소 시)
                }
            }
            showMessageForEmployee(arEmployee,"사건번호 " + choicedAccident.getId() + "의 긴급 접수가 취소되었습니다.");
            showMessageForCustomer(currentCustomer,"사건번호 " + choicedAccident.getId() + "의 긴급 접수가 취소되었습니다.");
            accidentDao.deleteById(choicedAccident.getId());//해당 사건 삭제
            assignWaitingUrgentReception();//할당 대기중인 사건 현장조사팀에 배치
        }
        else {//일반 접수 취소시
            showMessageForEmployee(arEmployee,"사건번호 " + choicedAccident.getId() + "의 접수가 취소되었습니다.");
            showMessageForCustomer(currentCustomer,"사건번호 " + choicedAccident.getId() + "의 접수가 취소되었습니다.");
            accidentDao.deleteById(choicedAccident.getId());
        }
        return true;
    }
    public static void chargeCancelFee(Customer customer,Accident accident){
        //해당 고객의 계약(Contract)에 수수료 30000원 부과
        ContractDao contractDao = new ContractDao();
        for(Contract contract: contractDao.retrieveAll()){
            if(accident.getCustomerID().equals(customer.getId()))
            if(contract.getInsuredCustomerID().equals(customer.getId())){
                contract.setFee(contract.getFee()+30000);
                contractDao.update(contract);
                return;
            }
        }
    }
    /*Employee's Functions*/
    public static boolean receiveReception(HashMap<String,String> accidentInfo, BufferedReader inputReader) throws IOException {
        String senderCustomerID = accidentInfo.get("customerID");
        //접수한 고객 반환
        InsuredCustomer senderCustomer = insuredCustomerDao.retrieveById(senderCustomerID);
        //무작위의 접수직원에게 접수를 전송
        int randomIndex = ThreadLocalRandom.current().nextInt(0, accidentReceptionTeamDao.retrieveAll().size());
        Employee arEmployee = accidentReceptionTeamDao.retrieveAll().get(randomIndex);
        //사건 생성
        Accident accident = new Accident(accidentInfo);
        accident.setReceptionEmployeeID(arEmployee.getId());
        System.out.println("******** " + arEmployee.getName() + " 사원님의 화면 ********");
        switch (accidentInfo.get("urgentLevel")) {
            case "normal":
                accident.setIsUrgent(false);
                System.out.println("(고객 id: " + senderCustomer.getId() + ") " + senderCustomer.getName() + "님의 사고가 접수되었습니다.\n<사고 정보>");
                System.out.println("사고 일시: " + accidentInfo.get("accidentDate"));
                System.out.println("사고 장소: " + accidentInfo.get("accidentPlace"));
                System.out.println("사고 유형: " + accidentInfo.get("accidentType"));
                System.out.println("사고 개요: " + accidentInfo.get("accidentOutline"));
                if (accidentInfo.get("existOfDestroyer").equals("true")) {
                    System.out.println("손괴자 이름: " + accidentInfo.get("destroyerName"));
                    System.out.println("손괴자 전화번호: " + accidentInfo.get("destroyerPhoneNum"));
                    accident.setExistOfDestroyer(true);
                    accident.setDestroyerName(accidentInfo.get("destroyerName"));
                    accident.setDestroyerPhoneNum(accidentInfo.get("destroyerPhoneNum"));
                }
                else accident.setExistOfDestroyer(false);
                System.out.println("1. 확인(접수 승인)");
                System.out.println("2. 거절");
                System.out.print("Choice: ");
                String userChoiceValue = inputReader.readLine().trim();
                System.out.println();
                if (userChoiceValue.equals("1")) {
                    showMessageForCustomer(senderCustomer, "고객님의 "+accident.getAccidentDateToString()+"에 발생한 사고의 접수가 완료되었습니다.");
                    accident.setStatus("접수 완료");
                } else if (userChoiceValue.equals("2")) {
                    System.out.print("거절 사유를 입력하세요: ");
                    String refusalReason = inputReader.readLine();
                    showMessageForCustomer(senderCustomer, "고객님의 사고 접수 요청이 거절되었습니다. 거절 사유: " + refusalReason);
                    accident.setStatus("접수 거절");
                }
                accidentDao.create(accident);
                break;
            case "urgent":
                accident.setIsUrgent(true);
                showMessageForEmployee(arEmployee,"(고객 id: " + senderCustomerID + ") " + senderCustomer.getName() + "님의 사고가 긴급 접수되었습니다.\n<사고 정보>" +
                        "\n사고 일시: " + accidentInfo.get("accidentDate")+"\n사고 장소: " + accidentInfo.get("accidentPlace")+"\n접수자 이름: " + senderCustomer.getName() +
                        "\n접수자 전화번호: " + senderCustomer.getPhoneNum());
                accident.setStatus("접수 완료(출동 대기)");
                accidentDao.create(accident);
                //Accident에는 auto pk가 할당되기에 id를 모르니까 언제,누가 접수했는지를 통해 검색해서 반환해야함
                for(Accident insertedAccident : accidentDao.retrieveAll()){
                    if(insertedAccident.getAccidentDateToString().equals(accidentInfo.get("accidentDate"))){
                        if(insertedAccident.getCustomerID().equals(senderCustomerID)){
                            boolean isAvailableDispatch = selectInvestigationEmployee(insertedAccident,senderCustomer);
                            //모든 현장조사팀이 출동중일 경우
                            if(!isAvailableDispatch) showMessageForCustomer(senderCustomer,"현재 모든 현장 조사팀 직원이 출동중입니다. 조속히 처리하겠습니다.");
                        }
                    }
                }
                break;
        }
        return true;
    }
    public static boolean selectInvestigationEmployee(Accident accident,InsuredCustomer senderCustomer){
        //현재 담당중인 긴급접수가 없는 현장조사팀에게 사건을 할당
        for(InvestigationTeam igEmployee : investigationTeamDao.retrieveAll()){
            if(igEmployee.getAccidentID()==null){
                igEmployee.setAccidentID(accident.getId());
                investigationTeamDao.update(igEmployee);
                showMessageForEmployee(igEmployee,"(고객 id: " + senderCustomer.getId() + ") " + senderCustomer.getName() + "님의 사고가 긴급 접수되었습니다.\n<사고 정보>" +
                        "\n사고 일시: " + accident.getAccidentDate()+"\n사고 장소: " + accident.getAccidentPlace()+"\n접수자 이름: " + senderCustomer.getName() +
                        "\n접수자 전화번호: " + senderCustomer.getPhoneNum());
                return true;
            }
        }
        //모든 현장조사팀이 사건들을 배정받고 있을 경우
        accident.setStatus("할당 대기중");
        accidentDao.update(accident);
        return false;
    }
    public static boolean showAccidentsForReceptionEmployee(BufferedReader inputReader) throws IOException {
        deleteExpiredAccidents();
        ArrayList<Accident> accidentsIncharge = accidentDao.retrieveByReceptionEmployeeID(currentEmployee.getId());
        for (int i = 0; i < accidentsIncharge.size(); i++) {
            Accident myAccident = accidentsIncharge.get(i);
            System.out.println((i + 1) + ". 사고번호: " + myAccident.getId());
        }
        if (accidentsIncharge.size() != 0) {
            System.out.println("몇번째 사고를 조회하시겠습니까?");
            System.out.print("Choice: ");
            String userChoiceValue = inputReader.readLine().trim();
            int choice = Integer.parseInt(userChoiceValue);
            Accident choicedAccident = accidentsIncharge.get(choice - 1);
            System.out.println("사고 일시: " + choicedAccident.getAccidentDate().toString());
            System.out.println("사고 장소: " + choicedAccident.getAccidentPlace());
            if(!choicedAccident.getIsUrgent()) {
                System.out.println("사고 유형: " + choicedAccident.getAccidentType());
                System.out.println("사고 개요: " + choicedAccident.getAccidentOutline());
            }
            if (choicedAccident.getExistOfDestroyer()) {
                System.out.println("손괴자 이름: " + choicedAccident.getDestroyerName());
                System.out.println("손괴자 전화번호: " + choicedAccident.getDestroyerPhoneNum());
            }
            System.out.println("처리 상태: " + choicedAccident.getStatus());
            System.out.println("\nx. 나가기");
            System.out.print("Choice: ");
            userChoiceValue = inputReader.readLine().trim();
            switch(userChoiceValue){
                case "x":
                    break;
            }
        }
        else {
            System.out.println("처리중인 사고가 없습니다.");
        }
        return true;
    }
    public static boolean deleteExpiredAccidents() {
        ArrayList<Accident> accidentsIncharge = accidentDao.retrieveByReceptionEmployeeID(currentEmployee.getId());
        if (accidentsIncharge.size() != 0) {//접수된 사고가 존재한다면
            Date currentDate = new Date();
            for (Accident accident : accidentsIncharge) {
                //접수 거절 상태로 5년 지난 사건 삭제
                Date accidentDate = accident.getAccidentDate();
                long diffInMillies = Math.abs(currentDate.getTime() - accidentDate.getTime());
                long diffInYears = diffInMillies / (24 * 60 * 60 * 1000 * 365L);
                if (diffInYears >= 5 && accident.getStatus().equals("접수 거절")) {
                    accidentDao.deleteById(accident.getId());
                }
            }
        }
        return true;
    }
    public static boolean showAccidentsForInvestigationEmployee(BufferedReader inputReader) throws IOException{
        Integer accidentInchargeId = ((InvestigationTeam)currentEmployee).getAccidentID();
        if(accidentInchargeId==null) {
            System.out.println("배정된 사건이 없습니다");
            return true;
        }
        else{
            Accident accidentIncharge = accidentDao.retrieveById(accidentInchargeId);
            if(accidentIncharge.getStatus().equals("접수 완료(출동 대기)")||accidentIncharge.getStatus().equals("접수 완료(출동 중)"))
                System.out.println("<배정된 사고>");
            System.out.println("사고 번호: "+accidentIncharge.getId());
            System.out.println("사고 일시: "+accidentIncharge.getAccidentDateToString());
            System.out.println("사고 장소: "+accidentIncharge.getAccidentPlace());
            Customer senderCustomer = customerDao.retrieveById(accidentIncharge.getCustomerID());
            System.out.println("피보험자 이름: "+senderCustomer.getName());
            System.out.println("피보험자 전화번호: "+senderCustomer.getPhoneNum());
            System.out.println("처리 상태: "+accidentIncharge.getStatus());
            System.out.println("\nx. 나가기");
            if(accidentIncharge.getStatus().equals("접수 완료(출동 대기)")) System.out.println("1. 출동하기");
            if(accidentIncharge.getStatus().equals("접수 완료(출동 중)")) System.out.println("1. 접수하기");
            System.out.print("Choice: ");
            String userChoiceValue = inputReader.readLine().trim();
            switch(userChoiceValue){
                case "x":
                    break;
                case "1":
                    if(accidentIncharge.getStatus().equals("접수 완료(출동 대기)")) {
                        dispatch(accidentIncharge);
                        showMessageForCustomer(senderCustomer,"현장 조사팀이 출동했습니다.");
                    }
                    else if(accidentIncharge.getStatus().equals("접수 완료(출동 중)")) sendReceptionByInvestigationEmployee(accidentIncharge, senderCustomer, inputReader);
            }
            return true;
        }

    }
    public static boolean dispatch(Accident accident){
        //해당 사건을 담당하던 현장 접수팀 직원의 isDispatching => true로 변경
        InvestigationTeam igEmployee = null;
        for(InvestigationTeam investigationEmployee : investigationTeamDao.retrieveAll()) {
            if (investigationEmployee.getAccidentID() == accident.getId()) {
                igEmployee = investigationEmployee;
                break;
            }
        }
        if(igEmployee!=null){
            igEmployee.setIsDispatching(true);
            investigationTeamDao.update(igEmployee);
        }
        accident.setStatus("접수 완료(출동 중)");
        accidentDao.update(accident);
        return true;
    }
    public static boolean sendReceptionByInvestigationEmployee(Accident accident, Customer customer, BufferedReader inputReader) throws IOException{
        System.out.println("사고 번호: "+accident.getId());
        System.out.println("사고 일시: "+accident.getAccidentDateToString());
        System.out.print("사고 장소: ");
        accident.setAccidentPlace(inputReader.readLine());
        System.out.print("사고 유형: ");
        accident.setAccidentType(inputReader.readLine());
        System.out.print("사고 개요: ");
        accident.setAccidentOutline(inputReader.readLine());
        System.out.println("<손괴자 여부 체크>\n해당 사고에 손괴자가 존재하나요?\n1.네\n2.아니요");
        System.out.print("\nChoice: ");
        String userChoiceValue = inputReader.readLine().trim();
        switch (userChoiceValue) {
            case "1":
                accident.setExistOfDestroyer(true);
                System.out.print("\n손괴자 이름을 입력하세요 (예시: 홍길동): ");
                accident.setDestroyerName(inputReader.readLine());
                System.out.print("손괴자 전화번호를 입력하세요 (예시: 01012341234): ");
                accident.setDestroyerPhoneNum(inputReader.readLine().trim());
                break;
            case "2":
                accident.setExistOfDestroyer(false);
                break;
            default:
                System.out.println("\n Please select from the menu");
                break;
        }
        accident.setIsUrgent(false);
        accident.setStatus("접수 완료");
        accidentDao.update(accident);
        ((InvestigationTeam) currentEmployee).setAccidentID(null);
        ((InvestigationTeam) currentEmployee).setIsDispatching(false);
        investigationTeamDao.update(((InvestigationTeam) currentEmployee));
        assignWaitingUrgentReception();
        return true;
    }
    public static boolean assignWaitingUrgentReception() {
        Accident newAssignedAccident = null;
        //할당 못받은 사건 검색
        for (Accident accident : accidentDao.retrieveAll()) {
            if (accident.getStatus().equals("할당 대기중")) {
                newAssignedAccident = accident;
                break;
            }
        }
        //검색된 미할당 사건을 담당 사건이 없는 현장접수팀에게 할당
        if (newAssignedAccident != null) {
            for (InvestigationTeam igEmployee : investigationTeamDao.retrieveAll()) {
                if (igEmployee.getAccidentID() == null) {
                    igEmployee.setAccidentID(newAssignedAccident.getId());
                    investigationTeamDao.update(igEmployee);
                    Customer senderCustomer = customerDao.retrieveById(newAssignedAccident.getCustomerID());
                    showMessageForEmployee(igEmployee, "(고객 id: " + senderCustomer.getId() + ") " + senderCustomer.getName() + "님의 사고가 긴급 접수되었습니다.\n<사고 정보>" +
                            "\n사고 일시: " + newAssignedAccident.getAccidentDate() + "\n사고 장소: " + newAssignedAccident.getAccidentPlace() + "\n접수자 이름: " + senderCustomer.getName() +
                            "\n접수자 전화번호: " + senderCustomer.getPhoneNum());
                    newAssignedAccident.setStatus("접수 완료(출동 대기)");
                    accidentDao.update(newAssignedAccident);
                    return true;
                }
            }
        }
        return false;
    }
}
