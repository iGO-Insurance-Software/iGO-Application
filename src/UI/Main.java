package UI;

import Customer.Customer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Main {
	protected static String user;
	
	public static void main(String[] args) throws IOException {
		BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
		
			boolean isLogin = true;
			while(isLogin) {
				printMenu();
				String userChoiceValue = inputReader.readLine().trim();
				switch (userChoiceValue) {
				case "1":
//					printAllData(server.getAllStudentData(user));
					registerCustomer(inputReader);
					break;
				case "2":
//					printAllData(server.getAllCourseData());
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
	private static boolean login(BufferedReader inputReader) throws IOException {
		System.out.println("____________Login____________");
		System.out.print("ID: "); String id = inputReader.readLine().trim();
		System.out.print("Password: "); String password = inputReader.readLine().trim();
		return true;
	}
	private static void printRegisteredCourse(BufferedReader inputReader) throws IOException {
		System.out.println("-----courseRegistration Information-----");
		String studentID = user;
		if(user.equals("admin")) System.out.print("Student ID: "); studentID = inputReader.readLine().trim();
		
	}
	private static void courseRegistration(BufferedReader inputReader) throws IOException {
		System.out.println("-----courseRegistration Information-----");
		String studentID = user;
		if(user.equals("admin")) System.out.print("Student ID: "); studentID = inputReader.readLine().trim();
		System.out.print("Course ID: "); String courseID = inputReader.readLine().trim();
		
	}
	private static void registerCustomer(BufferedReader inputReader) throws  IOException {
		CustomerList customerlist = new CustomerListImpl();
		Customer customer = new Customer();
		System.out.println("-----회원가입정보 입력하기-----");
		System.out.print("1. 회원 이름을 입력하세요: "); 
		String customerName = inputReader.readLine().trim();
		customer.setName(customerName);
		System.out.println(customer.getName());
		System.out.print("2. 성별을 선택하세요 \n1. 남성 \n2. 여성 \n>"); 
		String customerJob = inputReader.readLine().trim();
		if(customerJob.equals("1")) customer.setGender(true);
		if(customerJob.equals("2")) customer.setGender(false);
		if(customer.isGender() == 셕ㄷ)
	}
	private static void deleteStudent(BufferedReader inputReader) throws  IOException {
		System.out.print("Student ID: ");
		
	}
	private static void addCourses(BufferedReader inputReader) throws  IOException {
		System.out.println("-----Course Information-----");
		System.out.print("Course ID: "); String courseId = inputReader.readLine().trim();
		System.out.print("Professor Last Name: "); String ProfessorLName = inputReader.readLine().trim();
		System.out.print("Course Name: "); String courseName = inputReader.readLine().trim();
		System.out.print("Course prerequisites: "); String prerequisites = inputReader.readLine().trim();
		
	
	}
	private static void deleteCourses(BufferedReader inputReader)  throws IOException {
		System.out.print("Course ID: ");
		
	}

	private static void printAllData(ArrayList<?> inputData) {	
		String list="";
		for(int i=0;i<inputData.size();i++) list+=inputData.get(i);
		System.out.println("Server's answer: \n"+list);
	}
	private static void printMenu() {
		System.out.println("************************ MENU ************************");
//		System.out.println("Current User: "+user);
		System.out.println("1. 회원 가입하기");
		System.out.println("2. 보험 설계하기");
		System.out.println("3. 보험가입하기");
		System.out.println("4. 사고접수하기");
		System.out.println("5. 보상하기");
		System.out.println("x. Exit");
		System.out.print("\nChoice: ");
	}

}
