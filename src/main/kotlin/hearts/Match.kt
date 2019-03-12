package hearts

class Match(vararg _algorithms: Algorithm) {
    private val playerNum = 4

    private val algorithms = if (_algorithms.size >= playerNum) {
        _algorithms.toList()
    } else {
        throw IllegalArgumentException("Algorithms' size must be over 4")
    }


    private fun createPermutation(): List<List<Int>> {
        return createPermutationRecursion(listOf(), 0)
    }

    private fun createPermutationRecursion(listedInts: List<Int>, count: Int): List<List<Int>> {
        if (count == 4) return listOf(listedInts)
        val returnInts: MutableList<List<Int>> = mutableListOf()
        (0 until algorithms.size).forEach {
            if (!listedInts.contains(it)) {
                createPermutationRecursion(listedInts + it, count + 1).forEach { listedInt ->
                    returnInts.add(listedInt)
                }
            }
        }
        return returnInts
    }

    fun run(): List<Int> {
        var count = 0
        val result = MutableList(algorithms.size) { 0 }
        createPermutation().forEach {
            count += 100
            println(it)
            for (i in 1..100) {
                Game(it.map { algorithms[it] }).run().forEach{
                    result[it.key] += it.value
                }
            }
        }
        return result.map{
            it/count
        }
    }

}