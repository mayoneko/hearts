package hearts

import hearts.Algorithm.Utils.getPlayableHand

class RandomAlgorithm : Algorithm() {
    override fun choiceCard(
        boardMap: List<Pair<Card, Int>>,
        hand: List<Card>,
        otherPlayersStatus: List<Player.Status>,
        leadSuit: Int?,
        isHeartBroken: Boolean
    ): Card {
        return getPlayableHand(hand,leadSuit,isHeartBroken).random()
    }
}