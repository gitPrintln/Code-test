package programmers;

import java.util.Scanner;

public class 홀짝구분하기 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        boolean x = n % 2 == 0 ? true : false;
        if(x){
            System.out.print(n + " is even");
        } else{
            System.out.print(n + " is odd");
        }
        sc.close();
    }
}
