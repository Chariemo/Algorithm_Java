package sort;

import java.util.Random;

/**
 * Created by Charley on 2017/8/11.
 */
public class InsertSortDemo {

    public static void sort(int[] arr) {

        int index = 2, i, key;
        while (index < arr.length) {
            key = arr[index];
            i = index - 1;
            for (; i >= 1 && arr[i] > key; --i) {
                arr[i + 1] = arr[i];
            }
            arr[i + 1] = key;
            index++;
        }
    }

    public static void main(String[] args) {

        Random random = new Random();
        int[] arr = new int[7];
        for (int i = 1; i < arr.length; ++i) {
            arr[i] = random.nextInt(10) + 1;
        }
        sort(arr);
        for (int i = 1; i < arr.length; ++i) {
            System.out.print(arr[i] + " ");
        }
    }
}
