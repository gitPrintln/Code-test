package programmerslv3;

import java.util.Arrays;
import java.util.Comparator;

public class 섬연결하기2 {

    /* 크루스칼 알고리즘으로 문제 풀이
     * 문제 설명
       n개의 섬 사이에 다리를 건설하는 비용(costs)이 주어질 때, 최소의 비용으로 모든 섬이 서로 통행 가능하도록 만들 때 필요한 최소 비용을 return 하도록 solution을 완성하세요.
    
       다리를 여러 번 건너더라도, 도달할 수만 있으면 통행 가능하다고 봅니다. 예를 들어 A 섬과 B 섬 사이에 다리가 있고, B 섬과 C 섬 사이에 다리가 있으면 A 섬과 C 섬은 서로 통행 가능합니다.
    
     * 제한사항
    
       섬의 개수 n은 1 이상 100 이하입니다.
       costs의 길이는 ((n-1) * n) / 2이하입니다.
       임의의 i에 대해, costs[i][0] 와 costs[i] [1]에는 다리가 연결되는 두 섬의 번호가 들어있고, costs[i] [2]에는 이 두 섬을 연결하는 다리를 건설할 때 드는 비용입니다.
       같은 연결은 두 번 주어지지 않습니다. 또한 순서가 바뀌더라도 같은 연결로 봅니다. 즉 0과 1 사이를 연결하는 비용이 주어졌을 때, 1과 0의 비용이 주어지지 않습니다.
       모든 섬 사이의 다리 건설 비용이 주어지지 않습니다. 이 경우, 두 섬 사이의 건설이 불가능한 것으로 봅니다.
       연결할 수 없는 섬은 주어지지 않습니다.
     * 입출력 예
    
       n   costs   return
       4   [[0,1,1],[0,2,2],[1,2,5],[1,3,1],[2,3,8]]   4
     * 입출력 예 설명
    
       costs를 그림으로 표현하면 다음과 같으며, 이때 초록색 경로로 연결하는 것이 가장 적은 비용으로 모두를 통행할 수 있도록 만드는 방법입니다.
    
       image.png
     */
    class Solution {
        public int solution(int n, int[][] costs) {
            // 비용 기준으로 간선 정렬
            Arrays.sort(costs, (a, b) -> Integer.compare(a[2], b[2]));

            // Union-Find 구조를 위한 부모 배열 초기화
            int[] parent = new int[n];
            for (int i = 0;i < n;i++) {
                parent[i] = i;
            }

            int totalCost = 0;
            int edgesUsed = 0;

            // 비용이 낮은 간선부터 선택
            for (int[] cost : costs) {
                int island1 = cost[0];
                int island2 = cost[1];
                int bridgeCost = cost[2];

                // 두 섬이 이미 연결되어 있지 않다면 다리를 연결
                if (find(parent, island1) != find(parent, island2)) {
                    union(parent, island1, island2);
                    totalCost += bridgeCost;
                    edgesUsed++;

                    // 모든 섬이 연결되었다면 종료
                    if (edgesUsed == n - 1) {
                        break;
                    }
                }
            }

            return totalCost;
        }

        // Union-Find에서 find 함수: 루트 노드를 찾는 함수
        private int find(int[] parent, int x) {
            if (parent[x] != x) {
                parent[x] = find(parent, parent[x]); // 경로 압축
            }
            return parent[x];
        }

        // Union-Find에서 union 함수: 두 집합을 합치는 함수
        private void union(int[] parent, int x, int y) {
            int rootX = find(parent, x);
            int rootY = find(parent, y);
            if (rootX < rootY) {
                parent[rootY] = rootX;
            } else {
                parent[rootX] = rootY;
            }
        }
    }
}
