package com.mission.test.string;

public class StringToInteger {

    public static int myAtoi(String s) {
        if (s == null || s.isEmpty())
            return 0;

        int result = 0;
        s = s.trim();
        boolean isNegative = false;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (i == 0 && (ch == '-' || ch == '+')) {
                isNegative = (ch == '-');
                continue;
            }

            if (!Character.isDigit(ch))
                break;

            int val = ch - '0';
            if (result > (Integer.MAX_VALUE - val) / 10) {
                return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            result = (result * 10) + val;
        }

        return isNegative ? -result : result;
    }

    public static void main(String[] args) {
        int temp = StringToInteger.myAtoi("    -042c78");
        System.out.println(temp == -42);
    }
}
