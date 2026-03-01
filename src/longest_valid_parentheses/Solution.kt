package com.totrit.leetcode.longest_valid_parentheses

class Solution {
    fun longestValidParentheses(s: String): Int {
        val indices = mutableListOf<Int>()
        for (i in 0..<s.length) {
            val c = s[i]
            val lastIndex = indices.lastOrNull()
            val lastSymbol = lastIndex?.let { s[it] }
            if (lastSymbol == '(' && c == ')') {
                indices.removeAt(indices.size - 1)
            } else {
                indices.add(i)
            }
        }
        indices.add(0, -1)
        indices.add(s.length)
        var lg = 1

        for (i in 0..<indices.size - 1) {
            val s = indices[i]
            val e = indices[i + 1]
            if (e - s > lg) {
                lg = e - s
            }
        }

        return lg - 1
    }
}