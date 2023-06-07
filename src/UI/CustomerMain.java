package UI;

import Contract.Contract;
import Customer.Customer;
import Dao.*;
import Employee.Employee;
import Insurance.Insurance;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import static UI.Main.accidentReceptionTeamDao;
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
            System.out.println("1. 보험 상품 확인");
            System.out.println("x. 로그아웃");
            System.out.print("\nChoice: ");
            userChoiceValue=inputReader.readLine().trim();
            switch (userChoiceValue){
                // 1. 보험 메뉴 확인
                case "1":
                    ArrayList<Insurance> insuranceMenu = insuranceDao.retrieveAll();
                    if(insuranceMenu.size()==0) {
                        System.out.println("등록된 보험 상품이 존재하지 없습니다.");
                        return false;
                    }
                    for (int i = 0; i < insuranceMenu.size(); i ++)  {
                        System.out.println("보험 Id: " + insuranceMenu.get(i).getId());
                        System.out.println("보험 설명: " + insuranceMenu.get(i).getDescription());
                        System.out.println("보험 이름: " + insuranceMenu.get(i).getName());
                        System.out.println("보험 가격: " + insuranceMenu.get(i).getPrice());
                        System.out.println("보험 종류: " + insuranceMenu.get(i).getDetailedCategory());
                        System.out.println("----------------------------------");
                    }
                    System.out.println("1. 가입 신청하기 x. 뒤로가기");
                    System.out.print("\nChoice: ");
                    userChoiceValue=inputReader.readLine().trim();
                    switch (userChoiceValue){
                        case "1":
                            System.out.println("가입하고자 하는 보험의 Id를 입력하세요");
                            System.out.print("\nId: ");
                            userChoiceValue=inputReader.readLine().trim();

                            String id = userChoiceValue;
                            Insurance selectedInsurance = insuranceDao.retrieveById(id);

                            if (selectedInsurance == null) {
                                System.out.println("해당 Id의 상품이 없습니다.");
                                break;
                            }
                            Contract createContract = new Contract();
                            createContract.setContractorID(currentCustomer.getId());
                            createContract.setInsuranceID(selectedInsurance.getId());
//                            createContract.setInsuredCustomerID(currentCustomer.getId());
                            SalesTeamDao salesTeamDao = new SalesTeamDao();
                            int randomIndex = ThreadLocalRandom.current().nextInt(0, salesTeamDao.retrieveAll().size());
                            String salesEmployee = salesTeamDao.retrieveAll().get(randomIndex).getId();
                            createContract.setEmployeeID(salesEmployee);
                            createContract.setPremium(selectedInsurance.getPrice());
                            createContract.setUnderwritingState("가입신청");
                            contractDao.create(createContract);
                            break;
                        case "x":
                            break;
                    }
                    break;
                case "x":
                    return false;
            }
        }
        return true;
    }
    public boolean showInsurancesForInsuredCustomer(BufferedReader inputReader) throws IOException {
        ArrayList<Insurance> insuranceMenu = insuranceDao.retrieveAll();
        if(insuranceMenu.size()==0) {
            System.out.println("등록된 보험 상품이 존재하지 없습니다.");
            return false;
        }
        for (int i = 0; i < insuranceMenu.size(); i ++)  {
            System.out.println("보험 Id: " + insuranceMenu.get(i).getId());
            System.out.println("보험 설명: " + insuranceMenu.get(i).getDescription());
            System.out.println("보험 이름: " + insuranceMenu.get(i).getName());
            System.out.println("보험 가격: " + insuranceMenu.get(i).getPrice());
            System.out.println("보험 종류: " + insuranceMenu.get(i).getDetailedCategory());
            System.out.println("----------------------------------");
        }
        System.out.println("1. 가입 신청하기 x. 뒤로가기");
        System.out.print("\nChoice: ");
        String userChoiceValue=inputReader.readLine().trim();
        switch (userChoiceValue) {
            case "1":
                System.out.println("가입하고자 하는 보험의 Id를 입력하세요");
                System.out.print("\nId: ");
                userChoiceValue = inputReader.readLine().trim();
                String id = userChoiceValue;
                Insurance selectedInsurance = insuranceDao.retrieveById(id);
                if (selectedInsurance == null) {
                    System.out.println("해당 Id의 상품이 없습니다.");
                    break;
                }
                Contract createContract = new Contract();
                createContract.setContractorID(currentCustomer.getId());
                createContract.setInsuranceID(selectedInsurance.getId());
                createContract.setInsuredCustomerID(currentCustomer.getId());
                SalesTeamDao salesTeamDao = new SalesTeamDao();
                int randomIndex = ThreadLocalRandom.current().nextInt(0, salesTeamDao.retrieveAll().size());
                String salesEmployee = salesTeamDao.retrieveAll().get(randomIndex).getId();
                createContract.setEmployeeID(salesEmployee);
                createContract.setPremium(selectedInsurance.getPrice());
                createContract.setUnderwritingState("가입신청");
                contractDao.create(createContract);
                break;
            case "x":
                break;
        }
        return true;
    }
}
