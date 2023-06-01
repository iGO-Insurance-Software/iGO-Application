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
        String query = "SELECT * " +
                "FROM Customer " +
                "INNER JOIN InsuredCustomer ON Customer.id = InsuredCustomer.id " +
                "WHERE InsuredCustomer.id = '"+insuredCustomerID+"';";
        InsuredCustomer isCustomer = null;
        try {
            ResultSet resultSet = super.retrieve(query);
            while(resultSet.next()) {
                isCustomer = new InsuredCustomer();
                isCustomer.setId(resultSet.getString("id"));
                isCustomer.setType(resultSet.getString("type"));
                isCustomer.setName(resultSet.getString("name"));
                isCustomer.setRrn(resultSet.getString("rrn"));
                isCustomer.setAge(Integer.parseInt(resultSet.getString("age")));
                isCustomer.setGender(resultSet.getString("gender"));
                isCustomer.setPhoneNum(resultSet.getString("phoneNum"));
                isCustomer.setOccupation(resultSet.getString("occupation"));
            }
        query = "SELECT * FROM InsuredCustomer " +
                "WHERE id = '"+insuredCustomerID+"';";
            resultSet = super.retrieve(query);
            while(resultSet.next()) {
                isCustomer.setFamilyHistory(resultSet.getString("familyHistory"));
                isCustomer.setHealthCertificate(resultSet.getString("healthCertificate"));
                isCustomer.setEmploymentCertificate(resultSet.getString("employmentCertificate"));
                isCustomer.setInheritanceCertificate(resultSet.getString("inheritanceCertificate"));
                isCustomer.setAccidentCertificate(resultSet.getString("accidentCertificate"));
                isCustomer.setMedicalCertificate(resultSet.getString("medicalCertificate"));
                isCustomer.setBankbookCopy(resultSet.getString("bankbookCopy"));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isCustomer;
    }
//    public InsuredCustomer retrieveById(String insuredCustomerID) {
//        String query = "SELECT Customer.id, Customer.type, Customer.name, Customer.rrn, Customer.age, Customer.gender, " +
//                "Customer.phoneNum, Customer.occupation, InsuredCustomer.familyHistory, InsuredCustomer.healthCertificate, " +
//                "InsuredCustomer.employmentCertificate, InsuredCustomer.inheritanceCertificate, InsuredCustomer.accidentCertificate, " +
//                "InsuredCustomer.medicalCertificate, InsuredCustomer.bankbookCopy " +
//                "FROM Customer " +
//                "INNER JOIN InsuredCustomer ON Customer.id = InsuredCustomer.id " +
//                "WHERE InsuredCustomer.id = '"+insuredCustomerID+"';";
//        InsuredCustomer isCustomer = null;
//        try {
//            ResultSet resultSet = super.retrieve(query);
//            while(resultSet.next()) {
//                isCustomer = new InsuredCustomer();
//                isCustomer.setId(resultSet.getString("id"));
//                isCustomer.setType(resultSet.getString("type"));
//                isCustomer.setName(resultSet.getString("name"));
//                isCustomer.setRrn(resultSet.getString("rrn"));
//                isCustomer.setAge(Integer.parseInt(resultSet.getString("age")));
//                isCustomer.setGender(resultSet.getString("gender"));
//                isCustomer.setPhoneNum(resultSet.getString("phoneNum"));
//                isCustomer.setOccupation(resultSet.getString("occupation"));
//                isCustomer.setFamilyHistory(resultSet.getString("famililyHistory"));
//                isCustomer.setHealthCertificate(resultSet.getString("healthCertificate"));
//                isCustomer.setEmploymentCertificate(resultSet.getString("employmentCertificate"));
//                isCustomer.setInheritanceCertificate(resultSet.getString("inheritanceCertificate"));
//                isCustomer.setAccidentCertificate(resultSet.getString("accidentCertificate"));
//                isCustomer.setMedicalCertificate(resultSet.getString("medicalCertificate"));
//                isCustomer.setBankbookCopy(resultSet.getString("bankbookCopy"));
//            }
//            resultSet.close();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return isCustomer;
//    }
    public ArrayList<InsuredCustomer> retrieveAll() {
        String query = "SELECT Customer.id, Customer.type, Customer.name, Customer.rrn, Customer.age, Customer.gender, " +
                "Customer.phoneNum, Customer.occupation, InsuredCustomer.familyHistory, InsuredCustomer.healthCertificate, " +
                "InsuredCustomer.employmentCertificate, InsuredCustomer.inheritanceCertificate, InsuredCustomer.accidentCertificate, " +
                "InsuredCustomer.medicalCertificate, InsuredCustomer.bankbookCopy " +
                "FROM Customer " +
                "INNER JOIN InsuredCustomer ON Customer.id = InsuredCustomer.id;";
        ArrayList<InsuredCustomer> insuredCustomerList = null;
        try {
            ResultSet resultSet = super.retrieve(query);
            insuredCustomerList = new ArrayList<InsuredCustomer>();
            while(resultSet.next()) {
                InsuredCustomer isCustomer = new InsuredCustomer();
                isCustomer.setId(resultSet.getString("id"));
                isCustomer.setType(resultSet.getString("type"));
                isCustomer.setName(resultSet.getString("name"));
                isCustomer.setRrn(resultSet.getString("rrn"));
                isCustomer.setAge(Integer.parseInt(resultSet.getString("age")));
                isCustomer.setGender(resultSet.getString("gender"));
                isCustomer.setPhoneNum(resultSet.getString("phoneNum"));
                isCustomer.setOccupation(resultSet.getString("occupation"));
                isCustomer.setFamilyHistory(resultSet.getString("familyHistory"));
                isCustomer.setHealthCertificate(resultSet.getString("healthCertificate"));
                isCustomer.setEmploymentCertificate(resultSet.getString("employmentCertificate"));
                isCustomer.setInheritanceCertificate(resultSet.getString("inheritanceCertificate"));
                isCustomer.setAccidentCertificate(resultSet.getString("accidentCertificate"));
                isCustomer.setMedicalCertificate(resultSet.getString("medicalCertificate"));
                isCustomer.setBankbookCopy(resultSet.getString("bankbookCopy"));
                insuredCustomerList.add(isCustomer);
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
