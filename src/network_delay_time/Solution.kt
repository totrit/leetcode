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
        val minDists = IntArray(n+1) { Int.MAX_VALUE }

        val pq = PriorityQueue<Pair<Int, Int>>(n) { a, b -> a.first - b.first }
        pq.add(0 to k)
        var dist = 0
        var toVisit = n
        while(pq.isNotEmpty()) {
            val popped = pq.remove()
            val node = popped.second
            val delay = popped.first
            if (delay >= minDists[node]) {
                continue
            } else {
                minDists[node] = delay
            }
            toVisit --
            dist = delay
            if (toVisit == 0) {
                break
            }
            edges[node]?.let {
                for ((nextNode, delay) in it) {
                    if (dist + delay < minDists[nextNode]) {
                        pq.add(dist + delay to nextNode)
                    }
                }
            }
        }
        return if (toVisit == 0) dist else -1
    }
}