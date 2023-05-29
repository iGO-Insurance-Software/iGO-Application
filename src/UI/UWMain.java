package UI;

import Contract.Contract;
import Contract.Reinsurance;
import Customer.Customer;
import Dao.ContractDao;
import Dao.CustomerDao;
import Dao.ReinsuranceDao;
import Employee.Employee;
import Employee.UWTeam;
import Insurance.Insurance;
import util.BaseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

public class UWMain {

    private Employee employee;
    private ContractDao contractDao;
    private CustomerDao customerDao;
    // private InsuranceDao insuranceDao;
    private ReinsuranceDao reinsuranceDao;
    public UWMain(Employee currentEmployee) {
        this.employee = currentEmployee;
        this.contractDao = new ContractDao();
        this.customerDao = new CustomerDao();
        //this.insuranceDao = new InsuranceDao();
        this.reinsuranceDao = new ReinsuranceDao();
    }


    public boolean showEmployeeMenu(BufferedReader inputReader) throws IOException {
        boolean isRemain = true;
        String userChoiceValue;
        while (isRemain) {
            System.out.println("\n************************ UW Team MENU ************************");
            System.out.println("x. 로그아웃하기 logout");
            System.out.println("1. 고객 정보 요청하기 require customer info");
            System.out.println("2. 손해율 분석하기 calculate loss ratio");
            System.out.print("\nChoice: ");
            userChoiceValue=inputReader.readLine().trim();

            // E1(7초동안 화면 못 불러왔을 때) test code////////////////////
            int responseTime = (int) Math.random() * 10 +1;
            if(responseTime >= 7) {
                System.out.println("화면을 불러오지 못하고 있습니다. 같은 현상이 반복되면 고객센터에 신고해 주십시오.\n1. 확인");
                System.out.print("\nChoice: ");
                inputReader.readLine().trim();
                continue;
            }
            ////////////////////////////////////////////////////
            switch (userChoiceValue){
                // 1. 고객정보 요청하기
                case "1":
                    HashMap<String, String> basicCustomerInfo = new HashMap<>();
                    HashMap<String, String> responseInfo = null;
                    System.out.print("이름: "); basicCustomerInfo.put("name", inputReader.readLine().trim());
                    System.out.print("\n주민등록번호: "); basicCustomerInfo.put("ssn", inputReader.readLine().trim());
                    System.out.print("\n요청 사유: "); basicCustomerInfo.put("requestReason", inputReader.readLine().trim());
                    try {
                        responseInfo = ((UWTeam) employee).requestCustomerInformation(basicCustomerInfo, ((UWTeam) employee).getBankClerkContact());
                    } catch (BaseException e) {throw new RuntimeException(e);}
                    if(responseInfo.get("rejectReason") != null) System.out.println("고객 정보 요청이 거절되었습니다\n거절 사유: "+responseInfo.get("rejectReason"));
                    else{
                        System.out.println("고객 정보가 조회 되었습니다.\n고객: "+basicCustomerInfo.get("name")+", "+basicCustomerInfo.get("ssn")+"\n고객의 정보");
                        System.out.print("이름: "+responseInfo.get("name"));
                        System.out.print("\n나이: "+responseInfo.get("age"));
                        System.out.print("\n주소: "+responseInfo.get("address"));
                        System.out.print("\n재산: "+responseInfo.get("property"));
                        System.out.print("\n신용점수: "+responseInfo.get("creditScore"));
                        System.out.print("\n조회내역: "+responseInfo.get("viewHistory"));
                        System.out.print("\n변동내역: "+responseInfo.get("changeHistory"));
                        System.out.print("\n월 카드 사용금액: "+responseInfo.get("monthlyCardUsageAmount"));
                        System.out.print("\n대출내역: "+responseInfo.get("loanDetails"));
                        System.out.print("\n연체내역: "+responseInfo.get("overdueDetails"));
                        System.out.println("\n연대보증 내역: "+responseInfo.get("jointWarrantyDetails"));
                    }
                    break;
                // 2. 손해율 분석하기
                case "2":
                    System.out.println("1. 보험 인수 손해율 계산, 2. 재보험 등록 손해율 계산");
                    System.out.print("\nChoice: ");
                    userChoiceValue=inputReader.readLine().trim();
                    switch (userChoiceValue){
                        case "1":
                            System.out.println("손해율을 계산할 계약 ID를 입력해주세요.");
                            System.out.print("\nContract ID: ");
                            userChoiceValue=inputReader.readLine().trim();
                            int contractId = Integer.parseInt(userChoiceValue);
                            Contract contract = contractDao.retrieveById(contractId);
                            Customer insuredCustomer =customerDao.retrieveById(contract.getInsuredCustomerID());
                            HashMap<String, String> reqCustomerInfos = new HashMap<>();
                            HashMap<String, String> insuredCustomerInfo = null;
                            reqCustomerInfos.put("name", insuredCustomer.getName());
                            reqCustomerInfos.put("ssn", insuredCustomer.getRrn());
                            reqCustomerInfos.put("requestReason", "보험 계약을 위한 고객 정보 요청");
                            try {
                                insuredCustomerInfo = ((UWTeam) employee).requestCustomerInformation(reqCustomerInfos, ((UWTeam) employee).getBankClerkContact());
                            } catch (BaseException e) {throw new RuntimeException(e);}
                            if(insuredCustomerInfo.get("rejectReason") != null) System.out.println("고객 정보 요청이 거절되었습니다\n거절 사유: "+insuredCustomerInfo.get("rejectReason"));
                            else{
                                System.out.println("--고객 정보--\n고객: "+reqCustomerInfos.get("name")+", "+reqCustomerInfos.get("ssn")+"\n고객의 정보");
                                System.out.print("이름: "+insuredCustomerInfo.get("name"));
                                System.out.print("\n나이: "+insuredCustomerInfo.get("age"));
                                System.out.print("\n주소: "+insuredCustomerInfo.get("address"));
                                System.out.print("\n재산: "+insuredCustomerInfo.get("property"));
                                System.out.print("\n신용점수: "+insuredCustomerInfo.get("creditScore"));
                                System.out.print("\n조회내역: "+insuredCustomerInfo.get("viewHistory"));
                                System.out.print("\n변동내역: "+insuredCustomerInfo.get("changeHistory"));
                                System.out.print("\n월 카드 사용금액: "+insuredCustomerInfo.get("monthlyCardUsageAmount"));
                                System.out.print("\n대출내역: "+insuredCustomerInfo.get("loanDetails"));
                                System.out.print("\n연체내역: "+insuredCustomerInfo.get("overdueDetails"));
                                System.out.println("\n연대보증 내역: "+insuredCustomerInfo.get("jointWarrantyDetails"));
                            }
                            // insuranceDao가 생성되면 주석 해제
//                            Insurance insurance = insuranceDao.retrieveById(contract.getInsuranceID());
//                            System.out.println("--보험 정보-- ");
//                            System.out.print("보험 상품 종류: "+insurance.getDetailedCategory());
//                            System.out.print("\n상품 이름: "+insurance.getName());
//                            System.out.print("\n지급 금액: "+insurance.getPrice());
//                            System.out.print("\n계약 금액: "+contract.getPremium());
//                            System.out.println("\n계약 기간: "+contract.getPeriod());
//                            System.out.println("1. 손해율 분석 버튼");
//                            System.out.print("\nChoice: ");
//                            inputReader.readLine();
//                            HashMap<String, String> result = contract.calculateLossRatio(insurance, insuredCustomer);
//                            if(result.get("isResult").equals("true")) {
//                                System.out.println("손해율 분석이 완료되었습니다.");
//                                System.out.print("예상 고객 납부금액: "+result.get("estimatedEarning"));
//                                System.out.print("\n예상 지급금액: "+result.get("estimatedPayment"));
//                                System.out.println("\n손해율: "+result.get("lossRatio"));
//                            }
//                            else{
//                                System.out.println("손해율 측정에 실패하였습니다. 다시 시도해주세요.");
//                            }
                            break;
                        case "2":
                            System.out.println("손해율을 계산할 재보험 계약 ID를 입력해주세요.");
                            System.out.print("\nReinsurance Contract ID: ");
                            userChoiceValue=inputReader.readLine().trim();
                            int reinsuranceId = Integer.parseInt(userChoiceValue);
                            Reinsurance reinsurance = reinsuranceDao.retrieveById(reinsuranceId);
                            System.out.println("--재보험 회사 정보--");
                            System.out.print("재보험 회사 이름: "+reinsurance.getReinsuranceCompanyName());
                            System.out.print("\n재보험 회사 담당자 이름: "+reinsurance.getReinsuranceCompanyManagerName());
                            System.out.print("\n재보험 회사 담당자 연락처: "+reinsurance.getReinsuranceCompanyManagerContract());
                            System.out.println("--계약 정보--");
                            System.out.print("계약 금액: "+reinsurance.getPaymentAmount());
                            System.out.print("\n계약 조건: "+reinsurance.getPeriod());
                            System.out.println("\n재보험 대상 계약ID: "+reinsurance.getContractID());
                            System.out.println("1. 손해율 분석 버튼");
                            System.out.print("\nChoice: ");
                            inputReader.readLine();
                            // insuranceDao가 생성되면 주석 해제
//                            Contract targetContract = contractDao.retrieveById(reinsurance.getContractID());
//                            Insurance targetInsurance = insuranceDao.retrieveById(targetContract.getInsuranceID());
//                            HashMap<String, String> result = reinsurance.calculateLossRatio(targetContract, targetInsurance);
//                            if(result.get("isResult").equals("true")) {
//                                System.out.println("손해율 분석이 완료되었습니다.");
//                                System.out.print("예상 고객 납부금액: "+result.get("estimatedEarning"));
//                                System.out.print("\n예상 지급금액: "+result.get("estimatedPayment"));
//                                System.out.println("\n손해율: "+result.get("lossRatio"));
//                            }
//                            else{
//                                System.out.println("손해율 측정에 실패하였습니다. 다시 시도해주세요.");
//                            }
                            break;
                    }
            }
        }
        return true;
    }
}
