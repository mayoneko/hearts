package hearts

abstract class Algorithm {
    abstract fun choiceCard(
        boardMap: List<Pair<Card, Int>>,
        hand: List<Card>,
        otherPlayersStatus: List<Player.Status>,
        leadSuit: Int?,
        isHeartBroken: Boolean
    ): Card

    object Utils {
        @JvmStatic
        fun play(card: Card): Card {
            return card
        }

        @JvmStatic
        fun getPlayableHand(board: List<Card>, hand: List<Card>, leadSuit: Int?, isHeartBroken: Boolean): List<Card> {
            val playableCards = mutableListOf<Card>()
            //TODO
            return playableCards
        }

    }
}