import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Linear implements SequenceAlgorithm {

    /*
    begin
        intmaxscore:=0; l:=1; r:=0;
        int rmaxscore := 0; rstart := 1;
        for (i := 1; i ≤ n; i++) do
            if (rmaxscore + a[i] > a[i]) then
                rmaxscore := rmaxscore + a[i];
            else
                rmaxscore := a[i]; rstart := i;
            if (rmaxscore > maxscore) then
                maxscore := rmaxscore; l := rstart; r := i;
    end



     */

    @Override
    public List<Integer[]> MSS(Integer[] sequence, boolean findShortest) {

        List<Integer[]> results = new ArrayList<>();

        int maxscore=Integer.MIN_VALUE;
        int l=0;
        int r=-1;
        int rmaxscore = 0;
        int rstart=0;
        int n = sequence.length;

        for(int i=0; i<n; i++){
            if(rmaxscore + sequence[i] > sequence[i]) rmaxscore+= sequence[i];
            else{
                rmaxscore = sequence[i];
                rstart = i;
            }
            if(rmaxscore>=maxscore){
                l = rstart;
                r=i;
                Integer[] tmp = {l,r,rmaxscore};

                if(rmaxscore == maxscore){
                    results.add(tmp);

                }else{
                    results.clear();
                    results.add(tmp);
                }
                int tmpl = l;
                while(tmpl!=0 && sequence[tmpl-1]==0){
                    tmpl--;
                    Integer[] tmp2 = {tmpl,r,rmaxscore};
                    results.add(tmp2);
                }
                maxscore = rmaxscore;
            }
        }

        if(findShortest){
            Dynamic d = new Dynamic();
            results = d.findShortestSequence(results);
            results = results.subList(0,1);
        }

        return results;
    }


}
