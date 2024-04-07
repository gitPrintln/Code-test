package programmers;

import java.util.LinkedList;
import java.util.Queue;

public class 주식가격 {

    /*
     * 문제 설명
       초 단위로 기록된 주식가격이 담긴 배열 prices가 매개변수로 주어질 때, 가격이 떨어지지 않은 기간은 몇 초인지를 return 하도록 solution 함수를 완성하세요.
    
     * 제한사항
       prices의 각 가격은 1 이상 10,000 이하인 자연수입니다.
       prices의 길이는 2 이상 100,000 이하입니다.
     * 입출력 예
       prices  return
       [1, 2, 3, 2, 3] [4, 3, 1, 1, 0]
     * 입출력 예 설명
       1초 시점의 ₩1은 끝까지 가격이 떨어지지 않았습니다.
       2초 시점의 ₩2은 끝까지 가격이 떨어지지 않았습니다.
       3초 시점의 ₩3은 1초뒤에 가격이 떨어집니다. 따라서 1초간 가격이 떨어지지 않은 것으로 봅니다.
       4초 시점의 ₩2은 1초간 가격이 떨어지지 않았습니다.
       5초 시점의 ₩3은 0초간 가격이 떨어지지 않았습니다.
     */
    class Solution {
        public int[] solution(int[] prices) {
            int[] answer = new int[prices.length];
            Queue<Integer> queue = new LinkedList<>();
     
            for (int i : prices) {
                queue.add(i);
            }
     
            int index = 0;
            while (!queue.isEmpty()) {
                int currentPrice = queue.poll();
                for (int i = (prices.length - queue.size());i < prices.length;i++) {
                    // 가격이 떨어졌을 경우
                    if (currentPrice > prices[i]) {
                        answer[index]++;
                        break;
                    }
                    // 가격이 떨어지지 않았을 경우
                    if (currentPrice <= prices[i]) {
                        answer[index]++;
                    }
                }
                index++;
            }
            return answer;
        }
    }
}
