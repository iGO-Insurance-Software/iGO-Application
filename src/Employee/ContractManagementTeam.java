package Employee;

import Customer.Customer;
import Customer.UnpaidCustomer;
import Dao.ContractDao;
import Dao.CustomerDao;

import java.io.BufferedReader;
import java.util.ArrayList;

public class ContractManagementTeam extends Employee {

	public ContractManagementTeam(){

	}

	public boolean examineRevivalOfInsurance(){
		return false;
	}

	public boolean informNonPayment(){
		return false;
	}

	public boolean informContractExpiration(){
		return false;
	}

	public boolean informVoidInsurance(){
		return false;
	}

	public ArrayList<UnpaidCustomer> getUnpaidCustomer(BufferedReader inputReader) {
		ArrayList<UnpaidCustomer> unpaidCustomers = new ArrayList<UnpaidCustomer>();

		ContractDao contractDao = new ContractDao();
		unpaidCustomers = contractDao.retrieveNonPaymentInfoList();
		CustomerDao customerDao = new CustomerDao();
//		unpaidCustomers = customerDao.retrieveNameById();
//		InsuranceDao insuranceDao = new InsuranceDao();
//		unpaidCustomers = insuranceDao.retrieveNameById(unpaidCustomers);
		// 계약에 들어가서 계약자(고객) id 가져온 후 다시 고객 DAO 가서 고객명, 폰번  가져오고,
		// 보험 id 가져온 후 다시 보험 DAO 가서 보험명 가져와야 함
		// 계약에서 가져올 것 : 미납횟수가 1회 이상인, 계약자 id, 보험 id, 미납횟수 (리스트)
		// 원칙: 테이블당 DAO 하나가 맞음.

		return null;
	}

	public ArrayList<Customer> getContractExpirationCustomer() {
		return null;
	}

	public ArrayList<Customer> getCustomerApplyingInsuranceRevival(){
		return null;
	}

}