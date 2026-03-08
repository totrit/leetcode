package permutation_in_string

class Solution {
    fun checkInclusion(s1: String, s2: String): Boolean {
        val required = s1.length
        val requiredChars = IntArray(26) { 0 }
        for (c in s1) {
            requiredChars[c - 'a'] ++
        }
        val map = IntArray(26) { 0 }
        var count = 0
        var left = 0
        for (right in s2.indices) {
            val index = s2[right] - 'a'
            map[index] ++
            if (map[index] <= requiredChars[index]) {
                count ++
            }

            if (left <= right - required) {
                val index = s2[left] - 'a'
                if (map[index] <= requiredChars[index]) {
                    count --
                }
                map[index] --
                left ++
            }

            if (count == required) {
                return true
            }
        }

        return false
    }
}