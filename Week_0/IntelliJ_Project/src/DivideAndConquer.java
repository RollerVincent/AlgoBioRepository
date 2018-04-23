import java.util.ArrayList;
import java.util.List;

public class DivideAndConquer extends SequenceAlgorithm {


    @Override
    public List<Integer[]> AMSS(Integer[] sequence) {

        return AMSS_DC(sequence,0,sequence.length-1);
    }



    List<Integer[]> AMSS_DC(Integer[] a,int i, int j){

        List<Integer[]> tmp = new ArrayList<Integer[]>();

        if(i==j){

            tmp.add(new Integer[]{i,i,a[i]});

        } else {

            int m = (j + i - 1) / 2;

            List<Integer[]> l1 = AMSS_DC(a, i, m);
            List<Integer[]> l2 = AMSS_DC(a,m + 1, j);
            List<Integer[]> l3 = Conquer(a,i, m, j);

            int s1 = l1.get(0)[2];
            int s2 = l2.get(0)[2];
            int s3 = l3.get(0)[2];

            tmp = l1;
            int max = s1;

            if (s2 >= max) {
                if (s2 > max) {
                    max = s2;
                    tmp.clear();
                }
                tmp.addAll(l2);
            }

            if (s3 >= max) {
                if (s3 > max) {
                    max = s3;
                    tmp.clear();
                }
                tmp.addAll(l3);
            }
        }
        return tmp;
    }



    List<Integer[]> Conquer(Integer[] a,int i, int m, int j){

        List<Integer> j3s = new ArrayList<>();
        List<Integer> i3s = new ArrayList<>();

        int s3r = a[m+1];
        int s=s3r;
        j3s.add(m+1);

        for (int k = m+2; k <= j; k++) {
            s += a[k];
            if(s>=s3r){
                if(s>s3r){
                    s3r=s;
                    j3s.clear();
                }
                j3s.add(k);
            }
        }

        int s3l = a[m];
        s=s3l;
        i3s.add(m);

        for (int k = m-1; k >= i ; k--) {
            s += a[k];
            if(s>=s3l){
                if(s>s3l){
                    s3l=s;
                    i3s.clear();
                }
                i3s.add(k);
            }
        }

        int s3=s3r+s3l;
        if(s3r<0){
            j3s.clear();
            j3s.add(m);
            s3-=s3r;
        }
        if(s3l<0){
            i3s.clear();
            i3s.add(m+1);
            s3-=s3l;
        }

        List<Integer[]> tmp = new ArrayList<Integer[]>();
        for(Integer i3 : i3s){
            for(Integer j3 : j3s){
                tmp.add(new Integer[]{i3,j3,s3});
            }
        }

        return tmp;
    }
}
