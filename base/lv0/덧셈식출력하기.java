package programmers;

import java.util.Scanner;

public class 덧셈식출력하기 {
    /*
     * 문제 설명
     * 두 정수 a, b가 주어질 때 다음과 같은 형태의 계산식을 출력하는 코드를 작성해 보세요.
     * a + b = c
    
     * 제한사항
       1 ≤ a, b ≤ 100
     * 입출력 예
     * 입력 #1
       4 5
    
     * 출력 #1
       4 + 5 = 9
     */
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
