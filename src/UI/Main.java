package UI;
import Dao.CustomerDao;
import Dao.Dao;
import Dao.InsuredCustomerDao;
import Dao.EmployeeDao;
import Dao.AccidentDao;
import Dao.AccidentReceptionTeamDao;
import Dao.InvestigationTeamDao;
import Dao.CompensationTeamDao;
import Dao.ContractDao;
import Customer.Customer;
import Employee.Employee;
import Employee.AccidentReceptionTeam;
import Employee.InvestigationTeam;
import Employee.CompensationTeam;
import Employee.UWTeam;
import util.BaseException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import static UI.AccidentReceptionMain.*;
import static UI.CalculateCompensationMain.showAccidentsForCalculateCompensation;
import static UI.PayCompensationMain.showAccidentsForPayCompensation;
import static util.DBFunctions.setDB;
import static UI.DecideCompensationMain.showAccidentsForDecideCompensation;

public class Main {
	public static Dao dao = new Dao();
	public static CustomerDao customerDao = new CustomerDao();
	public static InsuredCustomerDao insuredCustomerDao = new InsuredCustomerDao();
	public static EmployeeDao employeeDao = new EmployeeDao();
	public static AccidentReceptionTeamDao accidentReceptionTeamDao = new AccidentReceptionTeamDao();
	public static InvestigationTeamDao investigationTeamDao = new InvestigationTeamDao();
	public static CompensationTeamDao compensationTeamDao = new CompensationTeamDao();
	public static AccidentDao accidentDao = new AccidentDao();
	public static ContractDao contractDao = new ContractDao();
	public static Customer currentCustomer;
	public static Employee currentEmployee;


	public static void main(String[] args) throws IOException {
		//link DB
		try {
			dao.connect();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		//stanby DBmanagerMode
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
					login(inputReader);
					break;
				case "x":
					return;
				default:
					System.out.println("Please select from the menu");
			}
		}
	}
	private static void printMenu() {
		System.out.println("************************ MAIN MENU ************************");
		System.out.println("1. 로그인하기");
		//System.out.println("2. 회원가입하기");
		System.out.println("x. Exit");
		System.out.print("\nChoice: ");
	}
	private static boolean login(BufferedReader inputReader) throws IOException {
		boolean isRemain = true;
		while(isRemain) {
			System.out.println("____________Login____________");
			System.out.println("1.회원 로그인, 2.직원 로그인 x.이전으로 돌아가기");
			System.out.print("\nChoice: ");
			String userChoiceValue = inputReader.readLine().trim();
			switch (userChoiceValue) {
				case "1":
					if (loginCustomer(inputReader)) showCustomerMenu(inputReader);
					break;
				case "2":
					if (loginEmployee(inputReader)) showEmployeeMenu(inputReader);
					break;
				case "x":
					isRemain=false;
					break;
				default:
					System.out.println("Please select from the menu");
					break;
			}
		}
		return true;
	}
	private static boolean loginCustomer(BufferedReader inputReader) throws IOException{
		System.out.print("ID: ");
		String id = inputReader.readLine().trim();
		//System.out.print("Password: "); String password = inputReader.readLine().trim();
		for(Customer customer : customerDao.retrieveAllCustomer()){
			if (customer.getId().equals(id)){
				currentCustomer = customer; //현재 접속중인 고객을 cust로 설정
				System.out.println("# 로그인 성공. 환영합니다 "+currentCustomer.getName()+" 고객님\n");
				return true;
			}
		}
		System.out.println("# 로그인 실패. ID를 확인하고 다시 로그인 해주세요");
		return false;
	}
	private static boolean loginEmployee(BufferedReader inputReader) throws IOException{
		System.out.print("ID: ");
		String id = inputReader.readLine().trim();
		for(Employee employee : employeeDao.retrieveAllEmployee()){
			if (employee.getId().equals(id)){
				currentEmployee = employee; //현재 접속중인 직원을 emp로 설정
				System.out.println("# 로그인 성공. 환영합니다 "+currentEmployee.getName()+" 사원님\n");
				return true;
			}
		}
		System.out.println("# 로그인 실패. ID를 확인하고 다시 로그인 해주세요");
		return false;
	}
	private static boolean showCustomerMenu(BufferedReader inputReader) throws IOException{
		//Exception:7초이상의 로딩//
//		try {
//			loadingCustomer();
//		} catch (BaseException e) {
//			System.out.println(e.getMessage());
//			return false;
//		}
		////////////////////////
		boolean isRemain = true;
		String userChoiceValue;
		while(isRemain) {
			System.out.println("\n************************ "+currentCustomer.getName()+" 고객님의 MENU ************************");
			System.out.println("x. 로그아웃하기");
			switch(currentCustomer.getType()){
				case "Customer":
					//일반고객일 경우 메뉴
					userChoiceValue = inputReader.readLine().trim();
					System.out.println();
					switch (userChoiceValue){
						case "x":
							isRemain = false;
							break;
						default:
							System.out.println("Please select from the menu");
							break;
					}
					break;
				case "InsuredCustomer":
					//피보험자일 경우 메뉴
					System.out.println("1. 사고 접수\n2. 사고 조회");
					System.out.print("Choice: ");
					userChoiceValue = inputReader.readLine().trim();
					System.out.println();
					switch (userChoiceValue) {
						case "1":
							HashMap<String,String> accidentInfo = null;
							try {
								accidentInfo = sendReception(inputReader);
							} catch (BaseException e) {
								System.out.println(e.getMessage());
							}
							if (accidentInfo != null) receiveReception(accidentInfo,inputReader);
							break;
						case "2":
							showAccidentsForCustomer(inputReader);
							break;
						case "x":
							isRemain = false;
							break;
						default:
							System.out.println("Please select from the menu");
						}
					break;

				case "Contractor":
					//보험계약자일 경우 메뉴
					break;
			}
		}
		return true;
	}
	private static boolean showEmployeeMenu(BufferedReader inputReader) throws IOException {
		//Exception:7초이상의 로딩//
//		try {
//			loadingEmployee();
//		} catch (BaseException e) {
//			System.out.println(e.getMessage());
//			return false;
//		}
		////////////////////////
		boolean isRemain = true;
		String userChoiceValue;
		while (isRemain) {
			System.out.println("\n************************ " + currentEmployee.getName() + " 사원님의 MENU ************************");
			System.out.println("x. 로그아웃하기");
			//사고접수 직원
			if (currentEmployee instanceof AccidentReceptionTeam) {
				System.out.println("1. 사고 조회");
				userChoiceValue = inputReader.readLine().trim();
				switch (userChoiceValue) {
					case "1":
						showAccidentsForReceptionEmployee(inputReader);
						break;
					case "x":
						isRemain = false;
						break;
					default:
						System.out.println("Please select from the menu");
						break;
				}
			}
			else if(currentEmployee instanceof InvestigationTeam){
				System.out.println("1. 사고 조회");
				userChoiceValue = inputReader.readLine().trim();
				switch(userChoiceValue){
					case "1":
						showAccidentsForInvestigationEmployee(inputReader);
						break;
					case "x":
						isRemain = false;
						break;
					default:
						System.out.println("Please select from the menu");
						break;
				}
			}
			else if (currentEmployee instanceof CompensationTeam){
					System.out.println("1. 보상 여부 결정하기");
					System.out.println("2. 보상금 책정하기");
					System.out.println("3. 보상금 지급하기");
					System.out.println("4. 구상 신청하기");
					System.out.println("5. 구상 소송 요청하기");
					System.out.println("6. 사건 종결하기.");
					System.out.print("Choice: ");
					userChoiceValue = inputReader.readLine().trim();
					switch(userChoiceValue){
						case "1":
							showAccidentsForDecideCompensation(inputReader);
							break;
						case "2":
							showAccidentsForCalculateCompensation(inputReader);
							break;
						case "3":
							showAccidentsForPayCompensation(inputReader);
							break;
						case "x":
							isRemain=false;
							break;
						default:
							System.out.println("Please select from the menu");
							break;
					}
			}
			else if(currentEmployee instanceof UWTeam){
				UWMain uwMain = new UWMain(currentEmployee);
				isRemain = uwMain.showEmployeeMenu(inputReader);
			}
		}
		return true;
	}
	public static boolean showMessageForCustomer(Customer customer, String message){
		System.out.println("\n----- "+customer.getName()+" 고객님에게 전송된 메세지 -----");
		System.out.println(message);
		return true;
	}
	public static boolean showMessageForEmployee(Employee employee, String message){
		System.out.println("\n----- "+employee.getName()+" 사원님에게 전송된 메세지 -----");
		System.out.println(message);
		return true;
	}
	public static void loadingCustomer() throws BaseException {
		//Random random = new Random();
		//int waitTime = random.nextInt(7000); //랜덤 대기 시간 설정
		int waitTime = 7000;
		try {
			Thread.sleep(waitTime); //지정한 시간 동안 대기
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		if (waitTime >= 7000) {
			throw new BaseException("[Error!] 현재 시스템의 성능 저하로 서비스가 원할하게 진행되지 못하고 있습니다. 잠시후 다시 시도해 주세요." +
					"\n고객센터 번호: 02-8282-7575");
		}
	}
	public static void loadingEmployee() throws BaseException {
		//Random random = new Random();
		//int waitTime = random.nextInt(7000); //랜덤 대기 시간 설정
		int waitTime = 7000;
		try {
			Thread.sleep(waitTime); //지정한 시간 동안 대기
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		if (waitTime >= 7000) {
			throw new BaseException("[Error!] 현재 시스템의 성능 저하로 서비스가 원할하게 진행되지 못하고 있습니다. 잠시후 다시 시도해 주세요." +
					"\n전산팀 사내 유선번호: 047");
		}
	}
}
