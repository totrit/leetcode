package longest_consecutive_sequence

class Solution {
    fun longestConsecutive(nums: IntArray): Int {
        val groups = hashMapOf<Int,Int>() // key: start or end value of a consecutive section
        var longest = 0
        for (i in 0..<nums.size) {
            if (groups.contains(nums[i])) {
                continue
            }
            val leftLen = groups[nums[i] - 1] ?: 0
            val rightLen = groups[nums[i] + 1] ?: 0
            val newLen = leftLen + 1 + rightLen
            groups[nums[i] - leftLen] = newLen
            groups[nums[i] + rightLen] = newLen
            groups[nums[i]] = newLen
            if (newLen > longest) {
                longest = newLen
            }
        }

        return longest
    }
}