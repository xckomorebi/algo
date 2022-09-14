import java.util.ArrayList;
import java.util.List;

public class DFSII {
    public static void main(String[] args) {
        DFSII d = new DFSII();
        List<String> a = d.subSets("abc");
        for (String b : a) {
            System.out.print(b + ",");
        }
    }

    /**
      * All SubSets II
      * <p>
      * Given a set of characters represented by a String, return a list containing all subsets of the characters. Notice that each subset returned will be sorted to remove the sequence.
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
      * Given a set of characters represented by a String, return a list containing all subsets of the characters whose size is K.
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
}
