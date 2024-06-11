package programmers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class 시소짝꿍 {

    /*
     * 문제 설명
       어느 공원 놀이터에는 시소가 하나 설치되어 있습니다. 이 시소는 중심으로부터 2(m), 3(m), 4(m) 거리의 지점에 좌석이 하나씩 있습니다.
       이 시소를 두 명이 마주 보고 탄다고 할 때, 시소가 평형인 상태에서 각각에 의해 시소에 걸리는 토크의 크기가 서로 상쇄되어 완전한 균형을 이룰 수 있다면 그 두 사람을 시소 짝꿍이라고 합니다. 즉, 탑승한 사람의 무게와 시소 축과 좌석 간의 거리의 곱이 양쪽 다 같다면 시소 짝꿍이라고 할 수 있습니다.
       사람들의 몸무게 목록 weights이 주어질 때, 시소 짝꿍이 몇 쌍 존재하는지 구하여 return 하도록 solution 함수를 완성해주세요.
    
     * 제한 사항
       2 ≤ weights의 길이 ≤ 100,000
       100 ≤ weights[i] ≤ 1,000
       몸무게 단위는 N(뉴턴)으로 주어집니다.
       몸무게는 모두 정수입니다.
     * 입출력 예
       weights result
       [100,180,360,100,270]   4
     * 입출력 예 설명
       {100, 100} 은 서로 같은 거리에 마주보고 앉으면 균형을 이룹니다.
       {180, 360} 은 각각 4(m), 2(m) 거리에 마주보고 앉으면 균형을 이룹니다.
       {180, 270} 은 각각 3(m), 2(m) 거리에 마주보고 앉으면 균형을 이룹니다.
       {270, 360} 은 각각 4(m), 3(m) 거리에 마주보고 앉으면 균형을 이룹니다.
     */
    class Solution {
        public long solution(int[] weights) {
            long answer = 0;
            Arrays.sort(weights);
            Map<Double, Integer> map = new HashMap<>();
            for(int i : weights) {
                double a = i*1.0;
                double b = (i*2.0)/3.0;
                double c = (i*1.0)/2.0;
                double d = (i*3.0)/4.0;
                if(map.containsKey(a)) answer += map.get(a);
                if(map.containsKey(b)) answer += map.get(b);
                if(map.containsKey(c)) answer += map.get(c);
                if(map.containsKey(d)) answer += map.get(d);
                map.put((i*1.0), map.getOrDefault((i*1.0), 0)+1);
            }
            return answer;
        }
    }
}
