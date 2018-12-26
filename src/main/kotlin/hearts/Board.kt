package hearts

class Board {
    private var ownerMap = mutableMapOf<Int, Int>()
    //key: cardID
    //value: ownerID

    companion object {
        const val BOARD = -1
    }

    private fun toTrash(playerID: Int): Int = playerID + 10

    init {
        for (cardID in 0..51) {
            ownerMap[cardID] = BOARD
        }
    }

    private fun getCards(ownerID: Int): List<Int> {
        return ownerMap.filter { item ->
            item.value == ownerID
        }.map { item ->
            item.key
        }
    }

    fun getHand(playerID: Int): List<Int> {
        return getCards(playerID)
    }

    fun getTrash(playerID: Int): List<Int> {
        return getCards(toTrash(playerID))
    }

    fun getBoard(): List<Int> {
        return getCards(BOARD)
    }

    private fun setCardOwner(cardID: Int, ownerID: Int) {
        ownerMap[cardID] = ownerID
    }

    fun setCardToHand(cardID: Int, playerID: Int) {
        setCardOwner(cardID, playerID)
    }

    fun setCardToTrash(cardID: Int, playerID: Int) {
        setCardOwner(cardID, toTrash(playerID))
    }

    fun setCardOnBoard(cardID: Int) {
        setCardOwner(cardID, BOARD)
    }

}