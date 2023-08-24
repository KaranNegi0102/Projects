package CodSoft_Codes;

import java.util.Random;
import java.util.Scanner;

class Guess{
    public int computer;
    public int user;
    public int guesses=0;

    public Guess(){
        Random rn=new Random();
        computer=rn.nextInt(100);
    }
    public void User(int n){
        user=n;
    }
    public boolean rightGuess(){
        if(user>computer){
            System.out.println("bigger number taken");
            guesses=guesses+1;
            return false;
        }
        else if(user<computer){
            System.out.println("smaller number taken");
            guesses+=1;
            return false;
        }
        else{
            System.out.println("number guessed right");
            guesses+=1;
            System.out.println("guessed times "+guesses);
            return true;
        }
    }
}
public class GuessTheNumber {
    public static void main(String[] args) {
        Scanner sd=new Scanner(System.in);
        System.out.println("choose number from 1 to 100");
//        int f= sd.nextInt();
        Guess player=new Guess();
        player.User(sd.nextInt());
        while(!player.rightGuess()){
            System.out.println("guess the number again");
            player.User(sd.nextInt());
        }
    }
}
