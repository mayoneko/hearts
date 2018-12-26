package hearts

abstract class Algorithm {
    abstract fun choiceCard(
        board: List<Card>,
        hand: List<Card>,
        otherPlayersStatus: List<Player.Status>
    ): Card

    object Utils {
        @JvmStatic
        fun play(card: Card): Card {
            return card
        }

        @JvmStatic
        fun getPlayableHand(board: List<Card>, hand: List<Card>): List<Card> {
            val playableCards = mutableListOf<Card>()
            //TODO
            return playableCards
        }

    }
}