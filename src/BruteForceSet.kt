// O(n!)
fun travelRecursively(graph: Array<DoubleArray>): Double = TSP(graph, graph.indices.toSet(), 0, Double.MAX_VALUE)

fun TSP(graph: Array<DoubleArray>, set: Set<Int>, destPoint: Int, min: Double): Double {
    if (set.isEmpty()) return graph[destPoint][0]
    var localMin = min
    set.forEach {
        localMin = minOf(localMin, graph[it][destPoint] + TSP(graph, set - it, it, localMin))
    }
    return localMin
}

fun travelMemoizeHashTable(graph: Array<DoubleArray>): Double {
    val mins = HashMap<TSP, Double>()
    graph.indices.forEach {
        mins[TSP(it, emptySet())] = graph[it][0]
    }
    return TSPmemoizeHashTable(graph, graph.indices.toSet(), 0, Double.MAX_VALUE, mins)
}