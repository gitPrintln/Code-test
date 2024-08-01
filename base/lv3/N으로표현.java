package programmerslv3;

public class N으로표현 {

    /*
     * 문제 설명
       아래와 같이 5와 사칙연산만으로 12를 표현할 수 있습니다.
    
       12 = 5 + 5 + (5 / 5) + (5 / 5)
       12 = 55 / 5 + 5 / 5
       12 = (55 + 5) / 5
    
       5를 사용한 횟수는 각각 6,5,4 입니다. 그리고 이중 가장 작은 경우는 4입니다.
       이처럼 숫자 N과 number가 주어질 때, N과 사칙연산만 사용해서 표현 할 수 있는 방법 중 N 사용횟수의 최솟값을 return 하도록 solution 함수를 작성하세요.
    
     * 제한사항
       N은 1 이상 9 이하입니다.
       number는 1 이상 32,000 이하입니다.
       수식에는 괄호와 사칙연산만 가능하며 나누기 연산에서 나머지는 무시합니다.
       최솟값이 8보다 크면 -1을 return 합니다.
     * 입출력 예
       N   number  return
       5   12  4
       2   11  3
     * 입출력 예 설명
     * 예제 #1
       문제에 나온 예와 같습니다.
    
     * 예제 #2
       11 = 22 / 2와 같이 2를 3번만 사용하여 표현할 수 있습니다.
     */
    class Solution {
        int answer;
        public int solution(int N, int number) {
            //모든 최솟값이 8보다 커서 dfs가 return 되는 경우는 답이 -1
            answer = -1;
            
             DFS(N, number, 0, 0);
            
            return answer;
        }
        
        public void DFS(int N, int number, int sum, int count){
            if(count > 8) return;
            
            if(sum == number){
                if(count < answer || answer == -1) answer = count;
                return;
            }
            //새로운 DFS를 탈 때 tmpN을 초기화시켜줌으로써 N, NN, NNN 등을 탐색할 수 있다
            int tmpN = 0;
            for(int i = 1;i < 9;i++){
                tmpN = tmpN*10 + N;
                DFS(N, number, sum+tmpN, count+i);
                DFS(N, number, sum-tmpN, count+i);
                DFS(N, number, sum*tmpN, count+i);
                DFS(N, number, sum/tmpN, count+i);
            }
            
        }
    }
}
