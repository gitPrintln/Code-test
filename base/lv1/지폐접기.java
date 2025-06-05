package programmerslv012;

public class 지폐접기 {

    /*
     * 문제 설명
       민수는 다양한 지폐를 수집하는 취미를 가지고 있습니다. 지폐마다 크기가 달라 지갑에 넣으려면 여러 번 접어서 넣어야 합니다. 예를 들어 지갑의 크기가 30 * 15이고 지폐의 크기가 26 * 17이라면 한번 반으로 접어 13 * 17 크기로 만든 뒤 90도 돌려서 지갑에 넣을 수 있습니다. 지폐를 접을 때는 다음과 같은 규칙을 지킵니다.
    
       지폐를 접을 때는 항상 길이가 긴 쪽을 반으로 접습니다.
       접기 전 길이가 홀수였다면 접은 후 소수점 이하는 버립니다.
       접힌 지폐를 그대로 또는 90도 돌려서 지갑에 넣을 수 있다면 그만 접습니다.
       지갑의 가로, 세로 크기를 담은 정수 리스트 wallet과 지폐의 가로, 세로 크기를 담은 정수 리스트 bill가 주어질 때, 지갑에 넣기 위해서 지폐를 최소 몇 번 접어야 하는지 return하도록 solution함수를 완성해 주세요.
    
       지폐를 지갑에 넣기 위해 접어야 하는 최소 횟수를 구하는 과정은 다음과 같습니다.
    
       1. 지폐를 접은 횟수를 저장할 정수 변수 answer를 만들고 0을 저장합니다.
       2. 반복문을 이용해 bill의 작은 값이 wallet의 작은 값 보다 크거나 bill의 큰 값이 wallet의 큰 값 보다 큰 동안 아래 과정을 반복합니다.
           2-1. bill[0]이 bill[1]보다 크다면
              bill[0]을 2로 나누고 나머지는 버립니다.
           2-2. 그렇지 않다면
            bill[1]을 2로 나누고 나머지는 버립니다.
        2-3. answer을 1 증가시킵니다.
       3. answer을 return합니다.
       위의 의사코드와 작동방식이 다른 코드를 작성해도 상관없습니다.
     * 제한사항
       wallet의 길이 = bill의 길이 = 2
       10 ≤ wallet[0], wallet[1] ≤ 100
       10 ≤ bill[0], bill[1] ≤ 2,000
     * 입출력 예
       wallet  bill    result
       [30, 15]    [26, 17]    1
       [50, 50]    [100, 241]  4
     * 입출력 예 설명
     * 입출력 예 #1
    
       지문과 동일합니다.
     * 입출력 예 #2
    
       지폐를 접으면 다음과 같이 크기가 줄어듭니다. 따라서 4번 접으면 지갑에 넣을 수 있습니다.
       [100, 241] -> [100, 120] -> [100, 60] -> [50, 60] -> [50, 30]
     */
    class Solution {
        public int solution(int[] wallet, int[] bill) {
            int answer = 0; // 접은 횟수

            // 현재 지폐 크기
            int w = bill[0];
            int h = bill[1];

            // 지갑 크기
            int walletW = wallet[0];
            int walletH = wallet[1];

            // 지폐가 지갑에 들어갈 때까지 반복
            while (!canFit(w, h, walletW, walletH)) {
                // 지폐 긴쪽의 반을 접음
                if (w >= h) {
                    w = w / 2;
                } else {
                    h = h / 2;
                }
                answer++;
            }

            return answer;
        }

        private boolean canFit(int w, int h, int walletW, int walletH) {
            // 지갑에 들어갈 수 있는 경우: 두 가지 방향 모두 확인(w, h / h,w)
            return (w <= walletW && h <= walletH) || (h <= walletW && w <= walletH);
        }
    }
}
