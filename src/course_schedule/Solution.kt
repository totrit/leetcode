package course_schedule

class Solution {
    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        // The default distance between each course is 1
        // But for some there are pre-reqs
        // Goal is to detect loop of pre-reqs
        // Greedily launching from one node and searching out, until we find a node is dependant on itself

        //  Iterate through each prerequisites:
        //      Mark the cell of (ai, bi) to be 1, and
        //      Iterate through matrix[][ai] and for each element (ak, ai) that is 1, mark (ak, bi) to be 1
        //      Of all these markings, whenever we find ai == bi, we detect a loop, return false

        val dp = Array(numCourses) { hashMapOf<Int, Int>() }
        for (p in 0..<prerequisites.size) {
            val ai = prerequisites[p][0]
            val bi = prerequisites[p][1]
            if (ai == bi) {
                return false
            }
            dp[ai][bi] = 1
        }
        for (p in 0..<prerequisites.size) {
            if (dp[prerequisites[p][0]][prerequisites[p][1]] == 1 && !solve(dp, prerequisites[p][0], prerequisites[p][1], hashSetOf(prerequisites[p][0]))) {
                return false
            }
        }
        return true
    }

    private fun solve(dp: Array<HashMap<Int,Int>>, ai: Int, bi: Int, visited: HashSet<Int>): Boolean {
        dp[ai][bi] = 2
        visited.add(bi)
        for ((dep, mark) in dp[bi].entries) {
            when (mark) {
                1 -> {
                    if (visited.contains(dep)) {
                        return false
                    }
                    val ans = solve(dp, bi, dep, visited)
                    if (!ans) {
                        return false
                    }
                }
            }
        }
        visited.remove(bi)
        return true
    }
}