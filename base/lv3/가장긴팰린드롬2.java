package programmerslv3;

public class 가장긴팰린드롬2 {

    /* 확장 중심 방법 알고리즘 사용해보기
     * 문제 설명
       앞뒤를 뒤집어도 똑같은 문자열을 팰린드롬(palindrome)이라고 합니다.
       문자열 s가 주어질 때, s의 부분문자열(Substring)중 가장 긴 팰린드롬의 길이를 return 하는 solution 함수를 완성해 주세요.
    
       예를들면, 문자열 s가 "abcdcba"이면 7을 return하고 "abacde"이면 3을 return합니다.
    
     * 제한사항
       문자열 s의 길이 : 2,500 이하의 자연수
       문자열 s는 알파벳 소문자로만 구성
     * 입출력 예
       s   answer
       "abcdcba"   7
       "abacde"    3
     * 입출력 예 설명
     * 입출력 예 #1
       4번째자리 'd'를 기준으로 문자열 s 전체가 팰린드롬이 되므로 7을 return합니다.
    
     * 입출력 예 #2
       2번째자리 'b'를 기준으로 "aba"가 팰린드롬이 되므로 3을 return합니다.
     */
    class Solution {
        public int solution(String s) {
            int maxLength = 0;

            for (int i = 0;i < s.length();i++) {
                // 홀수 길이 팰린드롬
                maxLength = Math.max(maxLength, expandAroundCenter(s, i, i));
                // 짝수 길이 팰린드롬
                maxLength = Math.max(maxLength, expandAroundCenter(s, i, i + 1));
            }

            return maxLength;
        }

        private int expandAroundCenter(String s, int left, int right) {
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            }
            // 길이는 (right - left - 1)
            return right - left - 1;
        }
    }
}
