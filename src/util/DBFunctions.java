package util;

import Contract.Contract;
import Customer.Customer;
import Dao.CustomerDao;
import Employee.*;
import Customer.InsuredCustomer;
import Insurance.Insurance;
import Survey.Survey;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Date;

import static UI.Main.*;


public class DBFunctions {
    public static boolean setDB(BufferedReader inputReader) throws IOException {
        System.out.println("____________DB Manager Mode____________");
        boolean isRemain = true;
        while(isRemain) {
            System.out.println("x. 관리자모드 종료하기 1.테이블 생성 & 데이터 삽입하기(주의: 처음 1회에만 등록해주세요!), 2.테이블 삭제");
            System.out.print("\nChoice: ");
            String userChoiceValue = inputReader.readLine().trim();
            switch (userChoiceValue) {
                case "x":
                    isRemain=false;
                    break;
                case "1":
                    /*CREATE Tables*/
                    dao.create("CREATE TABLE Insurance("+
                            "id VARCHAR(20) PRIMARY KEY,"+
                            "name VARCHAR(50),"+
                            "type VARCHAR(20),"+
                            "description VARCHAR(255),"+
                            "price DOUBLE,"+
                            "detailedCategory VARCHAR(50)"+
                            ");");
                    dao.execute("CREATE TABLE Customer (" +
                            "id VARCHAR(20) PRIMARY KEY," +
                            "type VARCHAR(20) NOT NULL," +
                            "name VARCHAR(20)," +
                            "rrn VARCHAR(13) UNIQUE," +
                            "age INT," +
                            "gender VARCHAR(1)," +
                            "phoneNum VARCHAR(11) UNIQUE," +
                            "occupation VARCHAR(20)," +
                            "bankAccount VARCHAR(20)" +
                            ");");
                    dao.execute("CREATE TABLE InsuredCustomer(" +
                            "id VARCHAR(20) PRIMARY KEY," +
                            "familyHistory VARCHAR(300)," +
                            "healthCertificate VARCHAR(300)," +
                            "employmentCertificate VARCHAR(300)," +
                            "inheritanceCertificate VARCHAR(300)," +
                            "FOREIGN KEY (id) REFERENCES Customer (id) ON DELETE CASCADE ON UPDATE CASCADE" +
                            ");");
                    dao.execute("CREATE TABLE Employee(" +
                            "id VARCHAR(20) PRIMARY KEY," +
                            "type VARCHAR(20) NOT NULL," +
                            "name VARCHAR(20)," +
                            "age INT," +
                            "gender VARCHAR(1)," +
                            "phoneNum VARCHAR(11) UNIQUE," +
                            "email VARCHAR(50) UNIQUE," +
                            "`rank` VARCHAR(20)" +
                            ");");
                    dao.execute("CREATE TABLE AccidentReceptionTeam(" +
                            "id VARCHAR(20) PRIMARY KEY,"+
                            "FOREIGN KEY (id) REFERENCES Employee (id) ON DELETE CASCADE ON UPDATE CASCADE"+
                            ");");
                    dao.execute("CREATE TABLE ProductDevelopmentTeam(" +
                            "id VARCHAR(20) PRIMARY KEY,"+
                            "FOREIGN KEY (id) REFERENCES Employee (id) ON DELETE CASCADE ON UPDATE CASCADE"+
                            ");");
                    dao.execute("CREATE TABLE ComplianceTeam(" +
                            "id VARCHAR(20) PRIMARY KEY,"+
                            "FOREIGN KEY (id) REFERENCES Employee (id) ON DELETE CASCADE ON UPDATE CASCADE"+
                            ");");
                    dao.execute("CREATE TABLE CompensationTeam(" +
                            "id VARCHAR(20) PRIMARY KEY,"+
                            "FOREIGN KEY (id) REFERENCES Employee (id) ON DELETE CASCADE ON UPDATE CASCADE"+
                            ");");
                    dao.execute("CREATE TABLE SalesTeam(" +
                            "id VARCHAR(20) PRIMARY KEY,"+
                            "FOREIGN KEY (id) REFERENCES Employee (id) ON DELETE CASCADE ON UPDATE CASCADE"+
                            ");");
                    dao.execute("CREATE TABLE MarketingTeam(" +
                            "id VARCHAR(20) PRIMARY KEY,"+
                            "adName VARCHAR(50), "+
                            "adDescription VARCHAR(200), "+
                            "FOREIGN KEY (id) REFERENCES Employee (id) ON DELETE CASCADE ON UPDATE CASCADE"+
                            ");");
                    dao.execute("CREATE TABLE UWTeam(" +
                            "id VARCHAR(20) PRIMARY KEY,"+
                            "ffsContact VARCHAR(50)," +
                            "bankClerkContact VARCHAR(50)," +
                            "FOREIGN KEY (id) REFERENCES Employee (id) ON DELETE CASCADE ON UPDATE CASCADE"+
                            ");");
                    dao.execute("CREATE TABLE Prototype (" +
                            "id VARCHAR(20) PRIMARY KEY," +
                            "description VARCHAR(255), "+
                            "feedbacks VARCHAR(255)," +
                            "requirements VARCHAR(255)," +
                            "status VARCHAR(50)," +
                            "name VARCHAR(50)," +
                            "risks VARCHAR(255)," +
                            "developerID VARCHAR(20)," +
                            "detailedCategory VARCHAR(50)," +
                            "category VARCHAR(50)," +
                            "price DOUBLE," +
                            "paymentTerm INT," +
                            "FOREIGN KEY (developerID) REFERENCES Employee(id)" +
                            ");");
                    dao.execute("CREATE TABLE Contract(" +
                            "id INT AUTO_INCREMENT PRIMARY KEY," +
                            "contractorId VARCHAR(20),"+
                            "insuranceId VARCHAR(20),"+
                            "insuredCustomerId VARCHAR(20) DEFAULT NULL,"+
                            "employeeId VARCHAR(20) DEFAULT NULL,"+
                            "fee DOUBLE," +
                            "premium DOUBLE," +
                            "paymentRate DOUBLE," +
                            "numberOfNonPayments INT DEFAULT 0," +
                            "period INT," +
                            "signedDate VARCHAR(30) DEFAULT NULL," +
                            "expirationDate VARCHAR(30) DEFAULT NULL," +
                            "paymentTerm INT DEFAULT 30," +
                            "lossRatio DOUBLE DEFAULT 0," +
                            "underwritingState VARCHAR(50) DEFAULT '가입신청'," +
                            "rejectionReasons VARCHAR(200)," +
                            "FOREIGN KEY (contractorId) REFERENCES Customer (id)," +
                            "FOREIGN KEY (insuranceId) REFERENCES Insurance (id)," +
                            "FOREIGN KEY (insuredCustomerId) REFERENCES InsuredCustomer (id)," +
                            "FOREIGN KEY (employeeId) REFERENCES Employee (id)" +
                            ");");
                    dao.execute("CREATE TABLE Reinsurance(" +
                            "id INT AUTO_INCREMENT PRIMARY KEY," +
                            "contractId INT," +
                            "period INT," +
                            "paymentAmount DOUBLE," +
                            "contractRate DOUBLE," +
                            "lossRatio DOUBLE DEFAULT 0," +
                            "reinsuranceCompanyName VARCHAR(200)," +
                            "reinsuranceCompanyManagerName VARCHAR(200)," +
                            "reinsuranceCompanyManagerContract VARCHAR(200)," +
                            "contractResult VARCHAR(200)," +
                            "rejectionReasons VARCHAR(200)," +
                            "FOREIGN KEY (contractId) REFERENCES Contract (id)" +
                            ");");
                    dao.execute("CREATE TABLE Accident (" +
                            "id INT AUTO_INCREMENT PRIMARY KEY," +
                            "customerID VARCHAR(20) NOT NULL," +
                            "receptionEmployeeID VARCHAR(20) NOT NULL," +
                            "compensationEmployeeID VARCHAR(20)," +
                            "accidentDate VARCHAR(20)," +
                            "accidentPlace VARCHAR(50)," +
                            "accidentType VARCHAR(30)," +
                            "accidentOutline VARCHAR(300)," +
                            "existOfDestroyer BOOLEAN NOT NULL," +
                            "destroyerName VARCHAR(20)," +
                            "destroyerPhoneNum VARCHAR(11)," +
                            "isUrgent BOOLEAN NOT NULL," +
                            "status VARCHAR(100) NOT NULL," +
                            "medicalBill VARCHAR(500) DEFAULT NULL," +
                            "damageBill VARCHAR(500) DEFAULT NULL," +
                            "compensationMoney DOUBLE," +
                            "indemnityMoney DOUBLE," +
                            "indemnityDueDate VARCHAR(20)," +
                            "isWinLawsuit BOOLEAN," +
                            "lawsuitCost DOUBLE, " +
                            "winOrLoseMoney INT, " +
                            "FOREIGN KEY (customerID) REFERENCES Customer(id), " +
                            "FOREIGN KEY (receptionEmployeeID) REFERENCES Employee(id), "+
                            "FOREIGN KEY (compensationEmployeeID) REFERENCES Employee(id) " +
                            ") AUTO_INCREMENT = 1" +
                            ";");
                    dao.execute("CREATE TABLE InvestigationTeam(" +
                            "id VARCHAR(20) PRIMARY KEY,"+
                            "accidentID INT DEFAULT NULL,"+
                            "isDispatching BOOLEAN DEFAULT FALSE,"+
                            "FOREIGN KEY (accidentID) REFERENCES Accident (id) ON DELETE SET NULL,"+
                            "FOREIGN KEY (id) REFERENCES Employee (id) ON DELETE CASCADE ON UPDATE CASCADE"+
                            ");");
                    dao.execute("CREATE TABLE Survey(" +
                            "id INT AUTO_INCREMENT PRIMARY KEY," +
                            "surveyQuestionNum Int,"+
                            "customerChoice INT,"+
                            "surveyQuestionContent VARCHAR(250),"+
                            "selectedCount INT"+
                            ");");
                    /*Insert Values*/
                    registerEmployeeData();
                    registerCustomerData();
                    registerProductData();
                    registerSurveyData();
                    break;
                case "2":
                    dao.execute("DROP TABLE CompensationTeam;");
                    dao.execute("DROP TABLE InvestigationTeam;");
                    dao.execute("DROP TABLE AccidentReceptionTeam;");
                    dao.execute("DROP TABLE ProductDevelopmentTeam;");
                    dao.execute("DROP TABLE ComplianceTeam;");
                    dao.execute("DROP TABLE SalesTeam;");
                    dao.execute("DROP TABLE MarketingTeam;");
                    dao.execute("DROP TABLE UWTeam;");
                    dao.execute("DROP TABLE Reinsurance;");
                    dao.execute("DROP TABLE Contract;");
                    dao.execute("DROP TABLE Prototype;");
                    dao.execute("DROP TABLE Accident;");
                    dao.execute("DROP TABLE Employee;");
                    dao.execute("DROP TABLE InsuredCustomer;");
                    dao.execute("DROP TABLE Customer;");
                    dao.execute("DROP TABLE Insurance;");
                    dao.execute("DROP TABLE Survey;");
                    break;
                default:
                    System.out.println("Please select from the menu");
            }
        }
        return true;
    }
    private static boolean registerCustomerData(){
        //Normal Customer
        Customer customer = new Customer();
        customer = setCustomerBasicInfo(
                customer, "cs2023", "Customer",
                "김고객", "9412251111111", 30, "남",
                "01012345678", "대학교수", "3333139229763"
        );
        customerDao.create(customer);
        //Insured Customer
        InsuredCustomer insuredCustomer1 = new InsuredCustomer();
        insuredCustomer1 = (InsuredCustomer) setCustomerBasicInfo(
                insuredCustomer1, "ics2023", "InsuredCustomer",
                "김피보", "9601191123424", 28, "남",
                "01013579999", "회사원", "3520339229763"
        );
        insuredCustomer1 = setInsuredCustomerInfo(
                insuredCustomer1,
                "김빠더(부): 질환: 없음, 질병: 고혈압 초기\n박마더(모): 질환: 없음, 질병: 없음",
                "성명 : 김피보\n주민번호: 960119-1123424\n신장: 170cm\n체중: 65kg\n시력: 좌 1.0, 우 1.3" +
                        "\n혈액형: RH+B\n질환: 없음, 질병: 없음\n진단 일자: 2023-05-25\n진단 병원: 뉴삼성병원(경기도 서울시 서대문구 거북골로 3-22)",
                "성명 : 김피보\n주민번호: 960119-1123424\n" +
                        "직장명: 한국수자원공사\n직장 주소: 대전광역시 중구 둔산동 225-10\n입사일: 2023-01-10\n담당: R&D연구\n직책: 사원\n발급일시: 2023-02-10",
                "[김피보(본인)] 주민등록번호: 960119-1123424\n[김빠더(부)] 주민등록번호 : 670220-1242342\n" +
                        "[김마더(모)] 주민등록번호: 700315-2500030\n[김브라더(형제)] 주민등록번호 : 950725-1311232"
        );
        customerDao.create(insuredCustomer1);

        InsuredCustomer insuredCustomer2 = new InsuredCustomer();
        insuredCustomer2 = (InsuredCustomer) setCustomerBasicInfo(
                insuredCustomer2, "ics2024", "InsuredCustomer",
                "이아파", "9501192123424", 29, "여",
                "01066339999", "회사원", "2990339229763"
        );
        insuredCustomer2 = setInsuredCustomerInfo(
                insuredCustomer2,
                "이빠더(부): 질환: 없음, 질병: 고혈압 초기\n오마더(모): 질환: 없음, 질병: 없음",
                "성명 : 이아파\n주민번호: 950119-2123424\n신장: 160cm\n체중: 50kg\n시력: 좌 1.0, 우 1.3" +
                        "\n혈액형: RH+B\n질환: 없음, 질병: 없음\n진단 일자: 2023-05-27\n진단 병원: 명지병원(경기도 서울시 서대문구 거북골로 5-22)",
                "성명 : 이아파\n주민번호: 950119-2123424\n" +
                        "직장명: 명지대학교\n직장 주소: 서대문구 거북골로 5-10\n입사일: 2022-12-10\n담당: 교직원\n직책: 사원\n발급일시: 2022-02-10",
                "[이아파(본인)] 주민등록번호: 950119-2123424\n[이빠더(부)] 주민등록번호 : 700220-1142342\n" +
                        "[오마더(모)] 주민등록번호: 701215-2333333"
        );
        customerDao.create(insuredCustomer2);

        InsuredCustomer insuredCustomer3 = new InsuredCustomer();
        insuredCustomer3 = (InsuredCustomer) setCustomerBasicInfo(
                insuredCustomer3, "ics2025", "InsuredCustomer",
                "오인커", "9601191133532", 28, "남",
                "01088889999", "자영업자", "3520116229763"
        );
        insuredCustomer3 = setInsuredCustomerInfo(
                insuredCustomer3,
                "오아빠(부): 질환: 없음, 질병: 없음\n오엄마(모): 질환: 없음, 질병: 없음",
                "성명 : 김피보\n주민번호: 960119-1133532\n신장: 175cm\n체중: 68kg\n시력: 좌 1.0, 우 1.3" +
                        "\n혈액형: RH+B\n질환: 없음, 질병: 없음\n진단 일자: 2023-05-25\n진단 병원: 뉴삼성병원(경기도 서울시 서대문구 거북골로 3-22)",
                "성명 : 오인커\n주민번호: 960119-1133532\n" +
                        "직장명: 독채\n직장 주소: 경기도 의정부시 의정부3동 210\n입사일: 2023-01-01\n담당: 요식업\n직책: 사장\n발급일시: 2023-01-10",
                "[오인커(본인)] 주민등록번호: 960119-1133532\n[오아빠(부)] 주민등록번호 : 600120-1444342\n" +
                        "[오엄마(모)] 주민등록번호: 720315-2334567"
        );
        customerDao.create(insuredCustomer3);
        return true;
    }

    private static boolean registerEmployeeData(){
        //AccidentReception
        AccidentReceptionTeam accidentReceiptionEmployee1 = new AccidentReceptionTeam();
        accidentReceiptionEmployee1 = (AccidentReceptionTeam) setEmployeeBasicInfo(
                accidentReceiptionEmployee1, "re2023", "AccidentReception",
                "김접수", 30, "남", "01022223333",
                "receiptionman@naver.com", "주임"
        );
        employeeDao.create(accidentReceiptionEmployee1);
        AccidentReceptionTeam accidentReceiptionEmployee2 = new AccidentReceptionTeam();
        accidentReceiptionEmployee2 = (AccidentReceptionTeam) setEmployeeBasicInfo(
                accidentReceiptionEmployee2, "re2024", "AccidentReception",
                "박사접", 35, "남", "01055553333",
                "receiption99@naver.com", "대리")
        ;
        employeeDao.create(accidentReceiptionEmployee2);
        //Investigation
        InvestigationTeam investigationEmployee1 = new InvestigationTeam();
        investigationEmployee1 = (InvestigationTeam) setEmployeeBasicInfo(
                investigationEmployee1, "ie2023", "Investigation",
                "이조사", 27, "여", "01029478382",
                "investigate13@gmail.com", "사원"
        );
        employeeDao.create(investigationEmployee1);
        InvestigationTeam investigationEmployee2 = new InvestigationTeam();
        investigationEmployee2 = (InvestigationTeam) setEmployeeBasicInfo(
                investigationEmployee2, "ie2024", "Investigation",
                "김출동", 28, "남", "01033458111",
                "ig30@gmail.com", "사원"
        );
        employeeDao.create(investigationEmployee2);
        //Compensation
        CompensationTeam compensationEmployee = new CompensationTeam();
        compensationEmployee = (CompensationTeam) setEmployeeBasicInfo(
                compensationEmployee, "ce2023", "Compensation",
                "최보상", 26, "여", "01015571557",
                "compman@naver.com", "사원"
        );
        employeeDao.create(compensationEmployee);
        //productDevelopmentTeam
        ProductDevelopmentTeam productDevelopmentTeamEmployee = new ProductDevelopmentTeam();
        productDevelopmentTeamEmployee = (ProductDevelopmentTeam) setEmployeeBasicInfo(
                productDevelopmentTeamEmployee, "pd2025", "ProductDevelopment",
                "김노아", 30, "남", "01022223334",
                "productdevman@naver.com", "Manager"
        );
        employeeDao.create(productDevelopmentTeamEmployee);
        //ComplianceTeam
        ComplianceTeam complianceTeamEmployee = new ComplianceTeam();
        complianceTeamEmployee = (ComplianceTeam) setEmployeeBasicInfo(
                complianceTeamEmployee, "ct2026", "Compliance",
                "김미연", 30, "남", "01022223335",
                "compliancechiefn@naver.com", "Chief"
        );
        employeeDao.create(complianceTeamEmployee);
        //SalesTeam
        SalesTeam SalesTeamEmployee1 = new SalesTeam();
        SalesTeamEmployee1 = (SalesTeam) setEmployeeBasicInfo(
                SalesTeamEmployee1, "se2023", "Sales",
                "김영업", 32, "남", "01012349876",
                "salesman@naver.com", "대리"
        );
        employeeDao.create(SalesTeamEmployee1);
        SalesTeam SalesTeamEmployee2 = new SalesTeam();
        SalesTeamEmployee2 = (SalesTeam) setEmployeeBasicInfo(
                SalesTeamEmployee2, "se2024", "Sales",
                "이발품", 26, "여", "01054686357",
                "salessales@gmail.com", "사원"
        );
        employeeDao.create(SalesTeamEmployee2);
        //MarketingTeam
        MarketingTeam MarketingTeamEmployee1 = new MarketingTeam();
        MarketingTeamEmployee1 = (MarketingTeam) setEmployeeBasicInfo(
                MarketingTeamEmployee1, "me2023", "Marketing",
                "최홍보", 31, "여", "01024578965",
                "marketing11@naver.com", "대리"
        );
        employeeDao.create(MarketingTeamEmployee1);
        //UW
        UWTeam uwEmployee = new UWTeam();
        uwEmployee = (UWTeam) setEmployeeBasicInfo(
                uwEmployee, "uw01", "UW",
                "홍길동", 35, "남", "01012345678",
                "uwuwwuwu@naver.com", "주임"
        );
        uwEmployee.setFfsContact("1234");
        uwEmployee.setBankClerkContact("2345");
        employeeDao.create(uwEmployee);
        //ContractManagement
        ContractManagementTeam contractManagementEmployee = new ContractManagementTeam();
        contractManagementEmployee = (ContractManagementTeam) setEmployeeBasicInfo(
                contractManagementEmployee, "cm2023", "ContractManagement",
                "김계약", 25, "여", "01012123434",
                "igo_cm@gmail.com", "사원"
        );
        employeeDao.create(contractManagementEmployee);

        return true;
    }

    private static Customer setCustomerBasicInfo(
            Customer customer,
            String id,
            String type,
            String name,
            String rrn,
            int age,
            String gender,
            String phoneNum,
            String occupation,
            String bankAccount
    ) {
        customer.setId(id);
        customer.setType(type);
        customer.setName(name);
        customer.setRrn(rrn);
        customer.setAge(age);
        customer.setGender(gender);
        customer.setPhoneNum(phoneNum);
        customer.setOccupation(occupation);
        customer.setBankAccount(bankAccount);

        return customer;
    }

    private static InsuredCustomer setInsuredCustomerInfo(
            InsuredCustomer insuredCustomer,
            String familyHistory,
            String healthCertificate,
            String employmentCertificate,
            String inheritanceCertificate
    ) {
        insuredCustomer.setFamilyHistory(familyHistory);
        insuredCustomer.setHealthCertificate(healthCertificate);
        insuredCustomer.setEmploymentCertificate(employmentCertificate);
        insuredCustomer.setInheritanceCertificate(inheritanceCertificate);

        return insuredCustomer;
    }

    private static Employee setEmployeeBasicInfo(
            Employee employee,
            String id,
            String type,
            String name,
            int age,
            String gender,
            String phoneNum,
            String email,
            String rank
    ) {
        employee.setId(id);
        employee.setType(type);
        employee.setName(name);
        employee.setAge(age);
        employee.setGender(gender);
        employee.setPhoneNum(phoneNum);
        employee.setEmail(email);
        employee.setRank(rank);

        return employee;
    }

    private static boolean registerProductData(){

        Insurance insurance1 = new Insurance();
        insurance1.setId("di01");
        insurance1.setDescription("만 7세부터 만 21세까지 갱신 없이 간편한 어린이 보험");
        insurance1.setName("oo 어린이 보험");
        insurance1.setPrice(12000);
        insurance1.setType("Damage Insurance");
        insurance1.setDetailedCategory("Accident Insurance");
        insuranceDao.create(insurance1);
        Insurance insurance2 = new Insurance();
        insurance2.setId("di02");
        insurance2.setDescription("만19세부터 안전한 자동차 운전을 할 수 있도록 도와주는 착한 자동차 보험");
        insurance2.setName("xx 자동차 보험");
        insurance2.setPrice(50000);
        insurance1.setType("Damage Insurance");
        insurance2.setDetailedCategory("Car Insurance");
        insuranceDao.create(insurance2);
        Insurance insurance3 = new Insurance();
        insurance3.setId("di03");
        insurance3.setDescription("내가 생활하는 어느 곳이든 화재로 인한 피해에 돈 걱정 할 필요 없는 화재 보험 ");
        insurance3.setName("uu 화재 보험");
        insurance3.setPrice(30000);
        insurance3.setType("Damage Insurance");
        insurance3.setDetailedCategory("Fire Insurance");
        insuranceDao.create(insurance3);
        Insurance insurance4 = new Insurance();
        insurance4.setId("li01");
        insurance4.setDescription("내가 죽으면 우리 자녀들 어떡하지.. 적어도 돈 걱정은 없는 빵빵한 사망 보험");
        insurance4.setName("$$ 사망 보험");
        insurance4.setPrice(80000);
        insurance4.setType("Life Insurance");
        insurance4.setDetailedCategory("Death Insurance");
        insuranceDao.create(insurance4);
        Insurance insurance5 = new Insurance();
        insurance5.setId("si01");
        insurance5.setDescription("우리 아이 대학교 갈 때 돈 걱정 덜어주는 돈 많은 친구 학비 저축 보험");
        insurance5.setName("qp 저축 보험");
        insurance5.setPrice(100000);
        insurance5.setType("Saving Insurance");
        insurance5.setDetailedCategory("Saving Insurance");
        insuranceDao.create(insurance5);
        Insurance insurance6 = new Insurance();
        insurance6.setId("si02");
        insurance6.setDescription("벌써부터 걱정되는 퇴직의 두려움.. 언제 잘려도 노후 자금 걱정 없도록 도와주는 든든한 노후 자금 저축 보험");
        insurance6.setName("TT 저축 보험");
        insurance6.setPrice(60000);
        insurance6.setType("Saving Insurance");
        insurance6.setDetailedCategory("Saving Insurance");
        insuranceDao.create(insurance6);

        Insurance insurance = new Insurance();
        insurance.setId("ci01");
        insurance.setName("자동차 보험");
        insurance.setType("Damage insurance");
        insurance.setDescription("자동차 보혐료의 110%를 보상해주는 보험입니다.");
        insurance.setPrice(50000);
        insurance.setDetailedCategory("Car Insurance");
        insuranceDao.create(insurance);

        Contract contract = new Contract();
        contract.setContractorID("cs2023");
        contract.setInsuranceID("ci01");
        contract.setInsuredCustomerID("ics2023");
        contract.setEmployeeID("uw01");
        contract.setFee(0.1);
        contract.setPremium(50000);
        contract.setPaymentRate(0.2);
        contract.setNumberOfNonPayments(0);
        contract.setPeriod(365);
        contract.setPaymentTerm(30);
        contractDao.create(contract);

        return true;
    }
    private static boolean registerSurveyData() {
        Survey survey1 = new Survey();
        survey1.setSurveyQuestionNum(1);
        survey1.setCustomerChoice(1);
        survey1.setSurveyQuestionContent("20~29세");
        survey1.setSelectedCount(0);
        surveyDao.create(survey1);
        Survey survey2 = new Survey();
        survey2.setSurveyQuestionNum(1);
        survey2.setCustomerChoice(2);
        survey2.setSurveyQuestionContent("30~39세");
        survey2.setSelectedCount(0);
        surveyDao.create(survey2);
        Survey survey3 = new Survey();
        survey3.setSurveyQuestionNum(1);
        survey3.setCustomerChoice(3);
        survey3.setSurveyQuestionContent("40~49세");
        survey3.setSelectedCount(0);
        surveyDao.create(survey3);
        Survey survey4 = new Survey();
        survey4.setSurveyQuestionNum(1);
        survey4.setCustomerChoice(4);
        survey4.setSurveyQuestionContent("50~59세");
        survey4.setSelectedCount(0);
        surveyDao.create(survey4);
        Survey survey5 = new Survey();
        survey5.setSurveyQuestionNum(1);
        survey5.setCustomerChoice(5);
        survey5.setSurveyQuestionContent("60~69세");
        survey5.setSelectedCount(0);
        surveyDao.create(survey5);
        Survey survey6 = new Survey();
        survey6.setSurveyQuestionNum(1);
        survey6.setCustomerChoice(6);
        survey6.setSurveyQuestionContent("70세 이상");
        survey6.setSelectedCount(0);
        surveyDao.create(survey6);

        Survey survey7 = new Survey();
        survey7.setSurveyQuestionNum(2);
        survey7.setCustomerChoice(1);
        survey7.setSurveyQuestionContent("주변 지인의 추천");
        survey7.setSelectedCount(0);
        surveyDao.create(survey7);
        Survey survey8 = new Survey();
        survey8.setSurveyQuestionNum(2);
        survey8.setCustomerChoice(2);
        survey8.setSurveyQuestionContent("자사 직원의 추천");
        survey8.setSelectedCount(0);
        surveyDao.create(survey8);
        Survey survey9 = new Survey();
        survey9.setSurveyQuestionNum(2);
        survey9.setCustomerChoice(3);
        survey9.setSurveyQuestionContent("광고");
        survey9.setSelectedCount(0);
        surveyDao.create(survey9);
        Survey survey10 = new Survey();
        survey10.setSurveyQuestionNum(2);
        survey10.setCustomerChoice(4);
        survey10.setSurveyQuestionContent("기타");
        survey10.setSelectedCount(0);
        surveyDao.create(survey10);

        Survey survey11 = new Survey();
        survey11.setSurveyQuestionNum(3);
        survey11.setCustomerChoice(1);
        survey11.setSurveyQuestionContent("다른 사람들도 보험에 가입해서");
        survey11.setSelectedCount(0);
        surveyDao.create(survey11);
        Survey survey12 = new Survey();
        survey12.setSurveyQuestionNum(3);
        survey12.setCustomerChoice(2);
        survey12.setSurveyQuestionContent("혹시 모를 사고에 대비하고 싶어서");
        survey12.setSelectedCount(0);
        surveyDao.create(survey12);
        Survey survey13 = new Survey();
        survey13.setSurveyQuestionNum(3);
        survey13.setCustomerChoice(3);
        survey13.setSurveyQuestionContent("주변에서 보험에 가입하라고 조언해서");
        survey13.setSelectedCount(0);
        surveyDao.create(survey13);
        Survey survey14 = new Survey();
        survey14.setSurveyQuestionNum(3);
        survey14.setCustomerChoice(4);
        survey14.setSurveyQuestionContent("기타");
        survey14.setSelectedCount(0);
        surveyDao.create(survey14);
        return true;
    }
}
