package programmerslv012;

public class 동영상재생기 {

    /*
     * 문제 설명
       당신은 동영상 재생기를 만들고 있습니다. 당신의 동영상 재생기는 10초 전으로 이동, 10초 후로 이동, 오프닝 건너뛰기 3가지 기능을 지원합니다. 각 기능이 수행하는 작업은 다음과 같습니다.
    
       10초 전으로 이동: 사용자가 "prev" 명령을 입력할 경우 동영상의 재생 위치를 현재 위치에서 10초 전으로 이동합니다. 현재 위치가 10초 미만인 경우 영상의 처음 위치로 이동합니다. 영상의 처음 위치는 0분 0초입니다.
       10초 후로 이동: 사용자가 "next" 명령을 입력할 경우 동영상의 재생 위치를 현재 위치에서 10초 후로 이동합니다. 동영상의 남은 시간이 10초 미만일 경우 영상의 마지막 위치로 이동합니다. 영상의 마지막 위치는 동영상의 길이와 같습니다.
       오프닝 건너뛰기: 현재 재생 위치가 오프닝 구간(op_start ≤ 현재 재생 위치 ≤ op_end)인 경우 자동으로 오프닝이 끝나는 위치로 이동합니다.
       동영상의 길이를 나타내는 문자열 video_len, 기능이 수행되기 직전의 재생위치를 나타내는 문자열 pos, 오프닝 시작 시각을 나타내는 문자열 op_start, 오프닝이 끝나는 시각을 나타내는 문자열 op_end, 사용자의 입력을 나타내는 1차원 문자열 배열 commands가 매개변수로 주어집니다. 이때 사용자의 입력이 모두 끝난 후 동영상의 위치를 "mm:ss" 형식으로 return 하도록 solution 함수를 완성해 주세요.
    
     * 제한사항
       video_len의 길이 = pos의 길이 = op_start의 길이 = op_end의 길이 = 5
       video_len, pos, op_start, op_end는 "mm:ss" 형식으로 mm분 ss초를 나타냅니다.
       0 ≤ mm ≤ 59
       0 ≤ ss ≤ 59
       분, 초가 한 자리일 경우 0을 붙여 두 자리로 나타냅니다.
       비디오의 현재 위치 혹은 오프닝이 끝나는 시각이 동영상의 범위 밖인 경우는 주어지지 않습니다.
       오프닝이 시작하는 시각은 항상 오프닝이 끝나는 시각보다 전입니다.
       1 ≤ commands의 길이 ≤ 100
       commands의 원소는 "prev" 혹은 "next"입니다.
       "prev"는 10초 전으로 이동하는 명령입니다.
       "next"는 10초 후로 이동하는 명령입니다.
     * 입출력 예
       video_len   pos op_start    op_end  commands    result
       "34:33" "13:00" "00:55" "02:55" ["next", "prev"]    "13:00"
       "10:55" "00:05" "00:15" "06:55" ["prev", "next", "next"]    "06:55"
       "07:22" "04:05" "00:15" "04:07" ["next"]    "04:17"
     * 입출력 예 설명
     * 입출력 예 #1
    
       시작 위치 13분 0초에서 10초 후로 이동하면 13분 10초입니다.
       13분 10초에서 10초 전으로 이동하면 13분 0초입니다.
       따라서 "13:00"을 return 하면 됩니다.
     * 입출력 예 #2
    
       시작 위치 0분 5초에서 10초 전으로 이동합니다. 현재 위치가 10초 미만이기 때문에 0분 0초로 이동합니다.
       0분 0초에서 10초 후로 이동하면 0분 10초입니다.
       0분 10초에서 10초 후로 이동하면 0분 20초입니다. 0분 20초는 오프닝 구간이기 때문에 오프닝이 끝나는 위치인 6분 55초로 이동합니다. 따라서 "06:55"를 return 하면 됩니다.
     * 입출력 예 #3
    
       시작 위치 4분 5초는 오프닝 구간이기 때문에 오프닝이 끝나는 위치인 4분 7초로 이동합니다. 4분 7초에서 10초 후로 이동하면 4분 17초입니다. 따라서 "04:17"을 return 하면 됩니다.
     */
    class Solution {
        public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
            int videoLen = toSeconds(video_len);
            int curPos = toSeconds(pos);
            int opStart = toSeconds(op_start);
            int opEnd = toSeconds(op_end);

            // 오프닝 구간이라면 처음부터 체크
            if (curPos >= opStart && curPos <= opEnd) {
                curPos = opEnd;
            }

            for (String command : commands) {
                if (command.equals("prev")) {
                    curPos = Math.max(0, curPos - 10);
                } else if (command.equals("next")) {
                    curPos = Math.min(videoLen, curPos + 10);
                }

                // 각 명령 후 오프닝 체크
                if (curPos >= opStart && curPos <= opEnd) {
                    curPos = opEnd;
                }
            }

            return toTimeFormat(curPos);
        }

        // "mm:ss" → 초 단위
        private static int toSeconds(String time) {
            String[] parts = time.split(":");
            int minutes = Integer.parseInt(parts[0]);
            int seconds = Integer.parseInt(parts[1]);
            return minutes * 60 + seconds;
        }

        // 초 단위 → "mm:ss"
        private static String toTimeFormat(int totalSeconds) {
            int minutes = totalSeconds / 60;
            int seconds = totalSeconds % 60;
            return String.format("%02d:%02d", minutes, seconds);
        }
    }
}
