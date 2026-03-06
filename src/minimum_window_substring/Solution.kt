package minimum_window_substring

class Solution {
    fun minWindow(s: String, t: String): String {
        // Use a Map to record the occurences of each char from t
        //  Use sliding window to move across s
        //  First move the right pointer, until the target is met
        //  Then move the left pointer so that the target is just missed. And then move the right pointer again, until met
        //  Use a total-char-count to check if target has been met
        //  Every time the target is met, try to update the min
        //  Finish the worm crawl if:
        //      1. Right pointer has reached end of s and target not met
        //      2. Right pointer has reached end of s and left pointer has reached the right-most point that just matches the target without exceesive chars on its front or end
        // 2 maps: one for current window char count; the other is for target char count

        val targetMap = hashMapOf<Char, Int>()
        var targetCount = 0
        for (i in 0..<t.length) {
            targetMap[t[i]] = (targetMap[t[i]] ?: 0) + 1
            targetCount ++
        }
        val currentMap = hashMapOf<Char, Int>()
        var currentCount = 0
        var minL = -1
        var minR = -1
        var l = 0
        var r = -1
        while (true) {
            //  Move right cursor to right until just meet target
            var met = false
            while (r < s.length - 1) {
                r ++
                val v = s[r]
                val target = targetMap.getOrDefault(v, 0)
                val current = currentMap.getOrDefault(v, 0)
                when {
                    current < target -> {
                        currentMap[v] = current + 1
                        currentCount ++

                        if (currentCount >= targetCount) {
                            met = true
                            break
                        }
                    }
                    target > 0 -> {
                        currentMap[v] = current + 1
                    }
                }
            }
            if (!met) {
                break
            }

            // Move left cursor to right until just missed the target
            while (l < r - targetCount + 2) {
                val v = s[l]
                val target = targetMap.getOrDefault(v, 0)
                val current = currentMap.getOrDefault(v, 0)
                when {
                    current > target -> {
                        currentMap[v] = current - 1
                    }
                    target != 0 && current == target && currentCount == targetCount -> {
                        if (minL == -1 || r - l < minR - minL) {
                            minL = l
                            minR = r
                        }
                        l ++
                        currentMap[v] = current - 1
                        currentCount --
                        break
                    }
                    else -> {
                        // Can't reach
                    }
                }
                l ++
            }
        }

        return if (minL != -1) {
            s.substring(minL, minR + 1)
        } else {
            ""
        }
    }
}