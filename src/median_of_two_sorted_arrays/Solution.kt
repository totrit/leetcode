package com.totrit.leetcode.median_of_two_sorted_arrays

class Solution {
    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        return if ((nums1.size + nums2.size) % 2 == 0) {
            (findNth((nums1.size + nums2.size) / 2 - 1, nums1, 0, nums1.size, nums2, 0, nums2.size) +
                    findNth((nums1.size + nums2.size) / 2, nums1, 0, nums1.size, nums2, 0, nums2.size)) / 2
        } else {
            findNth((nums1.size + nums2.size - 1) / 2, nums1, 0, nums1.size, nums2, 0, nums2.size)
        }
    }

    private fun findNth(
        n: Int,
        nums1: IntArray,
        nums1l: Int,
        nums1r: Int,
        nums2: IntArray,
        nums2l: Int,
        nums2r: Int,
    ): Double {
        val cutOffIndex1 = if (nums1r - nums1l > 0) (nums1l + nums1r - 1) / 2 else null
        val cutOffIndex2 = if (nums2r - nums2l > 0) (nums2l + nums2r - 1) / 2 else null
        val cutOffValue1: Int? = cutOffIndex1?.let { nums1[it] }
        val cutOffValue2: Int? = cutOffIndex2?.let { nums2[it] }
        val (a, al, am, ar, b, bl, bm, br) = when {
            cutOffValue2 == null || cutOffValue1 != null && cutOffValue1 > cutOffValue2 -> {
                Split(nums1, nums1l, cutOffIndex1 ?: 0, nums1r, nums2, nums2l, cutOffIndex2 ?: 0, nums2r)
            }
            else -> Split(nums2, nums2l, cutOffIndex2, nums2r, nums1, nums1l, cutOffIndex1 ?: 0, nums1r)
        }

        return when {
            br - bl == 0 -> a[al + n].toDouble()
            n <= am - al + bm - bl -> findNth(n, a, al, am, b, bl, br)
            else -> findNth(n - bm + bl - 1, a, al, ar, b, bm + 1, br)
        }
    }

    data class Split(
        val a: IntArray, val al: Int, val am: Int, val ar: Int,
        val b: IntArray, val bl: Int, val bm: Int, val br: Int
    )
}