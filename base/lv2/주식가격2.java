package programmerslv012;

import java.util.Stack;

public class 주식가격2 {

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
    public int[] solution(int[] prices) {
        int n = prices.length;
        int[] answer = new int[n];
        Stack<Integer> stack = new Stack<>();
        /* Queue보다 stack을 사용하는게 더 시간복잡도가 O(n)으로 할 수 있어 효율적임.
         * 두개의 중첩 루프를 돌릴 필요가 없음
         */
        for (int i = 0; i < n; i++) {
            // 스택 비어있지 않고 가격 현재가격과 스택의 가격 비교
            while (!stack.isEmpty() && prices[i] < prices[stack.peek()]) {
                int index = stack.pop();
                answer[index] = i - index;
            }
            stack.push(i);
        }
        
        // 스택에 남아 있는 인덱스들은 떨어지지 않ㄴ은 가격이기 때문에 계산해줌
        while (!stack.isEmpty()) {
            int index = stack.pop();
            answer[index] = n - index - 1;
        }
        
        return answer;
    }
}
