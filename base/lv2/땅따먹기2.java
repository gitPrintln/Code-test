package programmerslv012;

public class 땅따먹기2 {

    /* 인라인 업데이트 : 데이터 구조를 수정할 때 별도의 임시 구조를 사용하지 않고, 기존의 데이터 구조를 직접 수정하여 결과를 저장하는 기법
     * 문제 설명
       땅따먹기 게임을 하려고 합니다. 땅따먹기 게임의 땅(land)은 총 N행 4열로 이루어져 있고, 모든 칸에는 점수가 쓰여 있습니다. 1행부터 땅을 밟으며 한 행씩 내려올 때, 각 행의 4칸 중 한 칸만 밟으면서 내려와야 합니다. 단, 땅따먹기 게임에는 한 행씩 내려올 때, 같은 열을 연속해서 밟을 수 없는 특수 규칙이 있습니다.
    
       예를 들면,
    
       | 1 | 2 | 3 | 5 |
    
       | 5 | 6 | 7 | 8 |
    
       | 4 | 3 | 2 | 1 |
    
       로 땅이 주어졌다면, 1행에서 네번째 칸 (5)를 밟았으면, 2행의 네번째 칸 (8)은 밟을 수 없습니다.
    
       마지막 행까지 모두 내려왔을 때, 얻을 수 있는 점수의 최대값을 return하는 solution 함수를 완성해 주세요. 위 예의 경우, 1행의 네번째 칸 (5), 2행의 세번째 칸 (7), 3행의 첫번째 칸 (4) 땅을 밟아 16점이 최고점이 되므로 16을 return 하면 됩니다.
       
     * 제한사항
       행의 개수 N : 100,000 이하의 자연수
       열의 개수는 4개이고, 땅(land)은 2차원 배열로 주어집니다.
       점수 : 100 이하의 자연수
     * 입출력 예
       land    answer
       [[1,2,3,5],[5,6,7,8],[4,3,2,1]] 16
     * 입출력 예 설명
     * 입출력 예 #1
       문제의 예시와 같습니다.
     */
    class Solution {
        public int solution(int[][] land) {
            int N = land.length;
            int[] prev = new int[4]; // 이전 행의 최대 점수를 저장할 배열
            
            // 첫 번째 행의 점수로 초기화
            for (int j = 0;j < 4;j++) {
                prev[j] = land[0][j];
            }
            
            // 현재 행을 계산
            for (int i = 1;i < N;i++) {
                int[] current = new int[4]; // 현재 행의 최대 점수를 저장할 배열
                for (int j = 0;j < 4;j++) {
                    // 현재 행의 j열을 선택할 때, 이전 행의 j열을 제외한 최대 점수 찾기
                    int maxPrev = 0;
                    for (int k = 0;k < 4;k++) {
                        if (k != j) {
                            maxPrev = Math.max(maxPrev, prev[k]);
                        }
                    }
                    current[j] = land[i][j] + maxPrev;
                }
                // 현재 행을 이전 행으로 업데이트
                prev = current;
            }
            
            // 마지막 행에서의 최대 점수를 반환
            int answer = 0;
            for (int j = 0;j < 4;j++) {
                answer = Math.max(answer, prev[j]);
            }
            
            return answer;
        }
    }
}
