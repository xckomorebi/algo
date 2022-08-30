import java.util.*;

public class StringII {
    public static void main(String[] args) {
        StringII s = new StringII();
        // System.out.println(s.rightShift("abcdefg", 39));
        System.out.println(s.replace("123", "12", "4"));
    }

    /**
     * Reverse String
     * <p>
     * Reverse a given string
     */
    public String reverse(String input) {
        char[] ch = input.toCharArray();
        reverse(ch, 0, ch.length - 1);
        return new String(ch);
    }

    private void reverse(char[] ch, int a, int b) {
        while (a < b) {
            swap(ch, a++, b--);
        }
    }

    private void swap(char[] ch, int a, int b) {
        char temp = ch[a];
        ch[a] = ch[b];
        ch[b] = temp;
    }

    /**
     * Reverse Words In A Sentence I
     * <p>
     * Reverse the words in a sentence
     */
    public String reverseWords(String input) {
        if (input == null) {
            return null;
        }
        char[] ch = input.toCharArray();
        reverse(ch, 0, ch.length - 1);

        int i = 0;
        int j = 0;
        while (i < ch.length) {
            while (j < ch.length && ch[j] != ' ') {
                j++;
            }
            reverse(ch, i, j - 1);
            i = j + 1;
            j = i;
        }

        return new String(ch);
    }

    /**
     * Right Shift By N Characters
     * <p>
     * Right shift a given string by n characters.
     */
    public String rightShift(String input, int n) {
        if (n == 0 || input.isEmpty()) {
            return input;
        }
        n %= input.length();
        char[] ch = input.toCharArray();
        return new String(ch, n, ch.length - n) + new String(ch, 0, n);
    }

    /**
     * String Replace (basic)
     * <p>
     * Given an original string input, and two strings S and T, from left to right
     * replace all occurrences of S in input with T.
     */
    public String replace(String input, String source, String target) {
        char[] array = input.toCharArray();
        if (source.length() > input.length()) {
            return input;
        }
        if (target.length() > source.length()) {
            return replaceLonger(array, source, target);
        } else {
            return replaceShorter(array, source, target);
        }
    }

    private String replaceShorter(char[] array, String source, String target) {
        int i = 0;
        int j = 0;
        while (i < array.length) {
            if (isSubString(array, i, source)) {
                i += source.length();
                for (int k = 0; k < target.length(); k++) {
                    array[j++] = target.charAt(k);
                }
            } else {
                array[j++] = array[i++];
            }
        }
        return new String(array, 0, j);
    }

    private String replaceLonger(char[] array, String source, String target) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < array.length) {
            if (isSubString(array, i, source)) {
                i += source.length();
                sb.append(target);
            } else {
                sb.append(array[i++]);
            }
        }
        return sb.toString();
    }

    /**
     * All Permutations II
     * <p>
     * Given a string with possible duplicate characters, return a list with all
     * permutations of the characters.
     */
    public List<String> permutations(String input) {
        List<String> result = new ArrayList<>();
    }


    private boolean isSubString(char[] array, int start, String source) {
        if (start + source.length() > array.length) {
            return false;
        }

        for (int i = 0; i < source.length(); i++) {
            if (array[i + start] != source.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * ReOrder Array
     * <p>
     * Given an array of elements, reorder it as follow: * { N1, N2, N3, …, N2k } ->
     * { N1, Nk+1, N2, Nk+2, N3, Nk+3, … , Nk, N2k } * { N1, N2, N3, …, N2k+1 } -> {
     * N1, Nk+1, N2, Nk+2, N3, Nk+3, … , Nk, N2k, N2k+1 } Try to do it in place
     */
    public int[] reorder(int[] array) {
        int[] result = new int[array.length];
        for (int i = 0; i < array.length / 2; i++) {
            result[2 * i] = array[i];
            result[2 * i + 1] = array[i + array.length / 2];
        }
        if (array.length % 2 == 1) {
            result[array.length - 1] = array[array.length - 1];
        }
        return result;
    }

    /**
     * Compress String II
     * <p>
     * Given a string, replace adjacent, repeated characters with the character
     * followed by the number of repeated occurrences.
     */
    public String compress(String input) {
        char prev = (char) 0;
        StringBuilder sb = new StringBuilder();
        int cur = 0;
        for (char c : input.toCharArray()) {
            if (cur == 0) {
                prev = c;
                cur = 1;
                continue;
            }
            if (prev == c) {
                cur++;
            } else {
                sb.append(prev);
                sb.append(cur);
                cur = 1;
                prev = c;
            }
        }
        if (cur != 0) {
            sb.append(prev);
            sb.append(cur);
        }
        return sb.toString();
    }

    /**
     * Decompress String II
     * <p>
     * Given a string in compressed form, decompress it to the original string. The
     * adjacent repeated characters in the original string are compressed to have
     * the character followed by the number of repeated occurrences.
     */
    public String decompress(String input) {
        char[] array = input.toCharArray();
        int l = 0;
        StringBuilder sb = new StringBuilder();
        char prev = (char) 0;

        for (char c : array) {
            if (c - '0' < 10 && c - '0' >= 0) {
                l *= 10;
                l += c - '0';
            } else {
                while (l-- > 0) {
                    sb.append(prev);
                }
                l = 0;
                prev = c;
            }
        }
        while (l-- > 0) {
            sb.append(prev);
        }
        return sb.toString();
    }

    /**
     * Longest Substring Without Repeating Characters
     * <p>
     * Given a string, find the longest substring without any repeating characters
     * and return the length of it. The input string is guaranteed to be not null.
     * For example, the longest substring without repeating letters for "bcdfbd" is
     * "bcdf", we should return 4 in this case.
     */
    public int longest(String input) {
        int max = 0;
        boolean[] set = new boolean[26];
        int i = 0;
        int j = 0;
        while (j < input.length()) {
            if (!set[input.charAt(j) - 'a']) {
                set[input.charAt(j++) - 'a'] = true;
                max = Math.max(max, j - i);
            } else {
                while (input.charAt(i) != input.charAt(j)) {
                    set[input.charAt(i++) - 'a'] = false;
                }
                set[input.charAt(i++) - 'a'] = false;
                set[input.charAt(j++) - 'a'] = true;
            }
        }
        return max;
    }

}
