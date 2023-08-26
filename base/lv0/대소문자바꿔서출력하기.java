package programmers;

import java.util.Scanner;

public class 대소문자바꿔서출력하기 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        String result = "";
        for (char x : a.toCharArray()){
            if(Character.isLowerCase(x)){
                result += Character.toUpperCase(x);
            } else{
                result += Character.toLowerCase(x);
            }
        }
        
        System.out.print(result);
        sc.close();
    }
}
