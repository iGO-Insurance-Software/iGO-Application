package Dao;

import Insurance.Insurance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InsuranceDao extends Dao {
    public InsuranceDao(){
        try {
            super.connect();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void create(Insurance insurance){
        String query = "INSERT INTO Insurance VALUES(" +
                "'"+insurance.getId()+"'," +
                "'"+insurance.getDescription()+"'," +
                "'"+insurance.getName()+"'," +
                "'"+insurance.getPrice()+"'," +
                "'"+insurance.getDetailedCategory()+"'," +
                "'"+insurance.getPaymentTerm()+"'" +
                ");";
        super.create(query);
    }
    public Insurance retrieveById(int insuranceId) {
        String query = "SELECT * FROM Insurance WHERE id  = '"+
                insuranceId+"';";
        Insurance insurance = null;
        try {
            ResultSet resultSet = super.retrieve(query);
            while(resultSet.next()) {
                insurance = new Insurance();
                insurance.setId(resultSet.getString("id"));
                insurance.setDescription(resultSet.getString("description"));
                insurance.setName(resultSet.getString("name"));
                insurance.setPrice(resultSet.getDouble("price"));
                insurance.setDetailedCategory(resultSet.getString("detailedCategory"));
                insurance.setPaymentTerm(resultSet.getInt("paymentTerm"));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return insurance;
    }
    public ArrayList<Insurance> retrieveAll() {
        String query = "SELECT * FROM Insurance;";
        ArrayList<Insurance> insuranceList = null;
        try {
            ResultSet resultSet = super.retrieve(query);
            insuranceList = new ArrayList<Insurance>();
            while(resultSet.next()) {
                Insurance insurance = new Insurance();
                insurance.setId(resultSet.getString("id"));
                insurance.setDescription(resultSet.getString("description"));
                insurance.setName(resultSet.getString("name"));
                insurance.setPrice(resultSet.getDouble("price"));
                insurance.setDetailedCategory(resultSet.getString("detailedCategory"));
                insurance.setPaymentTerm(resultSet.getInt("paymentTerm"));
                insuranceList.add(insurance);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return insuranceList;
    }

    public void update(Insurance insurance){
        String query = "UPDATE Insurance SET " +
                "id = '" + insurance.getId() + "', " +
                "description = '" + insurance.getDescription() + "', " +
                "name = " + insurance.getName() + ", " +
                "price = '" + insurance.getPrice() + "', " +
                "detailedCategory = '" + insurance.getDetailedCategory() + "', " +
                "paymentTerm = '" + insurance.getPaymentTerm() + "', " + "';";
        super.update(query);
    }
    public void deleteById(String insuranceId){
        String query = "DELETE FROM Insurance WHERE id = '"+insuranceId+"';";
        super.delete(query);
    }
    public void deleteAll(){
        String query = "DELETE FROM Insurance;";
        super.deleteAll(query);
    }
}
