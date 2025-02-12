package programmerslv3;

public class 자물쇠와열쇠2 {

    /* 슬라이딩 윈도우(Sliding Window)로 풀이
     * 키를 자물쇠에 슬라이드 할 경우 자물쇠가 열리는지 확인
     * 코드가 직관적이어서 이해하기 좋지만 최적화 필요
     * 키와 자물쇠의 크기가 크면, 각 위치마다 계산을 반복해야 하므로 성능에 제한(시간복잡도 증가)
     * 문제 설명
       고고학자인 "튜브"는 고대 유적지에서 보물과 유적이 가득할 것으로 추정되는 비밀의 문을 발견하였습니다. 그런데 문을 열려고 살펴보니 특이한 형태의 자물쇠로 잠겨 있었고 문 앞에는 특이한 형태의 열쇠와 함께 자물쇠를 푸는 방법에 대해 다음과 같이 설명해 주는 종이가 발견되었습니다.
    
       잠겨있는 자물쇠는 격자 한 칸의 크기가 1 x 1인 N x N 크기의 정사각 격자 형태이고 특이한 모양의 열쇠는 M x M 크기인 정사각 격자 형태로 되어 있습니다.
    
       자물쇠에는 홈이 파여 있고 열쇠 또한 홈과 돌기 부분이 있습니다. 열쇠는 회전과 이동이 가능하며 열쇠의 돌기 부분을 자물쇠의 홈 부분에 딱 맞게 채우면 자물쇠가 열리게 되는 구조입니다. 자물쇠 영역을 벗어난 부분에 있는 열쇠의 홈과 돌기는 자물쇠를 여는 데 영향을 주지 않지만, 자물쇠 영역 내에서는 열쇠의 돌기 부분과 자물쇠의 홈 부분이 정확히 일치해야 하며 열쇠의 돌기와 자물쇠의 돌기가 만나서는 안됩니다. 또한 자물쇠의 모든 홈을 채워 비어있는 곳이 없어야 자물쇠를 열 수 있습니다.
    
       열쇠를 나타내는 2차원 배열 key와 자물쇠를 나타내는 2차원 배열 lock이 매개변수로 주어질 때, 열쇠로 자물쇠를 열수 있으면 true를, 열 수 없으면 false를 return 하도록 solution 함수를 완성해주세요.
    
     * 제한사항
       key는 M x M(3 ≤ M ≤ 20, M은 자연수)크기 2차원 배열입니다.
       lock은 N x N(3 ≤ N ≤ 20, N은 자연수)크기 2차원 배열입니다.
       M은 항상 N 이하입니다.
       key와 lock의 원소는 0 또는 1로 이루어져 있습니다.
       0은 홈 부분, 1은 돌기 부분을 나타냅니다.
     * 입출력 예
       key lock    result
       [[0, 0, 0], [1, 0, 0], [0, 1, 1]]   [[1, 1, 1], [1, 1, 0], [1, 0, 1]]   true
     * 입출력 예에 대한 설명
       자물쇠.jpg
    
       key를 시계 방향으로 90도 회전하고, 오른쪽으로 한 칸, 아래로 한 칸 이동하면 lock의 홈 부분을 정확히 모두 채울 수 있습니다.
     */
    class Solution {
        public static boolean solution(int[][] key, int[][] lock) {
            int n = lock.length;
            int m = key.length;

            // 자물쇠와 키에 대한 회전 상태 저장
            int[][][] rotatedKey = new int[4][m][m];
            rotatedKey[0] = key;

            // 키의 4가지 회전 상태 계산
            for (int i = 1;i < 4;i++) {
                rotatedKey[i] = rotate(rotatedKey[i-1]);
            }

            // 자물쇠 크기 확장
            int mapSize = n + 2 * (m - 1);
            int[][] map = new int[mapSize][mapSize];
            
            // 자물쇠 복사
            for (int i = 0;i < n;i++) {
                for (int j = 0;j < n;j++) {
                    map[m - 1 + i][m - 1 + j] = lock[i][j];
                }
            }

            // 각 회전 상태에 대해 자물쇠를 열 수 있는지 확인
            for (int i = 0;i < 4;i++) {
                int[][] currentKey = rotatedKey[i];
                for (int row = 0;row <= mapSize - m;row++) {
                    for (int col = 0;col <= mapSize - m;col++) {
                        if (trying(currentKey, map, row, col, m, n)) {
                            return true;
                        }
                    }
                }
            }

            return false;
        }

        // 키를 90도 회전시키는 함수
        public static int[][] rotate(int[][] key) {
            int len = key.length;
            int[][] rotated = new int[len][len];

            for (int i = 0;i < len;i++) {
                for (int j = 0; j < len; j++) {
                    rotated[j][len - 1 - i] = key[i][j];
                }
            }

            return rotated;
        }

        // 키를 자물쇠에 맞추려고 시도하는 함수
        public static boolean trying(int[][] key, int[][] map, int row, int col, int m, int n) {
            int[][] tempMap = new int[map.length][map.length];

            // map을 복사하여 tempMap을 만든다
            for (int i = 0;i < map.length;i++) {
                tempMap[i] = map[i].clone();
            }

            // key를 삽입
            for (int i = 0;i < m;i++) {
                for (int j = 0;j < m;j++) {
                    if (key[i][j] == 1) {
                        if (tempMap[row + i][col + j] == 1) {
                            return false;  // 이미 자물쇠에 맞지 않음
                        }
                        tempMap[row + i][col + j] = 1;  // 키가 자물쇠에 맞음
                    }
                }
            }

            // 자물쇠 검사 (모든 자물쇠가 채워져 있으면 성공)
            for (int i = m - 1;i < n + m - 1;i++) {
                for (int j = m - 1;j < n + m - 1;j++) {
                    if (tempMap[i][j] == 0) {
                        return false;
                    }
                }
            }

            return true;
        }

    }
}
