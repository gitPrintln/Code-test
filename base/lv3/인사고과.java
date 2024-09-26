package programmerslv3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 인사고과 {

    /*
     * 문제 설명
       완호네 회사는 연말마다 1년 간의 인사고과에 따라 인센티브를 지급합니다. 각 사원마다 근무 태도 점수와 동료 평가 점수가 기록되어 있는데 만약 어떤 사원이 다른 임의의 사원보다 두 점수가 모두 낮은 경우가 한 번이라도 있다면 그 사원은 인센티브를 받지 못합니다. 그렇지 않은 사원들에 대해서는 두 점수의 합이 높은 순으로 석차를 내어 석차에 따라 인센티브가 차등 지급됩니다. 이때, 두 점수의 합이 동일한 사원들은 동석차이며, 동석차의 수만큼 다음 석차는 건너 뜁니다. 예를 들어 점수의 합이 가장 큰 사원이 2명이라면 1등이 2명이고 2등 없이 다음 석차는 3등부터입니다.
    
       각 사원의 근무 태도 점수와 동료 평가 점수 목록 scores이 주어졌을 때, 완호의 석차를 return 하도록 solution 함수를 완성해주세요.
    
     * 제한 사항
       1 ≤ scores의 길이 ≤ 100,000
       scores의 각 행은 한 사원의 근무 태도 점수와 동료 평가 점수를 나타내며 [a, b] 형태입니다.
       scores[0]은 완호의 점수입니다.
       0 ≤ a, b ≤ 100,000
       완호가 인센티브를 받지 못하는 경우 -1을 return 합니다.
     * 입출력 예
       scores  result
       [[2,2],[1,4],[3,2],[3,2],[2,1]] 4
     * 입출력 예 설명
       5 번째 사원은 3 번째 또는 4 번째 사원보다 근무 태도 점수와 동료 평가 점수가 모두 낮기 때문에 인센티브를 받을 수 없습니다. 2 번째 사원, 3 번째 사원, 4 번째 사원은 두 점수의 합이 5 점으로 최고점이므로 1 등입니다. 1 등이 세 명이므로 2 등과 3 등은 없고 1 번째 사원인 완호는 두 점수의 합이 4 점으로 4 등입니다.
     */
    class Solution {
        public int solution(int[][] scores) {
            int[] wanho = scores[0];

            // scores 배열을 근무 태도 점수 기준으로 내림차순, 동료 평가 점수는 오름차순으로 정렬
            // 이렇게 하면 근무 태도 점수가 높은 사람부터 차례로 동료 평가 점수를 확인할 수 있음
            Arrays.sort(scores, (a, b) -> {
                if (a[0] == b[0]) {
                    return a[1] - b[1]; // 근무 태도 점수가 같다면 동료 평가 점수 오름차순 정렬
                } else {
                    return b[0] - a[0]; // 근무 태도 점수는 내림차순 정렬
                }
            });
            
            // 인센티브를 받을 수 있는 사원들 합을 저장
            List<int[]> list = new ArrayList<>();
            // 인센티브 받을 수 없는 사람 추려냄(동료 점수만 확인 나머지는 내림차순되있음)
            // 현재 사원의 동료 평가 점수가 지금까지의 최대 동료 평가 점수보다 작다면 탈락
            int maxScore = 0;
            for (int[] score : scores) {
                if (score[1] < maxScore) { // 만약 그 사원이 완호라면 바로 -1 반환
                    if (Arrays.equals(score, wanho)) {
                        return -1;
                    }
                } else {
                    list.add(score); // 인센티브를 받을 수 있는 사원 저장
                    maxScore = Math.max(maxScore, score[1]); // 최대 동료 평가 점수 갱신
                }
            }
            
            // 인센티브를 받을 수 있는 사원들 점수 합을 내림차순 정렬
            list.sort((a, b) -> (b[0] + b[1]) - (a[0] + a[1]));
            
            int rank = 1; // 현재 석차
            int sameRankCount = 0; // 같은 석차인 사람의 수
            int prevSum = -1; // 이전 점수 합을 저장

            for (int i = 0;i < list.size();i++) {
                int[] score = list.get(i);
                int totalScore = score[0] + score[1];

                // 이전 점수 합과 현재 점수 합이 다르면 새로운 석차 할당
                if (totalScore != prevSum) {
                    rank += sameRankCount; // 동석차만큼 다음 석차 건너뜀
                    sameRankCount = 1; // 새로운 동석차 그룹 시작
                    prevSum = totalScore;
                } else {
                    sameRankCount++; // 동석차 그룹에 포함
                }

                if (Arrays.equals(score, wanho)) { // 완호의 점수 합과 동일하면 석차 반환
                    return rank;
                }
            }

            return -1; // 예외적으로 완호가 석차를 찾지 못하는 경우
        }
    }
}
