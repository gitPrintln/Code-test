package programmerslv4;

public class 사칙연산 {

    /*
     * 문제 설명
       사칙연산에서 더하기(+)는 결합법칙이 성립하지만, 빼기(-)는 결합법칙이 성립하지 않습니다.
       예를 들어 식 1 - 5 - 3은 연산 순서에 따라 다음과 같이 다른 결과를 가집니다.
    
       ((1 - 5) - 3) = -7
       (1 - (5 - 3)) = -1
       위 예시와 같이 뺄셈은 연산 순서에 따라 그 결과가 바뀔 수 있습니다.
       또 다른 예로 식 1 - 3 + 5 - 8은 연산 순서에 따라 다음과 같이 5가지 결과가 나옵니다.
    
       (((1 - 3) + 5) - 8) = -5
       ((1 - (3 + 5)) - 8) = -15
       (1 - ((3 + 5) - 8)) = 1
       (1 - (3 + (5 - 8))) = 1
       ((1 - 3) + (5 - 8)) = -5
       위와 같이 서로 다른 연산 순서의 계산 결과는 [-15, -5, -5, 1, 1]이 되며, 이중 최댓값은 1입니다.
       문자열 형태의 숫자와, 더하기 기호("+"), 뺄셈 기호("-")가 들어있는 배열 arr가 매개변수로 주어질 때, 서로 다른 연산순서의 계산 결과 중 최댓값을 return 하도록 solution 함수를 완성해 주세요.
    
     * 제한 사항
       arr는 두 연산자 "+", "-" 와 숫자가 들어있는 배열이며, 길이는 3 이상 201 이하 입니다.
       arr의 길이는 항상 홀수입니다.
       arr에 들어있는 숫자의 개수는 2개 이상 101개 이하이며, 연산자의 개수는 (숫자의 개수) -1 입니다.
       숫자는 1 이상 1,000 이하의 자연수가 문자열 형태로 들어있습니다.. (ex : "456")
       배열의 첫 번째 원소와 마지막 원소는 반드시 숫자이며, 숫자와 연산자가 항상 번갈아가며 들어있습니다.
     * 입출력 예
       arr result
       ["1", "-", "3", "+", "5", "-", "8"] 1
       ["5", "-", "3", "+", "1", "+", "2", "-", "4"]   3
     * 입출력 예시
     * 입출력 예 #1
       위의 예시와 같이 (1-(3+(5-8))) = 1 입니다.
    
     * 입출력 예 #2
       (5-(3+((1+2)-4))) = 3 입니다.
     */
    class Solution {
        public int solution(String[] arr) {
            int n = arr.length;
            int numCount = (n + 1) / 2; // 숫자의 개수
            int[][] maxDp = new int[numCount][numCount];
            int[][] minDp = new int[numCount][numCount];

            // 숫자 초기화
            for (int i = 0;i < numCount;i++) {
                maxDp[i][i] = Integer.parseInt(arr[i * 2]);
                minDp[i][i] = Integer.parseInt(arr[i * 2]);
            }

            // DP 계산
            for (int size = 1;size < numCount;size++) { // 구간 크기
                for (int i = 0;i < numCount - size;i++) {
                    int j = i + size; // 구간 끝
                    maxDp[i][j] = Integer.MIN_VALUE;
                    minDp[i][j] = Integer.MAX_VALUE;

                    for (int k = i;k < j;k++) { // 중간 지점
                        char operator = arr[k * 2 + 1].charAt(0);
                        int[] results = calculate(maxDp[i][k], minDp[i][k], maxDp[k + 1][j], minDp[k + 1][j], operator);

                        maxDp[i][j] = Math.max(maxDp[i][j], results[0]);
                        minDp[i][j] = Math.min(minDp[i][j], results[1]);
                    }
                }
            }

            return maxDp[0][numCount - 1];
        }

        private int[] calculate(int max1, int min1, int max2, int min2, char operator) {
            int maxVal = Integer.MIN_VALUE;
            int minVal = Integer.MAX_VALUE;

            if (operator == '+') {
                maxVal = Math.max(maxVal, max1 + max2);
                maxVal = Math.max(maxVal, min1 + min2);
                minVal = Math.min(minVal, max1 + max2);
                minVal = Math.min(minVal, min1 + min2);
            } else if (operator == '-') {
                maxVal = Math.max(maxVal, max1 - min2);
                maxVal = Math.max(maxVal, min1 - max2);
                minVal = Math.min(minVal, max1 - min2);
                minVal = Math.min(minVal, min1 - max2);
            }

            return new int[]{maxVal, minVal};
        }
    }
}
