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
        val chosenCard = algorithm.choiceCard(board, hand, myStatus, otherPlayersStatus, leadSuit, isHeartBroken)
        val playable = Algorithm.Utils.getPlayableHand(hand, leadSuit, isHeartBroken)
        if (chosenCard in playable) {
            playedCardList.add(chosenCard)
            return chosenCard
        }
        println("Illegal Choice in Play")
        val randomCard = playable.random()
        playedCardList.add(randomCard)
        return randomCard
    }

    fun exchangeCards(hand: List<Card>): List<Card> {
        val chosenCards = algorithm.choiceExchange(hand)
        if (chosenCards.size != 3) {
            println("Illegal Size Of Exchange Cards")
            return (1..3).map { hand[it] }
        }
        chosenCards.forEach {
            if (!hand.contains(it)) {
                println("Illegal Choice in Exchange")
                return (1..3).map { hand[it] }
            }
        }
        return chosenCards
    }
}