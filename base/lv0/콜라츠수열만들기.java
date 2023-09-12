package programmers;
import java.util.stream.Stream;

public class 콜라츠수열만들기 {
    class Solution {
        public int[] solution(int n) {
            return Stream.iterate(n, i -> i>=1, i -> i == 1 ? 0 : i%2 == 0 ? i/2 : 3*i+1)
                .mapToInt(Integer::intValue).toArray();
        }
    }
}
