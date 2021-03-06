package submitted;

/**
 * https://leetcode.com/submissions/detail/208368484/
 */
public class RangeSumQueryMutable {
    class NumArray {
        SegmentTree tree;
        SegmentTreeNode root;
        public NumArray(int[] nums) {
            if (nums == null || nums.length == 0) {
                return;
            }
            tree = new SegmentTree();
            root = tree.buildTree(0, nums.length-1, nums);
        }

        public void update(int i, int val) {
            tree.updateTree(root, i, val);
        }

        public int sumRange(int i, int j) {
            return tree.querySum(root, i, j);
        }
    }

    /**
     * Your NumArray object will be instantiated and called as such:
     * NumArray obj = new NumArray(nums);
     * obj.update(i,val);
     * int param_2 = obj.sumRange(i,j);
     */
    class SegmentTree {

        SegmentTreeNode buildTree(int start, int end, int[] vals) {
            if (start == end) { // leaf node then
                return new SegmentTreeNode(start, end, vals[start], null, null);
            }
            int mid = (start + end) / 2;
            SegmentTreeNode left = buildTree(start, mid, vals);
            SegmentTreeNode right = buildTree(mid + 1, end, vals);
            return new SegmentTreeNode(start, end, left.sum + right.sum, left, right);
        }

        void updateTree(SegmentTreeNode root, int index, int val) {
            // leaf node
            if (root.start == root.end && root.start == index) {
                root.sum = val;
                return;
            }
            int mid = (root.start + root.end) / 2;
            if (index <= mid) {
                updateTree(root.left, index, val);
            } else {
                updateTree(root.right, index, val   );
            }

            root.sum = root.left.sum + root.right.sum;
        }

        // Return sum of elements in range [i, j]
        int querySum(SegmentTreeNode root, int i, int j) {
            if (root.start == i && root.end == j) {
                return root.sum;
            }
            int mid = (root.start + root.end) / 2;
            if (j <= mid) {
                return querySum(root.left, i, j);
            } else if (i > mid) {
                return querySum(root.right, i, j);
            } else {
                return querySum(root.left, i, mid) + querySum(root.right, mid+1, j);
            }
        }
    }

    class SegmentTreeNode {
        int start;
        int end;
        int sum;
        SegmentTreeNode left;
        SegmentTreeNode right;

        public SegmentTreeNode(int start, int end, int sum, SegmentTreeNode left, SegmentTreeNode right) {
            this.start = start;
            this.end = end;
            this.sum = sum;
            this.left = left;
            this.right = right;
        }
    }
}
