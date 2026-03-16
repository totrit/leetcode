# 🚀 Cheat Sheet (Kotlin)

## 🛠️ Core Algorithmic Patterns

### 1. Graphs & Connectivity
| Scenario | Algorithm | The "Vibe" | Key Logic |
| :--- | :--- | :--- | :--- |
| **Grouping / Circles** | **DSU** | *Magnets* | `find(i)` with path compression; `union(i, j)` by rank. |
| **Dependencies** | **Kahn's (TopSort)** | *Water Flow* | Track `inDegree`. When `inDegree == 0`, add to `Queue`. |
| **Critical Links** | **Tarjan's** | *Voltage Leak* | `low[u] = min(disc[u], low[v])`. If `low[v] > disc[u]`, it's a bridge. |
| **Shortest Path** | **Dijkstra** | *Expanding Frontier* | `PriorityQueue<Node>` sorted by distance. Relax edges. |
| **Min Cost to Connect** | **Prim's / Kruskal's** | *Greedy Bridge* | Connect the cheapest node/edge that doesn't form a cycle. |

### 2. Linear Optimizations (O(N))
| Scenario | Algorithm | The "Vibe" | Key Logic |
| :--- | :--- | :--- | :--- |
| **Next Greater Element** | **Monotonic Stack** | *The Wall* | While `nums[stack.top] < current`, pop and set `res[pop] = current`. |
| **Sliding Window Max** | **Monotonic Queue** | *The Champion* | Remove "weaker" elements from back; `front` is current max. |
| **Contiguous Subarrays** | **Sliding Window** | *Caterpillar* | Move `right` to expand; move `left` to satisfy constraint. |

### 3. State, Sorting & BIT
| Scenario | Algorithm | The "Vibe" | Key Logic |
| :--- | :--- | :--- | :--- |
| **Point Update/Range Sum** | **BIT (Fenwick)** | *The Bit Jump* | `i += i and -i` to update; `i -= i and -i` to query prefix sum. |
| **Dynamic Top-K** | **TreeMap** | *Filing Cabinet* | `countMap[id]++`, update `treeMap[count].add(id)`. |
| **Search for Answer** | **Binary Search** | *The Oracle* | `while (l <= r)`. Check if `mid` is feasible, then adjust range. |
| **Complex Paths** | **3D BFS/Dijkstra** | *Layered Grid* | Use `visited[row][col][fuel]` to prune paths. |

---

## ⚡ Kotlin Performance & Syntax Tips

### 1. The "1D Index" Trick (Grid to Array)
* **Index:** `val id = r * cols + c`
* **Row/Col:** `val r = id / cols`, `val c = id % cols`

### 2. Essential Collections
* **PriorityQueue (Min-Heap):** `PriorityQueue<IntArray> { a, b -> a[0] - b[0] }`
* **TreeMap:** `val map = java.util.TreeMap<Int, MutableSet<String>>()`
* **Deque:** `val deque = ArrayDeque<Int>()` (Use `removeFirst()`, `removeLast()`).

### 3. BIT Snippet
```kotlin
fun update(i: Int, delta: Int) {
    var idx = i + 1 // BIT is 1-indexed
    while (idx <= n) { tree[idx] += delta; idx += idx and -idx }
}
```

# Algorithms
## Monotonic Queue / Stack
If on a micro level you can chop away a few numbers from a list, on macro level you can achieve Monotonic
E.g. `239. Sliding Window Maximum`

## Binary Index Tree (BIT)
Use `i and -i` (1-based) to Least Significant Bit (LSB), to help traverse the sum hierarchy.
E.g. To get sum of position 1~7 (index is 1-based), only need to sum up position 4 (taking care of 1~4), 6 (taking care of 5~6) and 7
O(LogN) query time to get sum between 2 indices