package programmerslv3;

public class 선입선출스케줄링 {

    /*
     * 문제 설명
       처리해야 할 동일한 작업이 n 개가 있고, 이를 처리하기 위한 CPU가 있습니다.
    
       이 CPU는 다음과 같은 특징이 있습니다.
    
       CPU에는 여러 개의 코어가 있고, 코어별로 한 작업을 처리하는 시간이 다릅니다.
       한 코어에서 작업이 끝나면 작업이 없는 코어가 바로 다음 작업을 수행합니다.
       2개 이상의 코어가 남을 경우 앞의 코어부터 작업을 처리 합니다.
       처리해야 될 작업의 개수 n과, 각 코어의 처리시간이 담긴 배열 cores 가 매개변수로 주어질 때, 마지막 작업을 처리하는 코어의 번호를 return 하는 solution 함수를 완성해주세요.
    
     * 제한 사항
       코어의 수는 10,000 이하 2이상 입니다.
       코어당 작업을 처리하는 시간은 10,000이하 입니다.
       처리해야 하는 일의 개수는 50,000개를 넘기지 않습니다.
     * 입출력 예
       n   cores   result
       6   [1,2,3] 2
     * 입출력 예 설명
     * 입출력 예 #1
       처음 3개의 작업은 각각 1,2,3번에 들어가고, 1시간 뒤 1번 코어에 4번째 작업,다시 1시간 뒤 1,2번 코어에 5,6번째 작업이 들어가므로 2를 반환해주면 됩니다.
     */
    class Solution {
        public int solution(int n, int[] cores) {
           int answer = 0;

            int min = 0; // 시간 최소값
            int max = 10000 * n; // 시간 최대값
     
            int time = 0;
            int m = 0;
     
            while (true) {  
                int mid = (min + max) / 2;
                int count = calculate(mid, cores);
     
                if(min > max){
                    break;
                }
                
                if (count >= n) { // 해당시간까지 처리한 작업량보다 n이 크면 time과 m갱신
                    max = mid-1;   
                    time = mid;     
                    m = count; 
                }else{
                    min = mid+1;
                }
            }
     
            m -= n;
            
            for(int i = cores.length-1; i >= 0; i--){
                if (time % cores[i] == 0) { 
                    if (m == 0) {
                        answer = i+1;
                        break;
                    }
                    m--;
                }
            }
     
            return answer;
        }
        
          static int calculate(int time, int[] cores){
            if (time == 0) { // 시간이 0일 때, 처리할 수 있는 작업량은 코어의 개수 
                return cores.length;
            }
     
            int count = cores.length; // 시간이 0일 때, 처리한 작업량 
     
            for(int i = 0;i < cores.length;i++){ // time까지 코어가 처리할 수 있는 작업량 합산
                count += (time / cores[i]);
            }
     
            return count;
        }
    }
}
