import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SavingsAccount extends BankAccount{
     protected int transaction_count=1;

    @Override
    protected void Withdrawal() throws IOException,ClassNotFoundException{
        System.out.println("WITHDRAWAL");
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Ur Account No : ");
        account=sc.nextLine();
        System.out.println("Amount For  Withdrawal: ");
        money=sc.nextInt();
        List<User> tempList=file_code();
        same_code(tempList);

        if(transaction_count>5){
            System.out.println("Transaction limit : 5 times per day");
        }
        else {
            equal(tempList, Main.Input_Id, account);
            if (info_check) {
                if (money > User_Money) {
                    System.out.println("Balance is Insufficient...Again Try");
                    Withdrawal();
                } else if (money > 20000) {
                    System.out.println("Sorry U can't withdrawn more than 20,000 at a time");
                    Withdrawal();
                } else {
                    System.out.println(money + " has been withdrawn from the Account");
                    User user = new User(User_Name, User_CNIC, User_Account_Type, User_Password, UserID, User_Account, money, "Withdrawn", User_Money - money);
                    accountHandling.save_User_Info(user);
                }
            }
            else{
                System.out.println("Info Wrong");
            }
        }

    }
    @Override
    protected void Transfer_Money() throws IOException,ClassNotFoundException{
        System.out.println("Transfer Money");
        Scanner sc=new Scanner(System.in);
        System.out.println("Your Account number : ");
        account=sc.nextLine();
        System.out.println("Account number of Whom U transfer Ur Money : ");
        String another_account=sc.nextLine();
        System.out.println("Amount : ");
        money=sc.nextInt();
        List<User> tempList=file_code();
        same_code(tempList);

        if(transaction_count>5){
            System.out.println("Transaction limit : 5 times per day");
        }
        else {
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
    protected int same_code(List<User> tempList){
        List<User> specific_list=new ArrayList<>();
        for(User list_item:tempList){
            if(list_item.getId()==Main.Input_Id&&list_item.getAccount_No().equals(account)){
                specific_list.add(list_item);
            }
        }
        List<User> count_list=FilterUserList_AccToDay(specific_list,LocalDate.now());
        for(User check_list:count_list){
            if(check_list.getMoney_type().equalsIgnoreCase("Deposited")||
                    check_list.getMoney_type().equalsIgnoreCase("Withdrawn")){
                transaction_count++;
            }
        }
        return transaction_count;
    }
}
