public class StringII {
    public static void main(String[] args) {
        StringII s = new StringII();
        System.out.println(s.rightShift("abcdefg", 39));
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
}
