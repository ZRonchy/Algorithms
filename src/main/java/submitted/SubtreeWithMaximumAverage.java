package submitted;

import leetcode.TreeTrie.TreeNode;

/**
 * Given a binary tree, find the subtree with maximum average. Return the root of the subtree.
 */
public class SubtreeWithMaximumAverage {
    private class ResultType {
        public int sum, size;
        public ResultType(int sum, int size) {
            this.sum = sum;
            this.size = size;
        }
    }

    private TreeNode subtree = null;
    private ResultType subtreeResult = null;

    public TreeNode findSubtree(TreeNode root) {
        helper(root);
        return subtree;
    }

    private ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(0, 0);
        }

        ResultType left = helper(root.left);
        ResultType right = helper(root.right);

        ResultType result = new ResultType(
                left.sum + right.sum + root.val,
                left.size + right.size + 1
        );

        if (subtree == null || subtreeResult.sum /subtreeResult.size < result.sum / result.size) {
            subtree = root;
            subtreeResult = result;
        }
        return result;
    }
}
