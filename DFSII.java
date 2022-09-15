import java.util.*;

public class DFSII {
    public static void main(String[] args) {
        DFSII d = new DFSII();
        // List<String> a = d.subSets("abc");
        // for (String b : a) {
        // System.out.print(b + ",");
        // }
        System.out.println(d.validParenthesesIII(1, 2, 1));
    }

    /**
     * All SubSets II
     * <p>
     * Given a set of characters represented by a String, return a list containing
     * all subsets of the characters. Notice that each subset returned will be
     * sorted to remove the sequence.
     */
    public List<String> subSets(String set) {
        List<String> result = new ArrayList<>();
        if (set == null) {
            return result;
        }
        StringBuilder sb = new StringBuilder();
        int[] charSet = new int[26];
        for (int i = 0; i < set.length(); i++) {
            charSet[set.charAt(i) - 'a']++;
        }
        result.add("");
        subSets(charSet, 0, result, sb);
        return result;
    }

    private void subSets(int[] charSet, int index, List<String> result, StringBuilder sb) {
        if (index == 26) {
            return;
        }

        int k = charSet[index];

        subSets(charSet, index + 1, result, sb);
        for (int i = 0; i < k; i++) {
            sb.append((char) (index + 'a'));
            result.add(sb.toString());
            subSets(charSet, index + 1, result, sb);
        }

        sb.delete(sb.length() - k, sb.length());
    }

    /**
     * All Subsets of Size K
     * <p>
     * Given a set of characters represented by a String, return a list containing
     * all subsets of the characters whose size is K.
     */
    public List<String> subSetsOfSizeK(String set, int k) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        char[] ch = set.toCharArray();
        subSetsOfSizeK(ch, 0, k, result, sb);
        return result;
    }

    private void subSetsOfSizeK(char[] ch, int index, int k, List<String> result, StringBuilder sb) {
        if (sb.length() == k) {
            result.add(sb.toString());
            return;
        }

        if (index == ch.length) {
            return;
        }

        sb.append(ch[index]);
        subSetsOfSizeK(ch, index + 1, k, result, sb);
        sb.deleteCharAt(sb.length() - 1);
        subSetsOfSizeK(ch, index + 1, k, result, sb);
    }

    /**
     * All Valid Permutations Of Parentheses II
     * <p>
     * Get all valid permutations of l pairs of (), m pairs of <> and n pairs of {}.
     */
    public List<String> validParentheses(int l, int m, int n) {
        List<String> result = new ArrayList<>();
        Deque<Integer> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        validParentheses(0, 0, l, 0, 0, m, 0, 0, n, stack, sb, result);
        return result;
    }

    private void validParentheses(int lL, int lR, int l, int mL, int mR, int m, int nL, int nR, int n,
            Deque<Integer> stack, StringBuilder sb, List<String> result) {
        if (lL == l && lR == l && mL == m && mR == m && nL == n && nR == n) {
            result.add(sb.toString());
            return;
        }

        if (!stack.isEmpty()) {
            int cur = stack.peekLast();
            stack.pollLast();
            if (cur == 0) {
                sb.append(")");
                validParentheses(lL, lR + 1, l, mL, mR, m, nL, nR, n, stack, sb, result);
                stack.offerLast(0);
            } else if (cur == 1) {
                sb.append(">");
                validParentheses(lL, lR, l, mL, mR + 1, m, nL, nR, n, stack, sb, result);
                stack.offerLast(1);
            } else {
                sb.append("}");
                validParentheses(lL, lR, l, mL, mR, m, nL, nR + 1, n, stack, sb, result);
                stack.offerLast(2);
            }
            sb.deleteCharAt(sb.length() - 1);
        }

        if (lL < l) {
            stack.offerLast(0);
            sb.append("(");
            validParentheses(lL + 1, lR, l, mL, mR, m, nL, nR, n, stack, sb, result);
            sb.deleteCharAt(sb.length() - 1);
            stack.pollLast();
        }
        if (mL < m) {
            stack.offerLast(1);
            sb.append("<");
            validParentheses(lL, lR, l, mL + 1, mR, m, nL, nR, n, stack, sb, result);
            sb.deleteCharAt(sb.length() - 1);
            stack.pollLast();
        }
        if (nL < n) {
            stack.offerLast(0);
            sb.append("{");
            validParentheses(lL, lR, l, mL, mR, m, nL + 1, nR, n, stack, sb, result);
            sb.deleteCharAt(sb.length() - 1);
            stack.pollLast();
        }
    }

    /**
     * Factor Combinations
     * <p>
     * Given an integer number, return all possible combinations of the factors that
     * can multiply to the target number.
     */
    public List<List<Integer>> combinations(int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        combinations(target, cur, result);
        return result;
    }

    private void combinations(int target, List<Integer> cur, List<List<Integer>> result) {
        if (target == 1 && cur.size() > 1) {
            result.add(new ArrayList<>(cur));
            return;
        }

        for (int i = 2; i <= target; i++) {
            if (target % i == 0) {
                if (cur.isEmpty() || cur.get(cur.size() - 1) <= i) {
                    cur.add(i);
                    combinations(target / i, cur, result);
                    cur.remove(cur.size() - 1);
                }
            }
        }
    }

    /**
     * All Permutations of Subsets
     * <p>
     * Given a string with no duplicate characters, return a list with all
     * permutations of the string and all its subsets.
     */
    public List<String> allPermutationsOfSubsets(String set) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        char[] arr = set.toCharArray();

        allPermutationsOfSubsets(arr, 0, sb, result);
        return result;
    }

    private void allPermutationsOfSubsets(char[] arr, int index, StringBuilder sb, List<String> result) {
        result.add(sb.toString());
        if (index == arr.length) {
            return;
        }

        for (int i = index; i < arr.length; i++) {
            swap(arr, index, i);
            sb.append(arr[index]);
            allPermutationsOfSubsets(arr, index + 1, sb, result);
            sb.deleteCharAt(sb.length() - 1);
            swap(arr, index, i);
        }
    }

    private void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Two Subsets With Min Difference
     * <p>
     * Given a set of n integers, divide the set in two subsets of n/2 sizes each
     * such that the difference of the sum of two subsets is as minimum as possible.
     * Return the minimum difference(absolute value).
     */
    public int minDifference(int[] array) {
        int sum = 0;
        for (int num : array) {
            sum += num;
        }
        int[] closest = new int[] { Integer.MAX_VALUE };
        minDifference(array, 0, 0, 0, sum, closest);
        return closest[0];
    }

    private void minDifference(int[] array, int index, int size, int curSum, int sum, int[] closest) {
        if (size == array.length / 2) {
            closest[0] = Math.min(closest[0], Math.abs(sum - 2 * curSum));
            return;
        }

        if (index == array.length) {
            return;
        }

        minDifference(array, index + 1, size + 1, curSum + array[index], sum, closest);
        minDifference(array, index + 1, size, curSum, sum, closest);
    }

    /**
     * All SubSets II of Size K
     * <p>
     * Given a set of characters represented by a String, return a list containing
     * all subsets of the characters whose size is K. Notice that each subset
     * returned will be sorted for deduplication.
     */
    public List<String> subSetsIIOfSizeK(String set, int k) {
        char[] arr = set.toCharArray();
        Arrays.sort(arr);
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        subSetsIIOfSizeK(arr, 0, k, sb, result);
        return result;
    }

    private void subSetsIIOfSizeK(char[] arr, int index, int k, StringBuilder sb, List<String> result) {
        if (sb.length() == k) {
            result.add(sb.toString());
            return;
        }
        if (index == arr.length) {
            return;
        }

        int nextIndex = index;
        while (nextIndex < arr.length && arr[nextIndex] == arr[index]) {
            nextIndex++;
        }

        subSetsIIOfSizeK(arr, nextIndex, k, sb, result);
        for (int i = index; i < nextIndex; i++) {
            sb.append(arr[i]);
            subSetsIIOfSizeK(arr, nextIndex, k, sb, result);
        }
        int length = sb.length();
        sb.delete(length - nextIndex + index, length);
    }

    /**
     * All Valid Permutations Of Parentheses III
     * <p>
     * Get all valid permutations of l pairs of (), m pairs of <> and n pairs of {},
     * subject to the priority restriction: {} higher than <> higher than ().
     */
    public List<String> validParenthesesIII(int l, int m, int n) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        validParenthesesIII(0, 0, l, 0, 0, m, 0, 0, n, sb, result);
        return result;
    }

    private void validParenthesesIII(int lLeft, int lRight, int l, int mLeft, int mRight, int m, int nLeft, int nRight,
            int n, StringBuilder sb, List<String> result) {
        if (lLeft == l && lRight == l && mLeft == m && mRight == m && nLeft == n && nRight == n) {
            result.add(sb.toString());
            return;
        }

        if (lLeft < l && lLeft == lRight) {
            sb.append('(');
            validParenthesesIII(lLeft + 1, lRight, l, mLeft, mRight, m, nLeft, nRight, n, sb, result);
            sb.deleteCharAt(sb.length() - 1);
        }

        if (mLeft < m && mLeft == mRight && lLeft == lRight) {
            sb.append('<');
            validParenthesesIII(lLeft, lRight, l, mLeft + 1, mRight, m, nLeft, nRight, n, sb, result);
            sb.deleteCharAt(sb.length() - 1);
        }

        if (nLeft < n && nLeft == nRight && lLeft - lRight == 0 && mLeft - mRight == 0) {
            sb.append('{');
            validParenthesesIII(lLeft, lRight, l, mLeft, mRight, m, nLeft + 1, nRight, n, sb, result);
            sb.deleteCharAt(sb.length() - 1);
        }

        if (lLeft > lRight) {
            sb.append(')');
            validParenthesesIII(lLeft, lRight + 1, l, mLeft, mRight, m, nLeft, nRight, n, sb, result);
            sb.deleteCharAt(sb.length() - 1);
        }

        if (mLeft > mRight && lLeft - lRight == 0) {
            sb.append('>');
            validParenthesesIII(lLeft, lRight, l, mLeft, mRight + 1, m, nLeft, nRight, n, sb, result);
            sb.deleteCharAt(sb.length() - 1);
        }

        if (nLeft > nRight && lLeft - lRight == 0 && mLeft - mRight == 0) {
            sb.append('}');
            validParenthesesIII(lLeft, lRight, l, mLeft, mRight, m, nLeft, nRight + 1, n, sb, result);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    /**
     * Keep Distance For Identical Elements
     * <p>
     * 
     * Given an integer k, arrange the sequence of integers [1, 1, 2, 2, 3, 3, ....,
     * k - 1, k - 1, k, k], such that the output integer array satisfy this
     * condition:
     * 
     * Between each two i's, they are exactly i integers (for example: between the
     * two 1s, there is one number, between the two 2's there are two numbers).
     */
    public int[] keepDistance(int k) {
        int[] result = new int[2 * k];
        return keepDistance(result, k) ? result : null;
    }

    private boolean keepDistance(int[] result, int n) {
        for (int i = 0; i + n + 1 < result.length; i++) {
            if (result[i] == 0 && result[i + n + 1] == 0) {
                result[i] = n;
                result[i + n + 1] = n;
                if (keepDistance(result, n - 1)) {
                    return true;
                }
                result[i] = 0;
                result[i + n + 1] = 0;
            }
        }
        return false;
    }
}
