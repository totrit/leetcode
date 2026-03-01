package com.totrit.leetcode.maximal_rectangle

class Solution {
    fun maximalRectangle(matrix: Array<CharArray>): Int {
        // Use dp (rows x cols) to store for each element the consecutive 1s above it (and including it)
        // Loop from top to bottom and left to right to fill up the dp table
        // By looking back at the bar heights of the elements on its left we can iterate candidate rects and find the max
        // To optimise speed we maintain a list of chockpoints (index, height), of which on the right can have a greater height of rect
        // Iterate through each element in the matrix:
        //  Get bar height of the current element, say H (by looking at the element above, and plus 1)
        //  Update the list of chockpoints: remove any that's higher than H, then put (current-index, H) to the end of the list
        //  Iterate through the remaining chockpoints:
        //      Get the area of the rect that's anchored at the current chockpoint: (current-index - chockpoint-index) * next-chockpoint-height
        // Keep the max area and update it during iteration
        val rows = matrix.size
        val cols = matrix[0].size
        val dp = Array(rows){ IntArray(cols) { 0 } }
        var ans = 0

        for (y in 0..<rows) {
            val chokes = mutableListOf<Choke>(Choke(-1, 0))
            for (x in 0..<cols) {
                val currentBarHeight = if (matrix[y][x] == '0') {
                    0
                } else {
                    if (y > 0) {
                        dp[y-1][x] + 1
                    } else {
                        1
                    }
                }
                dp[y][x] = currentBarHeight

                for (c in chokes.size - 1 downTo 0) {
                    val chokeHeight = chokes[c].h
                    if (chokeHeight >= currentBarHeight) {
                        chokes.removeAt(c)
                    } else {
                        break
                    }
                }
                chokes.add(Choke(x, currentBarHeight))

                // Get the max area of the rects ending with the element
                for (c in 0..<chokes.size - 1) {
                    val area = (x - chokes[c].col) * chokes[c+1].h
                    if (area > ans) {
                        ans = area
                    }
                }
            }
        }

        return ans
    }

    private data class Choke(
        val col: Int,
        val h: Int,
    )
}