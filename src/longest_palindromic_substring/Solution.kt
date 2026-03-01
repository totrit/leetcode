package com.totrit.leetcode.longest_palindromic_substring

import kotlin.math.min

class Solution {
    fun longestPalindrome(s: String): String {
        var longest: Int = 1
        var longestLeft: Int = 0
        var longestRight: Int = 0
        for (i in 0..<s.length) {
            for (k in 1..min(i, s.length - i - 1)) {
                if (s[i - k] != s[i + k]) {
                    break
                } else if (2 * k + 1 > longest) {
                    longest = 2 * k + 1
                    longestLeft = i - k
                    longestRight = i + k
                }
            }
            if (s.getOrNull(i + 1) == s[i]) {
                for (k in 1..min(i + 1, s.length - i - 1)) {
                    if (s[i - k + 1] != s[i + k]) {
                        break
                    } else if (2 * k > longest) {
                        longest = 2 * k
                        longestLeft = i - k + 1
                        longestRight = i + k
                    }
                }
            }
        }
        return s.substring(longestLeft, longestRight + 1)
    }
}