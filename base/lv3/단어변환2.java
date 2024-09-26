package programmerslv3;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class 단어변환2 {

    /* DFS로 풀이
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
        private int answer = Integer.MAX_VALUE; // 최소 단계 수
        
        public int solution(String begin, String target, String[] words) {
            Set<String> set = new HashSet<>(Arrays.asList(words)); // 단어들의 집합 set
            if(!set.contains(target)){
                return 0; // 변환할 수 없는 경우
            }
            
            // DFS 탐색 시작
            dfs(begin, target, words, 0, new boolean[words.length]);
            
            return answer == Integer.MAX_VALUE ? 0 : answer; // 변환 불가능한 경우 0 반환
        }
        
        private void dfs(String currentWord, String target, String[] words, int steps, boolean[] visited) {
            // 목표 단어에 도달한 경우 최소 단계 수를 업데이트
            if (currentWord.equals(target)) {
                answer = Math.min(answer, steps);
                return;
            }
            
            // 모든 단어를 확인
            for (int i = 0;i < words.length;i++) {
                // 아직 방문하지 않았고 변환 가능한 경우
                if (!visited[i] && canConvert(currentWord, words[i])) {
                    visited[i] = true; // 방문 처리
                    dfs(words[i], target, words, steps + 1, visited); // 다음 단계로 DFS
                    visited[i] = false; // 백트래킹
                }
            }
        }
        
        private boolean canConvert(String word1, String word2){
            int diffCnt = 0;
            for (int i = 0;i < word1.length();i++){
                if (word1.charAt(i) != word2.charAt(i)){
                    diffCnt++;
                }
            }
            return diffCnt ==1;
        }
    }
}
