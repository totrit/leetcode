package course_schedule_ii

class Solution {
    fun findOrder(numCourses: Int, prerequisites: Array<IntArray>): IntArray {
        //  Form graph where child points to a list of parents
        //  Keep state of course visiting state: no-touched, visiting, visited
        //  Loop through each course and if it's not visited, start recursive call from it until reach all its deps
        //  The recursive call returns the index of the filled up output array
        //  If during recursive call, before reaching end, found a dep that's being visited, that means a cycle exists. Just return -1 in that case
        val allDeps = Array(numCourses) { mutableListOf<Int>() }
        val states = IntArray(numCourses) // 0: not touched; -1: visiting; 1: visited
        val ans = IntArray(numCourses)
        var ansCur = 0

        for (p in prerequisites) {
            val (self, dep) = p
            allDeps[self].add(dep)
        }

        for (c in 0..<numCourses) {
            if (states[c] == 0) {
                ansCur = solve(c, allDeps, states, ans, ansCur)
                if (ansCur == -1) {
                    return intArrayOf()
                }
            }
        }

        return ans
    }

    private fun solve(
        course: Int,
        allDeps: Array<MutableList<Int>>,
        states: IntArray,
        ans: IntArray,
        ansCur: Int,
    ): Int {
        val deps = allDeps[course]
        var cur = ansCur
        if (states[course] == -1) {
            return -1
        } else if (states[course] == 1) {
            return ansCur
        }
        states[course] = -1
        for (i in deps.indices) {
            val dep = deps[i]
            cur = solve(dep, allDeps, states, ans, cur)
            if (cur == -1) {
                return -1
            }
        }
        ans[cur] = course
        states[course] = 1
        return cur + 1
    }
}