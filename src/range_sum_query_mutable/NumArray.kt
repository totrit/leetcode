package range_sum_query_mutable

class NumArray(val nums: IntArray) {
    private val tree = IntArray(nums.size + 1)

    init {
        for (i in nums.indices) {
            tree[i+1] = nums[i]
        }
        for (i in 1..nums.size) {
            val parent = i + (i and -i)
            if (parent < tree.size) {
                tree[parent] += tree[i]
            }
        }
    }

    // Rebuild the tree parts that includes the numIndex
    private fun alter(numIndex: Int, delta: Int) {
        var treeCur = numIndex + 1
        while (treeCur <= nums.size) {
            tree[treeCur] += delta
            treeCur += (treeCur and -treeCur)
        }
    }

    // Get pre-sum given a tree index (1=based)
    private fun query(treeIndex: Int): Int {
        var ans = 0
        var treeCur = treeIndex
        while (treeCur > 0) {
            ans += tree[treeCur]
            treeCur -= (treeCur and -treeCur)
        }
        return ans
    }

    fun update(index: Int, `val`: Int) {
        val diff = `val` - nums[index]
        nums[index] = `val`
        alter(index, diff)
    }

    fun sumRange(left: Int, right: Int): Int {
        return query(right + 1) - query(left)
    }
}
/**
 * Your NumArray object will be instantiated and called as such:
 * var obj = NumArray(nums)
 * obj.update(index,`val`)
 * var param_2 = obj.sumRange(left,right)
 */
/**
 * Your NumArray object will be instantiated and called as such:
 * var obj = NumArray(nums)
 * obj.update(index,`val`)
 * var param_2 = obj.sumRange(left,right)
 */