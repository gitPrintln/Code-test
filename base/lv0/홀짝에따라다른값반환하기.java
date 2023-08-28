package programmers;

public class 홀짝에따라다른값반환하기 {

    public int solution(int n) {
        int answer = 0;
        if(n%2 == 1){
            for(int i=n; i>0; i-=2){
                answer += i;
            }
            return answer;
        } else{
            for(int i=n; i>0; i-=2){
                answer += i*i;
            }
        }
        return answer;
    }
}
