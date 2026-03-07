package longest_repeating_character_replacement

import kotlin.math.max

class Solution {
    fun characterReplacement(s: String, k: Int): Int {
        var left = 0
        var right = 0
        val counter = hashMapOf(s[0] to 1)
        var ans = 1
        var maxReps = 1
        while (right < s.length) {
            val count = right - left + 1 - maxReps
            if (count <= k) {
                //  1. If it's within k, slide right to the right, with limit of k, to find the `maxReps`. And update ans
                right ++
                if (right < s.length) {
                    val rightCharCount = counter.getOrDefault(s[right], 0) + 1
                    counter[s[right]] = rightCharCount
                    if (count < k || rightCharCount > maxReps) {
                        if (rightCharCount > maxReps) {
                            maxReps = rightCharCount
                        }
                        if (right - left + 1 > ans) {
                            ans = right - left + 1
                        }
                    }
                }
            } else if (counter[s[right]]!! <= maxReps) {
                //  2. Continue to slide right to the right, to a position where maxReps will be exceeded (but don't update maxReps yet)
                right ++
                if (right < s.length) {
                    counter[s[right]] = counter.getOrDefault(s[right], 0) + 1
                }
            } else {
                //  3. Since now it's over k, move `left` ot the right, to a position where k is just met or the dominant char is equal to maxReps
                counter[s[left]] = counter.getOrDefault(s[left], 0) - 1
                left ++
                val rightCharCount = counter[s[right]]!!
                if (right - left + 1 - max(maxReps, rightCharCount) <= k) {
                    if (rightCharCount > maxReps) {
                        maxReps = rightCharCount
                    }
                    if (right - left + 1 > ans) {
                        ans = right - left + 1
                    }
                }
            }
        }

        return ans
    }
}