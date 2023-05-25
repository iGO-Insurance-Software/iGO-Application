package Dao;
import Customer.Customer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDao extends Dao{
    private InsuredCustomerDao insuredCustomerDao;
    public CustomerDao(){
        insuredCustomerDao = new InsuredCustomerDao();
        try {
            super.connect();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void create(Customer customer){
        String query = "INSERT INTO Customer VALUES(" +
                "'"+customer.getId()+"'," +
                "'"+customer.getType()+"'," +
                "'"+customer.getName()+"'," +
                customer.getRrn()+"," +
                customer.getAge()+"," +
                "'"+customer.getGender()+"'," +
                "'"+customer.getPhoneNum()+"'," +
                "'"+customer.getOccupation()+"'"+
                ");";
        super.create(query);
    }
    public Customer retrieveById(String customerID) {
        String query = "SELECT * FROM Customer WHERE id  = '"+
                customerID+"';";
        Customer cust = null;
        try {
            ResultSet resultSet = super.retrieve(query);
            if(resultSet!=null) {
                cust = new Customer();
                cust.setId(resultSet.getString("customerId"));
                cust.setType(resultSet.getString("type"));
                cust.setName(resultSet.getString("name"));
                cust.setRrn(resultSet.getString("rrn"));
                cust.setAge(Integer.parseInt(resultSet.getString("age")));
                cust.setGender(resultSet.getString("gender"));
                cust.setPhoneNum(resultSet.getString("phoneNum"));
                cust.setOccupation(resultSet.getString("occupation"));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cust;
    }
    public ArrayList<Customer> retrieveAll() {
        String query = "SELECT * FROM Customer;";
        ArrayList<Customer> customerList = null;
        try {
            ResultSet resultSet = super.retrieve(query);
            customerList = new ArrayList<Customer>();
            while(resultSet.next()) {
                Customer cust = new Customer();
                cust.setId(resultSet.getString("id"));
                cust.setType(resultSet.getString("type"));
                cust.setName(resultSet.getString("name"));
                cust.setRrn(resultSet.getString("rrn"));
                cust.setAge(Integer.parseInt(resultSet.getString("age")));
                cust.setGender(resultSet.getString("gender"));
                cust.setPhoneNum(resultSet.getString("phoneNum"));
                cust.setOccupation(resultSet.getString("occupation"));
                customerList.add(cust);

            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customerList;
    }
    public ArrayList<Customer> retrieveAllCustomer() {
        String query = "SELECT * FROM Customer;";
        ArrayList<Customer> customerList = null;
        try {
            ResultSet resultSet = super.retrieve(query);
            customerList = new ArrayList<Customer>();
            while(resultSet.next()) {
                String type = resultSet.getString("type");
                if(type.equals("InsuredCustomer")||type.equals("Contractor")) continue;//하위 테이블일 경우 여기서 조회 하지 않고 하위Dao로 넘김
                Customer cust = new Customer();
                cust.setId(resultSet.getString("id"));
                cust.setType(resultSet.getString("type"));
                cust.setName(resultSet.getString("name"));
                cust.setRrn(resultSet.getString("rrn"));
                cust.setAge(Integer.parseInt(resultSet.getString("age")));
                cust.setGender(resultSet.getString("gender"));
                cust.setPhoneNum(resultSet.getString("phoneNum"));
                cust.setOccupation(resultSet.getString("occupation"));
                customerList.add(cust);
            }
            resultSet.close();
            customerList.addAll(insuredCustomerDao.retrieveAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customerList;
    }
    public void update(Customer customer){

    }
    public void deleteById(String customerID){
        String query = "DELETE FROM Customer WHERE id = '"+customerID+"';";
        super.delete(query);
    }
    public void deleteAll(){
        String query = "DELETE FROM Customer;";
        super.deleteAll(query);
    }

}
