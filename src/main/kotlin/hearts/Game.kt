package hearts

class Game(private val algorithms: List<Algorithm>) {
    private var playerNum = 4
    private var board = Board()
    private var dealer = Dealer(playerNum)
    private var players = if (algorithms.size == playerNum) {
        createPlayers(algorithms)
    } else {
        throw IllegalArgumentException("Algorithms' size must be 4")
    }

    fun run(): Map<Int, Int> {
        val result = createPlayerScores()

        do {
            board = Board()
            dealer = Dealer(playerNum)
            players = if (algorithms.size == playerNum) {
                createPlayers(algorithms)
            } else {
                throw IllegalArgumentException("Algorithms' size must be 4")
            }
            dealer.dealCardsToPlayers(board, players)
            dealer.setStartPlayer(players)
            players.forEach {
                dealer.playExchange(board, it)
            }
            dealer.handleExchange(board, players)
            do {
                val player = players[dealer.turnPlayerID]
                val otherPlayersStatus = players.filter {
                    it.id != dealer.turnPlayerID
                }.map { otherPlayer -> otherPlayer.Status() }

//                println("Player${player.id}")
//                println("Cards")
//                println(player.cards.sortedBy { card -> card.id }.toString())

                dealer.playTurn(board, player, otherPlayersStatus)
                dealer.handleTurn(board, players)

            } while (!dealer.isGameEnded(players))

            dealer.getPlayerScores(board).forEach {
                val temp = result[it.key] ?: 0
                result[it.key] = temp + it.value
            }

        } while (!result.any { it.value >= 100 })

        return result
    }

    private fun createPlayers(algorithms: List<Algorithm>): List<Player> {
        return (0 until playerNum).map { Player(it, algorithms[it]) }
    }

    private fun createPlayerScores(): MutableMap<Int, Int> {
        val playerScores = mutableMapOf<Int, Int>()
        (0 until playerNum).forEach { playerScores += (it to 0) }
        return playerScores
    }

}