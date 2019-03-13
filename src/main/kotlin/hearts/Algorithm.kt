package hearts

abstract class Algorithm {
    abstract fun choiceCard(
        board: List<Card>,
        hand: List<Card>,
        myStatus:Player.Status,
        otherPlayersStatus: List<Player.Status>,
        leadSuit: Int?,
        isHeartBroken: Boolean
    ): Card

    abstract fun choiceExchange(hand: List<Card>): List<Card>

    object Utils {
        @JvmStatic
        fun getPlayableHand(hand: List<Card>, leadSuit: Int?, isHeartBroken: Boolean): List<Card> {
            val playableCards = mutableListOf<Card>()
            if (leadSuit == null) {
                if (isHeartBroken) {
                    playableCards.addAll(hand)
                } else {
                    playableCards.addAll(hand.filter { card ->
                        card.suit != Card.HEARTS
                    })
                    if(playableCards.isEmpty()){
                        playableCards.addAll(hand)
                    }
                }
            }else{
                playableCards.addAll(hand.filter { card ->
                    card.suit == leadSuit
                })
                if(playableCards.isEmpty()){
                    playableCards.addAll(hand)
                }
            }
            return playableCards
        }
    }
}