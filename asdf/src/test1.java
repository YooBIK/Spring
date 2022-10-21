// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {
    public int solution(int N, int K) {
        if (K<=N) {
            return 1;
        }

        int answer = 0;
        int curCap = 0;
        for(int i=N;i>=1;i--){
            if(curCap + i <= K){
                curCap += i;
                answer++;
            }
        }
        if(curCap != K){
            answer = -1;
        }

        return answer;
        // write your code in Java SE 8
    }
}
