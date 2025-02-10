import java.time.LocalDateTime;

public class User extends Person {
    private static final long serialVersionUID=1L;
    private int id;
    private String account_No;
    private int money;
    private String Money_type;
LocalDateTime localDateTime;
private int total_money;

    public User(String name, String CNIC, String type, String Password, int id, String account_no, int Money, String money_type, int total) {
        super(name, CNIC, type,Password);
        this.id=id;
        this.account_No=account_no;
        this.money=Money;
        this.Money_type=money_type;
        this.total_money=total;
        localDateTime=LocalDateTime.now();
    }

    public int getTotal_money() {
        return total_money;
    }

    public int getId() {
        return id;
    }

    public String getAccount_No() {
        return account_No;
    }

    public int getMoney() {
        return money;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public String getMoney_type() {
        return Money_type;
    }

    @Override
    public String toString() {
        return "User{" +
                "Name=" +getName() +
                "Password : "+getPassword()+
                "id=" + id +
                ", account_No='" + account_No + '\'' +
                "Type=" + getType() +
                '}';
    }
}
