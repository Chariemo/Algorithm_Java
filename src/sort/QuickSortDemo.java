package sort;

import java.util.Random;

/**
 * Created by Charley on 2017/8/11.
 */
public class QuickSortDemo {

    public static void swap(int[] arr, int a, int b) {

        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void randomSelectAndSwap(int[] arr, int start, int end) {

        Random random = new Random();
        int indexRandom = start + random.nextInt(end - start + 1);
        swap(arr, indexRandom, end);
    }



    public static int randomPartition(int[] arr, int start, int end) {

        randomSelectAndSwap(arr, start, end);
        int key = arr[end];
        int s = start - 1, b = start;

        for (; b < end; ++b) {
            if (arr[b] < key) {
                swap(arr, s + 1, b);
                s++;
            }
        }
        swap(arr, s + 1, end);
        return s + 1;
    }

    public static void sort(int[] arr, int start, int end) {

        if (start < end) {
            int partition = randomPartition(arr, start, end);
            sort(arr, start, partition - 1);
            sort(arr, partition + 1, end);
        }
    }

    public static void main(String[] args) {

        Random random = new Random();
        int size = 1000;
        int[] arr = new int[size];

        for (int i = 1; i < size; ++i) {
            arr[i] = random.nextInt(size) + 1;
        }
        sort(arr, 1, size - 1);
        for (int i = 1; i < size; ++i) {
            System.out.println(arr[i]);
        }
    }
}
