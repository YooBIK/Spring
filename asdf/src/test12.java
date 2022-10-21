// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {
    public int solution(int N, int K) {
        int answer = -1;
        int[] dp = new int[K+1];
        for(int i=1;i<=N;i++){
            dp[i] = 1;
        }
        boolean check[] = new boolean[N+1];
        for(int i=N+1;i<=K;i++){
            int nCnt = i/N;
            int mod = i%N;
            for(int j=N;j>=N-nCnt;j--){
                check[j] = true;
            }

        }

        if(dp[K] == 0){
            answer = -1;
        }else{
            answer = dp[K];
        }
        return answer;
        // write your code in Java SE 8
    }
}
