package UI;

import Contract.Contract;
import Customer.Customer;
import Dao.*;
import Employee.Employee;
import Insurance.Insurance;
import Survey.Survey;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import static UI.Main.*;

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
            if (isAdClosed == false) {showAdForCustomer(inputReader); }
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
                            System.out.println("개인정보 수집 및 이용동의서에 동의하십니까? (Y/N)");
                            System.out.print("\nChoice: ");
                            String choice = inputReader.readLine().trim();
                            if (choice.equals("Y") || choice.equals("y"))   {
                                System.out.println("개인정보 수집 및 이용동의서에 동의해주셨습니다.");
                                System.out.println("계약자의 이름을 입력해주세요.");
                                System.out.print("\n이름: ");
                                userChoiceValue=inputReader.readLine().trim();
                                String name = userChoiceValue;
                                if (!name.equals(currentCustomer.getName())) {
                                    System.out.println("고객 정보 입력에 오류가 있습니다. 고객 정보를 정확히 입력 후 제출해 주십시오."); break; }
                                System.out.println("계약자의 주민등록번호를 입력해주세요.");
                                System.out.print("\n주민등록번호: ");
                                userChoiceValue=inputReader.readLine().trim();
                                String rrn = userChoiceValue;
                                if (!rrn.equals(currentCustomer.getRrn())) {
                                    System.out.println("고객 정보 입력에 오류가 있습니다. 고객 정보를 정확히 입력 후 제출해 주십시오."); break; }
                                System.out.println("계약자의 전화번호를 입력해주세요.");
                                System.out.print("\n전화번호: ");
                                userChoiceValue=inputReader.readLine().trim();
                                String phoneNum = userChoiceValue;
                                if (!phoneNum.equals(currentCustomer.getPhoneNum())) {
                                    System.out.println("고객 정보 입력에 오류가 있습니다. 고객 정보를 정확히 입력 후 제출해 주십시오."); break; }
                                System.out.println("계약하고자 하는 보험의 Id를 입력해주세요.");
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
                                System.out.println("보험 가입 신청이 완료되었습니다. 고객 정보 및 서류 검토 이후 SMS를 통해 결과를 전달하겠습니다.");
                                System.out.println("간단한 설문조사에 참여해 주시겠습니까? (Y/N)");
                                System.out.print("\nChoice: ");
                                String surveyChoice = inputReader.readLine().trim();
                                if (surveyChoice.equals("Y") || surveyChoice.equals("y"))   {
                                    ArrayList<Survey> survey = surveyDao.retrieveAll();
                                    System.out.println("나이를 선택해주십시오. (x. 설문조사 그만하기)");
                                    for (int i = 0; i < 6; i ++)  {
                                        System.out.println(survey.get(i).getCustomerChoice() + ". " + survey.get(i).getSurveyQuestionContent());
                                    }
                                    System.out.print("\nChoice: ");
                                    String ageChoice = inputReader.readLine().trim();
                                    if (ageChoice.equals("x")) { System.out.println("보험에 가입해 주셔서 감사합니다."); break; }
                                    System.out.println("==================================");
                                    int ageSelected = Integer.parseInt(ageChoice);
                                    Survey firstSurvey = surveyDao.retrieveById(ageSelected);
                                    firstSurvey.setSelectedCount(firstSurvey.getSelectedCount()+1);
                                    surveyDao.update(firstSurvey);
                                    System.out.println("어떤 방식으로 자사 보험에 대해 알게 되었는지 선택해주십시오. (x. 설문조사 그만하기)");
                                    for (int i = 6; i < 10; i ++)  {
                                        System.out.println(survey.get(i).getCustomerChoice() + ". " + survey.get(i).getSurveyQuestionContent());
                                    }
                                    System.out.print("\nChoice: ");
                                    String howChoice = inputReader.readLine().trim();
                                    if (howChoice.equals("x")) { System.out.println("보험에 가입해 주셔서 감사합니다."); break; }
                                    System.out.println("==================================");
                                    int howSelected = Integer.parseInt(howChoice);
                                    Survey secondSurvey = surveyDao.retrieveById(6+howSelected);
                                    secondSurvey.setSelectedCount(secondSurvey.getSelectedCount()+1);
                                    surveyDao.update(secondSurvey);
                                    System.out.println("어떤 목적으로 자사 보험에 가입 하였는지 선택해주십시오. (x. 설문조사 그만하기)");
                                    for (int i = 10; i < 14; i ++)  {
                                        System.out.println(survey.get(i).getCustomerChoice() + ". " + survey.get(i).getSurveyQuestionContent());
                                    }
                                    System.out.print("\nChoice: ");
                                    String forChoice = inputReader.readLine().trim();
                                    if (forChoice.equals("x")) { System.out.println("보험에 가입해 주셔서 감사합니다."); break; }
                                    System.out.println("==================================");
                                    int forSelected = Integer.parseInt(forChoice);
                                    Survey thirdSurvey = surveyDao.retrieveById(10+forSelected);
                                    thirdSurvey.setSelectedCount(thirdSurvey.getSelectedCount()+1);
                                    surveyDao.update(thirdSurvey);
                                    System.out.println("설문에 참여해주셔서 감사합니다.");
                                    break;
                                }
                                else if (surveyChoice.equals("N") || surveyChoice.equals("n"))  {
                                    System.out.println("보험에 가입해주셔서 감사합니다.");
                                    break;
                                }
                                break;
                            }
                            else if (choice.equals("N") || choice.equals("n"))  {
                                System.out.println("개인정보 수집 및 이용동의에 거절하셨습니다.");
                                break;
                            }
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
                System.out.println("개인정보 수집 및 이용동의서에 동의하십니까? (Y/N)");
                System.out.print("\nChoice: ");
                String choice = inputReader.readLine().trim();
                if (choice.equals("Y") || choice.equals("y"))   {
                    System.out.println("개인정보 수집 및 이용동의서에 동의해주셨습니다.");
                    System.out.println("계약자의 이름을 입력해주세요.");
                    System.out.print("\n이름: ");
                    userChoiceValue=inputReader.readLine().trim();
                    String name = userChoiceValue;
                    if (!name.equals(currentCustomer.getName())) {
                        System.out.println("고객 정보 입력에 오류가 있습니다. 고객 정보를 정확히 입력 후 제출해 주십시오."); break; }
                    System.out.println("계약자의 주민등록번호를 입력해주세요.");
                    System.out.print("\n주민등록번호: ");
                    userChoiceValue=inputReader.readLine().trim();
                    String rrn = userChoiceValue;
                    if (!rrn.equals(currentCustomer.getRrn())) {
                        System.out.println("고객 정보 입력에 오류가 있습니다. 고객 정보를 정확히 입력 후 제출해 주십시오."); break; }
                    System.out.println("계약자의 전화번호를 입력해주세요.");
                    System.out.print("\n전화번호: ");
                    userChoiceValue=inputReader.readLine().trim();
                    String phoneNum = userChoiceValue;
                    if (!phoneNum.equals(currentCustomer.getPhoneNum())) {
                        System.out.println("고객 정보 입력에 오류가 있습니다. 고객 정보를 정확히 입력 후 제출해 주십시오."); break; }
                    System.out.println("계약하고자 하는 보험의 Id를 입력해주세요.");
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
                    System.out.println("보험 가입 신청이 완료되었습니다. 고객 정보 및 서류 검토 이후 SMS를 통해 결과를 전달하겠습니다.");
                    System.out.println("간단한 설문조사에 참여해 주시겠습니까? (Y/N)");
                    System.out.print("\nChoice: ");
                    String surveyChoice = inputReader.readLine().trim();
                    if (surveyChoice.equals("Y") || surveyChoice.equals("y"))   {
                        ArrayList<Survey> survey = surveyDao.retrieveAll();
                        System.out.println("나이를 선택해주십시오. (x. 설문조사 그만하기)");
                        for (int i = 0; i < 6; i ++)  {
                            System.out.println(survey.get(i).getCustomerChoice() + ". " + survey.get(i).getSurveyQuestionContent());
                        }
                        System.out.print("\nChoice: ");
                        String ageChoice = inputReader.readLine().trim();
                        if (ageChoice.equals("x")) { System.out.println("보험에 가입해 주셔서 감사합니다."); break; }
                        System.out.println("==================================");
                        int ageSelected = Integer.parseInt(ageChoice);
                        Survey firstSurvey = surveyDao.retrieveById(ageSelected);
                        firstSurvey.setSelectedCount(firstSurvey.getSelectedCount()+1);
                        surveyDao.update(firstSurvey);
                        System.out.println("어떤 방식으로 자사 보험에 대해 알게 되었는지 선택해주십시오. (x. 설문조사 그만하기)");
                        for (int i = 6; i < 10; i ++)  {
                            System.out.println(survey.get(i).getCustomerChoice() + ". " + survey.get(i).getSurveyQuestionContent());
                        }
                        System.out.print("\nChoice: ");
                        String howChoice = inputReader.readLine().trim();
                        if (howChoice.equals("x")) { System.out.println("보험에 가입해 주셔서 감사합니다."); break; }
                        System.out.println("==================================");
                        int howSelected = Integer.parseInt(howChoice);
                        Survey secondSurvey = surveyDao.retrieveById(6+howSelected);
                        secondSurvey.setSelectedCount(secondSurvey.getSelectedCount()+1);
                        surveyDao.update(secondSurvey);
                        System.out.println("어떤 목적으로 자사 보험에 가입 하였는지 선택해주십시오. (x. 설문조사 그만하기)");
                        for (int i = 10; i < 14; i ++)  {
                            System.out.println(survey.get(i).getCustomerChoice() + ". " + survey.get(i).getSurveyQuestionContent());
                        }
                        System.out.print("\nChoice: ");
                        String forChoice = inputReader.readLine().trim();
                        if (forChoice.equals("x")) { System.out.println("보험에 가입해 주셔서 감사합니다."); break; }
                        System.out.println("==================================");
                        int forSelected = Integer.parseInt(forChoice);
                        Survey thirdSurvey = surveyDao.retrieveById(10+forSelected);
                        thirdSurvey.setSelectedCount(thirdSurvey.getSelectedCount()+1);
                        surveyDao.update(thirdSurvey);
                        System.out.println("설문에 참여해주셔서 감사합니다.");
                        break;
                    }
                    else if (surveyChoice.equals("N") || surveyChoice.equals("n"))  {
                        System.out.println("보험에 가입해주셔서 감사합니다.");
                        break;
                    }
                    break;
                }
                else if (choice.equals("N") || choice.equals("n")) {
                    System.out.println("개인정보 수집 및 이용동의에 거절하셨습니다.");
                    break;
                }
            case "x":
                break;
        }
        return true;
    }
}
