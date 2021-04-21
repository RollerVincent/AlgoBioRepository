public class Insertionsort implements Sorting{



    @Override
    public void sort(int[] numbers) {
        for(int j=1; j<numbers.length; j++){
            int key = numbers[j];
            int i = j-1;
            while(i>=0 && numbers[i]>key){
                numbers[i+1] = numbers[i];
                i--;
            }
            numbers[i+1] = key;


        }
    }

    @Override
    public String getName() {
        return "Insertionsort";
    }
}
