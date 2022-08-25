class Solution(object):
    def strstr(self, large, small):
        """
        input: string large, string small
        return: int
        """
        # write your solution here
        if len(small) > len(large):
            return -1
        l_large = len(large)
        l_small = len(small)

        hash1 = 0
        hash2 = 0

        for i in range(l_small):
            hash1 *= 26
            hash2 *= 26
            hash1 += ord(small[i]) - ord('a')
            hash2 += ord(large[i]) - ord('a')

        if hash1 == hash2:
            return 0

        for j in range(1, l_large - l_small + 1):
            hash2 -= (ord(large[j - 1]) - ord('a')) * 26 ** (l_small - 1)
            hash2 *= 26
            hash2 += ord(large[j + l_small - 1]) - ord('a')
            if hash1 == hash2:
                return j

        return -1
