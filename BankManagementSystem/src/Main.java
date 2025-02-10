import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main {
    static int Input_Id;
    static String Input_Password;

    public static void main(String[] args) throws IOException ,ClassNotFoundException{
        BankAccount bankAccount=new CurrentAccount();
        BankAccount saving_account=new SavingsAccount();
        Account_Handling a=new Account_Handling();
        List<User> match_list = bankAccount.file_code();
        for(User list:match_list){
            System.out.println(list.toString());
        }
        System.out.println("BANK ACCOUNT MANAGEMENT ");
        System.out.println("Press 1 for Sign Up and Press 2 for Sign in");
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        sc.nextLine();
        if (input == 1) {
            a.SignUp();
        }
        if (input == 2) {
            boolean check=false;
            System.out.println("Enter Ur id : ");
            Input_Id = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter Password : ");
            Input_Password = sc.nextLine();
            int index=0;
            for (int i = match_list.size() - 1; i >= 0; i--) {
                if (Input_Id == match_list.get(i).getId() && Input_Password.equals(match_list.get(i).getPassword())) {
                    check = true;
                    break;
                }
            }
            if (check) {
                Scanner scanner = new Scanner(System.in);
                System.out.println(" Deposit ? Press 1");
                System.out.println(" Withdrawal ? Press 2");
                System.out.println(" Transfer Money ? Press 3");
                System.out.println(" Balance Inquiry ? Press 4");
                System.out.println(" Transaction Details? Press 5");
                System.out.println(" Account Details ? Press 6");
                System.out.println("Exit Programme ? Press 0 ");
                while (true) {
                    System.out.println("Press Key : ");
                    int option = scanner.nextInt();
                    switch (option) {
                        case 1:
                            bankAccount.Deposit();
                            break;
                        case 2:
                            if(match_list.get(index).getMoney_type().equalsIgnoreCase("Savings Account")) {
                                saving_account.Withdrawal();
                            }else{
                                bankAccount.Withdrawal();
                            }
                            break;
                        case 3:
                            if(match_list.get(index).getMoney_type().equalsIgnoreCase("Savings Account")) {
                                saving_account.Transfer_Money();
                            }else{
                                bankAccount.Transfer_Money();
                            }
                            break;
                        case 4:
                            bankAccount.Balance_Inquiry();
                            break;
                        case 5:
                            bankAccount.Transaction_Details();
                            break;
                        case 6:
                            bankAccount.Account_Details();
                            break;
                        case 0:
                            System.exit(0);
                    }

                }

            } else {
                System.out.println("Information is Wrong");
                System.out.println("Again TRY Or sign UP");
            }
        }


    }
}


