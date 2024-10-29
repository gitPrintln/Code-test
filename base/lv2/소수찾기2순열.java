package programmerslv012;

import java.util.HashSet;
import java.util.Set;

public class 소수찾기2순열 {

    /*
     * 문제 설명
       한자리 숫자가 적힌 종이 조각이 흩어져있습니다. 흩어진 종이 조각을 붙여 소수를 몇 개 만들 수 있는지 알아내려 합니다.
    
       각 종이 조각에 적힌 숫자가 적힌 문자열 numbers가 주어졌을 때, 종이 조각으로 만들 수 있는 소수가 몇 개인지 return 하도록 solution 함수를 완성해주세요.
    
     * 제한사항
       numbers는 길이 1 이상 7 이하인 문자열입니다.
       numbers는 0~9까지 숫자만으로 이루어져 있습니다.
       "013"은 0, 1, 3 숫자가 적힌 종이 조각이 흩어져있다는 의미입니다.
     * 입출력 예
       numbers return
       "17"    3
       "011"   2
     * 입출력 예 설명
     * 예제 #1
       [1, 7]으로는 소수 [7, 17, 71]를 만들 수 있습니다.
    
     * 예제 #2
       [0, 1, 1]으로는 소수 [11, 101]를 만들 수 있습니다.
    
       11과 011은 같은 숫자로 취급합니다.
     */
    class Solution {
        Set<Integer> uniqueNumbers = new HashSet<>();

        public int solution(String numbers) {
            // 모든 숫자 조합 생성
            generatePermutations("", numbers);

            // 소수 판별 및 개수 세기
            int primeCount = 0;
            for (int number : uniqueNumbers) {
                if (isPrime(number)) {
                    primeCount++;
                }
            }
            return primeCount;
        }

        // 순열을 통해 모든 숫자 조합 생성
        private void generatePermutations(String prefix, String remaining) {
            if (!prefix.isEmpty()) {
                uniqueNumbers.add(Integer.parseInt(prefix));
            }
            for (int i = 0;i < remaining.length();i++) {
                generatePermutations(prefix + remaining.charAt(i), 
                                     remaining.substring(0, i) + remaining.substring(i + 1));
            }
        }

        // 소수 판별 함수
        private boolean isPrime(int number) {
            if (number < 2) return false;
            for (int i = 2;i * i <= number;i++) {
                if (number % i == 0) return false;
            }
            return true;
        }
    }
}
