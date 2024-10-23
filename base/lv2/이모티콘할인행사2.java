package programmerslv012;

public class 이모티콘할인행사2 {

    /*
     * 문제 설명
       카카오톡에서는 이모티콘을 무제한으로 사용할 수 있는 이모티콘 플러스 서비스 가입자 수를 늘리려고 합니다.
       이를 위해 카카오톡에서는 이모티콘 할인 행사를 하는데, 목표는 다음과 같습니다.
    
       이모티콘 플러스 서비스 가입자를 최대한 늘리는 것.
       이모티콘 판매액을 최대한 늘리는 것.
       1번 목표가 우선이며, 2번 목표가 그 다음입니다.
       
       이모티콘 할인 행사는 다음과 같은 방식으로 진행됩니다.
       
       n명의 카카오톡 사용자들에게 이모티콘 m개를 할인하여 판매합니다.
       이모티콘마다 할인율은 다를 수 있으며, 할인율은 10%, 20%, 30%, 40% 중 하나로 설정됩니다.
       카카오톡 사용자들은 다음과 같은 기준을 따라 이모티콘을 사거나, 이모티콘 플러스 서비스에 가입합니다.
    
       각 사용자들은 자신의 기준에 따라 일정 비율 이상 할인하는 이모티콘을 모두 구매합니다.
       각 사용자들은 자신의 기준에 따라 이모티콘 구매 비용의 합이 일정 가격 이상이 된다면, 이모티콘 구매를 모두 취소하고 이모티콘 플러스 서비스에 가입합니다.
       다음은 2명의 카카오톡 사용자와 2개의 이모티콘이 있을때의 예시입니다.
       
       사용자 비율  가격
       1   40  10,000
       2   25  10,000
       이모티콘    가격
       1   7,000
       2   9,000
       1번 사용자는 40%이상 할인하는 이모티콘을 모두 구매하고, 이모티콘 구매 비용이 10,000원 이상이 되면 이모티콘 구매를 모두 취소하고 이모티콘 플러스 서비스에 가입합니다.
       2번 사용자는 25%이상 할인하는 이모티콘을 모두 구매하고, 이모티콘 구매 비용이 10,000원 이상이 되면 이모티콘 구매를 모두 취소하고 이모티콘 플러스 서비스에 가입합니다.
    
       1번 이모티콘의 가격은 7,000원, 2번 이모티콘의 가격은 9,000원입니다.
    
       만약, 2개의 이모티콘을 모두 40%씩 할인한다면, 1번 사용자와 2번 사용자 모두 1,2번 이모티콘을 구매하게 되고, 결과는 다음과 같습니다.
    
       사용자 구매한 이모티콘    이모티콘 구매 비용  이모티콘 플러스 서비스 가입 여부
       1   1, 2    9,600   X
       2   1, 2    9,600   X
       이모티콘 플러스 서비스 가입자는 0명이 늘어나고 이모티콘 판매액은 19,200원이 늘어납니다.
    
       하지만, 1번 이모티콘을 30% 할인하고 2번 이모티콘을 40% 할인한다면 결과는 다음과 같습니다.
    
       사용자 구매한 이모티콘    이모티콘 구매 비용  이모티콘 플러스 서비스 가입 여부
       1   2   5,400   X
       2   1, 2    10,300  O
       2번 사용자는 이모티콘 구매 비용을 10,000원 이상 사용하여 이모티콘 구매를 모두 취소하고 이모티콘 플러스 서비스에 가입하게 됩니다.
       따라서, 이모티콘 플러스 서비스 가입자는 1명이 늘어나고 이모티콘 판매액은 5,400원이 늘어나게 됩니다.
    
       카카오톡 사용자 n명의 구매 기준을 담은 2차원 정수 배열 users, 이모티콘 m개의 정가를 담은 1차원 정수 배열 emoticons가 주어집니다. 이때, 행사 목적을 최대한으로 달성했을 때의 이모티콘 플러스 서비스 가입 수와 이모티콘 매출액을 1차원 정수 배열에 담아 return 하도록 solution 함수를 완성해주세요.
    
     * 제한사항
       1 ≤ users의 길이 = n ≤ 100
       users의 원소는 [비율, 가격]의 형태입니다.
       users[i]는 i+1번 고객의 구매 기준을 의미합니다.
       비율% 이상의 할인이 있는 이모티콘을 모두 구매한다는 의미입니다.
       1 ≤ 비율 ≤ 40
       가격이상의 돈을 이모티콘 구매에 사용한다면, 이모티콘 구매를 모두 취소하고 이모티콘 플러스 서비스에 가입한다는 의미입니다.
       100 ≤ 가격 ≤ 1,000,000
       가격은 100의 배수입니다.
       1 ≤ emoticons의 길이 = m ≤ 7
       emoticons[i]는 i+1번 이모티콘의 정가를 의미합니다.
       100 ≤ emoticons의 원소 ≤ 1,000,000
       emoticons의 원소는 100의 배수입니다.
     * 입출력 예
       users   emoticons   result
       [[40, 10000], [25, 10000]]  [7000, 9000]    [1, 5400]
       [[40, 2900], [23, 10000], [11, 5200], [5, 5900], [40, 3100], [27, 9200], [32, 6900]]    [1300, 1500, 1600, 4900]    [4, 13860]
     * 입출력 예 설명
     * 입출력 예 #1
    
       문제의 예시와 같습니다.
    
     * 입출력 예 #2
    
       다음과 같이 할인하는 것이 이모티콘 플러스 서비스 가입자를 최대한 늘리면서, 이모티콘 판매액 또한 최대로 늘리는 방법입니다.
    
       이모티콘    할인율
       1   40
       2   40
       3   20
       4   40
       위와 같이 할인하면 4명의 이모티콘 플러스 가입자와 13,860원의 판매액을 달성할 수 있습니다. 다른 할인율을 적용하여 이모티콘을 판매할 수 있지만 이보다 이모티콘 플러스 서비스 가입자를 최대한 늘리면서, 이모티콘 판매액 또한 최대로 늘리는 방법은 없습니다.
       따라서, [4, 13860]을 return 하면 됩니다.
     */
    class Solution {
        static int[] discountRates = {10, 20, 30, 40};
        static int maxSubscribers = 0;
        static int maxSales = 0;
        
        public static int[] solution(int[][] users, int[] emoticons) {
            // 할인율을 적용할 이모티콘의 수
            int m = emoticons.length;
            
            exploreDiscounts(0, new int[m], users, emoticons);
            
            return new int[] {maxSubscribers, maxSales};
        }
        
        // 할인율 조합을 백트래킹으로 탐색하는 함수
        private static void exploreDiscounts(int depth, int[] discounts, int[][] users, int[] emoticons) {
            if (depth == emoticons.length) {
                // 할인율 조합이 완성되었을 때 결과를 계산
                evaluate(users, emoticons, discounts);
                return;
            }
            
            // 각 이모티콘에 대해 4가지 할인율을 적용
            for (int rate : discountRates) {
                discounts[depth] = rate;
                exploreDiscounts(depth + 1, discounts, users, emoticons);
            }
        }
        
        // 현재 할인율 조합에 대해 가입자 수와 매출액 계산
        private static void evaluate(int[][] users, int[] emoticons, int[] discounts) {
            int subscribers = 0;
            int sales = 0;
            
            for (int[] user : users) {
                int userRate = user[0];
                int userPriceThreshold = user[1];
                int totalSpent = 0;
                
                // 사용자가 구매할 이모티콘 계산
                for (int i = 0;i < emoticons.length;i++) {
                    if (discounts[i] >= userRate) {
                        totalSpent += emoticons[i] * (100 - discounts[i]) / 100;
                    }
                }
                
                // 이모티콘 플러스 가입 여부 판단
                if (totalSpent >= userPriceThreshold) {
                    subscribers++;
                } else {
                    sales += totalSpent;
                }
            }
            
            // 가입자 수 우선으로 비교 후, 매출액 비교
            if (subscribers > maxSubscribers) {
                maxSubscribers = subscribers;
                maxSales = sales;
            } else if (subscribers == maxSubscribers) {
                maxSales = Math.max(maxSales, sales);
            }
        }
    }
}
