package programmerslv3;

public class 최적의행렬 {

    /*
     * 문제 설명
       크기가 a by b인 행렬과 크기가 b by c 인 행렬이 있을 때, 두 행렬을 곱하기 위해서는 총 a x b x c 번 곱셈해야합니다.
       예를 들어서 크기가 5 by 3인 행렬과 크기가 3 by 2인 행렬을 곱할때는 총 5 x 3 x 2 = 30번의 곱하기 연산을 해야 합니다.
    
       행렬이 2개일 때는 연산 횟수가 일정 하지만, 행렬의 개수가 3개 이상일 때는 연산의 순서에 따라서 곱하기 연산의 횟수가 바뀔 수 있습니다. 예를 들어서 크기가 5 by 3인 행렬 A, 크기가 3 by 10인 행렬 B, 크기가 10 by 6인 행렬 C가 있을 때, 순서대로 A와 B를 먼저 곱하고, 그 결과에 C를 곱하면 A와 B행렬을 곱할 때 150번을, AB 에 C를 곱할 때 300번을 연산을 해서 총 450번의 곱하기 연산을 합니다. 하지만, B와 C를 먼저 곱한 다음 A 와 BC를 곱하면 180 + 90 = 270번 만에 연산이 끝납니다.
    
       각 행렬의 크기 matrix_sizes 가 매개변수로 주어 질 때, 모든 행렬을 곱하기 위한 최소 곱셈 연산의 수를 return하는 solution 함수를 완성해 주세요.
    
     * 제한 사항
       행렬의 개수는 3이상 200이하의 자연수입니다.
       각 행렬의 행과 열의 크기는 200이하의 자연수 입니다.
       계산을 할 수 없는 행렬은 입력으로 주어지지 않습니다.
     * 입출력 예
       matrix_sizes    result
       [[5,3],[3,10],[10,6]]   270
     * 입출력 예 설명
     * 입출력 예#1
       문제의 예시와 같습니다.
     */
    class Solution {
        public int solution(int[][] matrix_sizes) {
            int n = matrix_sizes.length;
            int[][] dp = new int[n][n];  // DP 테이블
            
            // DP 테이블 초기화
            for (int len = 1;len < n;len++) {
                for (int i = 0;i < n - len;i++) {
                    int j = i + len;
                    dp[i][j] = Integer.MAX_VALUE;  // 최소 값을 찾기 위해 초기화를 큰 값으로 설정
                    
                    // dp[i][j] 값을 갱신
                    for (int k = i;k < j;k++) {
                        int cost = dp[i][k] + dp[k+1][j] + matrix_sizes[i][0] * matrix_sizes[k][1] * matrix_sizes[j][1];
                        dp[i][j] = Math.min(dp[i][j], cost);
                    }
                }
            }
            
            // 최종적으로 dp[0][n-1]에는 모든 행렬을 곱하는 데 필요한 최소 곱셈 횟수가 저장됨
            return dp[0][n-1];
        }
    }
}
