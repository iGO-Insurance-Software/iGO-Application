package UI;

import Customer.Customer;
import Dao.CustomerDao;
import Employee.AccidentReceiptionTeam;
import Customer.InsuredCustomer;

import java.io.BufferedReader;
import java.io.IOException;

import static UI.Main.*;


public class DBFunctions {
    public static boolean setDB(BufferedReader inputReader) throws IOException {
        System.out.println("____________DB Manager Mode____________");
        CustomerDao customerDao = new CustomerDao();
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
                    //Customers
                    dao.execute("CREATE TABLE Customer (" +
                            "id VARCHAR(20) PRIMARY KEY," +
                            "type VARCHAR(20) NOT NULL," +
                            "name VARCHAR(20)," +
                            "rrn VARCHAR(13) UNIQUE," +
                            "age INT," +
                            "gender VARCHAR(1)," +
                            "phoneNum VARCHAR(11) UNIQUE," +
                            "occupation VARCHAR(20)" +
                            ");");
                    dao.execute("CREATE TABLE InsuredCustomer(" +
                            "id VARCHAR(20) PRIMARY KEY," +
                            "famililyHistory VARCHAR(300)," +
                            "healthCertificate VARCHAR(300) NOT NULL," +
                            "employmentCertificate VARCHAR(300)," +
                            "inheritanceCertificate VARCHAR(300)," +
                            "bankbookCopy VARCHAR(300) NOT NULL," +
                            "accidentCertificate VARCHAR(300)," +
                            "medicalCertificate VARCHAR(300)," +
                            "FOREIGN KEY (id) REFERENCES Customer (id) ON DELETE CASCADE" +
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
                    dao.execute("CREATE TABLE AccidentReceiptionTeam(" +
                            "id VARCHAR(20) PRIMARY KEY,"+
                            "FOREIGN KEY (id) REFERENCES Employee (id) ON DELETE CASCADE"+
                            ");");
                    //Products


                    /*Insert Values*/
                    registerEmployeeData();
                    registerCustomerData();
                    registerProductData();

                    break;
                case "2":
                    dao.execute("DROP TABLE AccidentReceiptionTeam");
                    dao.execute("DROP TABLE Employee");
                    dao.execute("DROP TABLE InsuredCustomer");
                    dao.execute("DROP TABLE Customer;");


                    break;
                default:
                    System.out.println("Please select from the menu");
            }
        }
        return true;
    }
    private static boolean registerCustomerData(){
        //일반 고객
        Customer customer1 = new Customer();
        customer1.setId("cs2023");
        customer1.setType("Customer");
        customer1.setName("김고객");
        customer1.setRrn("9412251111111");
        customer1.setAge(30);
        customer1.setGender("남");
        customer1.setPhoneNum("01012345678");
        customer1.setOccupation("대학교수");
        customerDao.create(customer1);

        //피보험자
        InsuredCustomer insuredCustomer1 = new InsuredCustomer();
        insuredCustomer1.setId("ics2023");
        insuredCustomer1.setType("InsuredCustomer");
        insuredCustomer1.setName("김피보");
        insuredCustomer1.setRrn("9601191123424");
        insuredCustomer1.setAge(28);
        insuredCustomer1.setGender("남");
        insuredCustomer1.setPhoneNum("01013579999");
        insuredCustomer1.setOccupation("회사원");
        insuredCustomer1.setFamilyHistory("김빠더(부): 질환: 없음, 질병: 고혈압 초기\n박마더(모): 질환: 없음, 질병: 없음");
        insuredCustomer1.setHealthCertificate("성명 : 김피보\n주민번호: 960119-1123424\n신장: 170cm\n체중: 65kg\n시력: 좌 1.0, 우 1.3" +
                "\n혈액형: RH+B\n질환: 없음, 질병: 없음\n진단 일자: 2023-05-25\n진단 병원: 뉴삼성병원(경기도 서울시 서대문구 거북골로 3-22)");
        insuredCustomer1.setEmploymentCertificate("성명 : 김피보\n주민번호: 960119-1123424\n" +
                "직장명: 한국수자원공사\n직장 주소: 대전광역시 중구 둔산동 225-10\n입사일: 2023-01-10\n담당: R&D연구\n직책: 사원\n발급일시: 2023-02-10");
        insuredCustomer1.setInheritanceCertificate("[김피보(본인)] 주민등록번호: 960119-1123424\n[김빠더(부)] 주민등록번호 : 670220-1242342\n" +
                "[김마더(모)] 주민등록번호: 700315-2500030\n[김브라더(형제)] 주민등록번호 : 950725-1311232");
        insuredCustomer1.setBankbookCopy("3520339229763");
        insuredCustomer1.setAccidentCertificate(null);
        insuredCustomer1.setMedicalCertificate(null);
        customerDao.create(insuredCustomer1);
        return true;
    }

    private static boolean registerEmployeeData(){
        //사고접수직원
        AccidentReceiptionTeam accidentReceiptionEmployee1 = new AccidentReceiptionTeam();
        accidentReceiptionEmployee1.setId("re2023");
        accidentReceiptionEmployee1.setType("AccidentReceiption");
        accidentReceiptionEmployee1.setName("김접수");
        accidentReceiptionEmployee1.setAge(30);
        accidentReceiptionEmployee1.setGender("남");
        accidentReceiptionEmployee1.setPhoneNum("01022223333");
        accidentReceiptionEmployee1.setEmail("receiptionman@naver.com");
        accidentReceiptionEmployee1.setRank("주임");
        employeeDao.create(accidentReceiptionEmployee1);
        AccidentReceiptionTeam accidentReceiptionEmployee2 = new AccidentReceiptionTeam();
        accidentReceiptionEmployee2.setId("re2024");
        accidentReceiptionEmployee2.setType("AccidentReceiption");
        accidentReceiptionEmployee2.setName("박사접");
        accidentReceiptionEmployee2.setAge(35);
        accidentReceiptionEmployee2.setGender("남");
        accidentReceiptionEmployee2.setPhoneNum("01055553333");
        accidentReceiptionEmployee2.setEmail("receiption99@naver.com");
        accidentReceiptionEmployee2.setRank("대리");
        employeeDao.create(accidentReceiptionEmployee2);
        return true;
    }

    private static boolean registerProductData(){
        return true;
    }
}
