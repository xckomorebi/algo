import java.util.*;

public class GraphSearchAlgorithmIII {
    /**
     * Largest Product Of Length
     * <p>
     * Given a dictionary containing many words, find the largest product of two
     * words’ lengths, such that the two words do not share any common characters.
     */
    public int largestProduct(String[] dict) {
        Arrays.sort(dict, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.length() - a.length();
            }
        });

        int[] bitmap = buildBitmap(dict);
        int n = dict.length;

        PriorityQueue<Pair> pq = new PriorityQueue<>();

        pq.offer(new Pair(0, 0, dict, bitmap));
        boolean[][] visited = new boolean[n][n];
        visited[0][0] = true;

        while (!pq.isEmpty()) {
            Pair cur = pq.poll();
            if (!cur.hasCommon()) {
                return cur.prod();
            }
            int i = cur.i;
            int j = cur.j;
            if (i < n - 1 && !visited[i + 1][j]) {
                visited[i + 1][j] = true;
                pq.offer(new Pair(i + 1, j, dict, bitmap));
            }
            if (j < n - 1 && !visited[i][j + 1]) {
                visited[i][j + 1] = true;
                pq.offer(new Pair(i, j + 1, dict, bitmap));
            }

        }

        return 0;
    }

    private class Pair implements Comparable<Pair> {
        public int i;
        public int j;
        private String[] dict;
        private int[] bitmap;

        public Pair(int i, int j, String[] dict, int[] bitmap) {
            this.i = i;
            this.j = j;
            this.dict = dict;
            this.bitmap = bitmap;
        }

        @Override
        public int compareTo(Pair other) {
            return other.prod() - this.prod();
        }

        public int prod() {
            return dict[i].length() * dict[j].length();
        }

        public boolean hasCommon() {
            return (bitmap[i] & bitmap[j]) != 0;
        }
    }

    private int[] buildBitmap(String[] dict) {
        int[] bitmap = new int[dict.length];
        for (int i = 0; i < dict.length; i++) {
            String cur = dict[i];
            int bits = 0;
            for (int j = 0; j < cur.length(); j++) {
                bits |= (1 << (cur.charAt(j) - 'a'));
            }
            bitmap[i] = bits;
        }
        return bitmap;
    }

    /**
     * Kth Smallest With Only 3, 5, 7 As Factors
     * <p>
     * Find the Kth smallest number s such that s = 3 ^ x * 5 ^ y * 7 ^ z, x > 0 and
     * y > 0 and z > 0, x, y, z are all integers.
     */
    public long kth(int k) {
        if (k == 1) {
            return 105L;
        }
        PriorityQueue<Long> pq = new PriorityQueue<>();
        Set<Long> set = new HashSet<>();

        pq.offer(105L);
        while (--k > 0) {
            long cur = pq.poll();
            if (!set.contains(cur * 3)) {
                pq.offer(cur * 3);
                set.add(cur * 3);
            }
            if (!set.contains(cur * 5)) {
                pq.offer(cur * 5);
                set.add(cur * 5);
            }
            if (!set.contains(cur * 7)) {
                pq.offer(cur * 7);
                set.add(cur * 7);
            }
        }

        return pq.peek();
    }

    /**
     * Kth Closest Point To <0,0,0>
     * <p>
     * Given three arrays sorted in ascending order. Pull one number from each array
     * to form a coordinate <x,y,z> in a 3D space. Find the coordinates of the
     * points that is k-th closest to <0,0,0>.
     */
    public List<Integer> closest(int[] a, int[] b, int[] c, int k) {
        int al = a.length;
        int bl = b.length;
        int cl = c.length;
        boolean[][][] visited = new boolean[al][bl][cl];
        PriorityQueue<Coord> pq = new PriorityQueue<>();

        return null;
    }

    private class Coord implements Comparable<Coord> {
        @Override
        public int compareTo(Coord other) {
            return 0;
        }
    }
}
