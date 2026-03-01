package com.totrit.leetcode.regular_expression_matching

class Solution {
    fun isMatch(s: String, p: String): Boolean {
        val dp: Array<Array<Boolean>> = Array(s.length + 1) { Array(p.length + 1) { false } }
        dp[0][0] = true

        for (j in 1..p.length) {
            if (p[j-1] == '*') {
                dp[0][j] = dp[0][j-2]
            }
        }

        for (i in 1..s.length) {
            for (j in 1..p.length) {
                val match = s[i-1] == p[j-1] || p[j-1] == '.'
                when {
                    p[j - 1] == '*' -> {
                        val match = s[i-1] == p[j-2] || p[j-2] == '.'
                        dp[i][j] = dp[i][j-2] || match && dp[i-1][j]
                    }
                    else -> {
                        dp[i][j] = match && dp[i-1][j-1]
                    }
                }
            }
        }
        return dp[s.length][p.length]
    }
}