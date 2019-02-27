package hearts

class Game(algorithms: List<Algorithm>) {
    private val playerNum = 4
    private val board = Board()
    private val dealer = Dealer()
    private val players = createPlayers(algorithms)

    fun run(): Map<Int, Int> {
        val result = createPlayerScores()

        do{
            dealer.dealCardsToPlayers(board, players)
            dealer.setStartPlayer(players)
            dealer.handleExchange(board, players)
            do {
                val player = players[dealer.turnPlayerID]
                val otherPlayersStatus = players.filter {
                    it.id != dealer.turnPlayerID
                }.map { otherPlayer -> otherPlayer.Status() }

//            println("Player${player.id}")
//            println("Cards")
//            println(player.cards.sortedBy { card -> card.id }.toString())

                dealer.playTurn(board, player, otherPlayersStatus)

                if (!dealer.isGameEnded(players)) {
                    dealer.handleTurn(board, players)
                } else {
                    dealer.getPlayerScores(board).forEach {
                        val temp = result[it.key] ?: 0
                        result[it.key] = temp + it.value
                    }
                }
            } while (!dealer.isGameEnded(players))

        }while(result.any { it.value>=100 })

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