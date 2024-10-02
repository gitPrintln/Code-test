package programmerslv3;

import java.util.Stack;

public class 일일공옮기기 {

    /*
     * 문제 설명
       0과 1로 이루어진 어떤 문자열 x에 대해서, 당신은 다음과 같은 행동을 통해 x를 최대한 사전 순으로 앞에 오도록 만들고자 합니다.
     
       x에 있는 "110"을 뽑아서, 임의의 위치에 다시 삽입합니다.
       예를 들어, x = "11100" 일 때, 여기서 중앙에 있는 "110"을 뽑으면 x = "10" 이 됩니다. 뽑았던 "110"을 x의 맨 앞에 다시 삽입하면 x = "11010" 이 됩니다.
    
       변형시킬 문자열 x가 여러 개 들어있는 문자열 배열 s가 주어졌을 때, 각 문자열에 대해서 위의 행동으로 변형해서 만들 수 있는 문자열 중 사전 순으로 가장 앞에 오는 문자열을 배열에 담아 return 하도록 solution 함수를 완성해주세요.
       
     * 제한사항
       1 ≤ s의 길이 ≤ 1,000,000
       1 ≤ s의 각 원소 길이 ≤ 1,000,000
       1 ≤ s의 모든 원소의 길이의 합 ≤ 1,000,000
     * 입출력 예
       s   result
       ["1110","100111100","0111111010"]   ["1101","100110110","0110110111"]
     * 입출력 예 설명
     * 입출력 예 #1
    
       다음 그림은 "1110"을 "1101"로 만드는 과정을 나타낸 것입니다.
       110_ex1.png
    
       "1101"보다 사전 순으로 더 앞에 오는 문자열을 만들 수 없으므로, 배열에 "1101"을 담아야 합니다.
    
       다음 그림은 "100111100"을 "100110110"으로 만드는 과정을 나타낸 것입니다.
    
       110_ex2.png
    
       "100110110"보다 사전 순으로 더 앞에 오는 문자열을 만들 수 없으므로, 배열에 "100110110"을 담아야 합니다.
       그림에 나온 방식 말고도 다른 방법으로 "100110110"을 만들 수 있습니다.
    
       다음 그림은 "0111111010"을 "0110110111"로 만드는 과정을 나타낸 것입니다.
    
       110_ex3.png
    
       "0110110111"보다 사전 순으로 더 앞에 오는 문자열을 만들 수 없으므로, 배열에 "0110110111"을 담아야 합니다.
       그림에 나온 방식 말고도 다른 방법으로 "0110110111"을 만들 수 있습니다.
     */
    class Solution {
        public String[] solution(String[] s) {
            String[] result = new String[s.length];
            for (int i = 0;i < s.length;i++) {
                result[i] = rearrange(s[i]);
            }
            return result;
        }

        private String rearrange(String s) {
            Stack<Character> stack = new Stack<>();
            int cnt = 0;
            int len = s.length();
            // 1. 문자열에서 "110"을 모두 제거하고 "110"의 개수를 카운팅
            for (int i = 0;i < len;i++) {
                stack.push(s.charAt(i));

                if (stack.size() >= 3) {
                    char third = stack.pop();
                    char second = stack.pop();
                    char first = stack.pop();

                    if (first == '1' && second == '1' && third == '0') {
                        // "110" 발견
                        cnt++;
                    } else {
                        // "110"이 아니므로 다시 스택에 push
                        stack.push(first);
                        stack.push(second);
                        stack.push(third);
                    }
                }
            }

            // 2. 스택에 남아 있는 문자들을 다시 구성 (사전 순으로 앞에 오도록)
            StringBuilder sb = new StringBuilder();
            
            int last0idx = stack.size();
            boolean flag = false;
            while (!stack.isEmpty()) {
                char c = stack.pop();
                if (!flag) {
                    if(c == '0'){
                        flag = true;
                    }
                    last0idx--;
                }
                sb.append(c);
            }
            sb.reverse();
            
            // 사전순 더 앞에오는 문자열 만들 수 없는 경우
            if(!flag) return "110".repeat(cnt) + sb.toString(); 
            
            // sb의 앞부분과 뒷부분을 나눔 (last0idx 기준으로)
            String prefix = sb.substring(0, last0idx + 1); // 마지막 '0'까지 포함된 앞부분
            String suffix = sb.substring(last0idx + 1); // 마지막 '0' 이후의 뒷부분

            // 최종 결과 문자열
            return prefix + "110".repeat(cnt) + suffix;
        }
    }
}
