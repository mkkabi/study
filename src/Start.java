import java.util.*;
import java.util.stream.Stream;

public class Start {

    public static void main(String[] args) {
        System.out.println("Running");

    }
    
    /**
    HOMEWORK
    */
    
    public static <T extends Comparable> List<T> bubbleSortArrayList(List<T> inputList) {
        int n = inputList.size();
        for(int i =0; i<n-1; i++){
            for (int j = 0; j < n-i-1; j++) {
                if((inputList.get(j).compareTo(inputList.get(j+1))) > 0){
                    T temp = inputList.get(j);
                    inputList.set(j, inputList.get(j+1));
                    inputList.set(j+1, temp);
                }
            }
        }
        return inputList;
    }
	
	
	
	
    

    // 59 60 61  74 75 76  89 90 91  -55  128 35 67 83 94
    public int convert(int grade) {
        int result = 0;
        if (grade <= 60) {
            result = 2;
        } else if (grade < 75) {
            result = 3;
        } else if (grade < 90) {
            result = 4;
        } else if (grade > 89) {
            result = 5;
        }
        return result;
    }

    public int summa(int... slagaemoe) {
        int result = 0;
//        for (int i =0; i<slagaemoe.length; i++){
//            result+=slagaemoe[i];
//        }

        int resultFromStream = Arrays.stream(slagaemoe).sum();

        return resultFromStream;
    }




    public int multiplication(int... mnogiteli) throws IllegalArgumentException {
        if (mnogiteli.length == 0) {
            throw new IllegalArgumentException("invalid parameters length");
        }else if (mnogiteli.length == 1) {
            return mnogiteli[0];
        }

        int result[] = {1};
        Arrays.stream(mnogiteli).forEach(i -> {
            result[0] *= i;
        });
        return result[0];
    }
}
