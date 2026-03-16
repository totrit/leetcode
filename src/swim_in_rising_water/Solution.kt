package swim_in_rising_water

class Solution {
    private val diffs = arrayOf(intArrayOf(0, -1), intArrayOf(0, 1), intArrayOf(-1, 0), intArrayOf(1, 0))

    fun swimInWater(grid: Array<IntArray>): Int {
        val n = grid.size
        val start = intArrayOf(0,0)
        val end = intArrayOf(n-1,n-1)
        val valueToPosition = Array(grid.size * grid.size) { intArrayOf() }
        val dsu = Array(grid.size) { r -> Array(grid.size) { c -> intArrayOf(r, c, 0) } }
        //  Loop through 0 to n*n-1, and do DSU join until a number finds itself belonging to both the (0,0) group and the (n-1,n-1) group. And return that number
        for (i in 0..<n) {
            for (j in 0..<n) {
                valueToPosition[grid[i][j]] = intArrayOf(i, j)
            }
        }
        var ans = 0
        if (find(start, dsu).contentEquals(find(end, dsu))) {
            return ans
        }
        while (ans < n*n) {
            val position = valueToPosition[ans]
            // Try to connect to neighbours
            for (nd in diffs) {
                val ni = position[0] + nd[0]
                val nj = position[1] + nd[1]
                val nc = intArrayOf(ni, nj)
                if (ni in 0..<n && nj in 0..<n && grid[ni][nj] < ans) {
                    // Join with the neighbour
                    val currGroup = find(position, dsu)
                    val nGroup = find(nc, dsu)
                    if (!currGroup.contentEquals(nGroup)) {
                        union(currGroup, nGroup, dsu)
                        if (find(start, dsu).contentEquals(find(end, dsu))) {
                            return ans
                        }
                    }
                }
            }
            ans ++
        }

        return ans
    }

    private fun setDsu(
        cell: IntArray,
        root: IntArray,
        dsu: Array<Array<IntArray>>,
    ) {
        dsu[cell[0]][cell[1]] = root
    }

    private fun find(
        cell: IntArray,
        dsu: Array<Array<IntArray>>,
    ): IntArray {
        var root = dsu[cell[0]][cell[1]]
        while (!root.contentEquals(dsu[root[0]][root[1]])) {
            root = dsu[root[0]][root[1]]
        }
        var parent = dsu[cell[0]][cell[1]]
        while (!parent.contentEquals(dsu[parent[0]][parent[1]])) {
            parent = dsu[parent[0]][parent[1]]
            setDsu(parent, root, dsu)
        }
        setDsu(cell, root, dsu)
        return root
    }

    private fun union(
        g1: IntArray,
        g2: IntArray,
        dsu: Array<Array<IntArray>>,
    ) {
        if (dsu[g1[0]][g1[1]][2] < dsu[g2[0]][g2[1]][2]) {
            setDsu(g1, g2, dsu)
        } else {
            setDsu(g2, g1, dsu)
            dsu[g1[0]][g1[1]][2] = maxOf(dsu[g1[0]][g1[1]][2], dsu[g2[0]][g2[1]][2] + 1)
        }
    }
}