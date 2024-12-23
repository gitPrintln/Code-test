package programmerslv3;

public class 억억단을외우자 {

    /*
     * 문제 설명
       영우는 천하제일 암산대회를 앞두고 있습니다. 암산보다는 암기에 일가견이 있는 영우는 구구단을 확장하여 억억단을 만들고 외워버리기로 하였습니다.
       그림1.png
       억억단은 1억 x 1억 크기의 행렬입니다. 억억단을 외우던 영우는 친구 수연에게 퀴즈를 내달라고 부탁하였습니다.
       수연은 평범하게 문제를 내봐야 영우가 너무 쉽게 맞히기 때문에 좀 어렵게 퀴즈를 내보려고 합니다. 적당한 수 e를 먼저 정하여 알려주고 e 이하의 임의의 수 s를 여러 개 얘기합니다. 영우는 각 s에 대해서 s보다 크거나 같고 e 보다 작거나 같은 수 중에서 억억단에서 가장 많이 등장한 수를 답해야 합니다. 만약 가장 많이 등장한 수가 여러 개라면 그 중 가장 작은 수를 답해야 합니다.
       수연은 영우가 정답을 말하는지 확인하기 위해 당신에게 프로그램 제작을 의뢰하였습니다. e와 s의 목록 starts가 매개변수로 주어질 때 각 퀴즈의 답 목록을 return 하도록 solution 함수를 완성해주세요.
    
     * 제한사항
       1 ≤ e ≤ 5,000,000
       1 ≤ starts의 길이 ≤ min {e,100,000}
       1 ≤ starts의 원소 ≤ e
       starts에는 중복되는 원소가 존재하지 않습니다.
     * 입출력 예
       e   starts  result
       8   [1,3,7] [6,6,8]
     * 입출력 예 설명
       억억단에서 1 ~ 8이 등장하는 횟수는 다음과 같습니다.
    
       1번 등장 : 1
       2번 등장 : 2, 3, 5, 7
       3번 등장 : 4
       4번 등장 : 6, 8
    
       [1, 8] 범위에서는 6과 8이 각각 4번씩 등장하여 가장 많은데 6이 더 작은 수이므로 6이 정답입니다.
       [3, 8] 범위에서도 위와 같으므로 6이 정답입니다.
       [7, 8] 범위에서는 7은 2번, 8은 4번 등장하므로 8이 정답입니다.
     */
    class Solution {
        public int[] solution(int e, int[] starts) {
            // 각 숫자가 억억단에서 등장하는 횟수를 저장할 배열
            int[] count = new int[e + 1];

            // 등장 횟수 계산
            for (int i = 1;i <= e;i++) {
                for (int j = i;j <= e;j += i) {
                    count[j]++;
                }
            }

            // e까지 각 구간에서 가장 많이 등장한 수를 기록할 배열
            int[] most = new int[e + 1];
            int max = e;

            // 역순으로 순회하면서 각 구간에서 가장 많이 등장한 수를 기록
            for (int i = e;i >= 1;i--) {
                if (count[i] >= count[max]) {
                    max = i;
                }
                most[i] = max;
            }

            // 각 start에 대해 답을 기록할 배열
            int[] result = new int[starts.length];

            // 각 start에 대해 결과를 저장
            for (int i = 0;i < starts.length;i++) {
                result[i] = most[starts[i]];
            }

            return result;
        }
    }
}
