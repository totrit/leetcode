package implement_trie_prefix_tree

import kotlin.math.abs

class Trie() {
    val tree = mutableListOf(IntArray(26) { 0 })

    fun insert(word: String) {
        var treeCur = 0
        for (i in word.indices) {
            val c = word[i]
            val ci = c - 'a'
            val nodeIndexOnTree = tree[treeCur][ci]
            if (nodeIndexOnTree == 0) {
                tree.add(IntArray(26) { 0 })
                tree[treeCur][ci] = -(tree.size - 1)
            }
            if (i == word.length - 1) {
                tree[treeCur][ci] = abs(tree[treeCur][ci])
            } else {
                treeCur = abs(tree[treeCur][ci])
            }
        }
    }

    fun search(word: String): Boolean {
        var treeCur = 0
        for (i in word.indices) {
            val c = word[i]
            val ci = c - 'a'
            val nodeIndex = tree[treeCur][ci]
            if (nodeIndex == 0) {
                return false
            } else if (nodeIndex > 0 && i == word.length - 1) {
                return true
            } else {
                treeCur = abs(nodeIndex)
            }
        }
        return false
    }

    fun startsWith(prefix: String): Boolean {
        var treeCur = 0
        for (i in prefix.indices) {
            val c = prefix[i]
            val ci = c - 'a'
            val nodeIndex = tree[treeCur][ci]
            if (nodeIndex == 0) {
                return false
            } else {
                treeCur = abs(nodeIndex)
            }
        }
        return true
    }
}