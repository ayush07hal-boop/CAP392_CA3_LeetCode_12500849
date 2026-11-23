class Solution {

    // User-defined exception
    class OverflowException extends Exception {
        OverflowException(String message) {
            super(message);
        }
    }

    public int myAtoi(String s) {
        try {
            return convert(s);
        }
        catch (OverflowException e) {
            if (e.getMessage().equals("positive")) {
                return Integer.MAX_VALUE;
            }

            return Integer.MIN_VALUE;
        }
        catch (NumberFormatException e) {
            return 0;
        }
        finally {
            // Finally block always executes
        }
    }

    private int convert(String s) throws OverflowException {
        int i = 0;
        int sign = 1;
        long result = 0;

        // Remove leading spaces
        while (i < s.length() && s.charAt(i) == ' ') {
            i++;
        }

        // Check sign
        if (i < s.length() &&
            (s.charAt(i) == '+' || s.charAt(i) == '-')) {

            if (s.charAt(i) == '-') {
                sign = -1;
            }

            i++;
        }

        // Convert digits
        while (i < s.length() &&
               Character.isDigit(s.charAt(i))) {

            int digit = s.charAt(i) - '0';

            result = result * 10 + digit;

            if (sign == 1 && result > Integer.MAX_VALUE) {
                throw new OverflowException("positive");
            }

            if (sign == -1 && -result < Integer.MIN_VALUE) {
                throw new OverflowException("negative");
            }

            i++;
        }

        return (int)(result * sign);
    }
}
