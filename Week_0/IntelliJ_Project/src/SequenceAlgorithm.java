import java.util.List;

public abstract class SequenceAlgorithm {

    /**
     * Die Methode soll jeweils für die übergebene Sequenz, die Resultate als int[] Liste ausgeben.
     * Wenn das Flag findShortest true ist dann soll die Methode das SMSS finden ansonsten alle MSS.
     *
     * @param sequence Die analysierte Sequenz als Integer[], wird von der main Methode übergeben.
     * @return Die Liste die die int[] ethält, bei der variante findShortest==true ist dann nur ein Element in der Liste.
     *         Jedes Element der Liste ist ein int[] der Länge 3 wobei das ertse Array-Element den Anfangsindex, das
     *         zweite den Endindex und das dritte den Score darstellt.
     */

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
