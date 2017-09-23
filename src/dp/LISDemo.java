package dp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Charley on 2017/9/21.
 */
public class LISDemo {


    public static int lis(String str, List<Character> result) {

        int len = str.length();
        int dp[] = new int[len];
        int lis = 0;
        for (int i = 0; i < len; i++) {
            dp[i] = 1;
        }

        for (int i = 1; i < len; i++) {
            char tmp = str.charAt(i);
            for (int j = i - 1; j >= 0; j--) {
                if (tmp >= str.charAt(j) && (dp[j] + 1) > dp[i]) {
                    dp[i] = dp[j] + 1;
                    if (result.isEmpty()) {
                        result.add(str.charAt(j));
                    }
                    if (!result.contains(tmp)) {
                        result.add(tmp);
                    }
                }
                if (lis < dp[i]) {
                    lis = dp[i];
                }
            }
        }

        return lis;
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        List<Character> result = new ArrayList<>();

        System.out.println(lis(str, result));
        result.stream().forEach(i -> System.out.println(i));
    }
}
