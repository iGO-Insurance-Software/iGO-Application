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
                "'"+customer.getRrn()+"'," +
                customer.getAge()+"," +
                "'"+customer.getGender()+"'," +
                "'"+customer.getPhoneNum()+"'," +
                "'"+customer.getOccupation()+"'"+
                ");";
        super.create(query);
        if(customer.getType().equals("InsuredCustomer")) insuredCustomerDao.create(customer);
    }
    public Customer retrieveById(String customerID) {
        String query = "SELECT * FROM Customer WHERE id  = '"+
                customerID+"';";
        Customer customer = null;
        try {
            ResultSet resultSet = super.retrieve(query);
            while(resultSet.next()) {
                customer = new Customer();
                customer.setId(resultSet.getString("id"));
                customer.setType(resultSet.getString("type"));
                customer.setName(resultSet.getString("name"));
                customer.setRrn(resultSet.getString("rrn"));
                customer.setAge(resultSet.getInt("age"));
                customer.setGender(resultSet.getString("gender"));
                customer.setPhoneNum(resultSet.getString("phoneNum"));
                customer.setOccupation(resultSet.getString("occupation"));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customer;
    }
    public ArrayList<Customer> retrieveAll() {
        String query = "SELECT * FROM Customer;";
        ArrayList<Customer> customerList = null;
        try {
            ResultSet resultSet = super.retrieve(query);
            customerList = new ArrayList<Customer>();
            while(resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getString("id"));
                customer.setType(resultSet.getString("type"));
                customer.setName(resultSet.getString("name"));
                customer.setRrn(resultSet.getString("rrn"));
                customer.setAge(resultSet.getInt("age"));
                customer.setGender(resultSet.getString("gender"));
                customer.setPhoneNum(resultSet.getString("phoneNum"));
                customer.setOccupation(resultSet.getString("occupation"));
                customerList.add(customer);
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
                if(!type.equals("Customer")) continue;//하위 테이블일 경우 여기서 조회 하지 않고 하위Dao로 넘김
                Customer customer = new Customer();
                customer.setId(resultSet.getString("id"));
                customer.setType(resultSet.getString("type"));
                customer.setName(resultSet.getString("name"));
                customer.setRrn(resultSet.getString("rrn"));
                customer.setAge(resultSet.getInt("age"));
                customer.setGender(resultSet.getString("gender"));
                customer.setPhoneNum(resultSet.getString("phoneNum"));
                customer.setOccupation(resultSet.getString("occupation"));
                customerList.add(customer);
            }
            resultSet.close();
            customerList.addAll(insuredCustomerDao.retrieveAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customerList;
    }
    public void update(Customer customer){
        String query = "UPDATE Customer SET " +
                "type = '" + customer.getType() + "', " +
                "name = '" + customer.getName() + "', " +
                "rrn = " + customer.getRrn() + ", " +
                "age = " + customer.getAge() + ", " +
                "gender = '" + customer.getGender() + "', " +
                "phoneNum = '" + customer.getPhoneNum() + "', " +
                "occupation = '" + customer.getOccupation() + "' " +
                "WHERE id = '" + customer.getId() + "';";
        super.update(query);
        if(customer.getType().equals("InsuredCustomer")) insuredCustomerDao.update(customer);
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
