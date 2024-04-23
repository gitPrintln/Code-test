package programmers;

import java.util.ArrayList;
import java.util.List;

public class 모음사전 {

    /*
     * 문제 설명
       사전에 알파벳 모음 'A', 'E', 'I', 'O', 'U'만을 사용하여 만들 수 있는, 길이 5 이하의 모든 단어가 수록되어 있습니다. 사전에서 첫 번째 단어는 "A"이고, 그다음은 "AA"이며, 마지막 단어는 "UUUUU"입니다.
    
       단어 하나 word가 매개변수로 주어질 때, 이 단어가 사전에서 몇 번째 단어인지 return 하도록 solution 함수를 완성해주세요.
    
     * 제한사항
       word의 길이는 1 이상 5 이하입니다.
       word는 알파벳 대문자 'A', 'E', 'I', 'O', 'U'로만 이루어져 있습니다.
     * 입출력 예
       word    result
       "AAAAE" 6
       "AAAE"  10
       "I" 1563
       "EIO"   1189
     * 입출력 예 설명
     * 입출력 예 #1
    
       사전에서 첫 번째 단어는 "A"이고, 그다음은 "AA", "AAA", "AAAA", "AAAAA", "AAAAE", ... 와 같습니다. "AAAAE"는 사전에서 6번째 단어입니다.
    
     * 입출력 예 #2
    
       "AAAE"는 "A", "AA", "AAA", "AAAA", "AAAAA", "AAAAE", "AAAAI", "AAAAO", "AAAAU"의 다음인 10번째 단어입니다.
    
     * 입출력 예 #3
    
       "I"는 1563번째 단어입니다.
    
     * 입출력 예 #4
    
       "EIO"는 1189번째 단어입니다.
     */
    class Solution {
        static List<String> list;
        static String [] words = {"A", "E", "I", "O", "U"};
        
        public int solution(String word) {
            int answer = 0;
            list = new ArrayList<>();
            dfs("", 0);
            int size = list.size();
            for (int i = 0;i < size;i++) {
                if (list.get(i).equals(word)) {
                    answer = i;
                    break;
                }
            }
            return answer;
        }

        static void dfs(String str, int len) {
            list.add(str);
            if (len == 5) return;
            for (int i = 0;i < 5;i++) {
                dfs(str + words[i], len + 1);
            }
        }
    }
}
