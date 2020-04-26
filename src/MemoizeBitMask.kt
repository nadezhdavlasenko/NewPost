import kotlin.math.min

// O(2^n * n^2)
fun travelMemoizeMask(graph: Array<DoubleArray>): Double {
    val minDists = Array(1 shl graph.size) { DoubleArray(graph.size) { Double.POSITIVE_INFINITY } }
    minDists[0][0] = 0.0
    for (mask in minDists.indices) {
        for (i in minDists[0].indices) {
            if (minDists[mask][i] == Double.POSITIVE_INFINITY) continue
            for (j in minDists[0].indices) {
                if ((mask and (1 shl j)) == 0) {
                    minDists[mask or (1 shl j)][j] =
                        min(minDists[mask or (1 shl j)][j], minDists[mask][i] + graph[i][j])
                }
            }
        }
    }
    return minDists[minDists.size - 1][0]
}