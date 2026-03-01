package com.totrit.leetcode.kth_largest_element_in_an_array

class Solution {
    fun findKthLargest(nums: IntArray, k: Int): Int {
        // Build a k-sized MIN heap
        // In-place build-up
        // For the initial k elements, use bottom-up sift-down
        // The 2(l+1)+1-1 = index-of-last-element-of-second-last-layer
        // 2l + x = k
        // l = k/2
        val lastParentIndex = (k-2)/2
        for (i in lastParentIndex downTo 0) {
            siftDown(nums, i, k)
        }

        // For the residual elements, loop through them and check if it is larger than top of the heap. If it is replace the top with the element then sift-down to the right place
        for (i in k..<nums.size) {
            if (nums[i] > nums[0]) {
                nums[0] = nums[i]
                siftDown(nums, 0, k)
            }
        }
        return nums[0]
    }

    private fun siftDown(nums: IntArray, start: Int, end: Int) {
        var cur = start
        while(cur < end) {
            val leftChildIndex = 2*cur + 1
            val rightChildIndex = 2*cur + 2
            when {
                leftChildIndex >= end -> break
                (rightChildIndex >= end || nums[leftChildIndex] < nums[rightChildIndex]) && nums[cur] > nums[leftChildIndex] -> {
                    swap(nums, cur, leftChildIndex)
                    cur = leftChildIndex
                }
                rightChildIndex < end && nums[cur] > nums[rightChildIndex] -> {
                    swap(nums, cur, rightChildIndex)
                    cur = rightChildIndex
                }
                else -> break
            }
        }
    }

    private fun swap(nums:IntArray, pos1: Int, pos2: Int) {
        val temp = nums[pos1]
        nums[pos1] = nums[pos2]
        nums[pos2] = temp
    }
}