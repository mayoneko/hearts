package hearts

abstract class Algorithm {
    abstract fun choiceCard(
        board: List<Card>,
        hand: List<Card>,
        otherPlayersStatus: List<Player.Status>,
        isHeartBroken: Boolean
    ): Card

    object Utils {
        @JvmStatic
        fun play(card: Card): Card {
            return card
        }

        @JvmStatic
        fun getPlayableHand(board: List<Card>, hand: List<Card>, isHeartBroken: Boolean): List<Card> {
            val playableCards = mutableListOf<Card>()
            //TODO
            return playableCards
        }

    }
}