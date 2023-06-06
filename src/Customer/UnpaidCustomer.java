package Customer;

public class UnpaidCustomer extends Customer {
    // 이름, 전화번호, 미납 횟수, 월 보험료, 보험상품명
    private String name;
    private String phoneNum;
    private int numberOfNonPayments;
    private double premium;
    private String insuranceName;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPhoneNum() {
        return phoneNum;
    }

    @Override
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public int getNumberOfNonPayments() {
        return numberOfNonPayments;
    }

    public void setNumberOfNonPayments(int numberOfNonPayments) {
        this.numberOfNonPayments = numberOfNonPayments;
    }

    public double getPremium() {
        return premium;
    }

    public void setPremium(double premium) {
        this.premium = premium;
    }

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

}
