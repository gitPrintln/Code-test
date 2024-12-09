package programmerslv3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 외벽점검2 {

    /* 이진탐색법으로 풀이
     * 이전의 브루트포스와 백트래킹으로이용한 방법보다 시간 더단축
     * 문제 설명
       레스토랑을 운영하고 있는 "스카피"는 레스토랑 내부가 너무 낡아 친구들과 함께 직접 리모델링 하기로 했습니다. 레스토랑이 있는 곳은 스노우타운으로 매우 추운 지역이어서 내부 공사를 하는 도중에 주기적으로 외벽의 상태를 점검해야 할 필요가 있습니다.
    
       레스토랑의 구조는 완전히 동그란 모양이고 외벽의 총 둘레는 n미터이며, 외벽의 몇몇 지점은 추위가 심할 경우 손상될 수도 있는 취약한 지점들이 있습니다. 따라서 내부 공사 도중에도 외벽의 취약 지점들이 손상되지 않았는 지, 주기적으로 친구들을 보내서 점검을 하기로 했습니다. 다만, 빠른 공사 진행을 위해 점검 시간을 1시간으로 제한했습니다. 친구들이 1시간 동안 이동할 수 있는 거리는 제각각이기 때문에, 최소한의 친구들을 투입해 취약 지점을 점검하고 나머지 친구들은 내부 공사를 돕도록 하려고 합니다. 편의 상 레스토랑의 정북 방향 지점을 0으로 나타내며, 취약 지점의 위치는 정북 방향 지점으로부터 시계 방향으로 떨어진 거리로 나타냅니다. 또, 친구들은 출발 지점부터 시계, 혹은 반시계 방향으로 외벽을 따라서만 이동합니다.
    
       외벽의 길이 n, 취약 지점의 위치가 담긴 배열 weak, 각 친구가 1시간 동안 이동할 수 있는 거리가 담긴 배열 dist가 매개변수로 주어질 때, 취약 지점을 점검하기 위해 보내야 하는 친구 수의 최소값을 return 하도록 solution 함수를 완성해주세요.
    
     * 제한사항
       n은 1 이상 200 이하인 자연수입니다.
       weak의 길이는 1 이상 15 이하입니다.
       서로 다른 두 취약점의 위치가 같은 경우는 주어지지 않습니다.
       취약 지점의 위치는 오름차순으로 정렬되어 주어집니다.
       weak의 원소는 0 이상 n - 1 이하인 정수입니다.
       dist의 길이는 1 이상 8 이하입니다.
       dist의 원소는 1 이상 100 이하인 자연수입니다.
       친구들을 모두 투입해도 취약 지점을 전부 점검할 수 없는 경우에는 -1을 return 해주세요.
     * 입출력 예
       n   weak    dist    result
       12  [1, 5, 6, 10]   [1, 2, 3, 4]    2
       12  [1, 3, 4, 9, 10]    [3, 5, 7]   1
     * 입출력 예에 대한 설명
     * 입출력 예 #1
    
       원형 레스토랑에서 외벽의 취약 지점의 위치는 다음과 같습니다.
    
       외벽점검-1.jpg
    
       친구들을 투입하는 예시 중 하나는 다음과 같습니다.
    
       4m를 이동할 수 있는 친구는 10m 지점에서 출발해 시계방향으로 돌아 1m 위치에 있는 취약 지점에서 외벽 점검을 마칩니다.
       2m를 이동할 수 있는 친구는 4.5m 지점에서 출발해 6.5m 지점에서 외벽 점검을 마칩니다.
       그 외에 여러 방법들이 있지만, 두 명보다 적은 친구를 투입하는 방법은 없습니다. 따라서 친구를 최소 두 명 투입해야 합니다.
    
     * 입출력 예 #2
    
       원형 레스토랑에서 외벽의 취약 지점의 위치는 다음과 같습니다.
    
       외벽점검-2.jpg
    
       7m를 이동할 수 있는 친구가 4m 지점에서 출발해 반시계 방향으로 점검을 돌면 모든 취약 지점을 점검할 수 있습니다. 따라서 친구를 최소 한 명 투입하면 됩니다.
     */
    class Solution {
        public int solution(int n, int[] weak, int[] dist) {
            int weakLen = weak.length;

            // 취약 지점을 확장하여 원형을 선형으로 처리
            int[] extendedWeak = new int[weakLen * 2];
            for (int i = 0; i < weakLen; i++) {
                extendedWeak[i] = weak[i];
                extendedWeak[i + weakLen] = weak[i] + n;
            }

            // 이진 탐색 범위 설정
            int left = 1;  // 최소 1명
            int right = dist.length;  // 최대 dist.length명
            int answer = -1;

            // 내림차순 정렬 (먼 거리부터 점검)
            Arrays.sort(dist);
            for (int i = 0; i < dist.length / 2; i++) {
                int temp = dist[i];
                dist[i] = dist[dist.length - 1 - i];
                dist[dist.length - 1 - i] = temp;
            }

            // 이진 탐색
            while (left <= right) {
                int mid = (left + right) / 2;

                if (canCoverAll(weak, extendedWeak, dist, mid, weakLen)) {
                    answer = mid; // 조건 만족 시 정답 갱신
                    right = mid - 1; // 더 적은 인원으로 가능한지 확인
                } else {
                    left = mid + 1; // 더 많은 인원이 필요한 경우
                }
            }

            return answer;
        }

        private boolean canCoverAll(int[] weak, int[] extendedWeak, int[] dist, int friendCount, int weakLen) {
            // 친구 순열 생성
            List<int[]> permutations = generatePermutations(Arrays.copyOf(dist, friendCount));

            for (int[] friends : permutations) {
                // 모든 시작점에 대해 확인
                for (int start = 0;start < weakLen;start++) {
                    int position = start;

                    // 친구를 하나씩 투입하여 점검
                    for (int friend : friends) {
                        int coverage = extendedWeak[position] + friend;

                        // 친구가 점검 가능한 범위까지 이동
                        while (position < start + weakLen && extendedWeak[position] <= coverage) {
                            position++;
                        }

                        // 모든 취약 지점 점검 완료 시 true 반환
                        if (position >= start + weakLen) {
                            return true;
                        }
                    }
                }
            }

            // 모든 경우의 수를 시도해도 점검 불가
            return false;
        }

        // 친구 순열 생성
        private List<int[]> generatePermutations(int[] dist) {
            List<int[]> permutations = new ArrayList<>();
            permute(dist, 0, permutations);
            return permutations;
        }

        private void permute(int[] arr, int depth, List<int[]> permutations) {
            if (depth == arr.length) {
                permutations.add(arr.clone());
                return;
            }
            for (int i = depth;i < arr.length;i++) {
                swap(arr, depth, i);
                permute(arr, depth + 1, permutations);
                swap(arr, depth, i);
            }
        }

        private void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}
