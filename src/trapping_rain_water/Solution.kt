package com.totrit.leetcode.trapping_rain_water

import kotlin.math.min

class Solution {
    fun trap(height: IntArray): Int {
        // The first 2 blocks can't form a pond
        // If the next block is <= prev block, no new pond
        // If the next block is higher than prev block, the new ponds is to be min(block-height, max-heights-prior) x distance

        // Iterate through the list, while maintaining a list of (index, value) that by that point can be seen from the right side
        // Whenever there is value increase, try to find valley from right of the list until 1. very left of the list or 2. the value is higher than current value
        // To count ponds (blue blocks), when iterate through the kept list (from its right to its left), for each item, count the min(the_value, current_value) * index_distance.
        // After counting finished, update the list: remove the items on the right that has value less or equal to the current value

        val visibleBars = mutableListOf<Bar>()
        var lastHeight = 0
        var count = 0

        for (i in 0..<height.size) {
            val h = height[i]
            when {
                h in 1..<lastHeight -> {
                    visibleBars.add(Bar(i, h))
                }
                h > lastHeight -> {
                    var lastBarHeight = 0
                    for (j in visibleBars.size - 1 downTo 0) {
                        val bar = visibleBars[j]
                        if (h > lastBarHeight) {
                            count += (min(h, bar.value) - lastBarHeight) * (i - bar.index - 1)
                            if (h >= bar.value) {
                                visibleBars.removeAt(j)
                            }
                            lastBarHeight = bar.value
                        } else {
                            break
                        }
                    }
                    visibleBars.add(Bar(i, h))
                }
                h == lastHeight && h != 0 && visibleBars.isNotEmpty() -> {
                    visibleBars.removeAt(visibleBars.size - 1)
                    visibleBars.add(Bar(i, h))
                }
            }
            lastHeight = h
        }

        return count
    }

    private data class Bar(val index: Int, val value: Int)
}