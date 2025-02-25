package programmerslv3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class 가장먼노드2 {

    /* 가중치가 다른 알고리즘에서 사용하는 다익스트라알고리즘 사용해보기
     * 여기서는 모든 가중치가 동일한 1로 두고 풀이
     * 문제 설명
       n개의 노드가 있는 그래프가 있습니다. 각 노드는 1부터 n까지 번호가 적혀있습니다. 1번 노드에서 가장 멀리 떨어진 노드의 갯수를 구하려고 합니다. 가장 멀리 떨어진 노드란 최단경로로 이동했을 때 간선의 개수가 가장 많은 노드들을 의미합니다.
    
       노드의 개수 n, 간선에 대한 정보가 담긴 2차원 배열 vertex가 매개변수로 주어질 때, 1번 노드로부터 가장 멀리 떨어진 노드가 몇 개인지를 return 하도록 solution 함수를 작성해주세요.
    
     * 제한사항
       노드의 개수 n은 2 이상 20,000 이하입니다.
       간선은 양방향이며 총 1개 이상 50,000개 이하의 간선이 있습니다.
       vertex 배열 각 행 [a, b]는 a번 노드와 b번 노드 사이에 간선이 있다는 의미입니다.
     * 입출력 예
       n   vertex  return
       6   [[3, 6], [4, 3], [3, 2], [1, 3], [1, 2], [2, 4], [5, 2]]    3
     * 입출력 예 설명
       예제의 그래프를 표현하면 아래 그림과 같고, 1번 노드에서 가장 멀리 떨어진 노드는 4,5,6번 노드입니다.
    
       image.png
     */
    class Solution {
        public int solution(int n, int[][] vertex) {
            // 1. 그래프 구성
            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }
            for (int[] edge : vertex) {
                int a = edge[0];
                int b = edge[1];
                graph.get(a).add(b);
                graph.get(b).add(a);
            }

            // 2. 다익스트라 알고리즘 초기화
            int[] distances = new int[n + 1];
            Arrays.fill(distances, Integer.MAX_VALUE);
            PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
            queue.offer(new int[]{1, 0});
            distances[1] = 0;

            // 3. 다익스트라 알고리즘으로 최단 거리 계산
            while (!queue.isEmpty()) {
                int[] current = queue.poll();
                int node = current[0];
                int distance = current[1];

                if (distance > distances[node]) continue;

                for (int neighbor : graph.get(node)) {
                    int newDist = distance + 1;
                    if (newDist < distances[neighbor]) {
                        distances[neighbor] = newDist;
                        queue.offer(new int[]{neighbor, newDist});
                    }
                }
            }

            // 4. 최대 거리와 해당 거리의 노드 개수 계산
            int maxDistance = 0;
            int count = 0;
            for (int i = 1; i <= n; i++) {
                if (distances[i] > maxDistance && distances[i] != Integer.MAX_VALUE) {
                    maxDistance = distances[i];
                    count = 1;
                } else if (distances[i] == maxDistance) {
                    count++;
                }
            }

            return count;
        }
    }
}
