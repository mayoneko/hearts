package hearts

class Dealer(private val playerNum: Int) {

    // key: playerID
    // val: score
    private val playerScores = mutableMapOf<Int, Int>()

    var turnPlayerID: Int = 0

    private var isHeartBroken = false

    private var leadSuit: Int? = null

    //game manage functions

    fun dealCardsToPlayers(board: Board, players: List<Player>) {
        for ((cardID, playerID) in (0..51).shuffled().zip((0..51).map { it % playerNum })) {
            board.setCardToHand(cardID, playerID)
        }
        for (player in players) {
            player.cards = board.getHand(player.id).map { cardID -> Card(cardID) }
        }
    }

    fun setStartPlayer(players: List<Player>) {
        for (player in players) {
            if (player.cards.any { card -> card.suit == Card.CLUBS && card.number == 2 }) {
                this.turnPlayerID = player.id
                return
            }
        }
    }

    fun playExchange(board: Board, player: Player) {
        val handCards = board.getHand(player.id).map { cardID -> Card(cardID) }
        val exchangeCards = player.exchangeCards(handCards)
        exchangeCards.forEach {
            board.setCardOnBoard(it.id, player.id)
        }
    }

    fun handleExchange(board: Board, players: List<Player>) {
        board.getBoard().forEach {
            board.setCardToHand(it.first, (it.second + 1) % playerNum)
        }
    }

    fun playTurn(board: Board, player: Player, otherPlayersStatus: List<Player.Status>) {
        val boardCards = board.getBoard().map { Card(it.first) }
        val handCards = board.getHand(player.id).map { cardID -> Card(cardID) }
        val playedCard =
            player.playCard(boardCards, handCards, player.Status(), otherPlayersStatus, leadSuit, isHeartBroken)
        board.setCardOnBoard(playedCard.id, player.id)
        if (playedCard.suit == Card.HEARTS && !isHeartBroken) {
            isHeartBroken = true
        }
        player.cards = board.getHand(player.id).map { cardID -> Card(cardID) }
    }

    fun handleTurn(board: Board, players: List<Player>) {
        val boardCardsMap = board.getBoard().map { Card(it.first) to it.second }
        when {
            boardCardsMap.size == playerNum -> {
                val willGetCardPlayerID =
                    boardCardsMap.filter { it.first.suit == leadSuit }.maxBy { it.first.strength }!!.second
                boardCardsMap.forEach {
                    board.setCardToTrash(it.first.id, willGetCardPlayerID)
                }
                players[willGetCardPlayerID].receivedCardList = board.getTrash(willGetCardPlayerID).map { Card(it) }
                leadSuit = null
                turnPlayerID = willGetCardPlayerID
            }
            boardCardsMap.size == 1 -> {
                leadSuit = boardCardsMap[0].first.suit
                turnPlayerID = (turnPlayerID + 1) % playerNum
            }
            else -> turnPlayerID = (turnPlayerID + 1) % playerNum
        }
    }

    fun isGameEnded(players: List<Player>): Boolean {
        return players.all { player ->
            player.Status().handNum == 0
        }
    }

    fun getPlayerScores(board: Board): Map<Int, Int> {
        var shootTheMoon = false
        (0 until playerNum).forEach { playerID ->
            val score = board.getTrash(playerID).sumBy { cardID ->
                Card(cardID).point
            }
            if (score != 26) {
                playerScores[playerID] = score
            } else {
                playerScores[playerID] = -26
                shootTheMoon = true
            }
        }
        if (shootTheMoon) {
            playerScores.forEach {
                playerScores[it.key] = playerScores[it.key]!! + 26
            }
//            println("ShootTheMoon!")
        }
        return playerScores
    }
}
