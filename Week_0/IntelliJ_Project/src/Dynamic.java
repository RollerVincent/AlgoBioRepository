import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dynamic extends SequenceAlgorithm {
    /*
            |-> 0,               für i>j
    σ(i,j)= |-> a[i],            für i=j
            |-> σ(i,k)+σ(k+1,j), für i<j und ein k∈[i:j-1]


    MSS DP (int[] a, int n)
    begin
        maxscore:=0; l:=1; r:=0;
        for (i := 1; i ≤ n; i++) do
            for (j := i; j ≤ n; j++) do
                if (i = j) then S[i, i] := a[i];
                else S[i, j] := S[i, j − 1] + a[j];
                if (S[i, j] > maxscore) then
                    maxscore := S[i, j]; l := i; r := j;
    end

     */

    @Override
    public List<Integer[]> AMSS(Integer[] sequence) {

        List<Integer[]> results = new ArrayList<>();
        int n = sequence.length;
        int maxscore = Integer.MIN_VALUE;
        int l = 0;
        int r = -1;
        int[][] s  = new int[n][n];
        for(int i=0; i<n; i++){
            for(int j=i; j<n; j++){
                if(i==j){
                    s[i][i] = sequence[i];
                }else s[i][j] = s[i][j-1]+sequence[j];
                if(s[i][j]>=maxscore){
                    l = i;
                    r = j;
                    int oldmax = maxscore;
                    maxscore = s[i][j];
                    Integer[] tmp = {l, r, maxscore};
                    if(s[i][j] == oldmax){
                        results.add(tmp);
                    }else{
                        results.clear();
                        results.add(tmp);
                    }
                }
            }
        }
        return results;
    }



}
