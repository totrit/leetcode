package path_with_minimum_effort

import java.util.PriorityQueue
import kotlin.math.abs
import kotlin.math.max

class Solution {
    fun minimumEffortPath(heights: Array<IntArray>): Int {
        // Brute-force: recursive examine every cell
        //  Maintain the visited stack (hash-set). For every call try every non-visited neighbour
        //  It will return the min-diff
        //  The leaf returning points: found (row-1, col-1) and it has the lowest diff to the current visiting cell
        //  The call will compare all alternative routes and pick the lowest one to return

        // Dijkastra way: flatten the matrix to be a list (with length of row x col). Then build a graph of abs-distance between the nodes
        //  So will be a graph of (rowxcol, rowxcol)
        //  The goal is to find the path with edges having minimum of max weights

        // A more efficient way of Dijkastra. Build a PriorityQuque. It'll hold node-info(row, col) and the abs-diff to get there
        //  Initially add the (0, (0,0))
        //  Loop with the pq: pop the queue and check the abs-diff and cell position (x, y)
        //      Update max-diff = abs-diff if abs-diff is larger than max-diff
        //      Get the neighbours of the node without repeating already visited ones, and put into the queue
        //      Return max-diff until find the node to be (row-1, col-1)
        val matrixRows = heights.size
        val matrixCols = heights[0].size
        val cellsMins = Array(matrixRows) { IntArray(matrixCols) { Int.MAX_VALUE } }
        val q = PriorityQueue<Pair<Int,Cell>> { a, b -> a.first - b.first }
        val lastCell = Cell(matrixRows - 1, matrixCols - 1)
        q.add(0 to Cell(0, 0))
        cellsMins[0][0] = 0
        var maxDiff = 0
        while(q.isNotEmpty()) {
            val popped = q.remove()
            val diff = popped.first
            val cell = popped.second
            if (diff > maxDiff) {
                maxDiff = diff
            }
            if (cell == lastCell) {
                break
            }
            arrayOf(0 to 1, 1 to 0, -1 to 0, 0 to -1).mapNotNull { Cell(cell.x + it.first, cell.y + it.second).takeIf { it.x in 0..<matrixRows && it.y in 0..<matrixCols } }
                .forEach {
                    val diffToNext = max(abs(heights[cell.x][cell.y] - heights[it.x][it.y]), maxDiff)
                    if (diffToNext < cellsMins[it.x][it.y]) {
                        q.add(diffToNext to it)
                        cellsMins[it.x][it.y] = diffToNext
                    }
                }
        }
        return maxDiff
    }

    private data class Cell(
        val x: Int,
        val y: Int,
    )
}