# BankManagementSystem
This is my project in which Main Concepts of OOP (Java) has been used like 
Encapsulation , Composition ,Polymorphism ,Inheritance ,Abstraction,Inheritance etc.....
# Key Points : 
Total Classes -> 7
Main class
Person Class ->(Implemented Serializable Interface)
User Class-> (inherit the Parent Class)
Account Handling ->(Handle the Sign up (Account Numbers generated randomely by using Java SecureRandom Class[ This ensures that the numbers are produced with cryptographic strength])-- Sign In Process  and Stores INfo in a file (In serialized form) 
BankAccount Class ->(Abstract Class) All the methods has been implemented in this : 
1) For Money Deposition 
2) For Account Details 
3) For Transaction Details
4) For Checking Balance
except 2 methods --which are abstract methods
5) For Money Withdrawal
6) For Money Transfer
CurrentAccount Class --> (Inherited BankAccount Class ) 2 methods are overriden in this class for monry Transfer and Withdrawn As This is made for Business Account (Unlimited Transactions)
SavingsAccount Class --> (Inherited BankAccount Class ) same like CurrentAccount Class --that 2 methods are also overriden here for Limited Transactions
Compartor has been used for Assorting in reverse Order acc to Time (In Transaction Details)
Data has been stored in Serialized form by Implemeting Serializable Interface
#The OUTPUT SCREENSHOTS :
   [Sign UP](Output_Images/1st.png)
