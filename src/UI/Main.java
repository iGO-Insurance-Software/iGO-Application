package UI;

import Accident.Accident;
import Customer.Customer;
import Customer.InsuredCustomer;
import Customer.CustomerList;
import Customer.CustomerListImpl;
import Employee.EmployeeListImpl;
import Employee.Employee;
import Employee.AccidentReceiptionTeam;
import Accident.Accident;
import Accident.AccidentListImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class Main {
	public static CustomerListImpl customerList = new CustomerListImpl();
	public static Customer customer;


	public static InsuredCustomer insuredCustomer = new InsuredCustomer();

	public static EmployeeListImpl employeeList = new EmployeeListImpl();
	public static Employee employee;
	public static AccidentReceiptionTeam accidentReceiptionEmployee = new AccidentReceiptionTeam();

	public static AccidentListImpl accidentList = new AccidentListImpl();
	public static Accident accident;


	public static void main(String[] args) throws IOException {
		registerEmployeeData();
		registerCustomerData();

		BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
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
		//피보험자
		insuredCustomer.setName("김피보");
		customerList.add(insuredCustomer);


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
			System.out.println("************************ "+customer.getName()+" 고객님의 MENU ************************");
			System.out.println("x. 로그아웃하기");
			//피보험자일 경우
			if (customer instanceof InsuredCustomer) {
				System.out.println("1. 사고 접수하기\n2. 내 접수 현황");
				userChoiceValue = inputReader.readLine().trim();
				switch (userChoiceValue) {
					case "1":
						HashMap<String, String> accidentInfo = ((InsuredCustomer) customer).sendReceiption(inputReader);
						if (accidentInfo != null) {
							boolean isAccepted = ((AccidentReceiptionTeam) employee).receiveReceiption(accidentInfo, customer.getId(), inputReader);
							if (isAccepted) {

								System.out.println();
							} else {

							}
						} else showCustomerMenu(inputReader);
						break;
					case "2":
						//InsuranceList에서 내 아이디를 가진 보험을 뽑아옴
						//cancelReceiption
						break;
					case "x":
						isRemain=false;
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
			System.out.println("************************ " + employee.getName() + " 사원님의 MENU ************************");
			System.out.println("x. 로그아웃하기");
			//사고접수 직원일 경우
			if (employee instanceof AccidentReceiptionTeam) {
				System.out.println("1. 사고 처리 현황");
				userChoiceValue = inputReader.readLine().trim();
				switch (userChoiceValue) {
					case "1":
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
		System.out.print("ID: "); String id = inputReader.readLine().trim();
		//System.out.print("Password: "); String password = inputReader.readLine().trim();
		for(Customer cust : customerList.retrieveAll()){
			if (cust.getId()==Integer.parseInt(id)){
				customer = cust; //현재 접속중인 고객을 cust로 설정
				System.out.println("# 로그인 성공. 환영합니다 "+customer.getName()+" 고객님");
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
				employee = emp; //현재 접속중인 직원을 emp로 설정
				System.out.println("# 로그인 성공. 환영합니다 "+employee.getName()+" 사원님");
				return true;
			}
		}
		System.out.println("# 로그인 실패. ID를 확인하고 다시 로그인 해주세요");
		return false;
	}

	private static boolean registerCustomer(BufferedReader inputReader) throws  IOException {
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
