package programmerslv012;

public class 가장큰정사각형찾기2 {

    /* DP로 풀이
     * 문제 설명
       1와 0로 채워진 표(board)가 있습니다. 표 1칸은 1 x 1 의 정사각형으로 이루어져 있습니다. 표에서 1로 이루어진 가장 큰 정사각형을 찾아 넓이를 return 하는 solution 함수를 완성해 주세요. (단, 정사각형이란 축에 평행한 정사각형을 말합니다.)
    
       예를 들어
    
       1   2   3   4
       0   1   1   1
       1   1   1   1
       1   1   1   1
       0   0   1   0
       가 있다면 가장 큰 정사각형은
    
       1   2   3   4
       0   1   1   1
       1   1   1   1
       1   1   1   1
       0   0   1   0
       가 되며 넓이는 9가 되므로 9를 반환해 주면 됩니다.
    
     * 제한사항
       표(board)는 2차원 배열로 주어집니다.
       표(board)의 행(row)의 크기 : 1,000 이하의 자연수
       표(board)의 열(column)의 크기 : 1,000 이하의 자연수
       표(board)의 값은 1또는 0으로만 이루어져 있습니다.
     * 입출력 예
       board   answer
       [[0,1,1,1],[1,1,1,1],[1,1,1,1],[0,0,1,0]]   9
       [[0,0,1,1],[1,1,1,1]]   4
     * 입출력 예 설명
     * 입출력 예 #1
       위의 예시와 같습니다.
    
     * 입출력 예 #2
       | 0 | 0 | 1 | 1 |
       | 1 | 1 | 1 | 1 |
       로 가장 큰 정사각형의 넓이는 4가 되므로 4를 return합니다.
     */
    public class Solution {
        public int solution(int[][] board) {
            int n = board.length;
            int m = board[0].length;
            
            // dp 배열 초기화
            int[][] dp = new int[n][m];
            int maxSize = 0;
            
            // dp 배열 계산
            for (int i = 0;i < n;i++) {
                for (int j = 0;j < m;j++) {
                    if (board[i][j] == 1) {
                        // 첫 번째 행이나 첫 번째 열의 경우, 정사각형 크기는 원본 배열 값과 동일
                        if (i == 0 || j == 0) {
                            dp[i][j] = 1;
                        } else {
                            // dp 값을 상단, 왼쪽, 왼쪽 상단 중 최소값에 1을 더한 값으로 설정
                            dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1])) + 1;
                        }
                        // 최대 정사각형의 크기를 갱신
                        maxSize = Math.max(maxSize, dp[i][j]);
                    }
                }
            }
            
            // 최대 정사각형의 넓이 계산
            return maxSize * maxSize;
        }
    }
}
