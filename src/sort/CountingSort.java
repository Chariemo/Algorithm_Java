package sort;

import java.util.*;

/**
 * Created by Charley on 2017/8/19.
 */
public class CountingSort {

    public static void sort(int[] arr, int[] arrSort) {

        int maxValue = arr[0], minValue = arr[0],
                len = arr.length;
        for (int i = 1; i < len; ++i) {
            if (arr[i] > maxValue) {
                maxValue = arr[i];
            }
            if (arr[i] < minValue) {
                minValue = arr[i];
            }
        }
        int k = maxValue - minValue + 1;
        int arrTmp[] = new int[k];

        for (int i = 0; i < k; ++i) {
            arrTmp[i] = 0;
        }
        for (int i = 1; i < len; ++i) {
            arrTmp[arr[i] - minValue] += 1;
        }
        for (int i = 1; i < k; ++i) {
            arrTmp[i] += arrTmp[i - 1];
        }

        for (int i = len - 1; i > 0; --i) {
            arrSort[arrTmp[arr[i] - minValue]] = arr[i];
            arrTmp[arr[i] - minValue] -= 1;
        }
    }

    public static void main(String[] args) {

        int len = 10000000 + 1;
        int arr[] = new int[len];
        int[] arrSort = new int[len];
        Random random = new Random();

        for (int i = 1; i < len; ++i) {
            arr[i] = random.nextInt(len) + 1;
        }

        long time = System.currentTimeMillis();
        sort(arr, arrSort);
        time = System.currentTimeMillis() - time;

        System.out.println("counting Sort time: " + time);

//        int arr1[] = arr.clone();
//        time = System.currentTimeMillis();
//        InsertSortDemo.sort(arr1);
//        time = System.currentTimeMillis() - time;
//        System.out.println("Insert Sort time: " + time);

        int[] arr2 = arr.clone();
        time = System.currentTimeMillis();
        MergeSortDemo.mergeSort(arr2, 1, len - 1);
        time = System.currentTimeMillis() - time;
        System.out.println("Merge Sort time: " + time);

        int[] arr3 = arr.clone();
        time = System.currentTimeMillis();
        HeapSortDemo.sort(arr3);
        time = System.currentTimeMillis() - time;
        System.out.println("Heap Sort time: " + time);

        int[] arr4 = arr.clone();
        time = System.currentTimeMillis();
        QuickSortDemo.sort(arr4, 1, len - 1);
        time = System.currentTimeMillis() - time;
        System.out.println("Quick Sort time: " + time);

        int arr5[] = arr.clone();
        time = System.currentTimeMillis();
        Arrays.sort(arr5);
        time = System.currentTimeMillis() - time;
        System.out.println("Arrays.Sort time: " + time);


        List<Integer> list = new ArrayList<>(len - 1);
        for (int i = 1; i < len; i++) {
            list.add(arr[i]);
        }
        System.out.println("list.length: " + list.size());
        time = System.currentTimeMillis();
        Collections.sort(list);
        time = System.currentTimeMillis() - time;
        System.out.println("Collection.Sort time: " + time);

    }
}
