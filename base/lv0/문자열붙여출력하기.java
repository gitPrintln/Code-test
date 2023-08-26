package programmers;

import java.util.Scanner;

public class 문자열붙여출력하기 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        String b = sc.next();
        System.out.print(a+b);
        sc.close();
    }
}
