fun travelMemoizeHashTable(graph: Array<DoubleArray>): Double {
    val mins = HashMap<TSP, Double>()
    graph.indices.forEach {
        mins[TSP(it, emptySet())] = graph[it][0]
    }
    return TSPmemoizeHashTable(graph, graph.indices.toSet(), 0, Double.MAX_VALUE, mins)
}

fun TSPmemoizeHashTable(
    graph: Array<DoubleArray>,
    set: Set<Int>,
    destPoint: Int,
    min: Double,
    mins: HashMap<TSP, Double>
): Double {
    if (mins.containsKey(TSP(destPoint, set))) return mins[TSP(destPoint, set)]!!
    var localMin = min
    set.forEach {
        localMin = minOf(localMin, graph[it][destPoint] + TSPmemoizeHashTable(graph, set - it, it, localMin, mins))
    }
    mins[TSP(destPoint, set)] = localMin
    return localMin
}

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
        if (set != other.set) return false

        return true
    }

    override fun hashCode(): Int {
        var result = destPoint
        result = 31 * result + set.hashCode()
        return result
    }
}