package network_delay_time

import java.util.PriorityQueue

class Solution {
    fun networkDelayTime(times: Array<IntArray>, n: Int, k: Int): Int {
        val edges = Array<MutableList<Pair<Int,Int>>?>(n+1) { null }
        for (i in 0..<times.size) {
            val list = edges[times[i][0]] ?: mutableListOf<Pair<Int, Int>>().also {
                edges[times[i][0]] = it
            }
            list.add(times[i][1] to times[i][2])
        }
        val toVisit = HashSet(IntArray(n) { it + 1 }.toList())

        val pq = PriorityQueue<Pair<Int, Int>>(n) { a, b -> a.first - b.first }
        pq.add(0 to k)
        var dist = 0
        while(pq.isNotEmpty()) {
            val popped = pq.remove()
            val node = popped.second
            val delay = popped.first
            if (toVisit.contains(node)) {
                toVisit.remove(node)
                if (toVisit.isEmpty()) {
                    dist = delay
                    break
                }
            } else {
                continue
            }
            dist = delay
            edges[node]?.let {
                for ((nextNode, delay) in it) {
                    if (toVisit.contains(nextNode)) {
                        pq.add(dist + delay to nextNode)
                    }
                }
            }
        }
        return if (toVisit.isEmpty()) dist else -1
    }
}