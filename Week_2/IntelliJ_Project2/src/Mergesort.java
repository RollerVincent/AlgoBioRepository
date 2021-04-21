public class Mergesort implements Sorting{

    public void sort(int[] numbers) {
        mergesort(numbers,0,numbers.length-1);
    }

    private void mergesort(int[] numbers, int l, int r){
        if(l==r) return;
        int m = (l+r)/2;
        mergesort(numbers,l,m);
        mergesort(numbers,m+1,r);
        int j = l;
        int k = m+1;
        int tmp[] = new int[numbers.length];
        for(int i=0; i<=r-l;i++){
            if(j>m){
                tmp[i] = numbers[k];
                k++;
            }
            else {
                if(k>r){
                    tmp[i] = numbers[j];
                    j++;
                }
                else {
                    if(numbers[j]<numbers[k]){
                        tmp[i] = numbers[j];
                        j++;
                    }
                    else {
                        tmp[i] = numbers[k];
                        k++;
                    }
                }
            }
        }
        for(int i = 0; i<=r-l;i++){
            numbers[l+i]=tmp[i];
        }

    }

    public String getName() {
        return "Mergesort";
    }
}
