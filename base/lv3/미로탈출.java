package programmerslv3;

public class 미로탈출 {

    /*
     * 문제 설명
       n x m 격자 미로가 주어집니다. 당신은 미로의 (x, y)에서 출발해 (r, c)로 이동해서 탈출해야 합니다.
    
       단, 미로를 탈출하는 조건이 세 가지 있습니다.
    
       격자의 바깥으로는 나갈 수 없습니다.
       (x, y)에서 (r, c)까지 이동하는 거리가 총 k여야 합니다. 이때, (x, y)와 (r, c)격자를 포함해, 같은 격자를 두 번 이상 방문해도 됩니다.
       미로에서 탈출한 경로를 문자열로 나타냈을 때, 문자열이 사전 순으로 가장 빠른 경로로 탈출해야 합니다.
       이동 경로는 다음과 같이 문자열로 바꿀 수 있습니다.
    
       l: 왼쪽으로 한 칸 이동
       r: 오른쪽으로 한 칸 이동
       u: 위쪽으로 한 칸 이동
       d: 아래쪽으로 한 칸 이동
       예를 들어, 왼쪽으로 한 칸, 위로 한 칸, 왼쪽으로 한 칸 움직였다면, 문자열 "lul"로 나타낼 수 있습니다.
    
       미로에서는 인접한 상, 하, 좌, 우 격자로 한 칸씩 이동할 수 있습니다.
    
       예를 들어 다음과 같이 3 x 4 격자가 있다고 가정해 보겠습니다.
    
       ....
       ..S.
       E...
       미로의 좌측 상단은 (1, 1)이고 우측 하단은 (3, 4)입니다. .은 빈 공간, S는 출발 지점, E는 탈출 지점입니다.
    
      탈출까지 이동해야 하는 거리 k가 5라면 다음과 같은 경로로 탈출할 수 있습니다.
       
       lldud
       ulldd
       rdlll
       dllrl
       dllud
       ...
       이때 dllrl보다 사전 순으로 빠른 경로로 탈출할 수는 없습니다.
    
       격자의 크기를 뜻하는 정수 n, m, 출발 위치를 뜻하는 정수 x, y, 탈출 지점을 뜻하는 정수 r, c, 탈출까지 이동해야 하는 거리를 뜻하는 정수 k가 매개변수로 주어집니다. 이때, 미로를 탈출하기 위한 경로를 return 하도록 solution 함수를 완성해주세요. 단, 위 조건대로 미로를 탈출할 수 없는 경우 "impossible"을 return 해야 합니다.
    
     * 제한사항
       2 ≤ n (= 미로의 세로 길이) ≤ 50
       2 ≤ m (= 미로의 가로 길이) ≤ 50
       1 ≤ x ≤ n
       1 ≤ y ≤ m
       1 ≤ r ≤ n
       1 ≤ c ≤ m
       (x, y) ≠ (r, c)
       1 ≤ k ≤ 2,500
     * 입출력 예
       n   m   x   y   r   c   k   result
       3   4   2   3   3   1   5   "dllrl"
       2   2   1   1   2   2   2   "dr"
       3   3   1   2   3   3   4   "impossible"
     * 입출력 예 설명
     * 입출력 예 #1
    
       문제 예시와 동일합니다.
    
     * 입출력 예 #2
    
       미로의 크기는 2 x 2입니다. 출발 지점은 (1, 1)이고, 탈출 지점은 (2, 2)입니다.
    
       빈 공간은 ., 출발 지점을 S, 탈출 지점을 E로 나타내면 다음과 같습니다.
    
       S.
       .E
       미로의 좌측 상단은 (1, 1)이고 우측 하단은 (2, 2)입니다.
    
       탈출까지 이동해야 하는 거리 k가 2이므로 다음과 같은 경로로 탈출할 수 있습니다.
    
       rd
       dr
       "dr"이 사전 순으로 가장 빠른 경로입니다. 따라서 "dr"을 return 해야 합니다.
    
     * 입출력 예 #3
    
       미로의 크기는 3 x 3입니다. 출발 지점은 (1, 2)이고, 탈출 지점은 (3, 3)입니다.
    
       빈 공간은 ., 출발 지점을 S, 탈출 지점을 E로 나타내면 다음과 같습니다.
    
       .S.
       ...
       ..E
       미로의 좌측 상단은 (1, 1)이고 우측 하단은 (3, 3)입니다.
    
       탈출까지 이동해야 하는 거리 k가 4입니다. 이때, 이동 거리가 4이면서, S에서 E까지 이동할 수 있는 경로는 존재하지 않습니다.
    
       따라서 "impossible"을 return 해야 합니다.
     */
    class Solution {
        int[][] array;
        String answer = null;
        StringBuilder route;
        char[] dir = {'d', 'l', 'r', 'u'};
        int[] rdir = {1, 0, 0, -1};
        int[] cdir = {0, -1, 1, 0};
        int endRow, endCol;
        int arrRow, arrCol; // 미로 길이
        
        public String solution(int n, int m, int x, int y, int r, int c, int k) {
            route = new StringBuilder();
            array = new int[n][m];
            endRow = r; endCol = c;
            arrRow = n; arrCol = m;
            // 최단거리 계산 - 거리 k로 갈 수 있는지 여부
            int length = distance(x, y, r, c);
            if((k - length) % 2 == 1 || k < length) return "impossible";
            dfs(x, y, 0, k);
            
            return answer == null ? "impossible" : answer;
        }

        private int distance(int x, int y, int r, int c){
            return (int) Math.abs(x-r) + (int) Math.abs(y-c);
        }

        private void dfs(int r, int c, int depth, int k){
            if(answer != null) return;
            if(depth + distance(r, c, endRow, endCol) > k) return; // 현재 깊이 + 남은 거리 > k
            if(k == depth) {
                answer = route.toString();
                return;
            }
            for(int i = 0;i < 4;i++){
                
                int nextRow = r + rdir[i];
                int nextCol = c + cdir[i];
                if(nextRow <= arrRow && nextCol <= arrCol && nextRow > 0 && nextCol >0){
                    route.append(dir[i]);
                    dfs(nextRow, nextCol, depth+1, k);
                    route.delete(depth, depth+1);
                }
                
            }
        }
    }
}
