import java.io.*;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Scanner;

public class Account_Handling {

    private int id;
    private String account;
    public void SignUp() throws IOException {
        Scanner sc=new Scanner(System.in);

        System.out.println("Enter Ur Name : ");
        String name=sc.nextLine();
        System.out.println("Enter Ur CNIC : ");
        String cnic=sc.nextLine();
        String password="";
        while(!(password.length()>=6)) {
            System.out.println("Set Ur Password : (Atleast 6 characters)");
            password = sc.nextLine();
        }
        System.out.println("Want to create Savings Account? Press 1  : Want to create Current Account? Press 2");
        int type=sc.nextInt();

try{
    Assigning_Account();
    System.out.println("Your Account has been opened Successfully\n");
    System.out.println("Your User ID : " +id);
    System.out.println("Your Account Number : "+account);
    System.out.println("Deposit Money to Activate Your Account : ");
    int money=sc.nextInt();
    String money_type="Deposited";
    System.out.println(money+" has been successfully deposited");
    User newUser=new User(name,cnic,type==1?"Savings Account":type==2?"Current Account":"Invalid Account",password,id,account,money,money_type,money);
    save_User_Info(newUser);
}catch (IOException|ClassNotFoundException ioException) {
    ioException.printStackTrace();

}
    }

    public static class AppendingStream extends ObjectOutputStream{

        public AppendingStream(OutputStream out) throws IOException {
            super(out);
        }

        @Override
        protected void writeStreamHeader() throws IOException {
            reset();
        }
    }
    public void save_User_Info(User userlist) throws IOException {
        File file=new File("Save_User_Info.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            if (file.exists()) {
                FileOutputStream fout=new FileOutputStream(file,true);
                ObjectOutputStream out=(file.length()!=0)?new AppendingStream(fout):new ObjectOutputStream(fout);
                out.writeObject(userlist);
                out.flush();
                out.close();
                fout.close();
            }
    }

    private static final String pass_str="ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";
    private static final int length=6;
    SecureRandom secureRandom=new SecureRandom();
    public void Assigning_Account() throws IOException,ClassNotFoundException{
        File file=new File("Save_User_Info.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        if(file.exists()&&file.length()!=0){
           ObjectInputStream ois=new ObjectInputStream(new FileInputStream(file));
           ArrayList<User> temp_list=new ArrayList<>();
           try {
               while (true) {
                   temp_list.add((User)ois.readObject());
               }
           }catch(EOFException eofException){
               eofException.getMessage();
           }
            User lastPerson=temp_list.get(temp_list.size()-1);
           id=lastPerson.getId()+1;
           ois.close();
        }else{
            id=1;
        }
        //Assigning Account Number
        StringBuilder account_No=new StringBuilder();
        for(int i=0;i<length;i++){
            int index=secureRandom.nextInt(pass_str.length());
            account_No.append(pass_str.charAt(index));
        }
        account=account_No.toString();
    }


}
