package util;

import Customer.Customer;
import Dao.CustomerDao;
import Employee.*;
import Customer.InsuredCustomer;

import java.io.BufferedReader;
import java.io.IOException;
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
                    //Products
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
                            "winOrLoseMoney INT" +
                            ") AUTO_INCREMENT = 1;");
                    dao.execute("CREATE TABLE Prototype (" +
                            "id VARCHAR(20) PRIMARY KEY," +
                            " description VARCHAR(255), "+
                            "feedbacks VARCHAR(255)," +
                            "requirements VARCHAR(255)," +
                            " status VARCHAR(50)," +
                            " name VARCHAR(50)," +
                            " risks VARCHAR(255)," +
                            " developerID VARCHAR(20)," +
                            "detailedCategory VARCHAR(50)," +
                            "category VARCHAR(50)," +
                            "price DOUBLE," +
                            "paymentTerm INT" +
                            ");");
                    //Customers
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
                    //Employees
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

                    dao.execute("CREATE TABLE InvestigationTeam(" +
                            "id VARCHAR(20) PRIMARY KEY,"+
                            "accidentID INT DEFAULT NULL,"+
                            "isDispatching BOOLEAN DEFAULT FALSE,"+
                            "FOREIGN KEY (accidentID) REFERENCES Accident (id) ON DELETE SET NULL,"+
                            "FOREIGN KEY (id) REFERENCES Employee (id) ON DELETE CASCADE ON UPDATE CASCADE"+
                            ");");
                    dao.execute("CREATE TABLE CompensationTeam(" +
                            "id VARCHAR(20) PRIMARY KEY,"+
                            "FOREIGN KEY (id) REFERENCES Employee (id) ON DELETE CASCADE ON UPDATE CASCADE"+
                            ");");
                    dao.execute("CREATE TABLE UWTeam(" +
                            "id VARCHAR(20) PRIMARY KEY,"+
                            "ffsContact VARCHAR(50)," +
                            "bankClerkContact VARCHAR(50)," +
                            "FOREIGN KEY (id) REFERENCES Employee (id) ON DELETE CASCADE ON UPDATE CASCADE"+
                            ");");
                    dao.execute("CREATE TABLE Contract(" +
                            "id INT AUTO_INCREMENT PRIMARY KEY," +
                            "contractorId VARCHAR(20),"+
//                            "insuranceId VARCHAR(20),"+
                            "insuredCustomerId VARCHAR(20),"+
                            "employeeId VARCHAR(20),"+
                            "fee DOUBLE," +
                            "premium DOUBLE," +
                            "paymentRate DOUBLE," +
                            "numberOfNonPayments INT," +
                            "period INT," +
                            "signedDate DATE," +
                            "expirationDate DATE," +
                            "paymentTerm INT," +
                            "lossRatio DOUBLE," +
                            "underwritingState VARCHAR(50) DEFAULT '대기'," +
                            "rejectionReasons VARCHAR(200)," +
                            "FOREIGN KEY (contractorId) REFERENCES Customer (id)," +
//                            "FOREIGN KEY (insuranceId) REFERENCES Insurance (id)," +
                            "FOREIGN KEY (insuredCustomerId) REFERENCES InsuredCustomer (id)," +
                            "FOREIGN KEY (employeeId) REFERENCES Employee (id)" +
                            ");");
                    dao.execute("CREATE TABLE Reinsurance(" +
                            "id INT AUTO_INCREMENT PRIMARY KEY," +
                            "contractId INT," +
                            "period INT," +
                            "paymentAmount DOUBLE," +
                            "contractRate DOUBLE," +
                            "lossRatio DOUBLE," +
                            "reinsuranceCompanyName VARCHAR(200)," +
                            "reinsuranceCompanyManagerName VARCHAR(200)," +
                            "reinsuranceCompanyManagerContract VARCHAR(200)," +
                            "contractResult VARCHAR(200)," +
                            "rejectionReasons VARCHAR(200)," +
                            "FOREIGN KEY (contractId) REFERENCES Contract (id)" +
                            ");");
                    /*Insert Values*/
                    registerEmployeeData();
                    registerCustomerData();
                    registerProductData();
                    break;
                case "2":
                    dao.execute("DROP TABLE CompensationTeam;");
                    dao.execute("DROP TABLE InvestigationTeam;");
                    dao.execute("DROP TABLE AccidentReceptionTeam;");
                    dao.execute("DROP TABLE ProductDevelopmentTeam;");
                    dao.execute("DROP TABLE ComplianceTeam;");
                    dao.execute("DROP TABLE UWTeam;");
                    dao.execute("DROP TABLE Reinsurance;");
                    dao.execute("DROP TABLE Contract;");
                    dao.execute("DROP TABLE Employee;");
                    dao.execute("DROP TABLE InsuredCustomer;");
                    dao.execute("DROP TABLE Customer;");
                    dao.execute("DROP TABLE Prototype;");
                    dao.execute("DROP TABLE Accident;");
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
        ProductDevelopmentTeam ProductDevelopmentTeamEmployee = new ProductDevelopmentTeam();
        ProductDevelopmentTeamEmployee.setId("pd2025");
        ProductDevelopmentTeamEmployee.setType("ProductDevelopment");
        ProductDevelopmentTeamEmployee.setName("김노아");
        ProductDevelopmentTeamEmployee.setAge(30);
        ProductDevelopmentTeamEmployee.setGender("남");
        ProductDevelopmentTeamEmployee.setPhoneNum("01022223334");
        ProductDevelopmentTeamEmployee.setEmail("productdevman@naver.com");
        ProductDevelopmentTeamEmployee.setRank("Manager");
        employeeDao.create(ProductDevelopmentTeamEmployee);
        ComplianceTeam ComplianceTeamEmployee = new ComplianceTeam();
        ComplianceTeamEmployee.setId("ct2026");
        ComplianceTeamEmployee.setType("Compliance");
        ComplianceTeamEmployee.setName("김미연");
        ComplianceTeamEmployee.setAge(30);
        ComplianceTeamEmployee.setGender("남");
        ComplianceTeamEmployee.setPhoneNum("01022223335");
        ComplianceTeamEmployee.setEmail("compliancechiefn@naver.com");
        ComplianceTeamEmployee.setRank("Chief");
        employeeDao.create(ComplianceTeamEmployee);
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
        return true;
    }
}
