package hearts

import hearts.Algorithm.Utils.getPlayableHand

class RandomAlgorithm : Algorithm() {
    override fun choiceExchange(hand: List<Card>): List<Card> {
        return (1..3).map { hand[it] }
    }

    override fun choiceCard(
        board: List<Card>,
        hand: List<Card>,
        myStatus: Player.Status,
        otherPlayersStatus: List<Player.Status>,
        leadSuit: Int?,
        isHeartBroken: Boolean
    ): Card {
        return getPlayableHand(hand, leadSuit, isHeartBroken).random()
    }
}

class TestAlgorithm : Algorithm() {
    override fun choiceExchange(hand: List<Card>): List<Card> {
        val suitList = mutableListOf(0, 0, 0, 0)
        hand.forEach {
            suitList[it.suit]++
        }
        val sortedSuit = suitList.withIndex().sortedBy { it.value }.map { it.index }
//        println("suitList"+suitList)
//        println("sortedSuit"+sortedSuit)
        val returnList = hand.sortedBy { -it.strength + 13 * sortedSuit.indexOf(it.suit) }.take(3)
//        println(returnList.map { it.suit to it.strength })
        return returnList
    }

    override fun choiceCard(
        board: List<Card>,
        hand: List<Card>,
        myStatus: Player.Status,
        otherPlayersStatus: List<Player.Status>,
        leadSuit: Int?,
        isHeartBroken: Boolean
    ): Card {
        val playable = getPlayableHand(hand, leadSuit, isHeartBroken)
        if ((hand.size < 6) && (otherPlayersStatus.filter { !it.receivedCards.none { it.point > 0 } }.size == 1) && (myStatus.receivedCards.none { it.point > 0 })) {
//            println("blockMoon")
            if (board.any { it.point > 0 }) {
                return playable.filter {
                    it.suit == leadSuit
                }.maxBy {
                    it.strength
                } ?: playable.maxBy {
                    it.point * 5 + it.strength
                } ?: playable.random()
            }
        }
        if (leadSuit == null) {
//            println("leader")
            val suitList = mutableListOf(0, 0, 0, 0)
            playable.forEach {
                suitList[it.suit]++
            }
            return playable.filter {
                it.suit == suitList.withIndex().filter { it.value > 0 }.minBy { it.value }?.index
            }.minBy {
                it.strength
            } ?: playable.random()
        } else {
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