package Dao;

import Contract.Contract;
import Customer.UnpaidCustomer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ContractDao extends Dao{
    public ContractDao(){
        try {
            super.connect();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void create(Contract contract){
        if(contract.getInsuredCustomerID() == null){
            String query = "INSERT INTO Contract(contractorId, insuranceId, insuredCustomerId, employeeId, " +
                    "fee, premium, paymentRate, numberOfNonPayments, period, paymentTerm) VALUES(" +
                    "'"+contract.getContractorID()+"'," +
                    "'"+contract.getInsuranceID()+"'," +
                    ""+contract.getInsuredCustomerID()+"," +
                    "'"+contract.getEmployeeID()+"'," +
                    contract.getFee()+"," +
                    contract.getPremium()+"," +
                    contract.getPaymentRate()+"," +
                    contract.getNumberOfNonPayments()+"," +
                    contract.getPeriod()+"," +
                    contract.getPaymentTerm() +
                    ");";
            super.create(query);
        }
        else{
            String query = "INSERT INTO Contract(contractorId, insuranceId, insuredCustomerId, employeeId, " +
                    "fee, premium, paymentRate, numberOfNonPayments, period, paymentTerm) VALUES(" +
                    "'"+contract.getContractorID()+"'," +
                    "'"+contract.getInsuranceID()+"'," +
                    "'"+contract.getInsuredCustomerID()+"'," +
                    "'"+contract.getEmployeeID()+"'," +
                    contract.getFee()+"," +
                    contract.getPremium()+"," +
                    contract.getPaymentRate()+"," +
                    contract.getNumberOfNonPayments()+"," +
                    contract.getPeriod()+"," +
                    contract.getPaymentTerm() +
                    ");";
            super.create(query);
        }

    }
    public Contract retrieveById(int contractID) {
        String query = "SELECT * FROM Contract WHERE id  = "+
                contractID+";";
        Contract contract = null;
        try {
            ResultSet resultSet = super.retrieve(query);
            while(resultSet.next()) {
                contract = new Contract();
                contract.setId(resultSet.getInt("id"));
                contract.setContractorID(resultSet.getString("contractorID"));
                contract.setInsuranceID(resultSet.getString("insuranceID"));
                contract.setInsuredCustomerID(resultSet.getString("insuredCustomerID"));
                contract.setEmployeeID(resultSet.getString("employeeID"));
                contract.setFee(resultSet.getDouble("fee"));
                contract.setPremium(resultSet.getDouble("premium"));
                contract.setNumberOfNonPayments((resultSet.getInt(", numberOfNonPayments")));
                contract.setPaymentRate(resultSet.getDouble("paymentRate"));
                contract.setPeriod(resultSet.getInt("period"));
                contract.setSignedDate(resultSet.getString("signedDate"));
                contract.setExpirationDate(resultSet.getString("expirationDate"));
                contract.setPaymentTerm(resultSet.getInt("paymentTerm"));
                contract.setLossRatio(resultSet.getDouble("lossRatio"));
                contract.setUnderwritingState(resultSet.getString("underwritingState"));
                contract.setRejectionReasons(resultSet.getString("rejectionReasons"));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contract;
    }
    public ArrayList<Contract> retrieveAll() {
        String query = "SELECT * FROM Contract;";
        ArrayList<Contract> contractList = null;
        try {
            ResultSet resultSet = super.retrieve(query);
            contractList = new ArrayList<Contract>();
            while(resultSet.next()) {
                Contract contract = new Contract();
                contract.setId(resultSet.getInt("id"));
                contract.setContractorID(resultSet.getString("contractorID"));
                contract.setInsuranceID(resultSet.getString("insuranceID"));
                contract.setInsuredCustomerID(resultSet.getString("insuredCustomerID"));
                contract.setEmployeeID(resultSet.getString("employeeID"));
                contract.setFee(resultSet.getDouble("fee"));
                contract.setPremium(resultSet.getDouble("premium"));
                contract.setNumberOfNonPayments((resultSet.getInt(", numberOfNonPayments")));
                contract.setPaymentRate(resultSet.getDouble("paymentRate"));
                contract.setPeriod(resultSet.getInt("period"));
                contract.setSignedDate(resultSet.getString("signedDate"));
                contract.setExpirationDate(resultSet.getString("expirationDate"));
                contract.setPaymentTerm(resultSet.getInt("paymentTerm"));
                contract.setLossRatio(resultSet.getDouble("lossRatio"));
                contract.setUnderwritingState(resultSet.getString("underwritingState"));
                contract.setRejectionReasons(resultSet.getString("rejectionReasons"));
                contractList.add(contract);

            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contractList;
    }
    public ArrayList<Contract> retrieveAllCustomerWait() {
        String query = "SELECT * FROM Contract WHERE underwritingState = '가입신청';";
        ArrayList<Contract> contractList = null;
        try {
            ResultSet resultSet = super.retrieve(query);
            contractList = new ArrayList<Contract>();
            while(resultSet.next()) {
                Contract contract = new Contract();
                contract.setId(resultSet.getInt("id"));
                contract.setContractorID(resultSet.getString("contractorID"));
                contract.setInsuranceID(resultSet.getString("insuranceID"));
                contract.setInsuredCustomerID(resultSet.getString("insuredCustomerID"));
                contract.setEmployeeID(resultSet.getString("employeeID"));
                contract.setFee(resultSet.getDouble("fee"));
                contract.setPremium(resultSet.getDouble("premium"));
                contract.setNumberOfNonPayments((resultSet.getInt(", numberOfNonPayments")));
                contract.setPaymentRate(resultSet.getDouble("paymentRate"));
                contract.setPeriod(resultSet.getInt("period"));
                contract.setSignedDate(resultSet.getString("signedDate"));
                contract.setExpirationDate(resultSet.getString("expirationDate"));
                contract.setPaymentTerm(resultSet.getInt("paymentTerm"));
                contract.setLossRatio(resultSet.getDouble("lossRatio"));
                contract.setUnderwritingState(resultSet.getString("underwritingState"));
                contract.setRejectionReasons(resultSet.getString("rejectionReasons"));
                contractList.add(contract);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contractList;
    }
    public ArrayList<Contract> retrieveAllCompleteJudge() {
        String query = "SELECT * FROM Contract WHERE underwritingState = '승인' OR underwritingState = '재심사 가능' OR underwritingState = '재심사 거절';";
        ArrayList<Contract> contractList = null;
        try {
            ResultSet resultSet = super.retrieve(query);
            contractList = new ArrayList<Contract>();
            while(resultSet.next()) {
                Contract contract = new Contract();
                contract.setId(resultSet.getInt("id"));
                contract.setContractorID(resultSet.getString("contractorID"));
                contract.setInsuranceID(resultSet.getString("insuranceID"));
                contract.setInsuredCustomerID(resultSet.getString("insuredCustomerID"));
                contract.setEmployeeID(resultSet.getString("employeeID"));
                contract.setFee(resultSet.getDouble("fee"));
                contract.setPremium(resultSet.getDouble("premium"));
                contract.setNumberOfNonPayments((resultSet.getInt(", numberOfNonPayments")));
                contract.setPaymentRate(resultSet.getDouble("paymentRate"));
                contract.setPeriod(resultSet.getInt("period"));
                contract.setSignedDate(resultSet.getString("signedDate"));
                contract.setExpirationDate(resultSet.getString("expirationDate"));
                contract.setPaymentTerm(resultSet.getInt("paymentTerm"));
                contract.setLossRatio(resultSet.getDouble("lossRatio"));
                contract.setUnderwritingState(resultSet.getString("underwritingState"));
                contract.setRejectionReasons(resultSet.getString("rejectionReasons"));
                contractList.add(contract);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contractList;
    }
    public ArrayList<Contract> retrieveAllWaitStateContract() {
        String query = "SELECT * FROM Contract WHERE underwritingState='대기';";
        ArrayList<Contract> contractList = null;
        try {
            ResultSet resultSet = super.retrieve(query);
            contractList = new ArrayList<Contract>();
            while(resultSet.next()) {
                Contract contract = new Contract();
                contract.setId(resultSet.getInt("id"));
                contract.setContractorID(resultSet.getString("contractorID"));
                contract.setInsuranceID(resultSet.getString("insuranceID"));
                contract.setInsuredCustomerID(resultSet.getString("insuredCustomerID"));
                contract.setEmployeeID(resultSet.getString("employeeID"));
                contract.setFee(resultSet.getDouble("fee"));
                contract.setPremium(resultSet.getDouble("premium"));
                contract.setNumberOfNonPayments((resultSet.getInt(", numberOfNonPayments")));
                contract.setPaymentRate(resultSet.getDouble("paymentRate"));
                contract.setPeriod(resultSet.getInt("period"));
                contract.setSignedDate(resultSet.getString("signedDate"));
                contract.setExpirationDate(resultSet.getString("expirationDate"));
                contract.setPaymentTerm(resultSet.getInt("paymentTerm"));
                contract.setLossRatio(resultSet.getDouble("lossRatio"));
                contract.setUnderwritingState(resultSet.getString("underwritingState"));
                contract.setRejectionReasons(resultSet.getString("rejectionReasons"));
                contractList.add(contract);

            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contractList;
    }

    public ArrayList<UnpaidCustomer> retrieveNonPaymentInfoList() {
        String query = "SELECT Customer.name AS customerName, Customer.phoneNum, numberOfNonPayments, premium, Insurance.name AS insuranceName " +
                "FROM Contract " +
                "JOIN Insurance ON insuranceId = Insurance.id " +
                "JOIN Customer ON contractorId = Customer.id " +
                "WHERE numberOfNonPayments > 0;";
        ArrayList<UnpaidCustomer> unpaidCustomerList;
        try {
            ResultSet resultSet = super.retrieve(query);
            unpaidCustomerList = new ArrayList<>();
            while(resultSet.next()) {
                UnpaidCustomer unpaidCustomer = new UnpaidCustomer();
                unpaidCustomer.setName(resultSet.getString("customerName"));
                unpaidCustomer.setPhoneNum(resultSet.getString("phoneNum"));
                unpaidCustomer.setNumberOfNonPayments(resultSet.getInt("numberOfNonPayments"));
                unpaidCustomer.setPremium(resultSet.getDouble("premium"));
                unpaidCustomer.setInsuranceName(resultSet.getString("insuranceName"));
                unpaidCustomerList.add(unpaidCustomer);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return unpaidCustomerList;
    }

    public void update(Contract contract) {
        String query = "UPDATE Contract SET " +
                "contractorID = '" + contract.getContractorID() + "', " +
                "insuranceID = '" + contract.getInsuranceID() + "', " +
                "insuredCustomerID = '" + contract.getInsuredCustomerID() + "', " +
                "employeeID = '" + contract.getEmployeeID() + "', " +
                "fee = " + contract.getFee() + ", " +
                "premium = " + contract.getPremium() + ", " +
                "numberOfNonPayments = " + contract.getNumberOfNonPayments() + ", " +
                "paymentRate = " + contract.getPaymentRate() + ", " +
                "period = " + contract.getPeriod() + ", " +
                "signedDate = '" + contract.getSignedDate() + "', " +
                "expirationDate = '" + contract.getExpirationDate() + "', " +
                "paymentTerm = " + contract.getPaymentTerm() + ", " +
                "lossRatio = " + contract.getLossRatio() + ", " +
                "underwritingState = '" + contract.getUnderwritingState() + "', " +
                "rejectionReasons = '" + contract.getRejectionReasons() + "' " +
                "WHERE id = " + contract.getId() + ";";
        super.update(query);

//        if(contract.getSignedDate() == null && contract.getExpirationDate() == null){
//            String query = "UPDATE Contract SET " +
//                    "contractorID = '" + contract.getContractorID() + "', " +
//                    "insuranceID = '" + contract.getInsuranceID() + "', " +
//                    "insuredCustomerID = '" + contract.getInsuredCustomerID() + "', " +
//                    "employeeID = '" + contract.getEmployeeID() + "', " +
//                    "fee = " + contract.getFee() + ", " +
//                    "premium = " + contract.getPremium() + ", " +
//                    "paymentRate = " + contract.getPaymentRate() + ", " +
//                    "period = " + contract.getPeriod() + ", " +
//                    "signedDate = " + contract.getSignedDate() + ", " +
//                    "expirationDate = " + contract.getExpirationDate() + ", " +
//                    "paymentTerm = " + contract.getPaymentTerm() + ", " +
//                    "lossRatio = " + contract.getLossRatio() + ", " +
//                    "underwritingState = '" + contract.getUnderwritingState() + "', " +
//                    "rejectionReasons = '" + contract.getRejectionReasons() + "'" +
//                    "WHERE id = " + contract.getId() + ";";
//            super.update(query);
//        }
//        else if(contract.getSignedDate() == null){
//            String query = "UPDATE Contract SET " +
//                    "contractorID = '" + contract.getContractorID() + "', " +
//                    "insuranceID = '" + contract.getInsuranceID() + "', " +
//                    "insuredCustomerID = '" + contract.getInsuredCustomerID() + "', " +
//                    "employeeID = '" + contract.getEmployeeID() + "', " +
//                    "fee = " + contract.getFee() + ", " +
//                    "premium = " + contract.getPremium() + ", " +
//                    "paymentRate = " + contract.getPaymentRate() + ", " +
//                    "period = " + contract.getPeriod() + ", " +
//                    "signedDate = " + contract.getSignedDate() + ", " +
//                    "expirationDate = '" + contract.getExpirationDate() + "', " +
//                    "paymentTerm = " + contract.getPaymentTerm() + ", " +
//                    "lossRatio = " + contract.getLossRatio() + ", " +
//                    "underwritingState = '" + contract.getUnderwritingState() + "', " +
//                    "rejectionReasons = '" + contract.getRejectionReasons() + "'" +
//                    "WHERE id = " + contract.getId() + ";";
//            super.update(query);
//        }
//        else if(contract.getExpirationDate() == null){
//            String query = "UPDATE Contract SET " +
//                    "contractorID = '" + contract.getContractorID() + "', " +
//                    "insuranceID = '" + contract.getInsuranceID() + "', " +
//                    "insuredCustomerID = '" + contract.getInsuredCustomerID() + "', " +
//                    "employeeID = '" + contract.getEmployeeID() + "', " +
//                    "fee = " + contract.getFee() + ", " +
//                    "premium = " + contract.getPremium() + ", " +
//                    "paymentRate = " + contract.getPaymentRate() + ", " +
//                    "period = " + contract.getPeriod() + ", " +
//                    "signedDate = '" + contract.getSignedDate() + "', " +
//                    "expirationDate = " + contract.getExpirationDate() + ", " +
//                    "paymentTerm = " + contract.getPaymentTerm() + ", " +
//                    "lossRatio = " + contract.getLossRatio() + ", " +
//                    "underwritingState = '" + contract.getUnderwritingState() + "', " +
//                    "rejectionReasons = '" + contract.getRejectionReasons() + "'" +
//                    "WHERE id = " + contract.getId() + ";";
//            super.update(query);
//        }

    }
    public void deleteById(int contractID){
        String query = "DELETE FROM Contract WHERE id = "+contractID+";";
        super.delete(query);
    }
    public void deleteAll(){
        String query = "DELETE FROM Contract;";
        super.deleteAll(query);
    }

}
