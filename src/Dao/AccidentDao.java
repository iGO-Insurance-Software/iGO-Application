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
        String query = "INSERT INTO Accident VALUES(" +
                "'"+accident.getCustomerID()+"'," +
                "'"+accident.getReceiptionEmployeeID()+"'," +
                "'"+accident.getCompensationEmployeeID()+"'," +
                "'"+accident.getAccidentDateToString()+"'," +
                "'"+accident.getAccidentPlace()+"," +
                "'"+accident.getAccidentType()+"'," +
                "'"+accident.getAccidentOutline()+"'," +
                accident.getExistOfDestroyer()+"," +
                "'"+accident.getDestroyerName()+"'," +
                accident.getDestroyerPhoneNum()+"'," +
                accident.getIsUrgent()+"," +
                "'"+accident.getStatus()+"'" +
                accident.getCompensationMoney()+"," +
                accident.getIndemnityMoney()+"," +
                "'"+accident.getIndemnityDueDateToString()+"'" +
                accident.getIsWinLawsuit()+"," +
                accident.getWinOrLoseMoney()+"," +
                ");";
        super.create(query);
    }
    public Accident retrieveById(String accidentID) {
        String query = "SELECT * FROM Accident WHERE id  = '"+
                accidentID+"';";
        Accident acdt = null;
        try {
            ResultSet resultSet = super.retrieve(query);
            while(resultSet.next()) {
                acdt = new Accident();
                acdt.setId(resultSet.getInt("id"));
                acdt.setCustomerID(resultSet.getString("customerID"));
                acdt.setReceiptionEmployeeID(resultSet.getString("receiptionEmployeeID"));
                acdt.setCompensationEmployeeID(resultSet.getString("compensationEmployeeID"));
                acdt.setAccidentDateStringtoDate(resultSet.getString("accidentDate"));
                acdt.setAccidentPlace(resultSet.getString("accidentPlace"));
                acdt.setAccidentType(resultSet.getString("accidentType"));
                acdt.setAccidentOutline(resultSet.getString("accidentOutline"));
                acdt.setExistOfDestroyer(resultSet.getBoolean("existOfDestroyer"));
                acdt.setDestroyerName(resultSet.getString("destroyerName"));
                acdt.setDestroyerPhoneNum(resultSet.getString("destroyerPhoneNum"));
                acdt.setIsUrgent(resultSet.getBoolean("isUrgent"));
                acdt.setStatus(resultSet.getString("status"));
                acdt.setCompensationMoney(resultSet.getDouble("compensationMoney"));
                acdt.setIndemnityMoney(resultSet.getDouble("indemnityMoney"));
                acdt.setIndemnityDueDateStringToDate(resultSet.getString("indemnityDueDate"));
                acdt.setIsWinLawsuit(resultSet.getBoolean("isWinLawsuit"));
                acdt.setWinOrLoseMoney(resultSet.getInt("winOrLoseMoney"));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return acdt;
    }
    public ArrayList<Accident> retrieveAll() {
        String query = "SELECT * FROM Accident;";
        ArrayList<Accident> accidentList = null;
        try {
            ResultSet resultSet = super.retrieve(query);
            accidentList = new ArrayList<Accident>();
            while(resultSet.next()) {
                Accident acdt = new Accident();
                acdt.setId(resultSet.getInt("id"));
                acdt.setCustomerID(resultSet.getString("customerID"));
                acdt.setReceiptionEmployeeID(resultSet.getString("receiptionEmployeeID"));
                acdt.setCompensationEmployeeID(resultSet.getString("compensationEmployeeID"));
                acdt.setAccidentDateStringtoDate(resultSet.getString("accidentDate"));
                acdt.setAccidentPlace(resultSet.getString("accidentPlace"));
                acdt.setAccidentType(resultSet.getString("accidentType"));
                acdt.setAccidentOutline(resultSet.getString("accidentOutline"));
                acdt.setExistOfDestroyer(resultSet.getBoolean("existOfDestroyer"));
                acdt.setDestroyerName(resultSet.getString("destroyerName"));
                acdt.setDestroyerPhoneNum(resultSet.getString("destroyerPhoneNum"));
                acdt.setIsUrgent(resultSet.getBoolean("isUrgent"));
                acdt.setStatus(resultSet.getString("status"));
                acdt.setCompensationMoney(resultSet.getDouble("compensationMoney"));
                acdt.setIndemnityMoney(resultSet.getDouble("indemnityMoney"));
                acdt.setIndemnityDueDateStringToDate(resultSet.getString("indemnityDueDate"));
                acdt.setIsWinLawsuit(resultSet.getBoolean("isWinLawsuit"));
                acdt.setWinOrLoseMoney(resultSet.getInt("winOrLoseMoney"));
                accidentList.add(acdt);

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
                "receiptionEmployeeID = '" + accident.getReceiptionEmployeeID() + "', " +
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
                "winOrLoseMoney = " + accident.getWinOrLoseMoney() + " " +
                "WHERE id = '" + accident.getId() + "';";
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
