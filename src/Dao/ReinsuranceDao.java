package Dao;

import Contract.Reinsurance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReinsuranceDao extends Dao{
    public ReinsuranceDao(){
        try {
            super.connect();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void create(Reinsurance reinsurance){
        String query = "INSERT INTO Reinsurance VALUES(" +
                reinsurance.getPeriod()+"," +
                reinsurance.getPaymentAmount()+"," +
                reinsurance.getContractRate()+"," +
                reinsurance.getLossRatio()+"," +
                "'"+reinsurance.getReinsuranceCompanyName()+"'," +
                "'"+reinsurance.getReinsuranceCompanyManagerName()+"'," +
                "'"+reinsurance.getReinsuranceCompanyManagerContract()+"'," +
                "'"+reinsurance.getContractResult()+"'," +
                "'"+reinsurance.getRejectionReasons()+"'," +
                reinsurance.getContractID()+"," +
                ");";
        super.create(query);
    }
    public Reinsurance retrieveById(int reinsuranceID) {
        String query = "SELECT * FROM Reinsurance WHERE id  = '"+
                reinsuranceID+"';";
        Reinsurance reinsurance = null;
        try {
            ResultSet resultSet = super.retrieve(query);
            while(resultSet.next()) {
                reinsurance = new Reinsurance();
                reinsurance.setId(resultSet.getInt("id"));
                reinsurance.setPeriod(resultSet.getInt("period"));
                reinsurance.setPaymentAmount(resultSet.getDouble("paymentAmount"));
                reinsurance.setContractRate(resultSet.getDouble("contractRate"));
                reinsurance.setLossRatio(resultSet.getDouble("lossRatio"));
                reinsurance.setReinsuranceCompanyName(resultSet.getString("reinsuranceCompanyName"));
                reinsurance.setReinsuranceCompanyManagerName(resultSet.getString("reinsuranceCompanyManagerName"));
                reinsurance.setReinsuranceCompanyManagerContract(resultSet.getString("reinsuranceCompanyManagerContract"));
                reinsurance.setContractResult(resultSet.getString("contractResult"));
                reinsurance.setRejectionReasons(resultSet.getString("rejectionReasons"));
                reinsurance.setContractID(resultSet.getInt("contractID"));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reinsurance;
    }
    public ArrayList<Reinsurance> retrieveAll() {
        String query = "SELECT * FROM Reinsurance;";
        ArrayList<Reinsurance> reinsuranceList = null;
        try {
            ResultSet resultSet = super.retrieve(query);
            reinsuranceList = new ArrayList<Reinsurance>();
            while(resultSet.next()) {
                Reinsurance reinsurance = new Reinsurance();
                reinsurance.setId(resultSet.getInt("id"));
                reinsurance.setPeriod(resultSet.getInt("period"));
                reinsurance.setPaymentAmount(resultSet.getDouble("paymentAmount"));
                reinsurance.setContractRate(resultSet.getDouble("contractRate"));
                reinsurance.setLossRatio(resultSet.getDouble("lossRatio"));
                reinsurance.setReinsuranceCompanyName(resultSet.getString("reinsuranceCompanyName"));
                reinsurance.setReinsuranceCompanyManagerName(resultSet.getString("reinsuranceCompanyManagerName"));
                reinsurance.setReinsuranceCompanyManagerContract(resultSet.getString("reinsuranceCompanyManagerContract"));
                reinsurance.setContractResult(resultSet.getString("contractResult"));
                reinsurance.setRejectionReasons(resultSet.getString("rejectionReasons"));
                reinsurance.setContractID(resultSet.getInt("contractID"));
                reinsuranceList.add(reinsurance);

            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reinsuranceList;
    }
    public void update(Reinsurance reinsurance){
        String query = "UPDATE Reinsurance SET " +
                "period = " + reinsurance.getPeriod() + ", " +
                "paymentAmount = " + reinsurance.getPaymentAmount() + ", " +
                "contractRate = " + reinsurance.getLossRatio() + ", " +
                "lossRatio = " + reinsurance.getLossRatio() + ", " +
                "reinsuranceCompanyName = '" + reinsurance.getReinsuranceCompanyName() + "', " +
                "reinsuranceCompanyManagerName = '" + reinsurance.getReinsuranceCompanyManagerName() + "', " +
                "reinsuranceCompanyManagerContract = '" + reinsurance.getReinsuranceCompanyManagerContract() + "', " +
                "contractResult = '" + reinsurance.getContractResult() + "', " +
                "rejectionReasons = '" + reinsurance.getRejectionReasons() + "', " +
                "contractID = " + reinsurance.getContractID() + ", " +
                "WHERE id = " + reinsurance.getId() + ";";
        super.update(query);
    }
    public void deleteById(int reinsuranceID){
        String query = "DELETE FROM Reinsurance WHERE id = "+reinsuranceID+";";
        super.delete(query);
    }
    public void deleteAll(){
        String query = "DELETE FROM Reinsurance;";
        super.deleteAll(query);
    }

}
