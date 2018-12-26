package hearts

class Board {
    private var ownerMap = mutableMapOf<Int, Pair<Int, Int>>()
    //key: cardID
    //value: ownerID , state

    companion object {
        const val HAND = 0
        const val BOARD = 1
        const val TRASH = 2
    }

    init {
        for (cardID in 0..51) {
            ownerMap[cardID] = Pair(-1, -1)
        }
    }

    private fun getCards(ownerID: Int, state: Int): List<Int> {
        return ownerMap.filter { item ->
            item.value.first == ownerID && item.value.second == state
        }.map { item ->
            item.key
        }
    }

    fun getHand(playerID: Int): List<Int> {
        return getCards(playerID, HAND)
    }

    fun getTrash(playerID: Int): List<Int> {
        return getCards(playerID, TRASH)
    }

    fun getBoard(): List<Int> {
        return ownerMap.filter { item ->
            item.value.second == BOARD
        }.map { item ->
            item.key
        }
    }

    private fun setCardOwner(cardID: Int, ownerID: Int, state: Int) {
        ownerMap[cardID] = Pair(ownerID, state)
    }

    fun setCardToHand(cardID: Int, playerID: Int) {
        setCardOwner(cardID, playerID, HAND)
    }

    fun setCardToTrash(cardID: Int, playerID: Int) {
        setCardOwner(cardID, playerID, TRASH)
    }

    fun setCardOnBoard(cardID: Int, playerID: Int) {
        setCardOwner(cardID, playerID, BOARD)
    }

}