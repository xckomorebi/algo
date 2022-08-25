import java.util.*;

public class HashTable {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable();
        String s1 = "abcdefghijklmnopqrstuvwxyzzabcdefghijklmnopqrstu";
        String s2 = "qrstuvwxyzzabcdefghijklmnopqrstu";
        System.out.println(hashTable.strstr(s1, s2));
        
    }
    /**
     * Top K Frequent Words
     * <p>
     * Given a composition with different kinds of words, return a list of the top K
     * most frequent words in the composition.
     */
    public String[] topKFrequent(String[] combo, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String s : combo) {
            if (!map.containsKey(s)) {
                map.put(s, 1);
            } else {
                map.put(s, map.get(s) + 1);
            }
        }
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
                new Comparator<Map.Entry<String, Integer>>() {
                    @Override
                    public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
                        return a.getValue() - b.getValue();
                    }
                });

        int i = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (i++ < k) {
                pq.offer(entry);
            } else {
                if (pq.peek().getValue() < entry.getValue()) {
                    pq.poll();
                    pq.offer(entry);
                }
            }
        }

        int size = pq.size();
        String[] result = new String[size];
        while (size-- > 0) {
            result[size] = pq.poll().getKey();
        }
        return result;
    }

    /**
     * Missing Number I
     * <p>
     * Given an integer array of size N - 1, containing all the numbers from 1 to N
     * except one, find the missing number.
     */
    public int missing(int[] array) {
        long sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += i + 1;
            sum -= array[i];
        }
        sum += array.length + 1;
        return (int) sum;
    }

    public int missing2(int[] array) {
        int n = 0;
        for (int i = 0; i < array.length; i++) {
            n ^= i + 1;
            n ^= array[i];
        }
        return n ^ (array.length + 1);
    }

    /**
     * Common Number of Two Sorted Array(Array version)
     * <p>
     * Find all numbers that appear in both of two sorted arrays (the two arrays are
     * all sorted in ascending order).
     */
    public List<Integer> common(int[] A, int[] B) {
        List<Integer> result = new ArrayList<>();
        int a = 0;
        int b = 0;

        while (a < A.length && b < B.length) {
            if (A[a] == B[b]) {
                result.add(A[a++]);
                b++;
            } else if (A[a] < B[b]) {
                a++;
            } else {
                b++;
            }
        }

        return result;
    }

    /**
     * Remove Certain Characters
     * <p>
     * Remove given characters in input string, the relative order of other
     * characters should be remained. Return the new string after deletion.
     */
    public String remove(String input, String t) {
        boolean[] tList = new boolean[26];
        for (int i = 0; i < t.length(); i++) {
            tList[t.charAt(i) - 'a'] = true;
        }
        char[] ch = input.toCharArray();

        int i = 0;
        for (int j = 0; j < input.length(); j++) {
            if (!tList[ch[j] - 'a']) {
                ch[i++] = ch[j];
            }
        }

        return new String(ch, 0, i);
    }

    /**
     * Remove Spaces
     * <p>
     * Given a string, remove all leading/trailing/duplicated empty spaces.
     */
    public String removeSpaces(String input) {
        char[] ch = input.toCharArray();
        int i = 0;
        for (int j = 0; j < ch.length; j++) {
            if (ch[j] != ' ') {
                ch[i++] = ch[j];
            } else if (i == 0 || ch[i - 1] != ' ') {
                ch[i++] = ch[j];
            }
        }
        if (i == 0) {
            return "";
        }
        if (i == 1) {
            if (ch[0] == ' ') {
                return "";
            }
        }
        int offset = ch[0] == ' ' ? 1 : 0;
        int end = ch[i - 1] == ' ' ? i - 1 : i;
        return new String(ch, offset, end - offset);
    }

    /**
     * Remove Adjacent Repeated Character
     * <p>
     * Remove adjacent, repeated characters in a given string, leaving only one
     * character for each group of such characters.
     */
    public String deDup(String input) {
        char[] ch = input.toCharArray();
        int i = 0;
        for (int j = 0; j < ch.length; j++) {
            if (i == 0 || ch[j] != ch[i - 1]) {
                ch[i++] = ch[j];
            }
        }
        return new String(ch, 0, i);
    }

    /**
     * Remove Adjacent Repeated Characters IV
     * <p>
     * Repeatedly remove all adjacent, repeated characters in a given string from
     * left to right. No adjacent characters should be identified in the final
     * string.
     */
    public String deDup2(String input) {
        char[] ch = input.toCharArray();
        int i = 0;
        int j = 0;
        while (j < ch.length) {
            if (i == 0) {
                ch[i++] = ch[j++];
            } else if (ch[j] == ch[i - 1]) {
                while (j < ch.length && ch[j] == ch[i - 1]) {
                    j++;
                }
                i--;
            } else {
                ch[i++] = ch[j++];
            }
        }
        return new String(ch, 0, i);
    }

    /**
     * Determine If One String Is Another's Substring
     * <p>
     * Determine if a small string is a substring of another large string.
     * Return the index of the first occurrence of the small string in the large
     * string.
     * Return -1 if the small string is not a substring of the large string.
     */
    public int strstr(String large, String small) {
        if (small.length() > large.length()) {
            return -1;
        }

        int LLarge = large.length();
        int LSmall = small.length();

        long hash = 0; // overflow, see ./strstr.py
        long hash2 = 0;
        for (int i = 0; i < LSmall; i++) {
            hash *= 26;
            hash2 *= 26;
            hash += small.charAt(i) - 'a';
            hash2 += large.charAt(i) - 'a';
        }

        if (hash == hash2) {
            return 0;
        }

        for (int j = 1; j <= LLarge - LSmall; j++) {
            hash2 -= (large.charAt(j - 1) - 'a') * (int) Math.pow(26, LSmall - 1);
            hash2 *= 26;
            hash2 += (large.charAt(j + LSmall - 1) - 'a');
            if (hash == hash2) {
                return j;
            }
        }

        return -1;
    }
}
