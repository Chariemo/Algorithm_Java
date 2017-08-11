package sort;

import java.util.Random;

/**
 * Created by Charley on 2017/8/11.
 */
public class MergeSortDemo {

    public static void merge(int[] arr, int start, int mid, int end) {

        int lenA = mid - start + 1;
        int lenB = end - mid;
        int[] arrA = new int[lenA + 2],
                arrB = new int[lenB + 2];
        for (int i = 1; i <= lenA; ++i) {
            arrA[i] = arr[start + i - 1];
        }
        arrA[lenA+1] = Integer.MAX_VALUE;

        for (int i = 1; i <= lenB; ++i) {
            arrB[i] = arr[mid + i];
        }
        arrB[lenB+1] = Integer.MAX_VALUE;

        int indexA = 1, indexB = 1, index = start;
        while (index <= end) {
            if (arrA[indexA] <= arrB[indexB]) {
                arr[index++] = arrA[indexA++];
            }
            else {
                arr[index++] = arrB[indexB++];
            }
        }
    }

    public static void mergeSort(int[] arr, int start, int end) {

        if (start < end) {
            int mid = start + ((end - start) >> 1);
            mergeSort(arr, start, mid);
            mergeSort(arr, mid + 1, end);
            merge(arr, start, mid, end);
        }
    }

    public static void main(String[] args) {

        Random random = new Random();
        int[] arr = new int[7];
        for (int i = 1; i < arr.length; ++i) {
            arr[i] = random.nextInt(10);
        }
        mergeSort(arr, 1, arr.length - 1);

        for (int i = 1; i < arr.length; ++i) {
            System.out.println(arr[i]);
        }
    }
}
