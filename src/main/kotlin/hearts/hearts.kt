package hearts

fun main(args: Array<String>) {
    val result = Match(TestAlgorithm(), RandomAlgorithm(), RandomAlgorithm(), RandomAlgorithm()).run()
    println("平均失点\n" + result.mapIndexed { index, value -> "$index:$value" }.toString())
}