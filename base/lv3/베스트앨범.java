package programmerslv3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 베스트앨범 {

    /*
     * 문제 설명
       스트리밍 사이트에서 장르 별로 가장 많이 재생된 노래를 두 개씩 모아 베스트 앨범을 출시하려 합니다. 노래는 고유 번호로 구분하며, 노래를 수록하는 기준은 다음과 같습니다.
    
       속한 노래가 많이 재생된 장르를 먼저 수록합니다.
       장르 내에서 많이 재생된 노래를 먼저 수록합니다.
       장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록합니다.
       노래의 장르를 나타내는 문자열 배열 genres와 노래별 재생 횟수를 나타내는 정수 배열 plays가 주어질 때, 베스트 앨범에 들어갈 노래의 고유 번호를 순서대로 return 하도록 solution 함수를 완성하세요.
    
     * 제한사항
       genres[i]는 고유번호가 i인 노래의 장르입니다.
       plays[i]는 고유번호가 i인 노래가 재생된 횟수입니다.
       genres와 plays의 길이는 같으며, 이는 1 이상 10,000 이하입니다.
       장르 종류는 100개 미만입니다.
       장르에 속한 곡이 하나라면, 하나의 곡만 선택합니다.
       모든 장르는 재생된 횟수가 다릅니다.
     * 입출력 예
       genres  plays   return
       ["classic", "pop", "classic", "classic", "pop"] [500, 600, 150, 800, 2500]  [4, 1, 3, 0]
     * 입출력 예 설명
       classic 장르는 1,450회 재생되었으며, classic 노래는 다음과 같습니다.
    
       고유 번호 3: 800회 재생
       고유 번호 0: 500회 재생
       고유 번호 2: 150회 재생
       pop 장르는 3,100회 재생되었으며, pop 노래는 다음과 같습니다.
       
       고유 번호 4: 2,500회 재생
       고유 번호 1: 600회 재생
       따라서 pop 장르의 [4, 1]번 노래를 먼저, classic 장르의 [3, 0]번 노래를 그다음에 수록합니다.
    
       장르 별로 가장 많이 재생된 노래를 최대 두 개까지 모아 베스트 앨범을 출시하므로 2번 노래는 수록되지 않습니다.
     */
    class Solution {
        public int[] solution(String[] genres, int[] plays) {
            List<Integer> answer = new ArrayList<>();
            // 장르별 총 개수를 저장
            Map<String, Integer> num = new HashMap<>();
            // 장르에 속하는 노래 및 재생횟수를 저장
            Map<String, HashMap<Integer, Integer>> music = new HashMap<>();
            
            for(int i = 0;i < plays.length;i++) {
                // 노래 장르가 없다면
                if(!num.containsKey(genres[i])) {
                    // 새로운 노래 장르를 생성
                    HashMap<Integer, Integer> map = new HashMap<>();
                    map.put(i, plays[i]);
                    music.put(genres[i], map);
                    num.put(genres[i], plays[i]);
                }
                // 이미 존재하는 장르라면
                else {
                    music.get(genres[i]).put(i, plays[i]);
                    num.put(genres[i], num.get(genres[i]) + plays[i]);
                }
            }
            
            // key 값만 가져와서 배열에 저장
            List<String> keySet = new ArrayList(num.keySet());
            // 곡의 수를 기준으로 내림차순 정렬을 진행
            Collections.sort(keySet, (s1, s2) -> num.get(s2) - num.get(s1));
            
            for(String key : keySet) {
                Map<Integer, Integer> map = music.get(key);
                List<Integer> genresKey = new ArrayList(map.keySet());
                
                // 재생횟수를 내림차순으로 정렬
                Collections.sort(genresKey, (s1, s2) -> map.get(s2) - map.get(s1));
                
                // 가장 큰 값을 저장
                answer.add(genresKey.get(0));
                // 값이 2개 이상일 경우
                if(genresKey.size() > 1) {
                    // 두번째로 큰 값까지 저장
                    answer.add(genresKey.get(1));
                }
            }
            
            return answer.stream().mapToInt(i -> i).toArray();
        }
    }
}
