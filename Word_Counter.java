package CodSoft_Codes;

import java.util.Scanner;

public class Word_Counter {
    public static void main(String[] args) {
        System.out.println("enter the sentence");
        Scanner sc=new Scanner(System.in);

        String word=sc.nextLine();
        int count =1;

        for(int i=0;i<word.length()-1;i++){
            if( (word.charAt(i)==' ') && (word.charAt(i+1)!=' ')){
                count ++;
            }
        }

        System.out.println("the number of words in the text is "+count);
    }
}
