package dp;

import java.util.Scanner;

/**
 * Created by Charley on 2017/9/21.
 */
public class LCSDemo {

    public static int[][] lcsLength(String aStr, String bStr, char[][] pH) {

        int aLen = aStr.length();
        int bLen = bStr.length();
        int dp[][] = new int[aLen + 1][bLen + 1];

        for (int i = 0; i <= aLen; i++) {
            dp[i][0] = 0;
        }
        for (int i = 1; i <= bLen; i++) {
            dp[0][i] = 0;
        }

        for (int i = 1; i <= aLen; i++) {
            for (int j = 1; j <= bLen; j++) {
                char a = aStr.charAt(i - 1);
                char b = bStr.charAt(j - 1);
                if (a == b) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    pH[i][j] = 'q';
                } else if (dp[i - 1][j] >= dp[i][j - 1]) {
                    dp[i][j] = dp[i - 1][j];
                    pH[i][j] = 'w';
                } else {
                    dp[i][j] = dp[i][j - 1];
                    pH[i][j] = 'a';
                }
            }
        }

        return dp;
    }

    public static void lcsPrint(char[][] pH, String str, int a, int b) {

        if (a == 0 || b == 0) {
            return;
        }

        if (pH[a][b] == 'q') {
            lcsPrint(pH, str, a - 1, b - 1);
            System.out.print(str.charAt(a - 1) + " ");
        } else if (pH[a][b] == 'w') {
            lcsPrint(pH, str, a - 1, b);
        } else {
            lcsPrint(pH, str, a, b - 1);
        }
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        String a = input.nextLine();
        String b = input.nextLine();
        int[][] dp = null;
        char[][] pH = new char[a.length() + 1][b.length() + 1];
        dp = lcsLength(a, b, pH);
        System.out.println("LCS: " + dp[a.length()][b.length()]);
        lcsPrint(pH, a, a.length(), b.length());
    }
}
