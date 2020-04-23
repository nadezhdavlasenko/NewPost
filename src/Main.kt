fun main() {

    val graph = arrayOf(
        doubleArrayOf(0.0, 10.0, 15.0, 20.0),
        doubleArrayOf(10.0, 0.0, 35.0, 25.0),
        doubleArrayOf(15.0, 35.0, 0.0, 30.0),
        doubleArrayOf(20.0, 25.0, 30.0, 0.0)
    )

    val graph1 = arrayOf(
        doubleArrayOf(0.0, 29.0, 82.0, 46.0, 68.0, 52.0, 72.0, 42.0, 51.0, 55.0, 29.0, 74.0, 23.0, 72.0, 46.0),
        doubleArrayOf(29.0, 0.0, 55.0, 46.0, 42.0, 43.0, 43.0, 23.0, 23.0, 31.0, 41.0, 51.0, 11.0, 52.0, 21.0),
        doubleArrayOf(82.0, 55.0, 0.0, 68.0, 46.0, 55.0, 23.0, 43.0, 41.0, 29.0, 79.0, 21.0, 64.0, 31.0, 51.0),
        doubleArrayOf(46.0, 46.0, 68.0, 0.0, 82.0, 15.0, 72.0, 31.0, 62.0, 42.0, 21.0, 51.0, 51.0, 43.0, 64.0),
        doubleArrayOf(68.0, 42.0, 46.0, 82.0, 0.0, 74.0, 23.0, 52.0, 21.0, 46.0, 82.0, 58.0, 46.0, 65.0, 23.0),
        doubleArrayOf(52.0, 43.0, 55.0, 15.0, 74.0, 0.0, 61.0, 23.0, 55.0, 31.0, 33.0, 37.0, 51.0, 29.0, 59.0),
        doubleArrayOf(72.0, 43.0, 23.0, 72.0, 23.0, 61.0, 0.0, 42.0, 23.0, 31.0, 77.0, 37.0, 51.0, 46.0, 33.0),
        doubleArrayOf(42.0, 23.0, 43.0, 31.0, 52.0, 23.0, 42.0, 0.0, 33.0, 15.0, 37.0, 33.0, 33.0, 31.0, 37.0),
        doubleArrayOf(51.0, 23.0, 41.0, 62.0, 21.0, 55.0, 23.0, 33.0, 0.0, 29.0, 62.0, 46.0, 29.0, 51.0, 11.0),
        doubleArrayOf(55.0, 31.0, 29.0, 42.0, 46.0, 31.0, 31.0, 15.0, 29.0, 0.0, 51.0, 21.0, 41.0, 23.0, 37.0),
        doubleArrayOf(29.0, 41.0, 79.0, 21.0, 82.0, 33.0, 77.0, 37.0, 62.0, 51.0, 0.0, 65.0, 42.0, 59.0, 61.0),
        doubleArrayOf(74.0, 51.0, 21.0, 51.0, 58.0, 37.0, 37.0, 33.0, 46.0, 21.0, 65.0, 0.0, 61.0, 11.0, 55.0),
        doubleArrayOf(23.0, 11.0, 64.0, 51.0, 46.0, 51.0, 51.0, 33.0, 29.0, 41.0, 42.0, 61.0, 0.0, 62.0, 23.0),
        doubleArrayOf(72.0, 52.0, 31.0, 43.0, 65.0, 29.0, 46.0, 31.0, 51.0, 23.0, 59.0, 11.0, 62.0, 0.0, 59.0),
        doubleArrayOf(46.0, 21.0, 51.0, 64.0, 23.0, 59.0, 33.0, 37.0, 11.0, 37.0, 61.0, 55.0, 23.0, 59.0, 0.0)
    )

    val graph2 = arrayOf(
        doubleArrayOf(0.0, 3.0, 4.0, 2.0, 7.0),
        doubleArrayOf(3.0, 0.0, 4.0, 6.0, 3.0),
        doubleArrayOf(4.0, 4.0, 0.0, 5.0, 8.0),
        doubleArrayOf(2.0, 6.0, 5.0, 0.0, 6.0),
        doubleArrayOf(7.0, 3.0, 8.0, 6.0, 0.0)
    )

    val set = graph.mapIndexed { index, _ -> index }.toSet()

    set.forEach {
        mins[TSP(it, emptySet())] = graph[it][0]
    }

    var res: Double = Double.MIN_VALUE
    // 80
    var time = measureTimeMillis { res = travelMemoize(graph) }
    println("Memoization: time = $time, res = $res")
    time = measureTimeMillis { res = travelRecursively(graph) }
    println("Recursion:   time = $time, res = $res")
    // 19
    time = measureTimeMillis { res = travelMemoize(graph2) }
    println("Memoization: time = $time, res = $res")
    time = measureTimeMillis { res = travelRecursively(graph2) }
    println("Recursion:   time = $time, res = $res")

    // println(TSP(graph1, setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14), 0, Double.MAX_VALUE)) //291
}
fun travelRecursively(graph: Array<DoubleArray>): Double = TSP(graph, graph.indices.toSet(), 0, Double.MAX_VALUE)

fun travelMemoize(graph: Array<DoubleArray>): Double {
    graph.indices.forEach {
        mins[TSP(it, emptySet())] = graph[it][0]
    }
    return TSPmemoize(graph, graph.indices.toSet(), 0, Double.MAX_VALUE)
}

fun TSP(graph: Array<DoubleArray>, set: Set<Int>, destPoint: Int, min: Double): Double {
    if (set.isEmpty()) return graph[destPoint][0]
    var localMin = min
    set.forEach {
        localMin = minOf(localMin, graph[it][destPoint] + TSP(graph, set - it, it, localMin))
    }
    return localMin
}


fun TSPmemoize(graph: Array<DoubleArray>, set: Set<Int>, destPoint: Int, min: Double): Double {
    if (mins.containsKey(TSP(destPoint, set))) return mins[TSP(destPoint, set)]!!
    var localMin = min
    set.forEach {
        localMin = minOf(localMin, graph[it][destPoint] + TSPmemoize(graph, set - it, it, localMin))
    }
    mins[TSP(destPoint, set)] = localMin
    return localMin
}

val mins = HashMap<TSP, Double>()

class TSP {
    val destPoint: Int
    val set: Set<Int>

    constructor(destPoint: Int, set: Set<Int>) {
        this.destPoint = destPoint
        this.set = set
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TSP

        if (destPoint != other.destPoint) return false
        if (set.size != other.set.size) return false
        if (set.isEmpty()) return true
        if (!set.containsAll(other.set)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = destPoint
        result *= 31 + set.fold(1, { acc, it -> acc * it })

        return result
    }
}

inline fun measureTimeMillis(block: () -> Unit): Long {
    val start = System.currentTimeMillis()
    block()
    return System.currentTimeMillis() - start
}