package UI;

import Contract.Contract;
import Contract.Reinsurance;
import Customer.Customer;
import Customer.InsuredCustomer;
import Dao.ContractDao;
import Dao.CustomerDao;
import Dao.InsuredCustomerDao;
import Dao.ReinsuranceDao;
import Employee.Employee;
import Employee.UWTeam;
import Insurance.Insurance;
import util.BaseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class UWMain {

    private UWTeam employee;
    private ContractDao contractDao;
    private CustomerDao customerDao;
    private InsuredCustomerDao insuredCustomerDao;
    // private InsuranceDao insuranceDao;
    private ReinsuranceDao reinsuranceDao;

    public UWMain(Employee currentEmployee) {
        this.employee = (UWTeam) currentEmployee;
        this.contractDao = new ContractDao();
        this.customerDao = new CustomerDao();
        //this.insuranceDao = new InsuranceDao();
        this.reinsuranceDao = new ReinsuranceDao();
        this.insuredCustomerDao = new InsuredCustomerDao();
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
                case "x":
                    return false;
                // 1. 고객정보 요청하기
                case "1":
                    HashMap<String, String> basicCustomerInfo = new HashMap<>();
                    HashMap<String, String> responseInfo = null;
                    System.out.print("이름: "); basicCustomerInfo.put("name", inputReader.readLine().trim());
                    System.out.print("\n주민등록번호: "); basicCustomerInfo.put("ssn", inputReader.readLine().trim());
                    System.out.print("\n요청 사유: "); basicCustomerInfo.put("requestReason", inputReader.readLine().trim());
                    try {
                        responseInfo = employee.requestCustomerInformation(basicCustomerInfo, ((UWTeam) employee).getBankClerkContact());
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
                    break;
                // 3. 인수심사
                case "3":
                    ArrayList<Contract> waitStateContractList = contractDao.retrieveAllWaitStateContract();
                    System.out.println("--인수 심사 대기 리스트--");
                    for(Contract contract : waitStateContractList) {
                        System.out.print("계약 ID: " + contract.getId() +
                                ", 보험 ID: " + contract.getInsuranceID() +
                                ", 담당 영업팀 직원 ID: " + contract.getEmployeeID() +
                                ", 보험계약자 ID: " + contract.getContractorID() +
                                ", 피보험자 ID: " + contract.getInsuredCustomerID() +
                                ", 인수 심사 상태: " + contract.getUnderwritingState()+"\n");
                    }
                    System.out.println("인수 심사를 진행할 계약 ID를 선택해주세요.");
                    System.out.print("\nChoice: ");
                    userChoiceValue=inputReader.readLine().trim();
                    int contractId = Integer.parseInt(userChoiceValue);
                    Contract uwTargetContract = null;
                    for(Contract contract : waitStateContractList) {
                        if(contract.getId() == contractId) uwTargetContract = contract;
                    }
                    if(uwTargetContract == null){
                        userChoiceValue = "3";
                        continue;
                    }
                    // insuranceDao가 생성되면 주석 해제
                    Insurance uwTargetInsurance = insuranceDao.retrieveById(uwTargetContract.getInsuranceID());
                    InsuredCustomer uwTargetInsuredCustomer = insuredCustomerDao.retrieveById(uwTargetContract.getInsuredCustomerID());
                    System.out.println("--인수 심사 계약 정보--");
                    System.out.println("-피보험자 정보");
                    System.out.println("고객 ID: " + uwTargetInsuredCustomer.getId() +
                            ", 이름: " + uwTargetInsuredCustomer.getName() +
                            ", 주민등록번호: " + uwTargetInsuredCustomer.getRrn());
                    System.out.println("-보험 정보");
                    System.out.println("보험 ID: " + uwTargetInsurance.getId() +
                            ", 이름: " + uwTargetInsurance.getName() +
                            ", 지급 금액: " + uwTargetInsurance.getPrice());
                    System.out.println("-계약 내용");
                    System.out.println("계약 금액: " + uwTargetContract.getId() +
                            ", 계약 기간: " + uwTargetContract.getPeriod());
                    System.out.println("1.손해율 측정 버튼");
                    System.out.print("\nChoice: ");
                    inputReader.readLine().trim();
                    HashMap<String, String> result = uwTargetContract.calculateLossRatio(uwTargetInsurance, uwTargetInsuredCustomer);
                    if(result.get("isResult").equals("true")) {
                        System.out.println("손해율 분석이 완료되었습니다.");
                        System.out.print("예상 고객 납부금액: "+result.get("estimatedEarning"));
                        System.out.print("\n예상 지급금액: "+result.get("estimatedPayment"));
                        System.out.println("\n손해율: "+result.get("lossRatio"));
                    }
                    else{
                        System.out.println("손해율 측정에 실패하였습니다. 다시 시도해주세요.");
                        break;
                    }
                    System.out.println("1.인수심사 버튼 2.인수심사 거절 버튼");
                    System.out.print("\nChoice: ");
                    userChoiceValue=inputReader.readLine().trim();
                    if(userChoiceValue.equals("1")) {
                        result = uwTargetContract.underwrite(uwTargetContract, uwTargetInsurance, uwTargetInsuredCustomer);
                        if (result.get("isResult").equals("true")) {
                            System.out.println("인수 심사가 완료되었습니다.\n심사 대상자");
                            System.out.print("-이름: " + result.get("estimatedEarning"));
                            System.out.print("\n-주민등록번호: " + result.get("estimatedPayment"));
                            System.out.print("\n가입 보험 이름: " + result.get("lossRatio"));
                            System.out.print("\n가입 기간: " + result.get("lossRatio"));
                            System.out.println("\n가입 유무: " + result.get("lossRatio"));
                            System.out.println("1.확인");
                            System.out.print("\nChoice: ");
                            inputReader.readLine().trim();
                            break;
                        } else {
                            System.out.println("인수 심사가 거절되었습니다.");
                            System.out.print("-이름: " + result.get("estimatedEarning"));
                            System.out.print("\n-주민등록번호: " + result.get("estimatedPayment"));
                            System.out.print("\n가입 보험 이름: " + result.get("lossRatio"));
                            System.out.print("\n가입 기간: " + result.get("lossRatio"));
                            System.out.println("\n가입 유무: " + result.get("lossRatio"));
                            System.out.println("1.확인 2.재심사");
                            System.out.print("\nChoice: ");
                            userChoiceValue = inputReader.readLine().trim();
                            if(userChoiceValue.equals("1")) break;
                            if(userChoiceValue.equals("2")) {
                                System.out.println("재심사 사유란\n->");
                                String reUnderwriteReason = inputReader.readLine().trim();
                                System.out.println("1.재심사");
                                System.out.print("\nChoice: ");
                                inputReader.readLine().trim();
                                result = uwTargetContract.reexamine(uwTargetContract, uwTargetInsurance, uwTargetInsuredCustomer, reUnderwriteReason);
                                if (result.get("isResult").equals("true")) {
                                    System.out.println("인수 재심사가 완료되었습니다.\n재심사 대상자");
                                    System.out.print("-이름: " + result.get("estimatedEarning"));
                                    System.out.print("\n-주민등록번호: " + result.get("estimatedPayment"));
                                    System.out.print("\n가입 보험 이름: " + result.get("lossRatio"));
                                    System.out.print("\n가입 기간: " + result.get("lossRatio"));
                                    System.out.println("\n가입 유무: " + result.get("lossRatio"));
                                    System.out.println("1.확인");
                                    System.out.print("\nChoice: ");
                                    inputReader.readLine().trim();
                                    break;
                                } else {
                                    System.out.println("인수 심사가 거절되었습니다.");
                                    System.out.print("-이름: " + result.get("estimatedEarning"));
                                    System.out.print("\n-주민등록번호: " + result.get("estimatedPayment"));
                                    System.out.print("\n가입 보험 이름: " + result.get("lossRatio"));
                                    System.out.print("\n가입 기간: " + result.get("lossRatio"));
                                    System.out.println("\n가입 유무: " + result.get("lossRatio"));
                                    System.out.println("1.확인 2.재심사");
                                    System.out.print("\nChoice: ");
                                    inputReader.readLine().trim();
                                    break;
                                }
                            }
                        }
                    }
                    break;
            }
        }
        return true;
    }
}
