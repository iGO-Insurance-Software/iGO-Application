package UI;

import Contract.Contract;
import Customer.Customer;
import Dao.ContractDao;
import Dao.CustomerDao;
import Dao.InsuranceDao;
import Employee.Employee;
import Insurance.Insurance;
import util.BaseException;
import util.ErrorCode;

import java.io.BufferedReader;
import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.sql.Date;

import static UI.Main.currentEmployee;
import static UI.Main.*;

public class SalesMain {

    private Employee employee;
    private ContractDao contractDao;
    private CustomerDao customerDao;
    public SalesMain(Employee currentEmployee) {
        this.employee = currentEmployee;
        this.contractDao = new ContractDao();
        this.customerDao = new CustomerDao();
    }

    public boolean checkCustomerRequest(BufferedReader inputReader) throws IOException {
        boolean isRemain = true;
        String userChoiceValue;
        while (isRemain) {
            System.out.println("1. 가입 신청 현황 확인");
            System.out.println("2. 고객 정보 검색");
            System.out.println("x. 로그아웃");
            System.out.print("\nChoice: ");
            userChoiceValue=inputReader.readLine().trim();
            switch (userChoiceValue){
                // 1. 보험 가입 신청 현황 확인
                case "1":
                    ArrayList<Contract> waitingContract = contractDao.retrieveAllCustomerWait();
                    for (int i = 0; i < waitingContract.size(); i ++)  {
                        System.out.println("계약 id: " + waitingContract.get(i).getId());
                        System.out.println("계약자 id: " + waitingContract.get(i).getContractorID());
                        System.out.println("보험 id: " + waitingContract.get(i).getInsuranceID());
                        System.out.println("피보험자 id: " + waitingContract.get(i).getInsuredCustomerID());
                        System.out.println("담당직원 id: " + waitingContract.get(i).getEmployeeID());
                        System.out.println("수수료: " + waitingContract.get(i).getFee());
                        System.out.println("보험료 : " + waitingContract.get(i).getPremium());
//                        System.out.println("요율 : " + waitingContract.get(i).getPaymentRate());
                        System.out.println("계약 기간 : " + waitingContract.get(i).getPeriod());
                        System.out.println("계약 체결 날짜 : " + waitingContract.get(i).getSignedDate());
                        System.out.println("계약 만료 날짜: " + waitingContract.get(i).getExpirationDate());
//                        System.out.println("보험료 납부 간격: " + waitingContract.get(i).getPaymentTerm());
                        System.out.println("손해율: " + waitingContract.get(i).getLossRatio());
                        System.out.println("심사 상태 : " + waitingContract.get(i).getUnderwritingState());
                        System.out.println("계약 거부 이유 : " + waitingContract.get(i).getRejectionReasons());
                        System.out.println("==================================");
                    }
                    System.out.println("1. 심사 신청 보내기 2. 심사 결과 확인 3. 결과 전송하기 x. 뒤로가기");
                    System.out.print("\nChoice: ");
                    userChoiceValue=inputReader.readLine().trim();
                    switch (userChoiceValue){
                        case "1":
                            System.out.println("심사 요청을 보내고자 하는 계약의 id를 입력하세요");
                            System.out.print("\nId: ");
                            userChoiceValue=inputReader.readLine().trim();
                            int id = Integer.parseInt(userChoiceValue);
                            Contract contractToSend = contractDao.retrieveById(id);
                            if (contractToSend == null) {
                                System.out.println("해당 ID의 계약이 없습니다.");
                                break;
                            }
                            while(true) {
                                System.out.print("해당 계약의 피보험자 id를 입력하세요: ");
                                String insuredCustomerId = inputReader.readLine().trim();
                                //Exception - 존재하지 않는 고객 id일경우
                                try {
                                    Customer customer = customerDao.retrieveById(insuredCustomerId);
                                    if (customer == null) {
                                        throw new BaseException(ErrorCode.NOT_EXIST_ID);
                                    }
                                    else {
                                        //존재할 경우
                                        contractToSend.setInsuredCustomerID(customer.getId());
                                        break;
                                    }
                                } catch (BaseException e) {
                                    e.getMessage();
                                }
                            }
                            System.out.print("해당 계약의 계약기간을 입력하세요: ");
                            String contractPeriod = inputReader.readLine().trim();
                            contractToSend.setPeriod(Integer.parseInt(contractPeriod));
                            // 여기 임의로 수정
                            contractToSend.setPaymentTerm(30);
                            contractToSend.setEmployeeID(currentEmployee.getId());
                            contractToSend.setUnderwritingState("대기");
                            contractDao.update(contractToSend);
                            System.out.println("심사 요청이 완료되었습니다.");
                            break;
                        case "2":
                            ArrayList<Contract> finishedContract = contractDao.retrieveAllCompleteJudge();
                            if(finishedContract.size()==0){
                                System.out.println("심사완료된 계약이 없습니다.");
                                break;
                            }
                            for (int i = 0; i < finishedContract.size(); i ++)  {
                                System.out.println("계약 id : " + finishedContract.get(i).getId());
                                System.out.println("계약자 id : " + finishedContract.get(i).getContractorID());
                                System.out.println("보험 id : " + finishedContract.get(i).getInsuranceID());
                                System.out.println("피보험자 id : " + finishedContract.get(i).getInsuredCustomerID());
                                System.out.println("담당직원 id : " + finishedContract.get(i).getEmployeeID());
                                System.out.println("수수료 : " + finishedContract.get(i).getFee());
                                System.out.println("보험료 : " + finishedContract.get(i).getPremium());
                                System.out.println("요율 : " + finishedContract.get(i).getPaymentRate());
                                System.out.println("계약 기간 : " + finishedContract.get(i).getPeriod());
                                System.out.println("계약 체결 날짜 : " + finishedContract.get(i).getSignedDate());
                                System.out.println("계약 만료 날짜 : " + finishedContract.get(i).getExpirationDate());
                                System.out.println("보험료 납부 간격 : " + finishedContract.get(i).getPaymentTerm());
                                System.out.println("손해율 : " + finishedContract.get(i).getLossRatio());
                                System.out.println("심사 상태 : " + finishedContract.get(i).getUnderwritingState());
                                System.out.println("계약 거부 이유 : " + finishedContract.get(i).getRejectionReasons());
                                System.out.println("==================================");
                            }
                            break;
                        case "3":
                            System.out.println("결과를 전송하고자 하는 계약의 Id를 입력하세요.");
                            System.out.print("\nId: ");
                            userChoiceValue=inputReader.readLine().trim();
                            int targetContractId = Integer.parseInt(userChoiceValue);
                            Contract judgedContractToSend = contractDao.retrieveById(targetContractId);
                            switch(judgedContractToSend.getUnderwritingState()){
                                case "승인":
                                    //현재 시각을 사고 발생 일자로 자동 기입
                                    Date today = new Date(System.currentTimeMillis());
                                    String date = today.toString();
                                    System.out.println(date);
                                    judgedContractToSend.setSignedDate(date);
                                    System.out.print("해당 계약의 만료 날짜를 입력하세요: ");
                                    String contractExpirationDate = inputReader.readLine().trim();
                                    judgedContractToSend.setExpirationDate(contractExpirationDate);
                                    Customer customerTosend = customerDao.retrieveById(judgedContractToSend.getContractorID());
                                    Insurance insuranceContent = insuranceDao.retrieveById(judgedContractToSend.getInsuranceID());
                                    String insuranceName = insuranceContent.getName();
                                    showMessageForCustomer(customerTosend, "고객님의 "+insuranceName+" 가입 신청의 결과가 도착했습니다." +
                                            "\n결과: "+judgedContractToSend.getUnderwritingState());
                                    break;
                                case "재심사 가능":
                                    Customer customerTosend1 = customerDao.retrieveById(judgedContractToSend.getContractorID());
                                    Insurance insuranceContent1 = insuranceDao.retrieveById(judgedContractToSend.getInsuranceID());
                                    String insuranceName1 = insuranceContent1.getName();
                                    showMessageForCustomer(customerTosend1, "고객님의 "+insuranceName1+" 가입 신청이 재심사에 들어가게 되어 " +
                                            "추후에 결과를 알려드리겠습니다.");
                                    break;
                                case "재심사 거절":
                                    Customer customerTosend2 = customerDao.retrieveById(judgedContractToSend.getContractorID());
                                    Insurance insuranceContent2 = insuranceDao.retrieveById(judgedContractToSend.getInsuranceID());
                                    String insuranceName2 = insuranceContent2.getName();
                                    showMessageForCustomer(customerTosend2, "고객님의 "+insuranceName2+" 가입 신청의 결과가 도착했습니다." +
                                            "\n결과: "+judgedContractToSend.getUnderwritingState());
                                    break;
                            }
                            contractDao.update(judgedContractToSend);

                            break;
                        case "x":
                            isRemain = false;
                            break;
                    }
                    break;
                // 2. 고객 정보 검색
                case "2":
                    while(isRemain) {
                        System.out.println("1. 나이 검색 2. 성별 검색 x. 뒤로가기");
                        System.out.print("\nChoice: ");
                        userChoiceValue = inputReader.readLine().trim();
                        switch (userChoiceValue) {
                            case "1":
                                System.out.println("나이를 입력해 주세요");
                                System.out.print("\nAge: ");
                                userChoiceValue = inputReader.readLine().trim();
                                int age = Integer.parseInt(userChoiceValue);
                                ArrayList<Customer> ageTargetCustomer = customerDao.retrieveAllByAge(age);
                                if (ageTargetCustomer.size() == 0) {
                                    System.out.println("해당 나이의 고객이 없습니다.");
                                    break;
                                }
                                for (int i = 0; i < ageTargetCustomer.size(); i++) {
                                    System.out.println(ageTargetCustomer.get(i).getName());
                                    System.out.println(ageTargetCustomer.get(i).getPhoneNum());
                                }
                                break;
                            case "2":
                                System.out.println("성별을 입력해 주세요 ex) \"남\" \"여\"");
                                System.out.print("\nGender: ");
                                userChoiceValue = inputReader.readLine().trim();
                                String gender = userChoiceValue;
                                ArrayList<Customer> genderTargetCustomer = customerDao.retrieveAllByGender(gender);
                                if (genderTargetCustomer.size() == 0) {
                                    System.out.println("해당 성별의 고객이 없습니다.");
                                    break;
                                }
                                for (int i = 0; i < genderTargetCustomer.size(); i++) {
                                    System.out.println(genderTargetCustomer.get(i).getName());
                                    System.out.println(genderTargetCustomer.get(i).getPhoneNum());
                                    System.out.println("------------------------");
                                }
                                break;
                            case "x":
                                isRemain = false;
                                break;
                        }
                    }
                    break;
                case "x":
                    return false;
            }
        }
        return true;
    }
}
