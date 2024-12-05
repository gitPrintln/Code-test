package programmerslv3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class N으로표현2 {

    /* DP로 풀어보기
     * 문제 설명
       아래와 같이 5와 사칙연산만으로 12를 표현할 수 있습니다.
    
       12 = 5 + 5 + (5 / 5) + (5 / 5)
       12 = 55 / 5 + 5 / 5
       12 = (55 + 5) / 5
    
       5를 사용한 횟수는 각각 6,5,4 입니다. 그리고 이중 가장 작은 경우는 4입니다.
       이처럼 숫자 N과 number가 주어질 때, N과 사칙연산만 사용해서 표현 할 수 있는 방법 중 N 사용횟수의 최솟값을 return 하도록 solution 함수를 작성하세요.
    
     * 제한사항
       N은 1 이상 9 이하입니다.
       number는 1 이상 32,000 이하입니다.
       수식에는 괄호와 사칙연산만 가능하며 나누기 연산에서 나머지는 무시합니다.
       최솟값이 8보다 크면 -1을 return 합니다.
     * 입출력 예
       N   number  return
       5   12  4
       2   11  3
     * 입출력 예 설명
     * 예제 #1
       문제에 나온 예와 같습니다.
    
     * 예제 #2
       11 = 22 / 2와 같이 2를 3번만 사용하여 표현할 수 있습니다.
     */
    class Solution {
        public int solution(int N, int number) {
            if (N == number) return 1; // N과 number가 같다면 바로 1 반환

            List<Set<Integer>> dp = new ArrayList<>();
            for (int i = 0; i <= 8; i++) {
                dp.add(new HashSet<>());
            }

            dp.get(1).add(N); // N을 한 번만 사용한 경우

            for (int i = 2; i <= 8; i++) {
                Set<Integer> currentSet = dp.get(i);

                // N을 i번 연속 사용한 값 추가 (e.g., N, NN, NNN ...)
                int repeatedNumber = Integer.parseInt(String.valueOf(N).repeat(i));
                currentSet.add(repeatedNumber);

                // 이전 DP 결과로부터 계산
                for (int j = 1; j < i; j++) {
                    Set<Integer> set1 = dp.get(j);
                    Set<Integer> set2 = dp.get(i - j);

                    for (int num1 : set1) {
                        for (int num2 : set2) {
                            currentSet.add(num1 + num2);
                            currentSet.add(num1 - num2);
                            currentSet.add(num1 * num2);
                            if (num2 != 0) currentSet.add(num1 / num2);
                        }
                    }
                }

                // 목표 숫자가 현재 집합에 포함되어 있으면 결과 반환
                if (currentSet.contains(number)) {
                    return i;
                }
            }

            // 8번을 사용해도 목표 숫자를 만들 수 없는 경우
            return -1;
        }
    }
}
