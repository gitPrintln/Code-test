package programmerslv3;

public class 정수삼각형2 {

    /* DP가아닌 재귀로 사용해서 문제를 풀경우 너무 시간이 오래걸리고 비효율적임
     * 그래서 배열을 이중으로 덮어쓰면 메모리사용을 줄이면서 DP없이 풀 수 있음
     * 문제 설명
       위와 같은 삼각형의 꼭대기에서 바닥까지 이어지는 경로 중, 거쳐간 숫자의 합이 가장 큰 경우를 찾아보려고 합니다. 아래 칸으로 이동할 때는 대각선 방향으로 한 칸 오른쪽 또는 왼쪽으로만 이동 가능합니다. 예를 들어 3에서는 그 아래칸의 8 또는 1로만 이동이 가능합니다.
    
       삼각형의 정보가 담긴 배열 triangle이 매개변수로 주어질 때, 거쳐간 숫자의 최댓값을 return 하도록 solution 함수를 완성하세요.
    
     * 제한사항
       삼각형의 높이는 1 이상 500 이하입니다.
       삼각형을 이루고 있는 숫자는 0 이상 9,999 이하의 정수입니다.
     * 입출력 예
       triangle    result
       [[7], [3, 8], [8, 1, 0], [2, 7, 4, 4], [4, 5, 2, 6, 5]] 30
     */
    class Solution {
        public int solution(int[][] triangle) {
            int height = triangle.length;

            // 삼각형 아래에서부터 위로 최대값을 계산하며 덮어씀
            for (int row = height - 2;row >= 0;row--) {
                for (int col = 0;col < triangle[row].length;col++) {
                    triangle[row][col] += Math.max(triangle[row + 1][col], triangle[row + 1][col + 1]);
                }
            }

            // 최종적으로 triangle[0][0]에 최대 합이 저장됨
            return triangle[0][0];
        }
    }
}
