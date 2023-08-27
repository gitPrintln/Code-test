package programmers;

public class 두수의연산값비교하기 {

    public int solution(int a, int b) {
        int answer = 0;
        int x = Integer.valueOf(""+ a + b);
        int z = 2 * a * b;
        answer = x > z ? x : z;
        return answer;
    }
}
