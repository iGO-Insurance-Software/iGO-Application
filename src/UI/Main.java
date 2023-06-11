package UI;
import Dao.CustomerDao;
import Dao.Dao;
import Dao.InsuranceDao;
import Dao.InsuredCustomerDao;
import Dao.EmployeeDao;
import Dao.AccidentDao;
import Dao.AccidentReceptionTeamDao;
import Dao.InvestigationTeamDao;
import Dao.CompensationTeamDao;
import Dao.ContractDao;
import Dao.ProductDevelopmentTeamDao;
import Dao.ComplianceTeamDao;
import Dao.MarketingTeamDao;
import Dao.ReinsuranceDao;
import Dao.SurveyDao;
import Customer.Customer;
import Customer.UnpaidCustomer;
import Employee.Employee;
import Employee.MarketingTeam;
import Employee.ContractManagementTeam;
import Insurance.Insurance;
import Dao.PrototypeDao;
import util.BaseException;
import util.ErrorCode;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static UI.AccidentReceptionMain.*;
import static UI.CalculateCompensationMain.showAccidentsForCalculateCompensation;
import static UI.IndemnityMain.showAccidentsForIndemnity;
import static UI.PayCompensationMain.showAccidentsForPayCompensation;
import static util.DBFunctions.setDB;
import static UI.DecideCompensationMain.showAccidentsForDecideCompensation;


public class Main {
	public static Customer currentCustomer;
	public static Employee currentEmployee;
	public static Dao dao = new Dao();
	public static InsuranceDao insuranceDao = new InsuranceDao();
	public static CustomerDao customerDao = new CustomerDao();
	public static InsuredCustomerDao insuredCustomerDao = new InsuredCustomerDao();
	public static EmployeeDao employeeDao = new EmployeeDao();
	public static AccidentReceptionTeamDao accidentReceptionTeamDao = new AccidentReceptionTeamDao();
	public static InvestigationTeamDao investigationTeamDao = new InvestigationTeamDao();
	public static CompensationTeamDao compensationTeamDao = new CompensationTeamDao();
	public static MarketingTeamDao marketingTeamDao = new MarketingTeamDao();
	public static AccidentDao accidentDao = new AccidentDao();
	public static ContractDao contractDao = new ContractDao();
	public static ReinsuranceDao reinsuranceDao = new ReinsuranceDao();
	public static SurveyDao surveyDao = new SurveyDao();
	public static Customer currentCustomer;
	public static Employee currentEmployee;
	public static MarketingTeam marketingTeam;
	public static PrototypeDao prototypeDao = new PrototypeDao();
	public static ComplianceTeamDao complianceTeamDao = new ComplianceTeamDao();
	public static ProductDevelopmentTeamDao productDevelopmentTeamDao = new ProductDevelopmentTeamDao();
	public static boolean isAdClosed = false;

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
				} else break;
			}
		}

		// 로그인
		login(inputReader);
	}

	private static void login(BufferedReader inputReader) throws IOException {
		while (true) {
				printLoginMenu();
				String userChoiceValue = inputReader.readLine().trim();
				switch (userChoiceValue) {
					case "1":
						if (loginCustomer(inputReader)) showCustomerMenu(inputReader);
						break;
					case "2":
						if (loginEmployee(inputReader)) showEmployeeMenu(inputReader);
						break;
					case "x":
						System.out.println("프로그램을 종료합니다.");
						return;
					default:
						System.out.println("메뉴 번호를 정확하게 입력해주세요.");
						break;
				}
		}
	}

	private static void printLoginMenu() {
		System.out.println("____________Login____________");
		System.out.println("1.회원 로그인");
		System.out.println("2.직원 로그인");
		System.out.println("x. 종료");
		System.out.print("\nChoice: ");
	}

	private static boolean loginCustomer(BufferedReader inputReader) throws IOException {
		isAdClosed = false;
		System.out.println("---------회원 로그인---------");
		while(true) {
			System.out.println("x. 뒤로가기");
			System.out.print("고객 ID: "); String id = inputReader.readLine().trim();
			if (id.equals("x")) return false;
			try {
				// 로그인 정보 확인
				for (Customer customer : customerDao.retrieveAllCustomer()) {
					if (customer.getId().equals(id)) {
						currentCustomer = customer; // 현재 접속 고객 설정
						System.out.println("-> 로그인 성공! " + currentCustomer.getName() + " 고객님 환영합니다.");
						return true;
					}
				}
				// 해당하는 ID가 없을 경우 예외 처리
				throw new BaseException(ErrorCode.NOT_EXIST_ID);
			} catch (BaseException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private static boolean loginEmployee(BufferedReader inputReader) throws IOException {
		System.out.println("---------직원 로그인---------");
		while(true) {
			System.out.println("x. 뒤로가기");
			System.out.print("직원 ID: "); String id = inputReader.readLine().trim();
			if (id.equals("x")) return false;
			try {
				// 로그인 정보 확인
				for (Employee employee : employeeDao.retrieveAllEmployee()) {
					System.out.println(employee.getId());
					if (employee.getId().equals(id)) {
						currentEmployee = employee; // 현재 접속 직원 설정
						System.out.println("-> 로그인 성공! " + currentEmployee.getName() + " 사원님 환영합니다.");
						return true;
					}
				}
				// 해당하는 ID가 없을 경우 예외 처리
				throw new BaseException(ErrorCode.NOT_EXIST_ID);
			} catch (BaseException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private static void showCustomerMenu(BufferedReader inputReader) throws IOException {
		//Exception:7초이상의 로딩//
		// 최초 1회만 실행되도록 하기
//		try {
//			loadingCustomer();
//		} catch (BaseException e) {
//			System.out.println(e.getMessage());
//			return false;
//		}
		////////////////////////
		boolean isRemain=true;
		while (isRemain) {
			System.out.println("\n************************ " + currentCustomer.getName() + " 고객님의 MENU ************************");
			switch (currentCustomer.getType()) {
				case "Customer":
					// 일반 고객 메뉴
					showNormalCustomerMenu(inputReader);
					return;
				case "InsuredCustomer":
					// 피보험자 메뉴
					showInsuredCustomerMenu(inputReader);
					return;
				case "Contractor":
					// 보험 계약자 메뉴
					showContractorMenu(inputReader);
					return;
			}
		}
	}

	private static boolean showNormalCustomerMenu(BufferedReader inputReader) throws IOException {
    	CustomerMain customerMain = new CustomerMain(currentCustomer);
 		return customerMain.showCustomerMenu(inputReader);
	}

	private static void showInsuredCustomerMenu(BufferedReader inputReader) throws IOException {
		while(true) {
			if (isAdClosed == false) { showAdForCustomer(inputReader); }
			printInsuredCustomerMenu();
			String userChoiceValue = inputReader.readLine().trim();
			switch (userChoiceValue) {
				case "0":
					//보험 메뉴 확인
					CustomerMain customerMain =  new CustomerMain(currentCustomer);
					customerMain.showInsurancesForInsuredCustomer(inputReader);
					break;
				case "1":
					// 사고 접수
					reportAccident(inputReader);
					break;
				case "2":
					// 사고 조회
					getAccident(inputReader);
					break;
				case "x":
					return;
				default:
					System.out.println("메뉴 번호를 정확하게 입력해주세요.");
					break;
			}
		}
	}

	private static void printInsuredCustomerMenu() {
		System.out.println("\n\n0. 보험 메뉴 확인");
		System.out.println("1. 사고 접수");
		System.out.println("2. 사고 조회");
		System.out.println("x. 로그아웃");
		System.out.print("\nChoice: ");
	}

	private static void reportAccident(BufferedReader inputReader) throws IOException {
		HashMap<String, String> accidentInfo = null;
		try {
			accidentInfo = sendReception(inputReader);
		} catch (BaseException e) {
			System.out.println(e.getMessage());
		}
		if (accidentInfo != null) receiveReception(accidentInfo, inputReader);
	}
	private static void showContractorMenu(BufferedReader inputReader) throws IOException {
		while(true) {
			if (isAdClosed == false) { showAdForCustomer(inputReader); }
			printContractorMenu();
			String userChoiceValue = inputReader.readLine().trim();
			switch (userChoiceValue) {
				case "0":
					//보험 메뉴 확인
					CustomerMain customerMain =  new CustomerMain(currentCustomer);
					customerMain.showInsurancesForInsuredCustomer(inputReader);
					break;
				case "x":
					return;
				default:
					System.out.println("메뉴 번호를 정확하게 입력해주세요.");
					break;
			}
		}
	}
	private static void printContractorMenu() {
		System.out.println("\n\n0. 보험 메뉴 확인");
		System.out.print("\nChoice: ");
	}
  
  public static void showAdForCustomer(BufferedReader inputReader) throws IOException {
	  while (true) {
		  if(!isAdClosed) printAd();
		  String userChoiceValue = inputReader.readLine().trim();
		  switch (userChoiceValue) {
			  case "o":
				  isAdClosed = true;
				  return;
			  case "x":
				  return;
			  default:
				  System.out.println("번호를 정확하게 입력해주세요.");
				  break;
		  }
	  }
  }
    
  private static void printAd() {
				try{
					MarketingTeam marketingTeam = loadMarketingTeamIdForAd();
					if(marketingTeam!=null) {
						//광고 로딩 성공
						System.out.println(" ----------------------광고----------------------");
						System.out.println("|			      " + marketingTeam.getAdName() + "	               |");
						System.out.println("|            " + marketingTeam.getAdDescription() + "             |");
						System.out.println(" ------------------------------------------------");
						System.out.println("x. 닫기 o. 접어두기\n광고 속 상품의 가입을 원하실 경우 ");
						System.out.print("\nChoice: ");
						return;
					} 	//광고 로드 실패
						throw new BaseException(ErrorCode.LOADING_ERROR_AD);
				}catch(BaseException e){
					System.out.println(e.getMessage());
				}
	}
	private static MarketingTeam loadMarketingTeamIdForAd(){
		int randomIndex = ThreadLocalRandom.current().nextInt(0, marketingTeamDao.retrieveAll().size());
		String randomId = marketingTeamDao.retrieveAll().get(randomIndex).getId();
		MarketingTeam randomMarketingEmployee = marketingTeamDao.retrieveById(randomId);
		if(randomMarketingEmployee!=null) return randomMarketingEmployee;
		else return null;
	}
	private static void showEmployeeMenu(BufferedReader inputReader) throws IOException {
		//Exception:7초이상의 로딩//
//		try {
//			loadingEmployee();
//		} catch (BaseException e) {
//			System.out.println(e.getMessage());
//			return false;
//		}
		////////////////////////
		while (true) {
			System.out.println("\n************************ " + currentEmployee.getName() + " 사원님의 MENU ************************");
			switch (currentEmployee.getType()) {
				case "AccidentReception":
					showAccidentReceptionTeamMenu(inputReader);
					return;
				case "Investigation":
					showInvestigationTeamMenu(inputReader);
					return;
				case "Compensation":
					showCompensationTeamMenu(inputReader);
					return;
				case "UW":
					showUWTeamMenu(inputReader);
					return;
				case "ContractManagement":
					showContractManagementTeamMenu(inputReader);
					return;
				case "ProductDevelopment":
					showProductDevelopmentTeamMenu(inputReader);
				  	return;
				case "Compliance":
					showComplianceTeamMenu(inputReader);
					return;
				case "Sales":
					showSalesTeamMenu(inputReader);
					return;
				case "Marketing":
					showMarketingTeamMenu(inputReader);
					return;
				default:
					System.out.println("잘못된 접근입니다.");
					return;
			}
		}
	}

	private static void showAccidentReceptionTeamMenu(BufferedReader inputReader) throws IOException {
		while(true) {
			printAccidentReceptionTeamMenu();
			String userChoiceValue = inputReader.readLine().trim();
			switch (userChoiceValue) {
				case "1":
					// 사고 조회
					showAccidentsForReceptionEmployee(inputReader);
					break;
				case "x":
					return;
				default:
					System.out.println("메뉴 번호를 정확하게 입력해주세요.");
					break;
			}
		}
	}

	private static void printAccidentReceptionTeamMenu() {
		System.out.println("1. 사고 조회");
		System.out.println("x. 로그아웃");
		System.out.print("\nChoice: ");
	}

	private static void showInvestigationTeamMenu(BufferedReader inputReader) throws IOException {
		while(true) {
			printInvestigationTeamMenu();
			String userChoiceValue = inputReader.readLine().trim();
			switch (userChoiceValue) {
				case "1":
					// 사고 조회
					showAccidentsForInvestigationEmployee(inputReader);
					break;
				case "x":
					return;
				default:
					System.out.println("메뉴 번호를 정확하게 입력해주세요.");
					break;
			}
		}
	}

	private static void printInvestigationTeamMenu() {
		System.out.println("1. 사고 조회");
		System.out.println("x. 로그아웃");
		System.out.print("\nChoice: ");
	}

	private static void showCompensationTeamMenu(BufferedReader inputReader) throws IOException {
		while(true) {
			printCompensationTeamMenu();
			String userChoiceValue = inputReader.readLine().trim();
			switch (userChoiceValue) {
				case "1":
					// 보상 여부 결정하기
					showAccidentsForDecideCompensation(inputReader);
					break;
				case "2":
					// 보상금 책정하기
					showAccidentsForCalculateCompensation(inputReader);
					break;
				case "3":
					// 보상금 지급하기
					showAccidentsForPayCompensation(inputReader);
					break;
				case "4":
					// 구상 신청하기
					showAccidentsForIndemnity(inputReader);
					break;
				case "5":
					// 구상 소송 요청하기
					break;
				case "6":
					// 사건 종결하기
					break;
				case "x":
					return;
				default:
					System.out.println("메뉴 번호를 정확하게 입력해주세요.");
					break;
			}
		}
	}

	private static void printCompensationTeamMenu() {
		System.out.println("1. 보상 여부 결정하기");
		System.out.println("2. 보상금 책정하기");
		System.out.println("3. 보상금 지급하기");
		System.out.println("4. 구상 신청하기");
//		System.out.println("5. 구상 소송 요청하기");
//		System.out.println("6. 사건 종결하기");
		System.out.println("x. 로그아웃");
		System.out.print("\nChoice: ");
	}

	private static void showUWTeamMenu(BufferedReader inputReader) throws IOException {
		UWMain uwMain = new UWMain(currentEmployee);
		uwMain.showEmployeeMenu(inputReader);
	}

	private static void showContractManagementTeamMenu(BufferedReader inputReader) throws IOException {
		while(true) {
			printContractManagementTeamMenu();
			String userChoiceValue = inputReader.readLine().trim();
			try {
				switch (userChoiceValue) {
					case "1":
						// 보험료 미납 고객 조회
						getUnpaidCustomer(inputReader);
						break;
					case "2":
						// 만기 임박 고객 조회
	//					getContractExpirationCustomer(inputReader);
						break;
					case "3":
						// 부활 신청 고객 조회
	//					getCustomerApplyingInsuranceRevival(inputReader);
						break;
					case "x":
						return;
					default:
						System.out.println("메뉴 번호를 정확하게 입력해주세요.");
						break;
				}
			} catch (BaseException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private static void printContractManagementTeamMenu() {
		System.out.println("1. 보험료 미납 고객 조회");
//		System.out.println("2. 만기 임박 고객 조회");
//		System.out.println("3. 부활 신청 고객 조회");
		System.out.println("x. 로그아웃");
		System.out.print("\nChoice: ");
	}

	private static void getUnpaidCustomer(BufferedReader inputReader) throws IOException, BaseException {
		ContractManagementTeam employee = new ContractManagementTeam(currentEmployee);
		ArrayList<UnpaidCustomer> unpaidCustomerList = employee.getUnpaidCustomer(inputReader);
		System.out.println("이름\t전화번호\t미납횟수\t보험료\t보험명");
		for(UnpaidCustomer unpaidCustomer: unpaidCustomerList) {
			System.out.println(unpaidCustomer.getName() +"\t"+ unpaidCustomer.getPhoneNum() +"\t"+ unpaidCustomer.getNumberOfNonPayments()
								+"\t"+ unpaidCustomer.getPremium() +"\t"+ unpaidCustomer.getInsuranceName());
		}

		while(true) {
			System.out.println("x. 뒤로가기");
			System.out.print("미납 안내할 고객명 입력 >> "); String customerName = inputReader.readLine().trim();
			if (customerName.equals("x")) return;
			if(!informNonPayment(employee, unpaidCustomerList, customerName, inputReader)) System.out.println("일치하는 고객이 없습니다. 다시 입력해주세요.\n");
		}
	}

	private static boolean informNonPayment(ContractManagementTeam employee, ArrayList<UnpaidCustomer> unpaidCustomerList, String customerName, BufferedReader inputReader) throws IOException {
		for (UnpaidCustomer unpaidCustomer : unpaidCustomerList) {
			if(unpaidCustomer.getName().equals(customerName)) {
				if (unpaidCustomer.getNumberOfNonPayments() < 3) {
					while(true) {
						System.out.println("보험료 미납 안내 전화를 하시겠습니까? (y/n)"); String input = inputReader.readLine().trim();
						switch (input) {
							case "y":
								employee.informNonPayment(inputReader, customerName);
								return true;
							case "n":
								return false;
							default:
								System.out.println("정확하게 다시 입력해주세요.");
								break;
						}
					}
				} else {
					while(true) {
						System.out.println("실효보험 처리를 하시겠습니까? (y/n)"); String input = inputReader.readLine().trim();
						switch (input) {
							case "y":
								employee.informVoidInsurance(customerName, unpaidCustomer.getInsuranceName());
								return true;
							case "n":
								return false;
							default:
								System.out.println("정확하게 다시 입력해주세요.");
								break;
						}
					}
				}
			}
		}
		return false;
	}

//	private static void printSalesTeamMenu() {
//		System.out.println("1. 가입 신청 현황 확인");
//		System.out.println("2. 고객 정보 검색");
//		System.out.println("x. 로그아웃");
//		System.out.print("\nChoice: ");
//	}

	private static void showSalesTeamMenu(BufferedReader inputReader) throws IOException {
		SalesMain salesMain = new SalesMain(currentEmployee);
		boolean isRemain = true;
		while(isRemain) {
			isRemain = salesMain.checkCustomerRequest(inputReader);
		}
	}

	private static void showMarketingTeamMenu(BufferedReader inputReader) throws IOException {
		MarketingMain marketingMain = new MarketingMain(currentEmployee);
		boolean isRemain = true;
		while(isRemain) {
			isRemain = marketingMain.showMarketingMenu(inputReader);
		}
	}
  
  	private static void showProductDevelopmentTeamMenu(BufferedReader inputReader) throws IOException {
		ProductTeamMain productTeamMain = new ProductTeamMain();
    	productTeamMain.showFunctions(inputReader);
	}
  
  	private static void showComplianceTeamMenu(BufferedReader inputReader) throws IOException {
		ComplianceTeamMain complianceTeamMain = new ComplianceTeamMain();
	  	complianceTeamMain.showFunctions(inputReader);
	}

	public static boolean showMessageForCustomer(Customer customer, String message) {
		System.out.println("\n----- " + customer.getName() + " 고객님에게 전송된 메세지 -----");
		System.out.println(message);
		System.out.println();
		System.out.println();
		System.out.println();
		return true;
	}

	public static boolean showMessageForEmployee(Employee employee, String message) {
		System.out.println("\n----- " + employee.getName() + " 사원님에게 전송된 메세지 -----");
		System.out.println(message);
		System.out.println();
		System.out.println();
		System.out.println();
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
			throw new BaseException(ErrorCode.LOADING_ERROR_CUSTOMER);
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
			throw new BaseException(ErrorCode.LOADING_ERROR_EMPLOYEE);
		}
	}
}
