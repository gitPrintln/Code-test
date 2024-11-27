package programmerslv3;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class 단어변환3 {

    /* BFS로 풀이방법 중 코드 개선
     * Queue의 크기를 for 문에서 동적으로 관리하지 못함 -> 실제크기와 맞지않을 수 있음
     * set.toArray 성능 저하 -> Iterator
     * answer++;의 위치를 조정
     * 문제 설명
       두 개의 단어 begin, target과 단어의 집합 words가 있습니다. 아래와 같은 규칙을 이용하여 begin에서 target으로 변환하는 가장 짧은 변환 과정을 찾으려고 합니다.
    
       1. 한 번에 한 개의 알파벳만 바꿀 수 있습니다.
       2. words에 있는 단어로만 변환할 수 있습니다.
       예를 들어 begin이 "hit", target가 "cog", words가 ["hot","dot","dog","lot","log","cog"]라면 "hit" -> "hot" -> "dot" -> "dog" -> "cog"와 같이 4단계를 거쳐 변환할 수 있습니다.
    
       두 개의 단어 begin, target과 단어의 집합 words가 매개변수로 주어질 때, 최소 몇 단계의 과정을 거쳐 begin을 target으로 변환할 수 있는지 return 하도록 solution 함수를 작성해주세요.
    
     * 제한사항
       각 단어는 알파벳 소문자로만 이루어져 있습니다.
       각 단어의 길이는 3 이상 10 이하이며 모든 단어의 길이는 같습니다.
       words에는 3개 이상 50개 이하의 단어가 있으며 중복되는 단어는 없습니다.
       begin과 target은 같지 않습니다.
       변환할 수 없는 경우에는 0를 return 합니다.
     * 입출력 예
       begin   target  words   return
       "hit"   "cog"   ["hot", "dot", "dog", "lot", "log", "cog"]  4
       "hit"   "cog"   ["hot", "dot", "dog", "lot", "log"] 0
     * 입출력 예 설명
     * 예제 #1
       문제에 나온 예와 같습니다.
    
     * 예제 #2
       target인 "cog"는 words 안에 없기 때문에 변환할 수 없습니다.
     */
    class Solution {
        public int solution(String begin, String target, String[] words) {
            int answer = 0;
            Queue<String> queue = new LinkedList<>();
            Set<String> set = new HashSet<>(Arrays.asList(words));

            if (!set.contains(target)) {
                return 0; // 변환할 수 없는 경우
            }

            queue.offer(begin); 
            set.remove(begin);

            while (!queue.isEmpty()) {
                int size = queue.size();

                for (int i = 0;i < size;i++) {
                    String current = queue.poll();
                    if (current.equals(target)) {
                        return answer;
                    }

                    Iterator<String> iterator = set.iterator();
                    while (iterator.hasNext()) {
                        String word = iterator.next();
                        if (canConvert(current, word)) {
                            queue.offer(word);
                            iterator.remove();
                        }
                    }
                }

                answer++;
            }

            return 0;
        }

        private boolean canConvert(String word1, String word2) {
            int diffCnt = 0;
            for (int i = 0;i < word1.length();i++) {
                if (word1.charAt(i) != word2.charAt(i)) {
                    diffCnt++;
                }
            }
            return diffCnt == 1;
        }
    }
}
