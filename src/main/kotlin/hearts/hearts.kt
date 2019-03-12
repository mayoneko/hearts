package hearts

fun main(args: Array<String>) {
    val result = Match(TestAlgorithm(), RandomAlgorithm(), RandomAlgorithm(), RandomAlgorithm()).run()
    println(result)
}