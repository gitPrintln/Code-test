package programmers;

import java.util.ArrayList;

public class 삼차압축 {

    /*
     * 에 해당하는 색인 번호 11을 출력하고, 다음 글자인 A를 포함한 KA를 사전에 27 번째로 등록한다.
       두 번째 글자 A는 사전에 있으나, 세 번째 글자까지인 AK는 사전에 없으므로, A의 색인 번호 1을 출력하고, AK를 사전에 28 번째로 등록한다.
       세 번째 글자에서 시작하는 KA가 사전에 있으므로, KA에 해당하는 색인 번호 27을 출력하고, 다음 글자 O를 포함한 KAO를 29 번째로 등록한다.
       마지막으로 처리되지 않은 글자 O에 해당하는 색인 번호 15를 출력한다.
       현재 입력(w)    다음 글자(c)    출력  사전 추가(w+c)
       K   A   11  27: KA
       A   K   1   28: AK
       KA  O   27  29: KAO
       O       15  
       이 과정을 거쳐 다섯 글자의 문장 KAKAO가 4개의 색인 번호 [11, 1, 27, 15]로 압축된다.
    
       입력으로 TOBEORNOTTOBEORTOBEORNOT가 들어오면 다음과 같이 압축이 진행된다.
    
       현재 입력(w)    다음 글자(c)    출력  사전 추가(w+c)
       T   O   20  27: TO
       O   B   15  28: OB
       B   E   2   29: BE
       E   O   5   30: EO
       O   R   15  31: OR
       R   N   18  32: RN
       N   O   14  33: NO
       O   T   15  34: OT
       T   T   20  35: TT
       TO  B   27  36: TOB
       BE  O   29  37: BEO
       OR  T   31  38: ORT
       TOB E   36  39: TOBE
       EO  R   30  40: EOR
       RN  O   32  41: RNO
       OT      34  
     * 입력 형식
       입력으로 영문 대문자로만 이뤄진 문자열 msg가 주어진다. msg의 길이는 1 글자 이상, 1000 글자 이하이다.
    
     * 출력 형식
       주어진 문자열을 압축한 후의 사전 색인 번호를 배열로 출력하라.
    
     * 입출력 예제
       msg answer
       KAKAO   [11, 1, 27, 15]
       TOBEORNOTTOBEORTOBEORNOT    [20, 15, 2, 5, 15, 18, 14, 15, 20, 27, 29, 31, 36, 30, 32, 34]
       ABABABABABABABAB    [1, 2, 27, 29, 28, 31, 30]
     */
    class Solution {
        public int[] solution(String msg) {
            ArrayList<String> dic = new ArrayList<String>();
            ArrayList<Integer> result = new ArrayList<Integer>();

            for(int i = 0;i < 26;i++) {
                dic.add(String.valueOf((char)('A'+i)));
            }

            for(int i = 0;i < msg.length();i++) {
                for(int j = dic.size()-1;j >= 0;j--) {
                    if(msg.substring(i).startsWith(dic.get(j))) {
                        i += dic.get(j).length()-1;
                        result.add(j+1);
                        if(i+1 < msg.length())
                            dic.add(dic.get(j)+msg.charAt(i+1));
                        break;
                    }
                }
            }

            int[] answer = new int[result.size()];

            for(int i = 0;i < result.size();i++) 
                answer[i] = result.get(i);

            return answer;  
        }
    }
}
