package UI;
import Accident.Accident;
import Customer.Customer;
import Customer.CustomerListImpl;
import Dao.CustomerDao;
import Dao.Dao;
import Dao.InsuredCustomerDao;
import Dao.EmployeeDao;
import Dao.AccidentReceiptionTeamDao;
import Employee.Employee;
import Employee.AccidentReceiptionTeam;
import Employee.InvestigationTeam;
import Accident.AccidentListImpl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static UI.AccidentReceiptionFunctions.*;
import static UI.DBFunctions.setDB;


public class Main {
	public static Dao dao = new Dao();
	public static CustomerDao customerDao = new CustomerDao();
	public static InsuredCustomerDao insuredCustomerDao = new InsuredCustomerDao();
	public static EmployeeDao employeeDao = new EmployeeDao();
	public static AccidentReceiptionTeamDao accidentReceiptionTeamDao = new AccidentReceiptionTeamDao();
	public static Customer currentCustomer;
	public static Employee currentEmployee;

	//아래의 ListImpl들은 후에 모두 지우고 ~Dao로 대체해야함. -> List x, Dao로 접근
	public static CustomerListImpl customerList = new CustomerListImpl();
	public static AccidentListImpl accidentList = new AccidentListImpl();


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
					else break;
				case "2":
					if (loginEmployee(inputReader)) showEmployeeMenu(inputReader);
					else break;
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
		System.out.print("ID: ");
		String id = inputReader.readLine().trim();
		for(Employee emp : employeeDao.retrieveAllEmployee()){
			if (emp.getId().equals(id)){
				currentEmployee = emp; //현재 접속중인 직원을 emp로 설정
				System.out.println("# 로그인 성공. 환영합니다 "+currentEmployee.getName()+" 사원님\n");
				return true;
			}
		}
		System.out.println("# 로그인 실패. ID를 확인하고 다시 로그인 해주세요");
		return false;
	}
	private static boolean showCustomerMenu(BufferedReader inputReader) throws IOException{
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
							HashMap<String,String> accidentInfo = sendReceiption(inputReader);
							if (accidentInfo != null) receiveReceiption(accidentInfo,inputReader);
							break;
						case "2":
							searchReception(inputReader);
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
			else if(currentEmployee instanceof InvestigationTeam){

			}
		}
		return true;
	}
	public static boolean showMessageForCustomer(Customer customer, String message){
		System.out.println("\n******** "+customer.getName()+" 고객님의 화면 ********");
		System.out.println(message);
		return true;
	}
	public static boolean showMessageForEmployee(Employee employee, String message){
		System.out.println("\n******** "+employee.getName()+" 사원님의 화면 ********");
		System.out.println(message);
		return true;
	}




}
