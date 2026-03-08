package koko_eating_bananas

class Solution {
    fun minEatingSpeed(piles: IntArray, h: Int): Int {
        //  1. Find the greatest height
        var maxHeight = 0
        for (p in piles) {
            if (p > maxHeight) {
                maxHeight = p
            }
        }

        //  2. Binary search of the height, so that the modulated blocks are just h
        var lower = 1
        var upper = maxHeight
        var min = Int.MAX_VALUE
        while (lower <= upper) {
            val mid = (lower + upper) / 2
            var sum = 0
            for (height in piles) {
                sum += ((height - 1) / mid) + 1
                if (sum > h) {
                    break
                }
            }
            if (sum > h) {
                lower = mid + 1
            } else {
                upper = mid - 1
                if (mid < min) {
                    min = mid
                }
            }
        }

        return min
    }
}