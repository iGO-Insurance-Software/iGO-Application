package Dao;

import Customer.Customer;
import Customer.InsuredCustomer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InsuredCustomerDao extends Dao{
    public InsuredCustomerDao(){
        try {
            super.connect();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void create(Customer customer){
        InsuredCustomer insuredCustomer = (InsuredCustomer) customer;
        String query = "INSERT INTO InsuredCustomer VALUES(" +
                "'"+insuredCustomer.getId()+"'," +
                "'"+insuredCustomer.getFamilyHistory()+"'," +
                "'"+insuredCustomer.getHealthCertificate()+"'," +
                "'"+insuredCustomer.getEmploymentCertificate()+"'," +
                "'"+insuredCustomer.getInheritanceCertificate()+"'," +
                "'"+insuredCustomer.getBankbookCopy()+"',"+
                "'"+insuredCustomer.getAccidentCertificate()+"',"+
                "'"+insuredCustomer.getMedicalCertificate()+"'"+
                ");";
        super.create(query);
    }
    public InsuredCustomer retrieveById(String insuredCustomerID) {
        String query = "SELECT Customer.id, Customer.type, Customer.name, Customer.rrn, Customer.age, Customer.gender, " +
                "Customer.phoneNum, Customer.occupation, InsuredCustomer.famililyHistory, InsuredCustomer.healthCertificate, " +
                "InsuredCustomer.employmentCertificate, InsuredCustomer.inheritanceCertificate, InsuredCustomer.accidentCertificate, " +
                "InsuredCustomer.medicalCertificate, InsuredCustomer.bankbookCopy " +
                "FROM Customer " +
                "INNER JOIN InsuredCustomer ON Customer.id = InsuredCustomer.id " +
                "WHERE InsuredCustomer.id = '"+insuredCustomerID+"';";
        InsuredCustomer cust = null;
        try {
            ResultSet resultSet = super.retrieve(query);
            while(resultSet.next()) {
                cust = new InsuredCustomer();
                cust.setId(resultSet.getString("id"));
                cust.setType(resultSet.getString("type"));
                cust.setName(resultSet.getString("name"));
                cust.setRrn(resultSet.getString("rrn"));
                cust.setAge(Integer.parseInt(resultSet.getString("age")));
                cust.setGender(resultSet.getString("gender"));
                cust.setPhoneNum(resultSet.getString("phoneNum"));
                cust.setOccupation(resultSet.getString("occupation"));
                cust.setFamilyHistory(resultSet.getString("famililyHistory"));
                cust.setHealthCertificate(resultSet.getString("healthCertificate"));
                cust.setEmploymentCertificate(resultSet.getString("employmentCertificate"));
                cust.setInheritanceCertificate(resultSet.getString("inheritanceCertificate"));
                cust.setAccidentCertificate(resultSet.getString("accidentCertificate"));
                cust.setMedicalCertificate(resultSet.getString("medicalCertificate"));
                cust.setBankbookCopy(resultSet.getString("bankbookCopy"));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cust;
    }
    public ArrayList<InsuredCustomer> retrieveAll() {
        String query = "SELECT Customer.id, Customer.type, Customer.name, Customer.rrn, Customer.age, Customer.gender, " +
                "Customer.phoneNum, Customer.occupation, InsuredCustomer.famililyHistory, InsuredCustomer.healthCertificate, " +
                "InsuredCustomer.employmentCertificate, InsuredCustomer.inheritanceCertificate, InsuredCustomer.accidentCertificate, " +
                "InsuredCustomer.medicalCertificate, InsuredCustomer.bankbookCopy " +
                "FROM Customer " +
                "INNER JOIN InsuredCustomer ON Customer.id = InsuredCustomer.id;";
        ArrayList<InsuredCustomer> insuredCustomerList = null;
        try {
            ResultSet resultSet = super.retrieve(query);
            insuredCustomerList = new ArrayList<InsuredCustomer>();
            while(resultSet.next()) {
                InsuredCustomer cust = new InsuredCustomer();
                cust.setId(resultSet.getString("id"));
                cust.setType(resultSet.getString("type"));
                cust.setName(resultSet.getString("name"));
                cust.setRrn(resultSet.getString("rrn"));
                cust.setAge(Integer.parseInt(resultSet.getString("age")));
                cust.setGender(resultSet.getString("gender"));
                cust.setPhoneNum(resultSet.getString("phoneNum"));
                cust.setOccupation(resultSet.getString("occupation"));
                cust.setFamilyHistory(resultSet.getString("famililyHistory"));
                cust.setHealthCertificate(resultSet.getString("healthCertificate"));
                cust.setEmploymentCertificate(resultSet.getString("employmentCertificate"));
                cust.setInheritanceCertificate(resultSet.getString("inheritanceCertificate"));
                cust.setAccidentCertificate(resultSet.getString("accidentCertificate"));
                cust.setMedicalCertificate(resultSet.getString("medicalCertificate"));
                cust.setBankbookCopy(resultSet.getString("bankbookCopy"));
                insuredCustomerList.add(cust);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return insuredCustomerList;
    }

    public void update(Customer customer){
        InsuredCustomer insuredCustomer = (InsuredCustomer) customer;
        String query = "UPDATE InsuredCustomer SET " +
                "familyHistory = '" + insuredCustomer.getFamilyHistory() + "', " +
                "healthCertificate = '" + insuredCustomer.getHealthCertificate() + "', " +
                "employmentCertificate = '" + insuredCustomer.getEmploymentCertificate() + "', " +
                "inheritanceCertificate = '" + insuredCustomer.getInheritanceCertificate() + "', " +
                "bankbookCopy = '" + insuredCustomer.getBankbookCopy() + "', " +
                "accidentCertificate = '" + insuredCustomer.getAccidentCertificate() + "', " +
                "medicalCertificate = '" + insuredCustomer.getMedicalCertificate() + "' " +
                "WHERE id = '" + insuredCustomer.getId() + "';";
        super.update(query);
    }
    public void deleteById(String insuredCustomerID){
        String query = "DELETE FROM InsuredCustomer WHERE id = '"+insuredCustomerID+"';";
        super.delete(query);
    }
    public void deleteAll(){
        String query = "DELETE FROM InsuredCustomer;";
        super.deleteAll(query);
    }
}
