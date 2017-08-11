package sort;

import java.util.Random;


/**
 * Created by Charley on 2017/8/11.
 */
public class HeapSortDemo {

    public static void swap(int[] arr, int indexA, int indexB) {

        int temp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = temp;
    }

    public static void heapMaintain(int[] heap, int index, int len) {

        int indexLeft = index * 2,
                indexRight = index * 2 + 1,
                largest = index,
                temp;
        if (indexLeft < len && heap[indexLeft] > heap[index]) {
            largest = indexLeft;
        }
        if (indexRight < len && heap[indexRight] > heap[largest]) {
            largest = indexRight;
        }
        if (largest != index) {
            swap(heap, largest, index);
            heapMaintain(heap, largest, len);
        }
    }

    public static void heapBuild(int[] arr) {

        int index = (arr.length - 1) >> 1;
        for (; index >= 1; index--) {
            heapMaintain(arr, index, arr.length);
        }
    }

    public static void sort(int[] arr) {

        heapBuild(arr);
        int len = arr.length;
        for (int i = arr.length - 1; i > 1; --i) {
            swap(arr, i, 1);
            len--;
            heapMaintain(arr, 1, len);
        }
    }

    public static void main(String[] args) {

        Random random = new Random();
        int size = 100;
        int[] arr = new int[size];
        for (int i = 1 ; i < arr.length; ++i) {
            arr[i] = random.nextInt(size) + 1;
        }

        sort(arr);
        for (int i = 1; i < arr.length; ++i)
            System.out.println(arr[i]);
    }
}
