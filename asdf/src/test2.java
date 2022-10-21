
import java.util.*;




class Solution {

    int answer = 1;
    public void dfs(int curLength, int targetLength,int[] A, int[] temp,boolean[] visit){
        if(curLength == targetLength){
            int result = temp[0];
            for(int i=1;i<temp.length;i++) {
                result = result & temp[i];
            }
            if(result > 0) answer = targetLength;
            return;
        }

        for(int i=0;i<A.length;i++){
            if(visit[i]==false){
                visit[i]=true;
                temp[curLength] = A[i];
                dfs(curLength+1, targetLength, A, temp, visit);
                temp[curLength] = 0;
                visit[i] = false;
            }
        }
    }

    public int solution(int[] A) {
        // write your code in Java SE 8

        for (int i = 1; i <= A.length; i++) {
            boolean[] visit = new boolean[A.length];
            int[] temp = new int[i];
            dfs(0, i, A, temp, visit);
            if(answer < i) break;
        }

        return answer;
    }
}
