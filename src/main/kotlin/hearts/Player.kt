package hearts

class Player(val id: Int, private val algorithm: Algorithm) {

    inner class Status {
        val id = this@Player.id
        val handNum = this@Player.cards.size
        val playedCards: List<Card> = this@Player.playedCardList
        val receivedCards: List<Card> = this@Player.receivedCardList
    }

    var cards = listOf<Card>()

    private var playedCardList = mutableListOf<Card>()

    var receivedCardList = listOf<Card>()

    fun playCard(
        board: List<Card>,
        hand: List<Card>,
        myStatus: Status,
        otherPlayersStatus: List<Player.Status>,
        leadSuit: Int?,
        isHeartBroken: Boolean
    ): Card {
        val chosenCard = algorithm.choiceCard(board, hand, myStatus,otherPlayersStatus, leadSuit, isHeartBroken)
        //TODO: 例外処理
        playedCardList.add(chosenCard)
        return chosenCard
    }

    fun exchangeCards(hand: List<Card>): List<Card> {
        //TODO: 例外処理
        return algorithm.choiceExchange(hand)
    }
}