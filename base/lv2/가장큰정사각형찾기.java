package programmers;

public class 가장큰정사각형찾기 {

    /*
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
    class Solution
    {
        public int solution(int [][]board)
        {
            int x = board.length;
            int y = board[0].length;
            int max = 0;
            
            if(x <= 1 || y <= 1) return 1;
            
            for(int i = 1;i < x;i++){
                for(int j = 1;j < y;j++){
                    if(board[i][j] >= 1){
                        int up = board[i-1][j];
                        int left = board[i][j-1];
                        int upperLeft = board[i-1][j-1];
                        int min = Math.min(up, left);
                        min = Math.min(min, upperLeft);
                        board[i][j] = min+1;
                        max = Math.max(max, min+1);
                    }
                }
            }
            return max*max;
        }
    }
}
