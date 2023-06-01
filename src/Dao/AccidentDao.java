package Dao;

import Accident.Accident;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccidentDao extends Dao{
    public AccidentDao(){
        try {
            super.connect();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void create(Accident accident){
        String query = "INSERT INTO Accident " +
                "(customerID, receptionEmployeeID, compensationEmployeeID, accidentDate, accidentPlace, accidentType, accidentOutline, " +
                "existOfDestroyer, destroyerName, destroyerPhoneNum, isUrgent, status, compensationMoney, indemnityMoney, " +
                "indemnityDueDate, isWinLawsuit, lawsuitCost, winOrLoseMoney) VALUES (" +
                "'"+accident.getCustomerID()+"'," +
                "'"+accident.getReceptionEmployeeID()+"'," +
                "'"+accident.getCompensationEmployeeID()+"'," +
                "'"+accident.getAccidentDateToString()+"'," +
                "'"+accident.getAccidentPlace()+"'," +
                "'"+accident.getAccidentType()+"'," +
                "'"+accident.getAccidentOutline()+"'," +
                accident.getExistOfDestroyer()+"," +
                "'"+accident.getDestroyerName()+"'," +
                "'"+accident.getDestroyerPhoneNum()+"'," +
                accident.getIsUrgent()+"," +
                "'"+accident.getStatus()+"'," +
                accident.getCompensationMoney()+"," +
                accident.getIndemnityMoney()+"," +
                "'"+accident.getIndemnityDueDateToString()+"'," +
                accident.getIsWinLawsuit()+"," +
                accident.getLawsuitCost()+"," +
                accident.getWinOrLoseMoney()+
                ");";
        super.create(query);
    }
    public Accident retrieveById(int accidentID) {
        String query = "SELECT * FROM Accident WHERE id  = "+
                accidentID+";";
        Accident accident = null;
        try {
            ResultSet resultSet = super.retrieve(query);
            while(resultSet.next()) {
                accident = new Accident();
                accident.setId(resultSet.getInt("id"));
                accident.setCustomerID(resultSet.getString("customerID"));
                accident.setReceptionEmployeeID(resultSet.getString("receptionEmployeeID"));
                accident.setCompensationEmployeeID(resultSet.getString("compensationEmployeeID"));
                accident.setAccidentDateStringtoDate(resultSet.getString("accidentDate"));
                accident.setAccidentPlace(resultSet.getString("accidentPlace"));
                accident.setAccidentType(resultSet.getString("accidentType"));
                accident.setAccidentOutline(resultSet.getString("accidentOutline"));
                accident.setExistOfDestroyer(resultSet.getBoolean("existOfDestroyer"));
                accident.setDestroyerName(resultSet.getString("destroyerName"));
                accident.setDestroyerPhoneNum(resultSet.getString("destroyerPhoneNum"));
                accident.setIsUrgent(resultSet.getBoolean("isUrgent"));
                accident.setStatus(resultSet.getString("status"));
                accident.setCompensationMoney(resultSet.getDouble("compensationMoney"));
                accident.setIndemnityMoney(resultSet.getDouble("indemnityMoney"));
                accident.setIndemnityDueDateStringToDate(resultSet.getString("indemnityDueDate"));
                accident.setIsWinLawsuit(resultSet.getBoolean("isWinLawsuit"));
                accident.setLawsuitCost(resultSet.getDouble("lawsuitCost"));
                accident.setWinOrLoseMoney(resultSet.getInt("winOrLoseMoney"));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return accident;
    }
    public ArrayList<Accident> retrieveByReceptionEmployeeID(String receptionEmployeeID) {
        String query = "SELECT * FROM Accident WHERE receptionEmployeeID = '"+receptionEmployeeID+"';";
        ArrayList<Accident> accidentList = null;
        try {
            ResultSet resultSet = super.retrieve(query);
            accidentList = new ArrayList<Accident>();
            while(resultSet.next()) {
                Accident accident = new Accident();
                accident.setId(resultSet.getInt("id"));
                accident.setCustomerID(resultSet.getString("customerID"));
                accident.setReceptionEmployeeID(resultSet.getString("receptionEmployeeID"));
                accident.setCompensationEmployeeID(resultSet.getString("compensationEmployeeID"));
                accident.setAccidentDateStringtoDate(resultSet.getString("accidentDate"));
                accident.setAccidentPlace(resultSet.getString("accidentPlace"));
                accident.setAccidentType(resultSet.getString("accidentType"));
                accident.setAccidentOutline(resultSet.getString("accidentOutline"));
                accident.setExistOfDestroyer(resultSet.getBoolean("existOfDestroyer"));
                accident.setDestroyerName(resultSet.getString("destroyerName"));
                accident.setDestroyerPhoneNum(resultSet.getString("destroyerPhoneNum"));
                accident.setIsUrgent(resultSet.getBoolean("isUrgent"));
                accident.setStatus(resultSet.getString("status"));
                accident.setCompensationMoney(resultSet.getDouble("compensationMoney"));
                accident.setIndemnityMoney(resultSet.getDouble("indemnityMoney"));
                accident.setIndemnityDueDateStringToDate(resultSet.getString("indemnityDueDate"));
                accident.setIsWinLawsuit(resultSet.getBoolean("isWinLawsuit"));
                accident.setLawsuitCost(resultSet.getDouble("lawsuitCost"));
                accident.setWinOrLoseMoney(resultSet.getInt("winOrLoseMoney"));
                accidentList.add(accident);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return accidentList;
    }

    public ArrayList<Accident> retrieveAll() {
        String query = "SELECT * FROM Accident;";
        ArrayList<Accident> accidentList = null;
        try {
            ResultSet resultSet = super.retrieve(query);
            accidentList = new ArrayList<Accident>();
            while(resultSet.next()) {
                Accident accident = new Accident();
                accident.setId(resultSet.getInt("id"));
                accident.setCustomerID(resultSet.getString("customerID"));
                accident.setReceptionEmployeeID(resultSet.getString("receptionEmployeeID"));
                accident.setCompensationEmployeeID(resultSet.getString("compensationEmployeeID"));
                accident.setAccidentDateStringtoDate(resultSet.getString("accidentDate"));
                accident.setAccidentPlace(resultSet.getString("accidentPlace"));
                accident.setAccidentType(resultSet.getString("accidentType"));
                accident.setAccidentOutline(resultSet.getString("accidentOutline"));
                accident.setExistOfDestroyer(resultSet.getBoolean("existOfDestroyer"));
                accident.setDestroyerName(resultSet.getString("destroyerName"));
                accident.setDestroyerPhoneNum(resultSet.getString("destroyerPhoneNum"));
                accident.setIsUrgent(resultSet.getBoolean("isUrgent"));
                accident.setStatus(resultSet.getString("status"));
                accident.setCompensationMoney(resultSet.getDouble("compensationMoney"));
                accident.setIndemnityMoney(resultSet.getDouble("indemnityMoney"));
                accident.setIndemnityDueDateStringToDate(resultSet.getString("indemnityDueDate"));
                accident.setIsWinLawsuit(resultSet.getBoolean("isWinLawsuit"));
                accident.setLawsuitCost(resultSet.getDouble("lawsuitCost"));
                accident.setWinOrLoseMoney(resultSet.getInt("winOrLoseMoney"));
                accidentList.add(accident);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return accidentList;
    }
    public void update(Accident accident){
        String query = "UPDATE Accident SET " +
                "customerID = '" + accident.getCustomerID() + "', " +
                "receptionEmployeeID = '" + accident.getReceptionEmployeeID() + "', " +
                "compensationEmployeeID = '" + accident.getCompensationEmployeeID() + "', " +
                "accidentDate = '" + accident.getAccidentDateToString() + "', " +
                "accidentPlace = '" + accident.getAccidentPlace() + "', " +
                "accidentType = '" + accident.getAccidentType() + "', " +
                "accidentOutline = '" + accident.getAccidentOutline() + "', " +
                "existOfDestroyer = " + accident.getExistOfDestroyer() + ", " +
                "destroyerName = '" + accident.getDestroyerName() + "', " +
                "destroyerPhoneNum = '" + accident.getDestroyerPhoneNum() + "', " +
                "isUrgent = " + accident.getIsUrgent() + ", " +
                "status = '" + accident.getStatus() + "', " +
                "compensationMoney = " + accident.getCompensationMoney() + ", " +
                "indemnityMoney = " + accident.getIndemnityMoney() + ", " +
                "indemnityDueDate = '" + accident.getIndemnityDueDateToString() + "', " +
                "isWinLawsuit = " + accident.getIsWinLawsuit() + ", " +
                "lawsuitCost = " + accident.getLawsuitCost() + ", " +
                "winOrLoseMoney = " + accident.getWinOrLoseMoney() + " " +
                "WHERE id = " + accident.getId() + ";";
        super.update(query);
    }
    public void deleteById(int accidentID){
        String query = "DELETE FROM Accident WHERE id = "+accidentID+";";
        super.delete(query);
    }
    public void deleteAll(){
        String query = "DELETE FROM Accident;";
        super.deleteAll(query);
    }
}
