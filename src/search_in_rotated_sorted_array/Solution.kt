package search_in_rotated_sorted_array

class Solution {
    fun search(nums: IntArray, target: Int): Int {
        var left = 0
        var right = nums.size - 1
        val isRotated = nums[right] < nums[left]
        while (left < nums.size && right >= 0) {
            val midIndex = (left + right) / 2
            val midValue = nums[midIndex]
            when {
                midValue == target -> return midIndex
                left >= right -> break
                isRotated && target >= nums[0] -> {
                    when {
                        target > midValue && midValue >= nums[0] -> left = midIndex + 1
                        else -> right = midIndex - 1
                    }
                }
                isRotated -> {
                    when {
                        midValue >= nums[0] -> left = midIndex + 1
                        target > midValue -> left = midIndex + 1
                        else -> right = midIndex - 1
                    }
                }
                !isRotated && midValue < target -> left = midIndex + 1
                else -> {
                    right = midIndex - 1
                }
            }
        }
        return -1
    }
}