package programmerslv3;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

public class 여행경로2 {

    /* DFS가 아닌 스택을 통해 반복적인 탐색(DFS보다 더 빠른 결과도출)
     * 문제 설명
       주어진 항공권을 모두 이용하여 여행경로를 짜려고 합니다. 항상 "ICN" 공항에서 출발합니다.
    
       항공권 정보가 담긴 2차원 배열 tickets가 매개변수로 주어질 때, 방문하는 공항 경로를 배열에 담아 return 하도록 solution 함수를 작성해주세요.
    
     * 제한사항
       모든 공항은 알파벳 대문자 3글자로 이루어집니다.
       주어진 공항 수는 3개 이상 10,000개 이하입니다.
       tickets의 각 행 [a, b]는 a 공항에서 b 공항으로 가는 항공권이 있다는 의미입니다.
       주어진 항공권은 모두 사용해야 합니다.
       만일 가능한 경로가 2개 이상일 경우 알파벳 순서가 앞서는 경로를 return 합니다.
       모든 도시를 방문할 수 없는 경우는 주어지지 않습니다.
     * 입출력 예
       tickets return
       [["ICN", "JFK"], ["HND", "IAD"], ["JFK", "HND"]]    ["ICN", "JFK", "HND", "IAD"]
       [["ICN", "SFO"], ["ICN", "ATL"], ["SFO", "ATL"], ["ATL", "ICN"], ["ATL","SFO"]] ["ICN", "ATL", "ICN", "SFO", "ATL", "SFO"]
     * 입출력 예 설명
     * 예제 #1
    
       ["ICN", "JFK", "HND", "IAD"] 순으로 방문할 수 있습니다.
    
     * 예제 #2
    
       ["ICN", "SFO", "ATL", "ICN", "ATL", "SFO"] 순으로 방문할 수도 있지만 ["ICN", "ATL", "ICN", "SFO", "ATL", "SFO"] 가 알파벳 순으로 앞섭니다.
     */
    class Solution {
        public List<String> solution(String[][] tickets) {
            // 그래프 초기화: 출발 공항을 키로, 도착지 목록을 값으로 하는 Map 생성
            Map<String, PriorityQueue<String>> graph = new HashMap<>();
            for (String[] ticket : tickets) {
                String start = ticket[0];
                String destination = ticket[1];
                graph.putIfAbsent(start, new PriorityQueue<>()); // 알파벳 순서로 정렬
                graph.get(start).offer(destination);
            }

            List<String> route = new LinkedList<>(); 
            Stack<String> stack = new Stack<>(); // 탐색을 위한 스택
            stack.push("ICN"); // 출발점 "ICN"을 스택에 추가

            // 스택을 활용한 반복적 탐색
            while (!stack.isEmpty()) {
                String airport = stack.peek();
                if (graph.containsKey(airport) && !graph.get(airport).isEmpty()) {
                    stack.push(graph.get(airport).poll()); // 알파벳 순서로 다음 공항 방문
                } else {
                    route.add(0, stack.pop()); // 경로에 추가 (역순으로 삽입)
                }
            }

            return route; // 결과 경로 반환
        }
    }
}
