from typing import List


class Solution:
    def accountsMerge(self, accounts: List[List[str]]) -> List[List[str]]:
        reverse_dict = dict()
        result = dict()
        uf = UnionFind(len(accounts))

        for index, emails in enumerate(accounts):
            for email in emails[1::]:
                if email in reverse_dict:
                    uf.union(reverse_dict[email], index)
                reverse_dict[email] = index

        for index, emails in enumerate(accounts):
            root_index = uf.find_root(index)
            if root_index not in result:
                result[root_index] = set()

            result[root_index].update(set(emails[1::]))

        return [[accounts[k][0]] + sorted(list(v)) for k, v in result.items()]


class UnionFind:
    def __init__(self, length):
        self.list = list(range(length))

    def find_root(self, n):
        while n != self.list[n]:
            n = self.list[n]
        return n

    def union(self, i, j):
        i_root = self.find_root(i)
        j_root = self.find_root(j)
        if j_root < i_root:
            j_root, i_root = i_root, j_root
        
        self.list[j_root] = i_root


if __name__ == "__main__":
    accounts = [["David", "David0@m.co", "David1@m.co"],
                ["David", "David3@m.co", "David4@m.co"],
                ["David", "David4@m.co", "David5@m.co"],
                ["David", "David2@m.co", "David3@m.co"],
                ["David", "David1@m.co", "David2@m.co"]]
    print(Solution().accountsMerge(accounts))
