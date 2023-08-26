package programmers;

import java.util.Scanner;

public class 문자열돌리기 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        String[] x = a.split("");
        for (String b : x){
            System.out.println(b);
        }
        sc.close();
    }
}
