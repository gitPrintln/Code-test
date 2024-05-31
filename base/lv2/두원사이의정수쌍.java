package programmers;

public class 두원사이의정수쌍 {

    /*
     * 문제 설명
       x축과 y축으로 이루어진 2차원 직교 좌표계에 중심이 원점인 서로 다른 크기의 원이 두 개 주어집니다. 반지름을 나타내는 두 정수 r1, r2가 매개변수로 주어질 때, 두 원 사이의 공간에 x좌표와 y좌표가 모두 정수인 점의 개수를 return하도록 solution 함수를 완성해주세요.
       ※ 각 원 위의 점도 포함하여 셉니다.
    
     * 제한 사항
       1 ≤ r1 < r2 ≤ 1,000,000
     * 입출력 예
       r1  r2  result
       2   3   20
     * 입출력 예 설명
     * 입출력 예 설명.png
       그림과 같이 정수 쌍으로 이루어진 점은 총 20개 입니다.
     */
    class Solution {
        public long solution(int r1, int r2) {
            long answer = 0;
            long ro1 = (long) Math.pow(r1,2);
            long ro2 = (long) Math.pow(r2,2);
            long ans = 0;
            for(long x = 0;x <= r2;x++) {
                long y2 = (long) Math.sqrt(ro2 - x*x);
                long y1 = (long) Math.sqrt(ro1 - x*x);
                if(Math.sqrt(ro1-x*x) % 1 == 0) ans++;
                
                answer += (y2-y1) * 4;
            }
            answer += ans*4;
            answer -= 4; //x == r2 y == 0 일때 x == 0, y == r2 겹치는값 제거
            return answer;
        }
    }
}
