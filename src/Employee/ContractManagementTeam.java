package Employee;

import Customer.Customer;
import Customer.UnpaidCustomer;
import Dao.ContractDao;
import util.BaseException;
import util.ErrorCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class ContractManagementTeam extends Employee {
	public ContractManagementTeam() {
	}

	public ContractManagementTeam(Employee currentEmployee) {
		this.setId(currentEmployee.getId());
		this.setName(currentEmployee.getName());
		this.setAge(currentEmployee.getAge());
		this.setPhoneNum(currentEmployee.getPhoneNum());
		this.setGender(currentEmployee.getGender());
		this.setEmail(currentEmployee.getEmail());
		this.setType(currentEmployee.getType());
		this.setRank(currentEmployee.getRank());
	}

	public boolean examineRevivalOfInsurance(){
		return false;
	}

	public boolean informNonPayment(BufferedReader inputReader, String customerName) throws IOException {
		System.out.println(customerName + " 고객님과 전화 연결 중 ... ");
		System.out.println("미납 안내 사항 전달을 모두 마치셨다면 x를 입력해주세요.");
		while(true) {
			String input = inputReader.readLine().trim();
			if (input.equals("x")) {
				System.out.println("통화를 종료합니다.");
				return true;
			}
		}
	}

	public boolean informContractExpiration(){
		return false;
	}

	public boolean informVoidInsurance(String customerName, String insuranceName) {
		System.out.println("\n[iGO보험 " + insuranceName + "보험 계약 실효 안내]\n" +
				customerName + "고객님이 가입하신 " + insuranceName + "보험이 실효됨을 안내드립니다.\n" +
				"보험 부활을 원하시면 iGO보험 사이트에 접속하셔서 부활 신청을 접수해주시거나, 아래 번호로 문의전화 바랍니다.\n" +
				"-보험명 : " + insuranceName + "\n" +
				"-문의전화 : 02-202-5050\n");
		System.out.println(customerName + "고객님께 정상적으로 문자가 전송되었습니다.");
		return true;
	}

	public ArrayList<UnpaidCustomer> getUnpaidCustomer(BufferedReader inputReader) throws BaseException {
		ArrayList<UnpaidCustomer> unpaidCustomers;
		ContractDao contractDao = new ContractDao();
		unpaidCustomers = contractDao.retrieveNonPaymentInfoList();
		if (unpaidCustomers.isEmpty()) {
			throw new BaseException(ErrorCode.NULL_DATA);
		}
		return unpaidCustomers;
	}

	public ArrayList<Customer> getContractExpirationCustomer() {
		return null;
	}

	public ArrayList<Customer> getCustomerApplyingInsuranceRevival(){
		return null;
	}

}