package cheapest_flights_within_k_stops

import java.util.PriorityQueue

class Solution {
    fun findCheapestPrice(n: Int, flights: Array<IntArray>, src: Int, dst: Int, k: Int): Int {
        // Dikjestra: pq of (acc_price, stop, city_no)
        //  If pq is empty but no min is found, return -1
        //  Max distance between src and dst is k + 1
        val graph = Array(n) { mutableListOf<Pair<Int,Int>>() } // (price -> city)
        for (i in 0..<flights.size) {
            graph[flights[i][0]].add(flights[i][2] to flights[i][1])
        }
        val q = PriorityQueue<IntArray> { a, b -> a[0] - b[0] }
        val mins = IntArray(n) { Int.MAX_VALUE }
        q.add(intArrayOf(0, 0, src))
        var minPrice = Int.MAX_VALUE
        while (q.isNotEmpty()) {
            val (accPrice, stops, city) = q.remove()
            if (stops >= mins[city]) {
                continue
            }
            if (accPrice >= minPrice) {
                continue
            }
            mins[city] = stops

            if (city == dst) {
                minPrice = accPrice
                continue
            } else {
                val nextStops = graph[city]
                for (i in 0..<nextStops.size) {
                    val (priceToNextStop, nextStopNo) = nextStops[i]
                    if ((stops == k && nextStopNo == dst || stops < k)) {
                        q.add(intArrayOf(accPrice + priceToNextStop, stops + 1, nextStopNo))
                    }
                }
            }
        }

        return if (minPrice != Int.MAX_VALUE) minPrice else -1
    }
}