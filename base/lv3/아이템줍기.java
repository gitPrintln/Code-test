package programmerslv3;

import java.util.LinkedList;
import java.util.Queue;

public class 아이템줍기 {

    /*
     * BFS로 풀이
     * 문제 설명
       다음과 같은 다각형 모양 지형에서 캐릭터가 아이템을 줍기 위해 이동하려 합니다.
    
       rect_1.png
    
       지형은 각 변이 x축, y축과 평행한 직사각형이 겹쳐진 형태로 표현하며, 캐릭터는 이 다각형의 둘레(굵은 선)를 따라서 이동합니다.
       
       만약 직사각형을 겹친 후 다음과 같이 중앙에 빈 공간이 생기는 경우, 다각형의 가장 바깥쪽 테두리가 캐릭터의 이동 경로가 됩니다.
    
       rect_2.png
       
       단, 서로 다른 두 직사각형의 x축 좌표 또는 y축 좌표가 같은 경우는 없습니다.
    
       rect_4.png
    
       즉, 위 그림처럼 서로 다른 두 직사각형이 꼭짓점에서 만나거나, 변이 겹치는 경우 등은 없습니다.
    
       다음 그림과 같이 지형이 2개 이상으로 분리된 경우도 없습니다.
    
       rect_3.png
    
       한 직사각형이 다른 직사각형 안에 완전히 포함되는 경우 또한 없습니다.
    
       rect_7.png
    
       지형을 나타내는 직사각형이 담긴 2차원 배열 rectangle, 초기 캐릭터의 위치 characterX, characterY, 아이템의 위치 itemX, itemY가 solution 함수의 매개변수로 주어질 때, 캐릭터가 아이템을 줍기 위해 이동해야 하는 가장 짧은 거리를 return 하도록 solution 함수를 완성해주세요.
    
     * 제한사항
       rectangle의 세로(행) 길이는 1 이상 4 이하입니다.
       rectangle의 원소는 각 직사각형의 [좌측 하단 x, 좌측 하단 y, 우측 상단 x, 우측 상단 y] 좌표 형태입니다.
       직사각형을 나타내는 모든 좌표값은 1 이상 50 이하인 자연수입니다.
       서로 다른 두 직사각형의 x축 좌표, 혹은 y축 좌표가 같은 경우는 없습니다.
       문제에 주어진 조건에 맞는 직사각형만 입력으로 주어집니다.
       charcterX, charcterY는 1 이상 50 이하인 자연수입니다.
       지형을 나타내는 다각형 테두리 위의 한 점이 주어집니다.
       itemX, itemY는 1 이상 50 이하인 자연수입니다.
       지형을 나타내는 다각형 테두리 위의 한 점이 주어집니다.
       캐릭터와 아이템의 처음 위치가 같은 경우는 없습니다.
       전체 배점의 50%는 직사각형이 1개인 경우입니다.
       전체 배점의 25%는 직사각형이 2개인 경우입니다.
       전체 배점의 25%는 직사각형이 3개 또는 4개인 경우입니다.
     * 입출력 예
       rectangle   characterX  characterY  itemX   itemY   result
       [[1,1,7,4],[3,2,5,5],[4,3,6,9],[2,6,8,8]]   1   3   7   8   17
       [[1,1,8,4],[2,2,4,9],[3,6,9,8],[6,3,7,7]]   9   7   6   1   11
       [[1,1,5,7]] 1   1   4   7   9
       [[2,1,7,5],[6,4,10,10]] 3   1   7   10  15
       [[2,2,5,5],[1,3,6,4],[3,1,4,6]] 1   4   6   3   10
     * 입출력 예 설명
     * 입출력 예 #1
    
       rect_5.png
    
       캐릭터 위치는 (1, 3)이며, 아이템 위치는 (7, 8)입니다. 위 그림과 같이 굵은 선을 따라 이동하는 경로가 가장 짧습니다.
       
     * 입출력 예 #2
    
       rect_6.png
    
       캐릭터 위치는 (9, 7)이며, 아이템 위치는 (6, 1)입니다. 위 그림과 같이 굵은 선을 따라 이동하는 경로가 가장 짧습니다.
       
     * 입출력 예 #3
    
       rect_8.png
    
       캐릭터 위치는 (1, 1)이며, 아이템 위치는 (4, 7)입니다. 위 그림과 같이 굵은 선을 따라 이동하는 경로가 가장 짧습니다.
    
     * 입출력 예 #4, #5
    
       설명 생략
     */
    class Solution {
        private static final int[] dx = {1, -1, 0, 0}; // 상, 하, 좌, 우 이동
        private static final int[] dy = {0, 0, 1, -1};
        private static final int MAP_SIZE = 101; // 좌표를 2배로 확대하여 처리

        // 지도 정보 및 방문 여부
        private boolean[][] map = new boolean[MAP_SIZE][MAP_SIZE];
        private boolean[][] visited = new boolean[MAP_SIZE][MAP_SIZE];

        public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
            // 직사각형 테두리 설정
            drawRectangles(rectangle);

            // 캐릭터와 아이템의 위치를 2배로 늘림 (정확한 테두리 계산을 위함)
            characterX *= 2;
            characterY *= 2;
            itemX *= 2;
            itemY *= 2;

            // BFS로 최단 거리 탐색
            return bfs(characterX, characterY, itemX, itemY) / 2; // 결과를 2로 나눠 원래 거리로 복귀
        }

        private void drawRectangles(int[][] rectangles) {
            for (int[] rect : rectangles) {
                int x1 = rect[0] * 2;
                int y1 = rect[1] * 2;
                int x2 = rect[2] * 2;
                int y2 = rect[3] * 2;

                // 테두리 설정
                for (int i = x1;i <= x2;i++) {
                    map[i][y1] = true; // 아래쪽
                    map[i][y2] = true; // 위쪽
                }
                for (int i = y1;i <= y2;i++) {
                    map[x1][i] = true; // 왼쪽
                    map[x2][i] = true; // 오른쪽
                }
            }

            // 직사각형 내부는 방문할 수 없도록 처리
            for (int[] rect : rectangles) {
                int x1 = rect[0] * 2;
                int y1 = rect[1] * 2;
                int x2 = rect[2] * 2;
                int y2 = rect[3] * 2;

                for (int i = x1 + 1;i < x2;i++) {
                    for (int j = y1 + 1;j < y2;j++) {
                        map[i][j] = false; // 내부는 제외
                    }
                }
            }
        }

        private int bfs(int startX, int startY, int targetX, int targetY) {
            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[]{startX, startY, 0});
            visited[startX][startY] = true;

            while (!queue.isEmpty()) {
                int[] current = queue.poll();
                int x = current[0];
                int y = current[1];
                int distance = current[2];

                // 목표에 도달하면 거리 반환
                if (x == targetX && y == targetY) {
                    return distance;
                }

                // 상, 하, 좌, 우 탐색
                for (int i = 0;i < 4;i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if (nx >= 0 && ny >= 0 && nx < MAP_SIZE && ny < MAP_SIZE) {
                        if (!visited[nx][ny] && map[nx][ny]) {
                            visited[nx][ny] = true;
                            queue.add(new int[]{nx, ny, distance + 1});
                        }
                    }
                }
            }

            return -1; // 목표에 도달할 수 없는 경우
        }
    }
}
