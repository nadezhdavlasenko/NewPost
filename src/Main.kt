fun main() {


    val graph = arrayOf(
        doubleArrayOf(0.0, 10.0, 15.0, 20.0),
        doubleArrayOf(10.0, 0.0, 35.0, 25.0),
        doubleArrayOf(15.0, 35.0, 0.0, 30.0),
        doubleArrayOf(20.0, 25.0, 30.0, 0.0)
    )

    println(TSP(graph, setOf(0,1,2,3), 0, Double.MAX_VALUE))
}

fun TSP(graph: Array<DoubleArray>, set: Set<Int>, destPoint: Int, min : Double): Double {
    if (set.isEmpty()) return graph[destPoint][0]
    var localMin = min
    set.forEach {
        localMin = minOf(localMin, graph[it][destPoint] + TSP(graph, set - it, it, localMin))
    }
    return  localMin
}

