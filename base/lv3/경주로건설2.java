package programmerslv3;

import java.util.Arrays;
import java.util.PriorityQueue;

public class 경주로건설2 {

    /* 다익스트라 알고리즘으로 풀기
     * BFS 모든 우선순위 동일, 다익스트라 알고리즘 : 최소비용 기준 탐색
     * 문제 설명
       kakao_road1.png
    
       건설회사의 설계사인 죠르디는 고객사로부터 자동차 경주로 건설에 필요한 견적을 의뢰받았습니다.
       제공된 경주로 설계 도면에 따르면 경주로 부지는 N x N 크기의 정사각형 격자 형태이며 각 격자는 1 x 1 크기입니다.
       설계 도면에는 각 격자의 칸은 0 또는 1 로 채워져 있으며, 0은 칸이 비어 있음을 1은 해당 칸이 벽으로 채워져 있음을 나타냅니다.
       경주로의 출발점은 (0, 0) 칸(좌측 상단)이며, 도착점은 (N-1, N-1) 칸(우측 하단)입니다. 죠르디는 출발점인 (0, 0) 칸에서 출발한 자동차가 도착점인 (N-1, N-1) 칸까지 무사히 도달할 수 있게 중간에 끊기지 않도록 경주로를 건설해야 합니다.
       경주로는 상, 하, 좌, 우로 인접한 두 빈 칸을 연결하여 건설할 수 있으며, 벽이 있는 칸에는 경주로를 건설할 수 없습니다.
       이때, 인접한 두 빈 칸을 상하 또는 좌우로 연결한 경주로를 직선 도로 라고 합니다.
       또한 두 직선 도로가 서로 직각으로 만나는 지점을 코너 라고 부릅니다.
       건설 비용을 계산해 보니 직선 도로 하나를 만들 때는 100원이 소요되며, 코너를 하나 만들 때는 500원이 추가로 듭니다.
       죠르디는 견적서 작성을 위해 경주로를 건설하는 데 필요한 최소 비용을 계산해야 합니다.
       
       예를 들어, 아래 그림은 직선 도로 6개와 코너 4개로 구성된 임의의 경주로 예시이며, 건설 비용은 6 x 100 + 4 x 500 = 2600원 입니다.
    
       kakao_road2.png
    
       또 다른 예로, 아래 그림은 직선 도로 4개와 코너 1개로 구성된 경주로이며, 건설 비용은 4 x 100 + 1 x 500 = 900원 입니다.
    
       kakao_road3.png
       
       도면의 상태(0은 비어 있음, 1은 벽)을 나타내는 2차원 배열 board가 매개변수로 주어질 때, 경주로를 건설하는데 필요한 최소 비용을 return 하도록 solution 함수를 완성해주세요.
       
     * [제한사항]
       board는 2차원 정사각 배열로 배열의 크기는 3 이상 25 이하입니다.
       board 배열의 각 원소의 값은 0 또는 1 입니다.
       도면의 가장 왼쪽 상단 좌표는 (0, 0)이며, 가장 우측 하단 좌표는 (N-1, N-1) 입니다.
       원소의 값 0은 칸이 비어 있어 도로 연결이 가능함을 1은 칸이 벽으로 채워져 있어 도로 연결이 불가능함을 나타냅니다.
       board는 항상 출발점에서 도착점까지 경주로를 건설할 수 있는 형태로 주어집니다.
       출발점과 도착점 칸의 원소의 값은 항상 0으로 주어집니다.
     * 입출력 예
       board   result
       [[0,0,0],[0,0,0],[0,0,0]]   900
       [[0,0,0,0,0,0,0,1],[0,0,0,0,0,0,0,0],[0,0,0,0,0,1,0,0],[0,0,0,0,1,0,0,0],[0,0,0,1,0,0,0,1],[0,0,1,0,0,0,1,0],[0,1,0,0,0,1,0,0],[1,0,0,0,0,0,0,0]]   3800
       [[0,0,1,0],[0,0,0,0],[0,1,0,1],[1,0,0,0]]   2100
       [[0,0,0,0,0,0],[0,1,1,1,1,0],[0,0,1,0,0,0],[1,0,0,1,0,1],[0,1,0,0,0,1],[0,0,0,0,0,0]]   3200
     * 입출력 예에 대한 설명
     * 입출력 예 #1
    
       본문의 예시와 같습니다.
    
     * 입출력 예 #2
    
       kakao_road4.png
    
       위와 같이 경주로를 건설하면 직선 도로 18개, 코너 4개로 총 3800원이 듭니다.
    
     * 입출력 예 #3
    
       kakao_road5.png
    
       위와 같이 경주로를 건설하면 직선 도로 6개, 코너 3개로 총 2100원이 듭니다.
    
     * 입출력 예 #4
    
       kakao_road6.png
    
       붉은색 경로와 같이 경주로를 건설하면 직선 도로 12개, 코너 4개로 총 3200원이 듭니다.
       만약, 파란색 경로와 같이 경주로를 건설한다면 직선 도로 10개, 코너 5개로 총 3500원이 들며, 더 많은 비용이 듭니다.
     */
    class Solution {
        static int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우
        static int[] dy = {0, 0, -1, 1};
        static final int INF = Integer.MAX_VALUE;

        public static int solution(int[][] board) {
            int n = board.length;
            int[][][] cost = new int[n][n][4]; // 각 위치별 4방향 비용 저장
            for (int[][] row : cost) {
                for (int[] cell : row) {
                    Arrays.fill(cell, INF);
                }
            }

            PriorityQueue<Point> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.cost, b.cost));
            for (int i = 0;i < 4;i++) {
                pq.offer(new Point(0, 0, i, 0));
                cost[0][0][i] = 0;
            }

            while (!pq.isEmpty()) {
                Point cur = pq.poll();
                int x = cur.x, y = cur.y, dir = cur.dir, curCost = cur.cost;

                if (x == n - 1 && y == n - 1) {
                    return curCost; // 도착 지점 도달 시 최소 비용 반환
                }

                for (int newDir = 0;newDir < 4;newDir++) {
                    int nx = x + dx[newDir];
                    int ny = y + dy[newDir];

                    // 유효한 범위인지 확인
                    if (nx < 0 || ny < 0 || nx >= n || ny >= n || board[nx][ny] == 1) continue;

                    // 새로운 비용 계산
                    int newCost = curCost + 100; // 직선 도로
                    if (dir != newDir) newCost += 500; // 코너 비용

                    // 최소 비용 업데이트
                    if (cost[nx][ny][newDir] > newCost) {
                        cost[nx][ny][newDir] = newCost;
                        pq.offer(new Point(nx, ny, newDir, newCost));
                    }
                }
            }

            return INF; // 도달할 수 없는 경우 (문제 조건상 발생하지 않음)
        }

        static class Point {
            int x, y, dir, cost;

            Point(int x, int y, int dir, int cost) {
                this.x = x;
                this.y = y;
                this.dir = dir;
                this.cost = cost;
            }
        }
    }
}
