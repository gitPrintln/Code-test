package programmers;

public class NQueen {

    /*
     * 문제 설명
       가로, 세로 길이가 n인 정사각형으로된 체스판이 있습니다. 체스판 위의 n개의 퀸이 서로를 공격할 수 없도록 배치하고 싶습니다.
    
       예를 들어서 n이 4인경우 다음과 같이 퀸을 배치하면 n개의 퀸은 서로를 한번에 공격 할 수 없습니다.
    
       Imgur
       Imgur
       
       체스판의 가로 세로의 세로의 길이 n이 매개변수로 주어질 때, n개의 퀸이 조건에 만족 하도록 배치할 수 있는 방법의 수를 return하는 solution함수를 완성해주세요.
    
       제한사항
       퀸(Queen)은 가로, 세로, 대각선으로 이동할 수 있습니다.
       n은 12이하의 자연수 입니다.
       입출력 예
       n   result
       4   2
       입출력 예 설명
       입출력 예 #1
       문제의 예시와 같습니다.
     */
    class Solution {
        private static int[] board;
        private static int answer;

        public static int solution(int n) {
            // 배열의 값은 해당 행의 queen이 있는 '열(column)'을 의미함
            board = new int[n];

            backTracking(0, n);

            return answer;
        }

        // depth는 행을 의미함
        private static void backTracking(int depth, int n) {
            if (depth == n) {
                answer++;
                return;
            }
            for (int i = 0;i < n;i++) {
                board[depth] = i; // 해당 depth(행)과 i(열)에 퀸을 놓을 수 있는지 확인
                if (valid(depth)) {
                    backTracking(depth + 1, n);
                }
            }
        }

        private static boolean valid(int i) {
            for (int j = 0;j < i;j++) { // 마지막으로 놓여진 것과 이전의 것들을 비교
                if (board[i] == board[j]) return false;
                if (Math.abs(i - j) == Math.abs(board[i] - board[j])) return false;
            }
            return true;
        }
    }
}
