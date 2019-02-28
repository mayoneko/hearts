package hearts

fun main(args: Array<String>) {
    val result = mutableListOf(0, 0, 0, 0)
    for (i in 0 until 100) {
        Game(listOf(TestAlgorithm(), RandomAlgorithm(), RandomAlgorithm(), RandomAlgorithm())).run().forEach{
            result[it.key] += it.value
        }
    }
    println(result)
}