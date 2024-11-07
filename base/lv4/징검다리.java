package programmerslv4;

import java.util.Arrays;

public class 징검다리 {

    /*
     * 문제 설명
       출발지점부터 distance만큼 떨어진 곳에 도착지점이 있습니다. 그리고 그사이에는 바위들이 놓여있습니다. 바위 중 몇 개를 제거하려고 합니다.
       예를 들어, 도착지점이 25만큼 떨어져 있고, 바위가 [2, 14, 11, 21, 17] 지점에 놓여있을 때 바위 2개를 제거하면 출발지점, 도착지점, 바위 간의 거리가 아래와 같습니다.
    
       제거한 바위의 위치  각 바위 사이의 거리 거리의 최솟값
       [21, 17]    [2, 9, 3, 11]   2
       [2, 21] [11, 3, 3, 8]   3
       [2, 11] [14, 3, 4, 4]   3
       [11, 21]    [2, 12, 3, 8]   2
       [2, 14] [11, 6, 4, 4]   4
       위에서 구한 거리의 최솟값 중에 가장 큰 값은 4입니다.
    
       출발지점부터 도착지점까지의 거리 distance, 바위들이 있는 위치를 담은 배열 rocks, 제거할 바위의 수 n이 매개변수로 주어질 때, 바위를 n개 제거한 뒤 각 지점 사이의 거리의 최솟값 중에 가장 큰 값을 return 하도록 solution 함수를 작성해주세요.
    
     * 제한사항
       도착지점까지의 거리 distance는 1 이상 1,000,000,000 이하입니다.
       바위는 1개 이상 50,000개 이하가 있습니다.
       n 은 1 이상 바위의 개수 이하입니다.
     * 입출력 예
       distance    rocks   n   return
       25  [2, 14, 11, 21, 17] 2   4
     * 입출력 예 설명
       문제에 나온 예와 같습니다.
     */
    class Solution {
        public int solution(int distance, int[] rocks, int n) {
            Arrays.sort(rocks);
            int left = 1; // 가능한 최소 거리
            int right = distance; // 가능한 최대 거리
            int answer = 0;
            
            while (left <= right) {
                int mid = (left + right) / 2;
                int removeCount = 0;
                int previous = 0; // 이전 위치 (출발지점)
                
                for (int rock : rocks) {
                    if (rock - previous < mid) { 
                        // 거리가 mid보다 작으면 바위를 제거
                        removeCount++;
                    } else {
                        // 거리가 mid 이상이면 제거하지 않고 이전 위치 업데이트
                        previous = rock;
                    }
                }
                
                // 도착지점과 마지막 바위 사이의 거리 체크
                if (distance - previous < mid) {
                    removeCount++;
                }
                
                // 바위를 n개 이하로 제거할 수 있다면 가능한 거리
                if (removeCount <= n) {
                    answer = mid; // 가능한 거리 중 최댓값 갱신
                    left = mid + 1; // 더 큰 최소 거리를 찾기 위해 범위 상향
                } else {
                    right = mid - 1; // 최소 거리를 줄이기 위해 범위 축소
                }
            }
            
            return answer;
        }
    }
}
