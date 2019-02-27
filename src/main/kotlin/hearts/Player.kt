package hearts

class Player(val id: Int, private val algorithm: Algorithm) {

    inner class Status {
        val id = this@Player.id
        val handNum = this@Player.cards.size
        val chosenCard: List<Card> = this@Player.chosenCardList
    }

    var cards = listOf<Card>()

    private var chosenCardList = mutableListOf<Card>()

    fun playCard(
        boardMap: List<Pair<Card, Int>>,
        hand: List<Card>,
        otherPlayersStatus: List<Player.Status>,
        leadSuit: Int,
        isHeartBroken: Boolean
    ): Card {
        val chosenCard = algorithm.choiceCard(boardMap, hand, otherPlayersStatus, leadSuit, isHeartBroken)
        chosenCardList.add(chosenCard)
        return chosenCard
    }
}