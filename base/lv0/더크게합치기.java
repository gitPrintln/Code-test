package programmers;

public class 더크게합치기 {

    public int solution(int a, int b) {
        int answer = 0;
        String x = String.valueOf(a) + String.valueOf(b);
        String y = String.valueOf(b) + String.valueOf(a);
        answer = Integer.valueOf(x)>Integer.valueOf(y) ? Integer.valueOf(x) : Integer.valueOf(y);
        return answer;
    }
}
