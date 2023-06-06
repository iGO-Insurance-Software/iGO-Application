package UI;

import Contract.Contract;
import Contract.Reinsurance;
import Customer.Customer;
import Dao.ContractDao;
import Dao.CustomerDao;
import Dao.InsuranceDao;
import Dao.ReinsuranceDao;
import Employee.Employee;
import Employee.UWTeam;
import util.BaseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static UI.Main.currentEmployee;

public class SalesMain {

    private Employee employee;
    private ContractDao contractDao;
    private CustomerDao customerDao;
    public SalesMain(Employee currentEmployee) {
        this.employee = currentEmployee;
        this.contractDao = new ContractDao();
        this.customerDao = new CustomerDao();
    }

    public boolean showEmployeeMenu(BufferedReader inputReader) throws IOException {
        boolean isRemain = true;
        String userChoiceValue;
        while (isRemain) {
            System.out.println("1. 가입 신청 현황 확인");
            System.out.println("2. 고객 정보 검색");
            System.out.print("\nChoice: ");
            userChoiceValue=inputReader.readLine().trim();

//            // E1(7초동안 화면 못 불러왔을 때) test code////////////////////
//            int responseTime = (int) Math.random() * 10 +1;
//            if(responseTime >= 7) {
//                System.out.println("화면을 불러오지 못하고 있습니다. 같은 현상이 반복되면 고객센터에 신고해 주십시오.\n1. 확인");
//                System.out.print("\nChoice: ");
//                inputReader.readLine().trim();
//                continue;
//            }
//            ////////////////////////////////////////////////////
            switch (userChoiceValue){
                // 1. 보험 가입 신청 현황 확인
                case "1":
                    ArrayList<Contract> waitingContract = contractDao.retrieveAllByState("가입신청");
                    for (int i = 0; i < waitingContract.size(); i ++)  {
                        System.out.println(i);
                        System.out.println(waitingContract.get(i).getId());
                        System.out.println(waitingContract.get(i).getContractorID());
                        System.out.println(waitingContract.get(i).getInsuranceID());
                        System.out.println(waitingContract.get(i).getInsuredCustomerID());
                        System.out.println(waitingContract.get(i).getEmployeeID());
                        System.out.println(waitingContract.get(i).getFee());
                        System.out.println(waitingContract.get(i).getPremium());
                        System.out.println(waitingContract.get(i).getPaymentRate());
                        System.out.println(waitingContract.get(i).getPeriod());
                        System.out.println(waitingContract.get(i).getSignedDate());
                        System.out.println(waitingContract.get(i).getExpirationDate());
                        System.out.println(waitingContract.get(i).getPaymentTerm());
                        System.out.println(waitingContract.get(i).getLossRatio());
                        System.out.println(waitingContract.get(i).getUnderwritingState());
                        System.out.println(waitingContract.get(i).getRejectionReasons());
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
                            contractToSend.setEmployeeID(currentEmployee.getId());
                            contractToSend.setUnderwritingState("심사요청");
                            System.out.println("심사 요청이 완료되었습니다.");
                            break;
                        case "2":
                            ArrayList<Contract> finishedContract = contractDao.retrieveAllByState("가입신청");
                            for (int i = 0; i < finishedContract.size(); i ++)  {
                                System.out.println(i);
                                System.out.println(finishedContract.get(i).getId());
                                System.out.println(finishedContract.get(i).getContractorID());
                                System.out.println(finishedContract.get(i).getInsuranceID());
                                System.out.println(finishedContract.get(i).getInsuredCustomerID());
                                System.out.println(finishedContract.get(i).getEmployeeID());
                                System.out.println(finishedContract.get(i).getFee());
                                System.out.println(finishedContract.get(i).getPremium());
                                System.out.println(finishedContract.get(i).getPaymentRate());
                                System.out.println(finishedContract.get(i).getPeriod());
                                System.out.println(finishedContract.get(i).getSignedDate());
                                System.out.println(finishedContract.get(i).getExpirationDate());
                                System.out.println(finishedContract.get(i).getPaymentTerm());
                                System.out.println(finishedContract.get(i).getLossRatio());
                                System.out.println(finishedContract.get(i).getUnderwritingState());
                                System.out.println(finishedContract.get(i).getRejectionReasons());
                            }
                            break;
                        case "x":
                            isRemain = false;
                            break;
                    }
                    break;
                // 2. 고객 정보 검색
                case "2":
                    System.out.println("1. 나이 검색 2. 성별 검색 x. 뒤로가기");
                    System.out.print("\nChoice: ");
                    userChoiceValue=inputReader.readLine().trim();
                    switch (userChoiceValue){
                        case "1":
                            System.out.println("나이를 입력해 주세요");
                            System.out.print("\nAge: ");
                            userChoiceValue=inputReader.readLine().trim();
                            int age = Integer.parseInt(userChoiceValue);
                            Customer ageTargetCustomer = customerDao.retrieveByAge(age);
                            if (ageTargetCustomer == null) {
                                System.out.println("해당 나이의 고객이 없습니다.");
                                break;
                            }
                            System.out.println(ageTargetCustomer.getName());
                            System.out.println(ageTargetCustomer.getPhoneNum());
                            break;
                        case "2":
                            System.out.println("성별을 입력해 주세요 ex) \"남\" \"여\"");
                            System.out.print("\nGender: ");
                            userChoiceValue=inputReader.readLine().trim();
                            String gender = userChoiceValue;
                            ArrayList<Customer> genderTargetCustomer = customerDao.retrieveAllByGender(gender);
                            if (genderTargetCustomer.size() == 0)   {
                                System.out.println("해당 성별의 고객이 없습니다.");
                                break;
                            }
                            for (int i = 0; i < genderTargetCustomer.size(); i++)    {
                                System.out.println(genderTargetCustomer.get(i).getName());
                                System.out.println(genderTargetCustomer.get(i).getPhoneNum());
                        }
                            break;
                        case "x":
                            isRemain = false;
                            break;
                    }
                case "x":
                    return false;
            }
        }
        return true;
    }
}
