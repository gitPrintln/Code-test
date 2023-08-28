package programmers;

public class 조건문자열 {
    
    public int solution(String ineq, String eq, int n, int m) {
        int answer = 0;
        if(eq.equals("!")){
            if(ineq.equals(">")){
                return answer = (n>m ? 1 : 0);
            } else {
                return answer = (n<m ? 1 : 0);
            }
        } else {
            if(ineq.equals(">")){
                return answer = (n>=m ? 1 : 0);
            } else {
                return answer = (n<=m ? 1 : 0);
            }
        }
    }
}
