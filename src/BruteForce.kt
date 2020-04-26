import kotlin.math.min

var answer = Double.POSITIVE_INFINITY

fun travelBruteForce(graph: Array<DoubleArray>): Double {
    answer = Double.POSITIVE_INFINITY
    val permutations = IntArray(graph.size)
    TSPBruteForce(graph, 0, permutations, BooleanArray(graph.size+1))
    return answer
}

private fun TSPBruteForce(
    graph: Array<DoubleArray>,
    index: Int,
    permutations: IntArray,
    visited: BooleanArray
) {
    if (index == graph.size - 1) {
        answer = min(answer, count(graph, permutations))
//        println("----")
    }
    for (i in 1 until graph.size) {
        if (visited[i]) continue
        visited[i] = true
        permutations[index] = i
//        permutations.forEach { print("$it ") }
//        println()

        TSPBruteForce(graph, index + 1, permutations, visited)
        visited[i] = false
    }
}

private fun count(graph: Array<DoubleArray>, permutations: IntArray): Double {
    var acc = 0.0
    acc += graph[0][permutations[0]]
    for (i in 1 until permutations.size) {
        acc += graph[permutations[i - 1]][permutations[i]]
    }
    return acc
}