import java.util.List;

public abstract class SequenceAlgorithm {

    public abstract List<Integer[]> AMSS(Integer[] sequence);

    public List<Integer[]> SMSS(Integer[] sequence){

        List<Integer[]> tmpResults = AMSS(sequence);

        int ind = 0;
        int shortest = Integer.MAX_VALUE;
        for (int i = 0; i < tmpResults.size(); i++) {
            Integer[] r = tmpResults.get(i);
            int l = r[1]-r[0];
            if(l < shortest){
                ind = i;
                shortest = l;
            }
        }

        return tmpResults.subList(ind,ind+1);
    }


}
