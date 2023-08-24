package Projects;
import java.util.*;

public class AtmMachine{
    public static void main(String[] args) {
        ATMoperations obj = new ATMoperations();
    }
}
class userBalance{
    int balance;
}
class ATMoperations extends userBalance{

    Scanner sc = new Scanner(System.in);
    ATMoperations(){
        System.out.println("Welcome to our ATM");
        MainMenu();
        }

    public void MainMenu(){

        System.out.println("Enter your choice");

        System.out.println("1 . check balance");
        System.out.println("2 . withdraw money");
        System.out.println("3 . deposit money");
        System.out.println("4 . exit");
        System.out.println("Enter option choose");
        int x = sc.nextInt();

        if(x == 1){
            check_balance();

        }
        else if(x==2){
            withdraw();

        }
        else if(x==3){
            deposite();

        }
        else if(x == 4){
            System.out.println("thank you for using our ATM");
        }
        else{
            System.out.println("enter valid option");
            MainMenu();
        }
    }

    public void check_balance(){
        System.out.println("your current balance " + balance);
        MainMenu();
    }

    public void deposite(){
        System.out.println("enter the amount ");
        int d = sc.nextInt();
        balance += d;
        System.out.println("amount deposited successfully ");
        MainMenu();
    }

    public void withdraw(){
        System.out.println("enter the amount ");
        int d = sc.nextInt();
        if(d >balance) {
            System.out.println("ïnsufficient balance");
            MainMenu();
        }
        balance -= d;
        System.out.println("amount withdraw successfully ");
        MainMenu();
    }
}