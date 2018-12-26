package hearts

class Player(val id: Int, private val algorithm: Algorithm) {

    inner class Status {
        val id = this@Player.id
        val handNum = this@Player.cards.size
        val chosenCard:List<Card> = this@Player.chosenCardList
    }

    var cards = listOf<Card>()

    private var chosenCardList = mutableListOf<Card>()

    fun playCard(board: List<Card>, hand: List<Card>, otherPlayersStatus: List<Player.Status>): Card {
        val chosenCard = algorithm.choiceCard(board, hand, otherPlayersStatus)
        chosenCardList.add(chosenCard)
        return chosenCard
    }
}