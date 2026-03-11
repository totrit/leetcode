package word_search_ii

class Solution {
    private val neighboursDiff = arrayOf(0 to 1, 0 to -1, 1 to 0, -1 to 0)

    fun findWords(board: Array<CharArray>, words: Array<String>): List<String> {
        // Build a Trie with the `words`
        //  Then iterate through each cell and try to recursive call with itself and its neiboughs
        //  Use a matrix to mark visiting to prevent ineffective loop.
        val root = Node()
        // Build Trie
        for (w in words) {
            var curr = root
            root.count ++
            for (i in w.indices) {
                val c = w[i]
                val ci = c - 'a'
                val node = curr.nexts[ci] ?: Node().also {curr.nexts[ci] = it}
                node.count ++
                curr = node
                if (i == w.length - 1) {
                    curr.word = w
                }
            }
        }
        val ans = mutableListOf<String>()
        for (i in board.indices) {
            for (j in board[0].indices) {
                solve(ans, board, root, i, j)
            }
        }
        return ans.toList()
    }


    private fun solve(
        ans: MutableList<String>,
        board: Array<CharArray>,
        node: Node,
        row: Int,
        col: Int,
    ): Int {
        val char = board[row][col]
        val ci = char - 'a'
        val matched = node.nexts[ci]
        var ret = 0
        if (matched == null) {
            return 0
        }
        if (matched.word != null) {
            ans.add(matched.word!!)
            matched.word = null
            ret ++
        }
        board[row][col] = '#'
        // Traverse neighbours
        for ((rd, cd) in neighboursDiff) {
            if ((row + rd) in board.indices && (col + cd) in board[0].indices && board[row + rd][col + cd] != '#') {
                ret += solve(ans, board, matched, row + rd, col + cd)
            }
        }

        board[row][col] = char
        matched.count -= ret
        if (matched.count == 0) {
            node.nexts[ci] = null
        }

        return ret
    }

    data class Node(
        val nexts: Array<Node?> = arrayOfNulls<Node>(26),
        var word: String? = null,
        var count: Int = 0,
    )
}