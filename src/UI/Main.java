package UI;
import Accident.Accident;
import Customer.Customer;
import Customer.InsuredCustomer;
import Customer.CustomerListImpl;
import Dao.CustomerDao;
import Dao.Dao;
import Dao.InsuredCustomerDao;
import Employee.EmployeeListImpl;
import Employee.Employee;
import Employee.AccidentReceiptionTeam;
import Accident.AccidentListImpl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Main {
	public static Dao dao = new Dao();
	public static CustomerDao customerDao = new CustomerDao();
	public static InsuredCustomerDao insuredCustomerDao = new InsuredCustomerDao();

	public static CustomerListImpl customerList = new CustomerListImpl();
	public static Customer currentCustomer;
	public static InsuredCustomer insuredCustomer = new InsuredCustomer();
	public static EmployeeListImpl employeeList = new EmployeeListImpl();
	public static Employee currentEmployee;
	public static AccidentReceiptionTeam accidentReceiptionEmployee = new AccidentReceiptionTeam();
	public static AccidentListImpl accidentList = new AccidentListImpl();
	public static Accident accident;


	public static void main(String[] args) throws IOException {
		//link DB
		try {
			dao.connect();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
		long startTime = System.currentTimeMillis();
		long endTime = startTime + 7000; // 7초 후 종료 시간 설정

		System.out.println("Booting iGO...");
		while (System.currentTimeMillis() < endTime) {
			if (inputReader.ready()) {
				String userInput = inputReader.readLine().trim();
				if (userInput.equals("mngmode")) {
					setDB(inputReader);
					break;
				}
				else break;
			}
		}

		boolean isLogin = true;
		while(isLogin) {
			printMenu();
			String userChoiceValue = inputReader.readLine().trim();
			switch (userChoiceValue) {
				case "1":
//					printAllData(server.getAllStudentData(user);
					login(inputReader);
					break;
				case "2":
//					printAllData(server.getAllCourseData());
					registerCustomer(inputReader);
					break;
				case "3":
//					printRegisteredCourse(server, inputReader);
					break;
				case "4":
//					if (user.equals("admin")) addStudent(server, inputReader);
//					else System.out.println("This menu is accessed only manager");
					break;
				case "5":
//					if (user.equals("admin")) deleteStudent(server, inputReader);
//					else System.out.println("This menu is accessed only manager");
					break;
				case "6":
//					if (user.equals("admin")) addCourses(server, inputReader);
//					else System.out.println("This menu is accessed only manager");
					break;
				case "7":
//					if (user.equals("admin")) deleteCourses(server, inputReader);
//					else System.out.println("This menu is accessed only manager");
					break;
				case "8":
//					courseRegistration(server, inputReader);
					break;
				case "x":
					return;
				default:
					System.out.println("Please select from the menu");
				}
			}
		
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
		customerDao.create(insuredCustomer1);
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
		insuredCustomerDao.create(insuredCustomer1);
		return true;
	}

	private static boolean registerEmployeeData(){
		//사고접수직원
		accidentReceiptionEmployee.setId(1);
		accidentReceiptionEmployee.setName("김접수");
		accidentReceiptionEmployee.setAge(30);
		accidentReceiptionEmployee.setGender("남성");
		accidentReceiptionEmployee.setPhoneNum(01022223333);
		accidentReceiptionEmployee.setEmail("receiptionman@naver.com");
		accidentReceiptionEmployee.setRank("주임");
		employeeList.add(accidentReceiptionEmployee);

		return true;
	}

	private static boolean registerProductData(){
		return true;
	}

	public static boolean setDB(BufferedReader inputReader) throws IOException{
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


					/*Insert Values*/
					registerEmployeeData();
					registerCustomerData();
					registerProductData();

					break;
				case "2":
					dao.execute("DROP TABLE InsuredCustomer");
					dao.execute("DROP TABLE Customer;");


					break;
				default:
					System.out.println("Please select from the menu");
			}
		}
		return true;
	}

	private static boolean login(BufferedReader inputReader) throws IOException {
		System.out.println("____________Login____________");
		System.out.println("1.회원 로그인, 2.직원 로그인 x.이전으로 돌아가기");
		System.out.print("\nChoice: ");
		String userChoiceValue = inputReader.readLine().trim();
		switch (userChoiceValue){
			case "1":
				if(loginCustomer(inputReader)) showCustomerMenu(inputReader);
				else login(inputReader);
				break;
			case "2":
				if(loginEmployee(inputReader)) showEmployeeMenu(inputReader);
				else login(inputReader);
				break;
			case "x":
				printMenu();
				break;
			default:
				System.out.println("Please select from the menu");
		}
		return true;
	}

	private static boolean showCustomerMenu(BufferedReader inputReader) throws IOException{
		boolean isRemain = true;
		String userChoiceValue;
		while(isRemain) {
			System.out.println("\n************************ "+currentCustomer.getName()+" 고객님의 MENU ************************");
			System.out.println("x. 로그아웃하기");
			//피보험자일 경우
			if (currentCustomer instanceof InsuredCustomer) {
				System.out.println("1. 사고 접수\n2. 사고 조회");
				System.out.print("Choice: ");
				userChoiceValue = inputReader.readLine().trim();
				System.out.println();
				switch (userChoiceValue) {
					case "1":
						HashMap accidentInfo = new HashMap<String,String>();

						System.out.println("<사고 정보 입력창>");
						System.out.print("사고 발생 년-월-일(예시: 2023-01-01): ");
						String accidentYMD = inputReader.readLine().trim();
						System.out.print("\n사고 발생 시:분 (예시: 17:53): ");
						String accidentHM = inputReader.readLine().trim();
						String accidentDateStr = accidentYMD+" "+accidentHM;
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
						Date accidentDate;
						try {
							accidentDate = formatter.parse(accidentDateStr);
						} catch (ParseException e) {
							throw new IllegalArgumentException("날짜 형식이 올바르지 않습니다.");
						}
						accidentInfo.put("customerID",currentCustomer.getId());
						accidentInfo.put("사고 일시",accidentDateStr);

						System.out.print("\n사고 장소 (예시: 거북골로 3-2길 횡단보도): ");
						String accidentPlace = inputReader.readLine();
						accidentInfo.put("사고 장소", accidentPlace);

						System.out.print("\n사고 유형 (예시: 교통사고): ");
						String accidentType = inputReader.readLine();
						accidentInfo.put("사고 유형", accidentType);

						System.out.print("\n사고 개요 (예시: 초록불에 횡단보도를 건너다가 마티즈 차량이 저를 쳤어요): ");
						String accidentOutline = inputReader.readLine();
						accidentInfo.put("사고 개요", accidentOutline);

						System.out.println("\n<손괴자 여부 체크>\n해당 사고에 손괴자가 존재하나요?\n1.네\n2.아니요");
						System.out.print("\nChoice: ");
						userChoiceValue = inputReader.readLine().trim();
						switch(userChoiceValue){
							case "1" :
								System.out.print("\n손괴자 이름을 입력하세요 (예시: 홍길동): ");
								String destoryerName = inputReader.readLine().trim();
								accidentInfo.put("손괴자 이름",destoryerName);
								System.out.print("\n손괴자 전화번호를 입력하세요 (예시: 01012341234): ");
								String destoryerPhoneNum = inputReader.readLine().trim();
								accidentInfo.put("손괴자 전화번호",destoryerPhoneNum);
								break;
							case "2" :
								break;
							default :
								System.out.println("Please select from the menu");
						}
						System.out.println("1. 접수하기\n2. 긴급 접수하기\nx. 접수 취소하기 ");
						System.out.print("\nChoice: ");
						userChoiceValue = inputReader.readLine().trim();
						switch(userChoiceValue){
							case "1" :
								accidentInfo.put("긴급 여부","일반");
								break;
							case "2" :
								accidentInfo.put("긴급 여부","긴급");
								break;
							case "x" :
								accidentInfo=null;
								break;
							default :
								System.out.println("\n Please select from the menu");
						}

						//Exception: 5년이 지난 사고일 경우
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(accidentDate);
						calendar.add(Calendar.YEAR, 5);
						if (calendar.getTime().before(new Date())) {
							accidentInfo = null;
							try {
								throw new RuntimeException("발생한지 5년이 넘은 사고는 접수할 수 없습니다.");
							} catch (RuntimeException e) {
								System.out.println(e.getMessage());
							}
						}

						if (accidentInfo != null) {
							boolean isAccepted = ((AccidentReceiptionTeam) currentEmployee).receiveReceiption(accidentInfo, currentCustomer.getId(), inputReader);
							if (isAccepted) {

								System.out.println();
							} else {

							}
						} else showCustomerMenu(inputReader);
						break;
					case "2":
						ArrayList<Accident> myAccidentList = new ArrayList<Accident>();
						for (Accident acdt : accidentList.retrieveAll()) {
							if (acdt.getCustomerID().equals(currentCustomer.getId())) {
								myAccidentList.add(acdt);
							}
						}
						if (myAccidentList.size() != 0) {
							for (int i = 0; i < myAccidentList.size(); i++) {
								Accident myAcdt = myAccidentList.get(i);
								System.out.println((i + 1) + ". 사고번호: " + myAcdt.getId());
							}
							System.out.println("몇번째 사고를 조회하시겠습니까?");
							System.out.print("Choice: ");
							userChoiceValue = inputReader.readLine().trim();
							int choice = Integer.parseInt(userChoiceValue);
							Accident choicedAccident = myAccidentList.get(choice - 1);
							System.out.println("\n사고번호 [" + choicedAccident.getId() + "] 사고 선택.");
							System.out.println("사고 일시: " + choicedAccident.getAccidentDate().toString());
							System.out.println("사고 장소: " + choicedAccident.getAccidentPlace());
							System.out.println("사고 유형: " + choicedAccident.getAccidentType());
							if (choicedAccident.isExistOfDestroyer()) {
								System.out.println("손괴자 이름: " + choicedAccident.getDestroyerName());
								System.out.println("손괴자 전화번호: " + choicedAccident.getDestroyerPhoneNum());
							}
							System.out.println("처리 상태: " + choicedAccident.getStatus());
							System.out.println("\nx. 나가기");
							if (choicedAccident.getStatus().contains("접수")) {
								System.out.println("1. 접수 취소하기");
								System.out.print("Choice: ");
								userChoiceValue = inputReader.readLine().trim();
								if (userChoiceValue.equals("x")) break;
								else if (userChoiceValue.equals("1")) {
									if (choicedAccident.getStatus().contains("긴급")) {
										if (((AccidentReceiptionTeam) currentEmployee).getInvestigationTeam().getIsDispatching()) {
											//이미 출동중이면
											System.out.println("\n이미 현장 조사 직원이 출발하여 취소시 수수료가 발생합니다.");
											System.out.println("1.확인");
											System.out.println("2.취소");
											userChoiceValue = inputReader.readLine().trim();
											if (userChoiceValue.equals("1")) {
												currentEmployee.receiveMessage("사건번호 " + choicedAccident.getId() + "의 긴급 접수가 취소되었습니다.");
												currentCustomer.receiveMessage("사건번호 " + choicedAccident.getId() + "의 긴급 접수가 취소되었습니다.");
												//customer.cancelReceiption이 위의 로직들임 - 생략
												((AccidentReceiptionTeam) currentEmployee).cancelUrgentReceiption(choicedAccident.getId());
												//Contract 객체에다 수수료 30000원 부과하는 로직 작성
												//해당 사건 삭제
												accidentList.delete(choicedAccident.getId());
												break;
											} else break; //다른 버튼 클릭시(접수 취소를 취소 시)
										} else { //아직 출동중이 아니면
											currentEmployee.receiveMessage("사건번호 " + choicedAccident.getId() + "의 긴급 접수가 취소되었습니다.");
											currentCustomer.receiveMessage("사건번호 " + choicedAccident.getId() + "의 긴급 접수가 취소되었습니다.");
											//해당 사건 삭제
											accidentList.delete(choicedAccident.getId());
											break;
										}
									} else {//일반 접수 취소시
										currentEmployee.receiveMessage("사건번호 " + choicedAccident.getId() + "의 접수가 취소되었습니다.");
										currentCustomer.receiveMessage("사건번호 " + choicedAccident.getId() + "의 접수가 취소되었습니다.");
										//해당 사건 삭제
										accidentList.delete(choicedAccident.getId());
										break;
									}
								}

							}

						} else {
							System.out.println("처리중인 사고가 없습니다.");
						}
						break;
					case "x":
						isRemain = false;
						break;
					default:
						System.out.println("Please select from the menu");
				}
			}

			//~ 고객일 경우

















		}
		return true;
	}

	private static boolean showEmployeeMenu(BufferedReader inputReader) throws IOException {
		boolean isRemain = true;
		String userChoiceValue;
		while (isRemain) {
			System.out.println("\n************************ " + currentEmployee.getName() + " 사원님의 MENU ************************");
			System.out.println("x. 로그아웃하기");
			//사고접수 직원일 경우
			if (currentEmployee instanceof AccidentReceiptionTeam) {
				System.out.println("1. 사고 조회");
				userChoiceValue = inputReader.readLine().trim();
				switch (userChoiceValue) {
					case "1":
						if(accidentList.retrieveAll().size()!=0) {//접수된 사고가 존재한다면
							Date currentDate = new Date();
							for (Accident acdt : accidentList.retrieveAll()) {
								Date accidentDate = acdt.getAccidentDate();
								long diffInMillies = Math.abs(currentDate.getTime() - accidentDate.getTime());
								long diffInYears = diffInMillies / (24 * 60 * 60 * 1000 * 365L);
								if (diffInYears >= 5 && acdt.getStatus().equals("접수 거절")) {
									accidentList.delete(acdt.getId());
								}
							}
						}
						break;
					case "x":
						isRemain = false;
						break;
				}
			}

			//~ 직원일 경우


















		}
		return true;
	}

	private static boolean loginCustomer(BufferedReader inputReader) throws IOException{
		System.out.print("ID: ");
		String id = inputReader.readLine().trim();
		//System.out.print("Password: "); String password = inputReader.readLine().trim();
		for(Customer cust : customerDao.retrieveAllCustomer()){
			if (cust.getId().equals(id)){
				currentCustomer = cust; //현재 접속중인 고객을 cust로 설정
				System.out.println("# 로그인 성공. 환영합니다 "+currentCustomer.getName()+" 고객님\n");
				return true;
			}
		}
		System.out.println("# 로그인 실패. ID를 확인하고 다시 로그인 해주세요");
		return false;
	}

	private static boolean loginEmployee(BufferedReader inputReader) throws IOException{
		System.out.print("ID: "); String id = inputReader.readLine().trim();
		//System.out.print("Password: "); String password = inputReader.readLine().trim();
		for(Employee emp : employeeList.retrieveAll()){
			if (emp.getId()==Integer.parseInt(id)){
				currentEmployee = emp; //현재 접속중인 직원을 emp로 설정
				System.out.println("# 로그인 성공. 환영합니다 "+currentEmployee.getName()+" 사원님\n");
				return true;
			}
		}
		System.out.println("# 로그인 실패. ID를 확인하고 다시 로그인 해주세요");
		return false;
	}

	private static boolean
	registerCustomer(BufferedReader inputReader) throws  IOException {
		Customer customer = new Customer();
		customer.filloutInfo(inputReader);
		customerList.add(customer);
		System.out.println(customer.getName()+"님의 회원가입이 완료되었습니다. ID는 '"+customer.getId()+"'를 사용해주세요");
		return true;
	}

	private static void printMenu() {
		System.out.println("************************ MAIN MENU ************************");
		System.out.println("1. 로그인하기");
		System.out.println("2. 회원가입하기");
		System.out.println("x. Exit");
		System.out.print("\nChoice: ");
	}

}
