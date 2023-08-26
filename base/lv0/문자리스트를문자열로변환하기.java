package programmers;

public class 문자리스트를문자열로변환하기 {

    public String solution(String[] arr) {
        String answer = "";
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<arr.length; i++){
            sb.append(arr[i]);
        }
        answer = sb.toString();
        return answer;
    }
}
