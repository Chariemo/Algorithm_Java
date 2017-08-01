package search;

/**
 * Created by Charley on 2017/8/1.
 */
public class BinarySearchDemo {

    private int[] arr;
    private int low;
    private int high;

    public BinarySearchDemo(int[] arr) {

        this.arr = arr;
    }

    // 标准二分查找 存在即可
    int bin_search(int target) {

        this.low = 0;
        this.high = arr.length - 1; //如果high = arr.length; 1.>> low < high 防止溢出 2.>> high = mid; 和循环判断保持一致
        int mid;

        while (low <= high) {   //此时不能用 '<' 边界值可能找不到
            mid = low + ((high - low) >> 1);
            if (target > arr[mid]) {
                low = mid + 1;
            } else if (target < arr[mid]) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    //查找target第一次出现的下标 否则返回-1
    int binS_first(int target) {

        this.low = 0;
        this.high = arr.length - 1;
        int mid;

        while (low < high) {
            mid = low + ((high - low) >> 1);
            if (target > arr[mid]) { //
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        if (arr[low] == target) {
            return low;
        }
        return -1;
    }

    //查找target最后一次出现的下标 否则返回-1
    int binS_last(int target) {

        this.low = 0;
        this.high = arr.length - 1;
        int mid = 0;

        while (low + 1 < high) {
            mid = low + ((high - low) >> 1);
            if (target >= arr[mid]) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }

        if (arr[high] == target) {
            return high;
        } else if (arr[low] == target) {
            return low;
        }

        return -1;
    }

    public static void main(String[] args) {

        int[] arr = {-1, 1, 2, 2, 2, 4, 4, 4, 4, 4, 4, 4};
        BinarySearchDemo binarySearch = new BinarySearchDemo(arr);
        System.out.println("search '-1': " + binarySearch.bin_search(-1));
        System.out.println("search first '2': " + binarySearch.binS_first(2));
        System.out.println("search last '4': " + binarySearch.binS_last(4));

        int[] arr1 = {-2, -2, 0, 5, 5, 7, 7};
        binarySearch = new BinarySearchDemo(arr1);
        System.out.println("search first '-2': " + binarySearch.binS_first(-2));
        System.out.println("search last '-2': " + binarySearch.binS_last(-2));
        System.out.println("search first '5': " + binarySearch.binS_first(5));
        System.out.println("search last '5': " + binarySearch.binS_last(5));
    }
}
