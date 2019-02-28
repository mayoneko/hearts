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
        return getPlayableHand(hand, leadSuit, isHeartBroken).random()
    }
}

class TestAlgorithm : Algorithm() {
    override fun choiceCard(
        boardMap: List<Pair<Card, Int>>,
        hand: List<Card>,
        otherPlayersStatus: List<Player.Status>,
        leadSuit: Int?,
        isHeartBroken: Boolean
    ): Card {
        val playable = getPlayableHand(hand, leadSuit, isHeartBroken)
        if (leadSuit == null) {
            val suitList = mutableListOf(0, 0, 0, 0)
            playable.forEach {
                suitList[it.suit]++
            }
            return playable.filter {
                it.suit == suitList.max()
            }.minBy {
                it.strength
            } ?: playable.random()
        }else{
            return playable.filter {
                it.suit == leadSuit
            }.minBy {
                it.strength
            } ?: playable.maxBy {
                it.point * 5 + it.strength
            } ?: playable.random()
        }
    }
}