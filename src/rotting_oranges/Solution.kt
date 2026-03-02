package rotting_oranges

class Solution {
    fun orangesRotting(grid: Array<IntArray>): Int {
        // Use a list to maintain the oranges that will rot in next round
        //  Iterate through the to-rot list and for each to-rot orange:
        //      Look at the graph and find the unrotten neighbours and put into the list
        //      Mark the current orange in the graph to be rotten
        //  After one iteration, increase minutes by 1

        var minutes = 0
        val q = ArrayDeque<Pair<Int,Int>>()
        var totalToRotten = 0

        fun addNeighbours(i: Int, j: Int) {
            if (i > 0 && grid[i-1][j] == 1) {
                q.add(i-1 to j)
                grid[i-1][j] = -1
            }
            if (i < grid.size - 1 && grid[i+1][j] == 1) {
                q.add(i+1 to j)
                grid[i+1][j] = -1
            }
            if (j > 0 && grid[i][j-1] == 1) {
                q.add(i to j-1)
                grid[i][j-1] = -1
            }
            if (j < grid[0].size - 1 && grid[i][j+1] == 1) {
                q.add(i to j+1)
                grid[i][j+1] = -1
            }
        }

        for (i in 0..<grid.size) {
            for (j in 0..<grid[0].size) {
                when (grid[i][j]) {
                    2 -> addNeighbours(i, j)
                    1, -1 -> totalToRotten++
                }
            }
        }

        while (q.isNotEmpty()) {
            repeat(q.size) {
                val (x, y) = q.removeFirst()
                grid[x][y] = 2
                addNeighbours(x, y)
                totalToRotten--
            }
            minutes++
        }
        return if (totalToRotten > 0) -1 else minutes
    }
}