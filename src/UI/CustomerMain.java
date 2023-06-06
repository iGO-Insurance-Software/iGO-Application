package UI;

import Contract.Contract;
import Customer.Customer;
import Dao.ContractDao;
import Dao.CustomerDao;
import Dao.EmployeeDao;
import Dao.InsuranceDao;
import Insurance.Insurance;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import static UI.Main.currentCustomer;

public class CustomerMain {
    private Customer customer;
    private ContractDao contractDao;
    private CustomerDao customerDao;
    private EmployeeDao employeeDao;
    private InsuranceDao insuranceDao;
    public CustomerMain(Customer currentCustomer) {
        this.customer = currentCustomer;
        this.contractDao = new ContractDao();
        this.customerDao = new CustomerDao();
        this.employeeDao = new EmployeeDao();
        this.insuranceDao = new InsuranceDao();
    }

    public boolean showCustomerMenu(BufferedReader inputReader) throws IOException {
        boolean isRemain = true;
        String userChoiceValue;
        while (isRemain) {
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
                // 1. 보험 메뉴 확인
                case "1":
                    ArrayList<Insurance> insuranceMenu = insuranceDao.retrieveAll();
                    for (int i = 0; i < insuranceMenu.size(); i ++)  {
                        System.out.println(i+1);
                        System.out.println(insuranceMenu.get(i).getId());
                        System.out.println(insuranceMenu.get(i).getDescription());
                        System.out.println(insuranceMenu.get(i).getName());
                        System.out.println(insuranceMenu.get(i).getPrice());
                        System.out.println(insuranceMenu.get(i).getDetailedCategory());
                    }
                    System.out.println("1. 가입 신청하기 x. 뒤로가기");
                    System.out.print("\nChoice: ");
                    userChoiceValue=inputReader.readLine().trim();
                    switch (userChoiceValue){
                        case "1":
                            System.out.println("가입하고자 하는 보험의 id를 입력하세요");
                            System.out.print("\nId: ");
                            userChoiceValue=inputReader.readLine().trim();
                            int id = Integer.parseInt(userChoiceValue);
                            Insurance selectedInsurance = insuranceDao.retrieveById(id);
                            if (selectedInsurance == null) {
                                System.out.println("해당 ID의 상품이 없습니다.");
                                break;
                            }
                            Contract createContract = new Contract();
                            createContract.setContractorID(currentCustomer.getId());
                            createContract.setInsuranceID(selectedInsurance.getId());
                            createContract.setInsuredCustomerID(null);
                            createContract.setEmployeeID(null);
                            createContract.setPremium(selectedInsurance.getPrice());
                            createContract.setUnderwritingState("가입신청");
                            contractDao.create(createContract);
                            break;
                        case "2":
//                            ArrayList<Contract> finishedContract = contractDao.retrieveAllByState("가입신청");
//                            for (int i = 0; i < finishedContract.size(); i ++)  {
//                                System.out.println(i);
//                                System.out.println(finishedContract.get(i).getId());
//                                System.out.println(finishedContract.get(i).getContractorID());
//                                System.out.println(finishedContract.get(i).getInsuranceID());
//                                System.out.println(finishedContract.get(i).getInsuredCustomerID());
//                                System.out.println(finishedContract.get(i).getEmployeeID());
//                                System.out.println(finishedContract.get(i).getFee());
//                                System.out.println(finishedContract.get(i).getPremium());
//                                System.out.println(finishedContract.get(i).getPaymentRate());
//                                System.out.println(finishedContract.get(i).getPeriod());
//                                System.out.println(finishedContract.get(i).getSignedDate());
//                                System.out.println(finishedContract.get(i).getExpirationDate());
//                                System.out.println(finishedContract.get(i).getPaymentTerm());
//                                System.out.println(finishedContract.get(i).getLossRatio());
//                                System.out.println(finishedContract.get(i).getUnderwritingState());
//                                System.out.println(finishedContract.get(i).getRejectionReasons());
//                            }
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
                            ArrayList<Customer> ageTargetCustomer = customerDao.retrieveAllByAge(age);
                            if (ageTargetCustomer.size() == 0)   {
                                System.out.println("해당 나이의 고객이 없습니다.");
                                break;
                            }
                            for (int i = 0; i < ageTargetCustomer.size(); i++)    {
                                System.out.println(ageTargetCustomer.get(i).getName());
                                System.out.println(ageTargetCustomer.get(i).getPhoneNum());
                            }
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
                    isRemain = false;
                    break;
            }
        }
        return true;
    }
    public boolean showInsuredCustomerMenu(BufferedReader inputReader) throws IOException {
        return true;
    }
}
