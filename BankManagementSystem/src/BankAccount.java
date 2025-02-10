import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public abstract class BankAccount {
Account_Handling accountHandling=new Account_Handling();
   protected String account;
    protected int money;

    protected abstract void Withdrawal() throws IOException,ClassNotFoundException;
    protected abstract void Transfer_Money()throws IOException,ClassNotFoundException;

    public List<User> file_code() throws IOException,ClassNotFoundException {
        File file = new File("Save_User_Info.txt");
        ArrayList<User> temp_list = new ArrayList<>();
        if (file.exists() && file.length() != 0) {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            try {
                while (true) {
                    temp_list.add((User) ois.readObject());
                }
            } catch (EOFException eofException) {
                eofException.getMessage();
            }
        }
        return temp_list;
    }

    protected String User_Name;
    protected String User_CNIC;
    protected String User_Password;
    protected String User_Account_Type;
    protected int User_Money;
    protected LocalDateTime Time;
    protected String User_Account;
    protected int UserID;
    boolean info_check;
    protected void equal(List temp_list,int id,String account){
      info_check=false;
       ListIterator<User> iterator=temp_list.listIterator(temp_list.size());
       while(iterator.hasPrevious()){
           User user=iterator.previous();
           if(user.getId()==id&&user.getAccount_No().equals(account)&&Main.Input_Password.equals(user.getPassword())){
               User_Name=user.getName();
               User_CNIC=user.getCNIC();
               User_Password= user.getPassword();
               User_Account_Type=user.getType();
               User_Money=user.getTotal_money();
               Time=user.getLocalDateTime();
               User_Account=user.getAccount_No();
               UserID= user.getId();
               info_check=true;
               break;
           }
       }
    }

     protected void Deposit()throws IOException ,ClassNotFoundException{
         System.out.println("DEPOSIT");
         Scanner sc=new Scanner(System.in);
         System.out.println("Enter Ur Account No : ");
         account=sc.nextLine();
         System.out.println("Amount For  Deposit: ");
         money=sc.nextInt();
         System.out.println(money+"has been successfully deposited");
         List<User> templist=file_code();
         equal(templist,Main.Input_Id,account);
             User user = new User(User_Name, User_CNIC, User_Account_Type, User_Password,UserID,User_Account, money,"DEPOSITED",money + User_Money);
             //FIle Handling
             accountHandling.save_User_Info(user);
     }

     protected void Balance_Inquiry() throws IOException,ClassNotFoundException{
         System.out.println("Current Balance");
         Scanner sc=new Scanner(System.in);
         System.out.println("Your Account number : ");
         account=sc.nextLine();
         List<User> tempList=file_code();
         equal(tempList,Main.Input_Id,account);
         System.out.println("Your Current Balance : "+User_Money);

     }
    protected void Account_Details()throws IOException,ClassNotFoundException{
        System.out.println("ACCOUNT DETAILS : ");
        List<User> tempList=file_code();
        for(int i=tempList.size()-1;i>=0;i--){
            User user=tempList.get(i);
            if(Main.Input_Id==user.getId()&&Main.Input_Password.equals(user.getPassword())){
                System.out.println("Name : "+user.getName());
                System.out.println("CNIC : "+user.getCNIC());
                System.out.println(" ID Number : "+user.getId());
                System.out.println("Account No : "+user.getAccount_No());
                System.out.println("Account Type : "+user.getType());
                System.out.println("Total Amount in Your Account : "+user.getTotal_money());
                break;
            }
        }


    }
    protected void Transaction_Details()throws IOException,ClassNotFoundException{
        System.out.println("Transaction Details");
        Scanner sc=new Scanner(System.in);
        System.out.println("Your Account No : ");
        account=sc.nextLine();
        List<User> tempList=file_code();
        List<User> specific_list=new ArrayList<>();
        for(User list_item:tempList){
            if(list_item.getId()==Main.Input_Id&&list_item.getAccount_No().equals(account)){
                specific_list.add(list_item);
            }
        }
        LocalDate current_day= LocalDate.now();
        List<User> filterList=FilterUserList_AccToDay(specific_list,current_day);
        filterList.sort(Comparator.comparing(User::getLocalDateTime).reversed());
        System.out.println("Transaction Details -- Date : "+current_day);
        for(User details:filterList){
            System.out.println(String.format("The Amount %d is %s at %s",details.getMoney(),details.getMoney_type(),details.getLocalDateTime()));
        }
    }

    public List<User> FilterUserList_AccToDay(List<User> specific_list,LocalDate current_day){
        return specific_list.stream().filter(User->User.getLocalDateTime().toLocalDate().equals(current_day))
                .collect(Collectors.toList());
    }


}
