package main.experiment;

import java.util.Arrays;

// arr78 не должен измениться и выведено должно быть 1,2,3,4
// неверно, в ф-ю передается ссылка на массив, все изменения внутри метода изменяют массив, а вот почему не реагирует
// на переопределение внешней ссылки на внетреннюю ссылку, созданною локальной для метода (arr78) не могу ответить сразу
public class Example4 {

    public static void main(String[] args) {
        Example4 ex = new Example4();
        int[] arr78 = {1, 2, 3, 4};
        ex.one(arr78);
        System.out.println(Arrays.toString(arr78));
    }

     int[] one(int[] arr) {
        arr[0] = 56;
        int[] arr78 = {1, 2, 3, 4};
        arr = arr78;
        return arr;
    }
}