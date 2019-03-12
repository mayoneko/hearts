package hearts

abstract class Algorithm {
    abstract fun choiceCard(
        boardMap: List<Pair<Card, Int>>,
        hand: List<Card>,
        myStatus:Player.Status,
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