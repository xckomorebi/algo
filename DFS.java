import java.util.*;

public class DFS {
    public static void main(String[] args) {
        DFS dfs = new DFS();
        String s = "123";
        System.out.println(dfs.subSets(s));
    }

    /**
     * All Subsets I
     * <p>
     * Given a set of characters represented by a String, return a list containing
     * all subsets of the characters.
     */
    public List<String> subSets(String set) {
        List<String> result = new ArrayList<>();
        if (set == null) {
            return result;
        }
        StringBuilder sb = new StringBuilder();
        char[] s = set.toCharArray();
        subSets(s, 0, sb, result);
        return result;
    }

    private void subSets(char[] s, int index, StringBuilder sb, List<String> result) {
        if (index == s.length) {
            result.add(sb.toString());
            return;
        }

        sb.append(s[index]);
        subSets(s, index + 1, sb, result);
        sb.deleteCharAt(sb.length() - 1);
        subSets(s, index + 1, sb, result);
    }

    /**
     * All Valid Permutations Of Parentheses I
     * <p>
     * Given N pairs of parentheses “()”, return a list with all the valid
     * permutations.
     */
    public List<String> validParentheses(int n) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        validParentheses(0, 0, n, sb, result);
        return result;
    }

    private void validParentheses(int l, int r, int n, StringBuilder sb, List<String> result) {
        if (l == n && r == n) {
            result.add(sb.toString());
            return;
        }

        if (l < n) {
            sb.append("(");
            validParentheses(l + 1, r, n, sb, result);
            sb.deleteCharAt(sb.length() - 1);
        }

        if (r < l) {
            sb.append(")");
            validParentheses(l, r + 1, n, sb, result);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    /**
     * Combinations Of Coins
     * <p>
     * Given a number of different denominations of coins (e.g., 1 cent, 5 cents, 10
     * cents, 25 cents), get all the possible ways to pay a target number of cents.
     */
    public List<List<Integer>> combinations(int target, int[] coins) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        combinations(target, 0, coins, cur, result);
        return result;
    }

    private void combinations(int target, int index, int[] coins, List<Integer> cur, List<List<Integer>> result) {
        if (index == coins.length - 1) {
            if (target % coins[index] == 0) {
                cur.add(target / coins[index]);
                result.add(new ArrayList<>(cur));
                cur.remove(cur.size() - 1);
            }
            return;
        }

        int value = coins[index];
        for (int i = 0; i <= target / value; i++) {
            cur.add(i);
            combinations(target - value * i, index + 1, coins, cur, result);
            cur.remove(cur.size() - 1);
        }
    }

}
