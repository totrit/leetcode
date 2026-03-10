package course_schedule_ii

class Solution {
    fun findOrder(numCourses: Int, prerequisites: Array<IntArray>): IntArray {
        val allDeps = Array(numCourses) { mutableListOf<Int>() } // Who are dependant on me -- I control who
        val inDegrees = IntArray(numCourses)
        val queue = ArrayDeque<Int>(numCourses)
        val ans = IntArray(numCourses)
        var ansCur = 0

        for (p in prerequisites) {
            val (self, dep) = p
            allDeps[dep].add(self)
            inDegrees[self] ++
        }

        for (c in 0..<numCourses) {
            if (inDegrees[c] == 0) {
                queue.add(c)
            }
        }

        while (queue.isNotEmpty()) {
            val course = queue.removeFirst()
            for (dep in allDeps[course]) {
                inDegrees[dep] --
                if (inDegrees[dep] == 0) {
                    queue.add(dep)
                }
            }
            ans[ansCur++] = course
        }

        return if (ansCur == numCourses) ans else intArrayOf()
    }
}