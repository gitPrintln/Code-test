package programmerslv012;

import java.util.LinkedList;
import java.util.Queue;

public class 타겟넘버2 {

    /* BFS로 풀이 - DFS와 성능 차이는 크게 나지 않음.(구현 난이도는 DFS가 더 간결해서 메모리 사용도 적고, 적합할 수는 있음)
     * 문제 설명
       n개의 음이 아닌 정수들이 있습니다. 이 정수들을 순서를 바꾸지 않고 적절히 더하거나 빼서 타겟 넘버를 만들려고 합니다. 예를 들어 [1, 1, 1, 1, 1]로 숫자 3을 만들려면 다음 다섯 방법을 쓸 수 있습니다.
    
       -1+1+1+1+1 = 3
       +1-1+1+1+1 = 3
       +1+1-1+1+1 = 3
       +1+1+1-1+1 = 3
       +1+1+1+1-1 = 3
       사용할 수 있는 숫자가 담긴 배열 numbers, 타겟 넘버 target이 매개변수로 주어질 때 숫자를 적절히 더하고 빼서 타겟 넘버를 만드는 방법의 수를 return 하도록 solution 함수를 작성해주세요.
    
     * 제한사항
       주어지는 숫자의 개수는 2개 이상 20개 이하입니다.
       각 숫자는 1 이상 50 이하인 자연수입니다.
       타겟 넘버는 1 이상 1000 이하인 자연수입니다.
     * 입출력 예
       numbers target  return
       [1, 1, 1, 1, 1] 3   5
       [4, 1, 2, 1]    4   2
     * 입출력 예 설명
     * 입출력 예 #1
    
       문제 예시와 같습니다.
    
     * 입출력 예 #2
    
       +4+1-2+1 = 4
       +4-1+2-1 = 4
       총 2가지 방법이 있으므로, 2를 return 합니다.
     */
    public class Solution {
        public int solution(int[] numbers, int target) {
            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[] {0, 0});  // 처음에는 합이 0이고, 인덱스는 0
            
            int ways = 0;  // 방법의 수를 저장할 변수
            
            while (!queue.isEmpty()) {
                int[] current = queue.poll();
                int currentSum = current[0];
                int index = current[1];
                
                // 합이 타겟과 일치하는지 확인
                if (index == numbers.length) {
                    if (currentSum == target) {
                        ways++;
                    }
                } else {
                    // 현재 숫자를 더하는 경우
                    queue.add(new int[] {currentSum + numbers[index], index + 1});
                    // 현재 숫자를 빼는 경우
                    queue.add(new int[] {currentSum - numbers[index], index + 1});
                }
            }
            
            return ways;
        }
    }
}
