package utils;

public class TreeNodeP {
    public int key;
    public TreeNodeP left;
    public TreeNodeP right;
    public TreeNodeP parent;

    public TreeNodeP(int key, TreeNode parent) {
        this.key = key;
        this.parent = parent;
    }
}
