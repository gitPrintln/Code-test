package programmers;

import java.util.Scanner;

public class 덧셈식출력하기 {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = 0;
        int b = 0;
        boolean result = true;
        while(result){
            a = sc.nextInt();
            if(1 <= a){
                result = false;
            }
        }
        result = true;
        while(result){
            b = sc.nextInt();
            if(b <= 100){
                result = false;
            }
        }
        
        System.out.println(a + " + " + b + " = " + (a + b));
        sc.close();
    }
}
