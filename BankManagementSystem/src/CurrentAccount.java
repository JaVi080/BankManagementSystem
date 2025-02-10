import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class CurrentAccount extends BankAccount{

    @Override
    protected void Withdrawal() throws IOException,ClassNotFoundException{
        System.out.println("WITHDRAWAL");
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Ur Account No : ");
        account=sc.nextLine();
        System.out.println("Amount For  Withdrawal: ");
        money=sc.nextInt();
        List<User> tempList=file_code();
        equal(tempList,Main.Input_Id,account);
        if (info_check) {
            if (money > User_Money) {
                System.out.println("Balance is Insufficient...Again Try");
                Withdrawal();
            } else {
                System.out.println(money + " has been withdrawn from the Account");
                User user = new User(User_Name, User_CNIC, User_Account_Type, User_Password, UserID, User_Account, money, "Withdrawn", User_Money - money);
                accountHandling.save_User_Info(user);
            }
        }else{
            System.out.println("Info Wrong");
        }
    }
    @Override
    protected void Transfer_Money() throws IOException,ClassNotFoundException{
        System.out.println("Transfer Money");
        Scanner sc=new Scanner(System.in);
        System.out.println("Your Account number : ");
        account=sc.nextLine();
        System.out.println("Account number of Whom U Want to transfer Ur Money : ");
        String another_account=sc.nextLine();
        System.out.println("Amount : ");
        money=sc.nextInt();
        List<User> tempList=file_code();
        equal(tempList,Main.Input_Id,account);
        if (info_check) {
            if (money > User_Money) {
                System.out.println("Balance is Insufficient...Again Try");
                Transfer_Money();
            } else {
                System.out.println("Money has been transferred to Account No : " + another_account);
                User user = new User(User_Name, User_CNIC, User_Account_Type, User_Password, UserID, User_Account, money, "Transfered", User_Money - money);
                accountHandling.save_User_Info(user);
            }
        }else{
            System.out.println("Info Wrong");
        }
    }
}
