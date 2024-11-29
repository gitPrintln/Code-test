package programmerslv3;

import java.util.Arrays;

public class 등굣길2 {

    /* DFS와 메모이제이션으로 풀이 주어진 문제에서 테스트 효율은 DP 알고리즘과 비슷했음
     * 문제 설명
       계속되는 폭우로 일부 지역이 물에 잠겼습니다. 물에 잠기지 않은 지역을 통해 학교를 가려고 합니다. 집에서 학교까지 가는 길은 m x n 크기의 격자모양으로 나타낼 수 있습니다.
    
       아래 그림은 m = 4, n = 3 인 경우입니다.
    
       image0.png
    
       가장 왼쪽 위, 즉 집이 있는 곳의 좌표는 (1, 1)로 나타내고 가장 오른쪽 아래, 즉 학교가 있는 곳의 좌표는 (m, n)으로 나타냅니다.
    
       격자의 크기 m, n과 물이 잠긴 지역의 좌표를 담은 2차원 배열 puddles이 매개변수로 주어집니다. 오른쪽과 아래쪽으로만 움직여 집에서 학교까지 갈 수 있는 최단경로의 개수를 1,000,000,007로 나눈 나머지를 return 하도록 solution 함수를 작성해주세요.
    
     * 제한사항
       격자의 크기 m, n은 1 이상 100 이하인 자연수입니다.
       m과 n이 모두 1인 경우는 입력으로 주어지지 않습니다.
       물에 잠긴 지역은 0개 이상 10개 이하입니다.
       집과 학교가 물에 잠긴 경우는 입력으로 주어지지 않습니다.
     * 입출력 예
       m   n   puddles return
       4   3   [[2, 2]]    4
     * 입출력 예 설명
       image1.png
     */
    class Solution {
        private int[][] board;  // 지도 정보 저장
        private int[][] memo;   // 메모이제이션 배열
        private int mod = 1000000007;  // 모듈러 연산 값

        public int solution(int m, int n, int[][] puddles) {
            board = new int[n + 1][m + 1];
            memo = new int[n + 1][m + 1];

            // 메모이제이션 배열 초기화 (-1로 설정)
            for (int[] row : memo) {
                Arrays.fill(row, -1);
            }

            // 물웅덩이 표시
            for (int[] puddle : puddles) {
                board[puddle[1]][puddle[0]] = -1;
            }

            return dfs(1, 1, m, n);
        }

        private int dfs(int x, int y, int m, int n) {
            // 목적지에 도달하면 1개의 경로를 찾았다는 의미
            if (x == n && y == m) {
                return 1;
            }

            // 범위를 벗어나거나 물웅덩이인 경우 경로 없음
            if (x > n || y > m || board[x][y] == -1) {
                return 0;
            }

            // 이미 계산된 경로가 있으면 메모이제이션 값 반환
            if (memo[x][y] != -1) {
                return memo[x][y];
            }

            // 오른쪽과 아래쪽으로 이동하여 경로 탐색
            int right = dfs(x, y + 1, m, n);  // 오른쪽 이동
            int down = dfs(x + 1, y, m, n);   // 아래쪽 이동

            // 메모이제이션에 결과 저장 (모듈러 연산 적용)
            return memo[x][y] = (right + down) % mod;
        }
    }
}
