package longest_repeating_character_replacement

import kotlin.math.max

class Solution {
    fun characterReplacement(s: String, k: Int): Int {
        var left = 0
        val counter = IntArray(26)
        var ans = 1
        var maxReps = 1
        for (right in s.indices) {
            val c = s[right]
            val counterIndex = c - 'A'
            counter[counterIndex] ++

            while (right - left + 1 - max(maxReps, counter[counterIndex]) > k) {
                counter[s[left] - 'A'] --
                left ++
            }

            if (right - left + 1 > ans) {
                ans = right - left + 1
            }
            if (counter[counterIndex] > maxReps) {
                maxReps = counter[counterIndex]
            }
        }

        return ans
    }
}