package programmers;

public class 카운트다운 {

    /*
     * 문제 설명
       정수 start_num와 end_num가 주어질 때, start_num에서 end_num까지 1씩 감소하는 수들을 차례로 담은 리스트를 return하도록 solution 함수를 완성해주세요.
    
     * 제한사항
       0 ≤ end_num ≤ start_num ≤ 50
     * 입출력 예
       start_num   end_num result
       10  3   [10, 9, 8, 7, 6, 5, 4, 3]
    
     * 입출력 예 설명
     * 입출력 예 #1
       10부터 3까지 1씩 감소하는 수를 담은 리스트는 [10, 9, 8, 7, 6, 5, 4, 3]입니다.
     */
    class Solution {
        public int[] solution(int start, int end_num) {
            int[] answer = new int[start-end_num+1];
            int cnt = 0;
            for(int i=start;i>=end_num;i--){
                answer[cnt] = i;
                cnt++;
            }
            return answer;
        }
    }
}
