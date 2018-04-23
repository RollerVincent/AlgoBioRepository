import java.util.ArrayList;
import java.util.List;

public class Naive extends SequenceAlgorithm {


    @Override
    public List<Integer[]> AMSS(Integer[] sequence) {
        List<Integer[]> results = new ArrayList<>();

        int n = sequence.length;
        int maxscore = Integer.MIN_VALUE;
        int l = 0;
        int r = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {

                int s=0;
                for (int k = i; k < j+1; k++) {
                    s+=sequence[k];
                }

                if (s >= maxscore){
                    l=i;
                    r=j;
                    if (s>maxscore){
                        maxscore=s;
                        results.clear();
                    }
                    results.add(new Integer[]{l,r,s});
                }




            }

        }


        return results;
    }
}
