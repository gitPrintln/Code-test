package programmerslv3;

public class 이차원동전뒤집기 {

    /*
     * 문제 설명
       한수는 직사각형 모양의 공간에 놓인 동전들을 뒤집는 놀이를 하고 있습니다. 모든 동전들은 앞과 뒤가 구분되어 있으며, 동전을 뒤집기 위해서는 같은 줄에 있는 모든 동전을 뒤집어야 합니다. 동전들의 초기 상태와 목표 상태가 주어졌을 때, 초기 상태에서 최소 몇 번의 동전을 뒤집어야 목표 상태가 되는지 알아봅시다.
    
       2차원 1.png
    
       예를 들어, 위 그림에서 맨 왼쪽이 초기 상태, 맨 오른쪽이 목표 상태인 경우에 대해 알아봅시다. 그림에서 검은색 원은 앞면인 동전, 흰색 원은 뒷면인 동전을 의미합니다. 초기 상태에서 2행과 4행의 돌들을 뒤집으면, 두 번째 그림이 됩니다. 그 후, 2열, 4열, 5열의 돌들을 순서대로 뒤집는 다면, 총 5번의 동전 뒤집기를 통해 목표 상태가 되며, 이 경우가 최소인 경우입니다.
       
       직사각형 모양의 공간에 놓인 동전들의 초기 상태를 나타내는 2차원 정수 배열 beginning, 목표 상태를 나타내는 target이 주어졌을 때, 초기 상태에서 목표 상태로 만들기 위해 필요한 동전 뒤집기 횟수의 최솟값을 return 하는 solution 함수를 완성하세요. 단, 목표 상태를 만들지 못하는 경우에는 -1을 return 합니다.
    
     * 제한사항
       1 ≤ beginning의 길이 = target의 길이 ≤ 10
       1 ≤ beginning[i]의 길이 = target[i]의 길이 ≤ 10
       beginning[i][j]와 target[i][j]는 i + 1행 j + 1열의 동전의 상태를 나타내며, 0 또는 1의 값으로 주어집니다.
       0은 동전의 앞면을, 1은 동전의 뒷면을 의미합니다.
     * 입출력 예
       beginning   target  result
       [[0, 1, 0, 0, 0], [1, 0, 1, 0, 1], [0, 1, 1, 1, 0], [1, 0, 1, 1, 0], [0, 1, 0, 1, 0]]   [[0, 0, 0, 1, 1], [0, 0, 0, 0, 1], [0, 0, 1, 0, 1], [0, 0, 0, 1, 0], [0, 0, 0, 0, 1]]   5
       [[0, 0, 0], [0, 0, 0], [0, 0, 0]]   [[1, 0, 1], [0, 0, 0], [0, 0, 0]]   -1
     * 입출력 예 설명
     * 입출력 예 #1
    
       문제 예시와 같습니다.
     * 입출력 예 #2
    
       목표 상태를 만들지 못합니다. 따라서 -1을 return 합니다.
     */
    class Solution {
        // 목표 상태로 만들기 위해 뒤집어야 할 최소 횟수를 반환하는 함수
        public static int solution(int[][] beginning, int[][] target) {
            int n = beginning.length;
            int m = beginning[0].length;
            
            int minFlips = Integer.MAX_VALUE;
            
            // 행을 뒤집는 경우의 모든 경우의 수를 탐색 (행을 뒤집을지 말지 결정)
            for (int rowFlipMask = 0;rowFlipMask < (1 << n);rowFlipMask++) {
                int[][] copy = new int[n][m];
                
                // 복사한 배열에 beginning 배열의 값을 복사
                for (int i = 0;i < n;i++) {
                    copy[i] = beginning[i].clone();
                }

                // 행 뒤집기 적용
                for (int i = 0;i < n;i++) {
                    if ((rowFlipMask & (1 << i)) != 0) {
                        flipRow(copy, i);
                    }
                }
                
                // 열 뒤집기를 몇 번 해야 하는지 계산
                int flips = Integer.bitCount(rowFlipMask); // 현재 행 뒤집기 횟수
                boolean possible = true;

                for (int j = 0;j < m;j++) {
                    if (copy[0][j] != target[0][j]) {
                        flips++;
                        flipColumn(copy, j);
                    }
                }

                // 최종 배열이 목표 배열과 동일한지 확인
                if (isSame(copy, target)) {
                    minFlips = Math.min(minFlips, flips);
                }
            }
            
            return (minFlips == Integer.MAX_VALUE) ? -1 : minFlips;
        }

        // 행을 뒤집는 함수
        private static void flipRow(int[][] arr, int row) {
            for (int j = 0;j < arr[0].length;j++) {
                arr[row][j] = 1 - arr[row][j];
            }
        }

        // 열을 뒤집는 함수
        private static void flipColumn(int[][] arr, int col) {
            for (int i = 0;i < arr.length;i++) {
                arr[i][col] = 1 - arr[i][col];
            }
        }

        // 두 배열이 같은지 확인하는 함수
        private static boolean isSame(int[][] arr1, int[][] arr2) {
            for (int i = 0;i < arr1.length;i++) {
                for (int j = 0; j < arr1[0].length; j++) {
                    if (arr1[i][j] != arr2[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        }
    }
}
