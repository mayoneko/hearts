package hearts

import kotlin.random.Random

class Dealer {

    val playerNum = 4

    // key: rankID
    // val: playerID
    private val playerRanking = mutableMapOf<Int, Int>()

    var turnPlayerID: Int = 0

    private var isHeartBroken = false

    fun isHeartBroken() = isHeartBroken

    //game manage functions

    fun dealCardsToPlayers(board: Board, players: List<Player>) {
        val randomInt = Random.nextInt(playerNum)
        for ((cardID, playerID) in (0..51).shuffled().zip((0..51).map { (it + randomInt) % playerNum })) {
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

    fun handleExchange(board: Board, players: List<Player>) {
        //TODO

    }

    fun playTurn(board: Board, player: Player, otherPlayersStatus: List<Player.Status>) {
        val boardCards = board.getBoard().map { cardID -> Card(cardID) }
        val handCards = board.getHand(player.id).map { cardID -> Card(cardID) }
        val playedCard = player.playCard(boardCards, handCards, otherPlayersStatus, isHeartBroken)
        board.setCardOnBoard(playedCard.id)
        player.cards = board.getHand(player.id).map { cardID -> Card(cardID) }
    }

    fun handleTurn(players: List<Player>) {
        turnPlayerID = (turnPlayerID + 1) % playerNum
    }

    fun isGameEnded(players: List<Player>): Boolean {
        return players.all { player ->
            player.Status().handNum == 0
        }
    }

    fun getPlayerRanking(): Map<Int, Int> {
//        println("GameSet\n")
//        for (rankID in 1..playerNum) {
//            println("${rankID}位 : Player${playerRanking[rankID]}")
//        }
        return playerRanking
    }
}
