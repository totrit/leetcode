package com.totrit.leetcode.generate_parentheses

class Solution {
    fun generateParenthesis(n: Int): List<String> {
        val ans = mutableListOf<String>()
        val buffer = CharArray(2 * n)
        solve(ans, buffer, 0, 0, n)
        return ans
    }

    private fun solve(
        ans: MutableList<String>,
        buffer: CharArray,
        pos: Int,
        opened: Int,
        closed: Int,
    ) {
        if (opened == 0 && closed == 0) {
            ans.add(buffer.toString())
            return
        }
        if (opened > 0) {
            buffer[pos] = ')'
            solve(ans, buffer, pos + 1, opened - 1, closed)
        }
        if (closed > 0) {
            buffer[pos] = '('
            solve(ans, buffer, pos + 1, opened + 1, closed - 1)
        }
    }
}