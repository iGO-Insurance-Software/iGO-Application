package Dao;

import Prototype.Prototype;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PrototypeDao extends Dao {
    public PrototypeDao() {
        try {
            super.connect();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void create(Prototype prototype) {
        String query = "INSERT INTO Prototype VALUES ('" +
                prototype.getId()+ "', " +
                "'"+ prototype.getDescription() + "', " +
                "'"+ prototype.getFeedbacks() + "', " +
                "'"+ prototype.getRequirements() + "', " +
                "'"+ prototype.getStatus() + "', " +
                "'"+ prototype.getName() + "', " +
                "'"+ prototype.getRisks() + "', " +
                "'"+ prototype.getDeveloperID() + "', " +
                "'"+ prototype.getDetailedCategory() + "', " +
                "'"+ prototype.getCategory() + "'," +
                prototype.getPrice() +"," +
                prototype.getPaymentTerm() +
                ");";
        super.create(query);
    }
    public Prototype retrieveById(String prototypeID) {
        String query = "SELECT * FROM Prototype WHERE id  = '"+
                prototypeID+"';";
        Prototype prototype = null;
        try {
            ResultSet resultSet = super.retrieve(query);
            while(resultSet.next()) {
                prototype = new Prototype();
                prototype.setId(resultSet.getString("id"));
                prototype.setDescription(resultSet.getString("Description"));
                prototype.setFeedbacks(resultSet.getString("Feedbacks"));
                prototype.setRequirements(resultSet.getString("Requirements"));
                prototype.setStatus(resultSet.getString("Status"));
                prototype.setName(resultSet.getString("Name"));
                prototype.setRisks(resultSet.getString("Risks"));
                prototype.setDeveloperID(resultSet.getString("DeveloperID"));
                prototype.setDetailedCategory(resultSet.getString("DetailedCategory"));
                prototype.setCategory(resultSet.getString("Category"));
                prototype.setPrice(resultSet.getDouble("Price"));
                prototype.setPaymentTerm(resultSet.getInt("PaymentTerm"));

            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return prototype;
    }
    public ArrayList<Prototype> retrieveAll() {
        String query = "SELECT * FROM Prototype;";
        ArrayList<Prototype> prototypeList = null;
        try {
            ResultSet resultSet = super.retrieve(query);
            prototypeList = new ArrayList<Prototype>();
            while(resultSet.next()) {
                Prototype  prototype = new Prototype();
                prototype.setId(resultSet.getString("id"));
                prototype.setDescription(resultSet.getString("Description"));
                prototype.setFeedbacks(resultSet.getString("Feedbacks"));
                prototype.setRequirements(resultSet.getString("Requirements"));
                prototype.setStatus(resultSet.getString("Status"));
                prototype.setName(resultSet.getString("Name"));
                prototype.setRisks(resultSet.getString("Risks"));
                prototype.setDeveloperID(resultSet.getString("DeveloperID"));
                prototype.setDetailedCategory(resultSet.getString("DetailedCategory"));
                prototype.setCategory(resultSet.getString("Category"));
                prototype.setPrice(resultSet.getDouble("Price"));
                prototype.setPaymentTerm(resultSet.getInt("PaymentTerm"));

                prototypeList.add(prototype);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return prototypeList;
    }
    public void update(Prototype prototype) {
        String query = "UPDATE Prototype SET " +
                "description = '" + prototype.getDescription() + "', " +
                "feedbacks = '" + prototype.getFeedbacks() + "', " +
                "requirements = '" + prototype.getRequirements() + "', " +
                "status = '" + prototype.getStatus() + "', " +
                "name = '" + prototype.getName() + "', " +
                "risks = '" + prototype.getRisks() + "', " +
                "developerID = '" + prototype.getDeveloperID() + "', " +
                "detailedCategory = '" + prototype.getDetailedCategory() + "', " +
                "category = '" + prototype.getCategory() + "', " +
                "price = " + prototype.getPrice() + ", " +
                "paymentTerm = " + prototype.getPaymentTerm() + " "+
                "WHERE id = '" + prototype.getId() + "';";
        super.update(query);
    }
    public void deleteById(String prototypeId) {
        String query = "DELETE FROM Prototype WHERE id = '" + prototypeId + "';";
        super.delete(query);
    }
    public void deleteAll() {
        String query = "DELETE FROM Prototype;";
        super.deleteAll(query);
    }
}
