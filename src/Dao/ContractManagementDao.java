package Dao;

public class ContractManagementDao extends Dao {
    public ContractManagementDao() {
        try {
            super.connect();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
