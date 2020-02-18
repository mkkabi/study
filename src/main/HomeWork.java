package main;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class HomeWork {


    public static int multiplication(int... mnogiteli) throws IllegalArgumentException {
        // Если не было введено ни одного параметра, выбрасывается исключение
        if (mnogiteli.length == 0) {
            throw new IllegalArgumentException("invalid parameters length");
        } else if (mnogiteli.length == 1) {
            return mnogiteli[0];
        }

        int result[] = {1};
        Arrays.stream(mnogiteli).forEach(i -> result[0] *= i);
        return result[0];
    }

    public <T extends Comparable<? super T>> List<T> bubbleSortArrayList(List<T> inputList) {
        if (inputList.size() <= 1) {
            return inputList;
        }
        int n = inputList.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (inputList.get(j).compareTo(inputList.get(j + 1)) > 0) {
                    swap(inputList, j, j + 1);
                }
            }
        }
        return inputList;
    }

    public <T extends Comparable<? super T>> List<T> selectionSort(List<T> list) {
        if (list == null) {
            throw new IllegalArgumentException("null parameter passed into method");
        }

        if (list.size() <= 1) {
            return list;
        }

        for (int lastUnsortedIndex = list.size() - 1; lastUnsortedIndex > 0; lastUnsortedIndex--) {
            int largest = 0;
            for (int i = 1; i <= lastUnsortedIndex; i++) {
                if (list.get(i).compareTo(list.get(largest)) > 0) {
                    largest = i;
                }
            }
            swap(list, largest, lastUnsortedIndex);
        }
        return list;
    }

    public static <T extends Comparable<? super T>> void swap(List<T> list, int position1, int position2) {
        if (list.get(position1).equals(list.get(position2))) {
            return;
        }
        T temp = list.get(position2);
        list.set(position2, list.get(position1));
        list.set(position1, temp);
    }

}
