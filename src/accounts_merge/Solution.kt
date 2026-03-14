package accounts_merge

class Solution {
    fun accountsMerge(accounts: List<List<String>>): List<List<String>> {
        val parentMap = hashMapOf<String,String>()
        val rootEmailToName = hashMapOf<String,String>()
        for (account in accounts) {
            val name = account[0]
            var root: String? = null
            for (i in 1..<account.size) {
                val email = account[i]
                //Find root of the email
                findRootAndCompressPath(email, parentMap).let {
                    if (root == null) {
                        root = it
                    } else if (root != it) {
                        parentMap[it] = root!!
                    }
                }
                rootEmailToName[root!!] = name
            }
        }
        val builder: HashMap<String,MutableList<String>> = hashMapOf()
        for (email in parentMap.keys) {
            val root = findRootAndCompressPath(email, parentMap)
            val list = builder[root] ?: mutableListOf<String>().also { builder[root] = it }
            list.add(email)
        }
        return builder.entries.map { (k, v) ->
            listOf(rootEmailToName[k]!!).plus(v.sorted())
        }
    }

    private fun findRootAndCompressPath(
        email: String,
        parentMap: HashMap<String,String>,
    ): String {
        val parent = parentMap[email]
        return if (parent == null) {
            parentMap[email] = email
            email
        } else if (parent == email) {
            email
        } else {
            val root = findRootAndCompressPath(parent, parentMap)
            if (parent != root) {
                parentMap[email] = root
            }
            root
        }
    }
}